package fr.nostoNC.tasks.effects;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlowingLamp {

    private static final List<Location> all = new ArrayList<>();

    private static final HashMap<Integer, ArrayList<Location>> lignes = new HashMap<>();
    private static final HashMap<Integer, ArrayList<Location>> colone = new HashMap<>();

    public static void setup() {

        int lMax = 13;
        int LMax = 15;

        for (int l = 0; l < lMax*2; l += 2) {
            for (int L = 0; L < LMax*2; L += 2) {
                Location location = new Location(Utils.getDefaultWorld(), -16 + L, 113, 150 + l);
                all.add(location);
                if (!lignes.containsKey(l/2)) lignes.put(l/2, new ArrayList<>());
                ArrayList<Location> list = lignes.get(l/2);
                list.add(location);
                lignes.put(l/2, list);
            }
        }

        for (int i = 0; i < LMax; i++) {
            ArrayList<Location> list = new ArrayList<>();
            for (int i1 = 0; i1 < lignes.size(); i1++) {
                list.add(lignes.get(i1).get(i));
            }
            colone.put(i, list);
        }

        allChangeDimmer(true);
        allChangeDimmer(false);

        new BukkitRunnable() {
            final int defaultInt = 0;
            int stade = 0;
            @Override
            public void run() {
                switch (stade) {
                    case 0, 2 -> allWave(lignes, false, 3);
                    case 1, 3 -> allWave(lignes, true, 3);
                    case 4, 6 -> allWave(colone, false, 2);
                    case 5, 7 -> allWave(colone, true, 2);
                }
                stade ++;
                if (stade == 8) stade = defaultInt;
            }
        }.runTaskTimer(Main.getInstance(), 0, 5*8);

    }

    private static void allChangeDimmer(boolean bool) {
        for (Location location : all) {
            Block b = Utils.getDefaultWorld().getBlockAt(location);
            if (bool) b.setType(Material.GLOWSTONE);
            else b.setType(Material.STONE);
        }
    }

    private static void allWave(HashMap<Integer, ArrayList<Location>> hashMap, boolean direction, int timing) {
        int i;
        if (direction) i = -1;
        else i = hashMap.size();
        new BukkitRunnable() {
            int t = i;
            @Override
            public void run() {
                if (direction) {
                    if (t > hashMap.size()) return;
                    t++;
                } else {
                    if (t < 0) return;
                    t--;
                }
                if (hashMap.containsKey(t)) for (Location location : hashMap.get(t)) {
                    Block block = Utils.getDefaultWorld().getBlockAt(location);
                    block.setType(Material.GLOWSTONE);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.STONE);
                        }
                    }.runTaskLater(Main.getInstance(), timing* 3L);
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, timing);
    }

}
