package fr.nostoNC.tasks.effects;

import java.util.*;

import fr.nostoNC.Utils;
import fr.nostoNC.tasks.Laser;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import org.javatuples.Pair;

import org.bukkit.util.Vector;

public class WallLaser {

    private static final int duration = -1;
    private static final int distance = -1;
    private static final int longueur = 25;

    public static String move = "edge";
    public static Boolean alternance = false;
    public static int timing = 10;

    public static final LinkedHashMap<Laser, Pair<Location, Boolean>> all = new LinkedHashMap<>();

    private static double defaultT = 0;

    public static void setup() {

        addLaser(-8.5, 111.4, 147);
        addLaser(-8.5, 107.4, 147);
        addLaser(-5.5, 105.4, 146);
        addLaser(-5.5, 109.4, 146);
        addLaser(-5.5, 113.4, 146);
        addLaser(-2.5, 111.4, 146);
        addLaser(-2.5, 107.4, 146);
        addLaser(-1.5, 107.4, 146);
        addLaser(-1.5, 111.4, 146);
        addLaser(1.5, 113.4, 146);
        addLaser(1.5, 109.4, 146);
        addLaser(1.5, 105.4, 146);
        addLaser(4.5, 107.4, 147);
        addLaser(4.5, 111.4, 147);

        for (Laser laser : all.keySet()) {
            defaultT += (Math.PI*2)/all.size();
            new BukkitRunnable() {
                double t = defaultT;
                final double size = 45;
                @Override
                public void run() {

                    double scale = 2 / (3 - Math.cos(2*t));
                    double tilt = (scale * Math.cos(t))*size;
                    double pan = (scale * Math.sin(2*t) / 2)*(size*3);

                    Vector pointB = Utils.getBPoint(laser.getStart().toVector(),pan,tilt,longueur);
                    Pair<Location, Boolean> info = new Pair<>(new Location(Utils.getDefaultWorld(), pointB.getX(), pointB.getY(), pointB.getZ()),
                            all.get(laser).getValue1());

                    double tilt2 = Math.sin(t)*size;
                    double pan2 = 0;

                    Vector pointB2 = Utils.getBPoint(laser.getStart().toVector(),pan2,tilt2,longueur);
                    Pair<Location, Boolean> info2 = new Pair<>(new Location(Utils.getDefaultWorld(), pointB2.getX(), pointB2.getY(), pointB2.getZ()),
                            all.get(laser).getValue1());

                    Location start = laser.getStart();
                    Pair<Location, Boolean> info3 = new Pair<>(new Location(Utils.getDefaultWorld(), start.getX(), start.getY(), start.getZ() + longueur),
                            all.get(laser).getValue1());

                    if (move.equalsIgnoreCase("edge")) all.put(laser, info);
                    else if (move.equalsIgnoreCase("wave")) all.put(laser, info2);
                    else if (move.equalsIgnoreCase("front")) all.put(laser, info3);

                    t += .05;

                    try {
                        updateLaser(laser);
                    } catch (ReflectiveOperationException e) { e.printStackTrace(); }

                }
            }.runTaskTimer(Main.getInstance(), 0, 1);
        }
        new BukkitRunnable() {
            int t = 0;
            @Override
            public void run() {
                if (t >= timing) {
                    t = 0;
                    if (alternance) {

                        all.replaceAll((l, v) -> all.get(l).setAt1(new Random().nextBoolean()));

                    }
                }
                t++;
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }

    private static void addLaser(double x, double y, double z) {
        try {
            Location loc = new Location(Utils.getDefaultWorld(), x, y, z);
            Pair<Location, Boolean> info = new Pair<>(loc, true);
            Laser laser = new Laser.GuardianLaser(loc, loc, duration, distance);
            all.put(laser, info);
            laser.start(Main.getInstance());
        } catch (ReflectiveOperationException e) { e.printStackTrace(); }
    }

    private static void updateLaser(Laser laser) throws ReflectiveOperationException {

        Pair<Location, Boolean> info = all.get(laser);

        if (info.getValue1())
            laser.moveEnd(new Location(Utils.getDefaultWorld(), laser.getStart().getX(), laser.getStart().getY(), laser.getStart().getZ() + 0.99));
        else
            laser.moveEnd(info.getValue0());

    }

    public static void showOrHideAll(Boolean hide) {
        alternance = false;
        for (Laser laser : all.keySet()) {
            Pair<Location, Boolean> info = all.get(laser).setAt1(hide);
            all.put(laser, info);
        }
    }

    public static void alternance() { alternance = true; }

    public static void moveFront() { move = "front"; }

    public static void moveWave() { move = "wave"; }

    public static  void moveEdge() { move = "edge"; }

}
