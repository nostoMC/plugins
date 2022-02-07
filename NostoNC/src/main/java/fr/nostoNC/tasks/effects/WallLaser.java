package fr.nostoNC.tasks.effects;

import java.util.*;

import fr.nostoNC.Utils;
import fr.nostoNC.tasks.Laser;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import org.bukkit.util.Vector;

public class WallLaser {

    private static final int duration = -1;
    private static final int distance = -1;
    private static final int longueur = 25;

    public static String move = "edge";

    public static final List<Laser> all = new ArrayList<>();

    private static double defaultT = 0;

    public static void setup() {

        addLaser(-8.5, 111, 147);
        addLaser(-8.5, 107, 147);
        addLaser(-5.5, 105, 146);
        addLaser(-5.5, 109, 146);
        addLaser(-5.5, 113, 146);
        addLaser(-2.5, 111, 146);
        addLaser(-2.5, 107, 146);
        addLaser(-1.5, 107, 146);
        addLaser(-1.5, 111, 146);
        addLaser(1.5, 113, 146);
        addLaser(1.5, 109, 146);
        addLaser(1.5, 105, 146);
        addLaser(4.5, 107, 147);
        addLaser(4.5, 111, 147);

        Collections.shuffle(all);

        for (Laser laser : all) {
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
                    try {
                        if (move.equalsIgnoreCase("edge")) laser.moveEnd(new Location(Utils.defaultWorld, pointB.getX(), pointB.getY(), pointB.getZ()));
                    } catch (ReflectiveOperationException ignored) {
                    }

                    t += .05;

                }
            }.runTaskTimer(Main.instance, 0, 1);
            new BukkitRunnable() {
                double t = defaultT;
                final double size = 45;
                @Override
                public void run() {

                    double tilt = Math.sin(t)*size;
                    double pan = 0;

                    Vector pointB = Utils.getBPoint(laser.getStart().toVector(),pan,tilt,longueur);
                    try {
                        if (move.equalsIgnoreCase("wave")) laser.moveEnd(new Location(Utils.defaultWorld, pointB.getX(), pointB.getY(), pointB.getZ()));
                    } catch (ReflectiveOperationException ignored) {
                    }

                    t += .05;

                }
            }.runTaskTimer(Main.instance, 0, 1);

        }
    }

    public static void showAll() {
        for(Laser laser : all) {
            try {
                laser.start(Main.instance);
            } catch (IllegalArgumentException ignored) {
            }
            updateMovement(laser);

        }
    }

    public static void hideAll() {
        for(Laser laser : all) {
            try {
                laser.stop();
            } catch (IllegalArgumentException ignored) {
            }
        }
    }

    public static void moveFront() {
        move = "front";
        for (Laser laser : all) {
            updateMovement(laser);
        }
    }

    public static void moveWave() {
        move = "wave";
        for (Laser laser : all) {
            updateMovement(laser);
        }
    }

    public static  void moveEdge() {
        move = "edge";
        for (Laser laser : all) {
            updateMovement(laser);
        }
    }

    private static void addLaser(double x, double y, double z) {
        try {
            all.add(new Laser.GuardianLaser(new Location(Utils.defaultWorld, x, y, z), new Location(Utils.defaultWorld, x, y, z+longueur), duration, distance));
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    private static void updateMovement(Laser laser) {
        try {
            if ("front".equals(move)) {
                Location loc = laser.getStart();
                double x = loc.getX();
                double y = loc.getY();
                double z = loc.getZ() + longueur;
                laser.moveEnd(new Location(Utils.defaultWorld, x, y, z));
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

}
