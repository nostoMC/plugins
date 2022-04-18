package fr.nostoNC.tasks.effects;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.skytasul.guardianbeam.Laser;
import org.javatuples.Pair;

public class LaserUpDown {

    private static final int duration = -1;
    private static final int distance = -1;
    private static final int longueur = 30;

    private static final ArrayList<Laser> lasers = new ArrayList<>();
    private static boolean started = false;

    private static boolean inited = false;

    public static void init(Main main) {

        if (inited) return;
        inited = true;

        ArrayList<Pair<Location, Double>> lasersInfo = new ArrayList<>();

        for (int i = 1; i < 12; i++) {
            lasersInfo.add(new Pair<>(new Location(Utils.getDefaultWorld(), -2.5+(.1*i), 108.25, 145.9), 55.0-(9*i)));
            try {
                lasers.add(new Laser.GuardianLaser(lasersInfo.get(i-1).getValue0(), new Location(Utils.getDefaultWorld(), -2.0, 108.25, 150), duration, distance));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }

        new BukkitRunnable() {
            double t = 0;
            final double size = 33;
            @Override
            public void run() {

                for (int i = 0; i < lasers.size(); i++) {

                    Laser laser = lasers.get(i);
                    Pair<Location, Double> laserInfo = lasersInfo.get(i);

                    double tilt = Math.sin(t)*size + 11;
                    double pan = laserInfo.getValue1();

                    Vector pointB = Utils.getBPoint(laser.getStart().toVector(),pan,tilt,longueur);

                    try {
                        if (laser.isStarted()) laser.moveEnd(new Location(Utils.getDefaultWorld(), pointB.getX(), pointB.getY(), pointB.getZ()));
                    } catch (ReflectiveOperationException ignored) {
                    }

                    t += Math.PI/256;
                }

            }

        }.runTaskTimer(main, 0, 1);

    }

    public static void startLaser(Main main) {
        for (Laser laser : lasers) {
            laser.start(main);
        }
        started = true;
    }

    public static void stopLaser() {
        for (Laser laser : lasers) {
            laser.stop();
        }
        started = false;
    }

    public static boolean isStarted() {
        return started;
    }
}
