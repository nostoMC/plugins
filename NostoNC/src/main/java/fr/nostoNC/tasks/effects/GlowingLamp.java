package fr.nostoNC.tasks.effects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;

public class GlowingLamp {

    enum Axis {
        X, Z;

        public int getMaxIndex() {
            return this == X ? Xwide : Zwide;
        }
    }
    enum Direction { FRONT, BACK }

    private static final int Zwide = 13, Xwide = 15;

    public static void setup() {

        allChangeDimmer(true);
        allChangeDimmer(false);

        new BukkitRunnable() {
            final int defaultInt = 0;
            int stade = 0;
            @Override
            public void run() {
                switch (stade) {
                    case 0, 2 -> allWave(Axis.Z, Direction.BACK, 3);
                    case 1, 3 -> allWave(Axis.Z, Direction.FRONT, 3);
                    case 4, 6 -> allWave(Axis.X, Direction.BACK, 2);
                    case 5, 7 -> allWave(Axis.X, Direction.FRONT, 2);
                }
                stade ++;
                if (stade == 8) stade = defaultInt;
            }
        }.runTaskTimer(Main.getInstance(), 5, 5*8);

    }

    private static void setLamp(int x, int z, boolean on) {
        if (x >= Xwide || z >= Zwide || x < 0 || z < 0) return;

        Location loc = new Location(Utils.getDefaultWorld(), -16, 113, 150);
        loc.add(x*2, 0, z*2);

        if (on) loc.getBlock().setType(Material.GLOWSTONE);
        else loc.getBlock().setType(Material.STONE);
    }

    private static void setLine(Axis axis, int index, boolean on) {
        int maxValue = axis.getMaxIndex();

        for (int i = 0; i < maxValue; i++) {
            switch (axis) {
                case X -> setLamp(i, index, on);
                case Z -> setLamp(index, i, on);
            }
        }
    }

    private static void allChangeDimmer(boolean bool) {
        for (int x = 0; x < Xwide; x++) {
            for (int z = 0; z < Zwide; z++) {
                setLamp(x, z, bool);
            }
        }
    }

    private static void allWave(Axis axis, Direction dir, int timing) {
        int maxProgress = axis.getMaxIndex();
        Axis lineAxis = switch (axis) {
            case X -> Axis.Z;
            case Z -> Axis.X;
        };

        new BukkitRunnable() {
            int progress = switch (dir) {
                case FRONT -> 0;
                case BACK -> maxProgress - 1;
            };

            @Override
            public void run() {
                if (progress < 0 || progress >= maxProgress) {
                    this.cancel();
                    return;
                }

                setLine(lineAxis, progress, true);

                int progressClone = progress;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        setLine(lineAxis, progressClone, false);
                    }
                }.runTaskLater(Main.getInstance(), timing * 3L);

                switch (dir) {
                    case FRONT -> progress++;
                    case BACK -> progress--;
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, timing);
    }

}
