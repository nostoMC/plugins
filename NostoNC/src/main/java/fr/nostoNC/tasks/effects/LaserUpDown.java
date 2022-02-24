package fr.nostoNC.tasks.effects;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.nostoNC.tasks.Laser;

public class LaserUpDown {

    private static final int duration = -1;
    private static final int distance = -1;
    private static final int longueur = 30;

    private static boolean inited = false;

    public static void init(Main main) {

        if (inited) return;
        inited = true;

        ArrayList<Laser> lasers = new ArrayList<>();

        ArrayList<Map<Location, Double>> lasersInfo = new ArrayList<>();

        for (int i = 1; i < 12; i++) {
            lasersInfo.add(Map.of(new Location(Utils.getDefaultWorld(), -2.5-(1/i), 108.3, 145.5), 45.0/i));
            lasers.add(new Laser.GuardianLaser(lasersInfo.get(i-1), end, duration, distance));
        }

        new BukkitRunnable() {
            double t = 0;
            final double size = 45;
            @Override
            public void run() {

                for (Laser laser : lasers) {

                    double scale = 2 / (3 - Math.cos(2*t));
                    double tilt = (scale * Math.cos(t))*size;
                    double pan = 0;

                    Vector pointB = Utils.getBPoint(laser.getStart().toVector(),pan,tilt,longueur);

                    try {
                        if (Utils.getActiveEffects("laserUpDown")) laser.moveEnd(new Location(Utils.getDefaultWorld(), pointB.getX(), pointB.getY(), pointB.getZ()));
                    } catch (ReflectiveOperationException ignored) {
                    }

                    t += .05;
                }

            }

        }.runTaskTimer(main, 0, 1);

    }

}
