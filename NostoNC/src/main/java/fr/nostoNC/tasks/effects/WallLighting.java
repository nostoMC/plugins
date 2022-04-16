package fr.nostoNC.tasks.effects;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;

public class WallLighting {

    public static int timing = 5;
    private static String action = "null";

    private static final ArrayList<Block> all =  new ArrayList<>();

    public static void init(Main main) {

        addBlock(-9, 112, 146);
        addBlock(-9, 110, 146);
        addBlock(-9, 108, 146);
        addBlock(-9, 106, 146);
        addBlock(-9, 104, 146);

        addBlock(-6, 112, 145);
        addBlock(-6, 110, 145);
        addBlock(-6, 108, 145);
        addBlock(-6, 106, 145);
        addBlock(-6, 104, 145);

        addBlock(-3, 112, 145);
        addBlock(-2, 112, 145);
        addBlock(-3, 110, 145);
        addBlock(-2, 110, 145);

        addBlock(1, 112, 145);
        addBlock(1, 110, 145);
        addBlock(1, 108, 145);
        addBlock(1, 106, 145);
        addBlock(1, 104, 145);

        addBlock(4, 112, 146);
        addBlock(4, 110, 146);
        addBlock(4, 108, 146);
        addBlock(4, 106, 146);
        addBlock(4, 104, 146);

        showOrHideAll(true);

        new BukkitRunnable() {
            int t = 0;
            boolean hide = false;
            @Override
            public void run() {
                if (t >= timing) {
                    t = 0;
                    if (action.equals("strobe")) showOrHideAll(hide);
                    else if (action.equals("alternation")) showOrHideHalf(hide);
                    hide = !hide;
                }
                t++;
            }
        }.runTaskTimer(main, 0, 1);

    }

    private static void addBlock(int x, int y, int z) {
        all.add(Utils.getDefaultWorld().getBlockAt(new Location(Utils.getDefaultWorld(), x, y ,z)));
    }

    public static void setStrobe() { action = "strobe"; }
    public static void setAlternation() { action = "alternation"; }
    public static void stop() {
        action = "null";
        showOrHideAll(true);
    }

    private static void showOrHideAll(Boolean hide) {
        Material material = hide ? Material.STONE : Material.GLOWSTONE;
        for (Block block : all) { block.setType(material); }
    }

    private static void showOrHideHalf(Boolean hide) {
        Material material = hide ? Material.STONE : Material.GLOWSTONE;
        Material material2 = hide ? Material.GLOWSTONE : Material.STONE;
        for (int i = 0; i < all.size(); i += 2) {
            all.get(i).setType(material);
        }
        for (int i = 1; i < all.size(); i += 2) {
            all.get(i).setType(material2);
        }
    }

}
