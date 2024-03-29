package fr.nostoNC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import net.kyori.adventure.text.Component;

public class Utils {

    private static final ArrayList<String> on = new ArrayList<>(), off = new ArrayList<>();
    static {
        off.add("§c§loff");
        on.add("§a§lon");
    }

    public static ArrayList<String> getOnLore() { return on; }

    public static ArrayList<String> getOffLore() { return off; }

    private static final HashMap<String, Boolean> activeEffects = new HashMap<>();

    private static World defaultWorld;

    public static final ItemStack clearSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    static {
        ItemMeta clearSlotMeta = clearSlot.getItemMeta();
        clearSlotMeta.displayName(Component.empty());
        clearSlot.setItemMeta(clearSlotMeta);
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createItem(Material material, String name, String... lore) {

        ItemStack it = new ItemStack((material));
        ItemMeta itM = it.getItemMeta();

        if (name != null) itM.setDisplayName(name);

        List<String> itemLore = new ArrayList<>();
        Collections.addAll(itemLore, lore);
        itM.setLore(itemLore);

        itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        it.setItemMeta(itM);
        return it;

    }

    public static void fillEmptyItem(Inventory inv) {
        for(int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null) {
                inv.setItem(i, clearSlot);
            }
        }
    }

    public static boolean isInClub(Entity entity) {
        return entity.getWorld().getName().equals(defaultWorld.getName());
    }

    public static void sendMessageToClub(String message) {
        for (Player player : defaultWorld.getPlayers()) {
            player.sendMessage(message);
        }
    }

    public static Vector getBPoint(Vector pointA, double yaw, double pitch, double distance) {

        yaw *= 2 * Math.PI / 360 * -1;
        pitch *= 2 * Math.PI / 360 * -1;

        double multiplicator = Math.cos(pitch);

        Vector pointB = new Vector();

        pointB.setX(Math.sin(yaw) * multiplicator);
        pointB.setZ(Math.cos(yaw) * multiplicator);
        pointB.setY(Math.sin(pitch));

        pointB.multiply(distance);
        pointB.add(pointA);

        return pointB;
    }

    public static World getDefaultWorld() {
        return defaultWorld;
    }

    public static void setDefaultWorld(World defaultWorld) {
        Utils.defaultWorld = defaultWorld;
    }

    public static void putActiveEffects(String key, boolean value) {
        activeEffects.put(key, value);
    }

    public static boolean getActiveEffects(String key) {
        return activeEffects.get(key);
    }

    @SuppressWarnings("deprecation")
    public static void createAndCheckActiveEffectItem(Inventory inv, Material material, String itName, String var, int slot) {
        ItemStack it = new ItemStack(material);
        ItemMeta itM = it.getItemMeta();
        itM.setDisplayName(itName);

        if(var != null) {
            if(!Utils.getActiveEffects(var)) {
                itM.setLore(off);
            } else {
                itM.setLore(on);
                itM.addEnchant(Enchantment.LUCK, 1, false);
            }
        }

        itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        it.setItemMeta(itM);
        inv.setItem(slot, it);
    }

    public static void checkActiveEffectItem(Player player, String var) {
        if (!Utils.getActiveEffects(var)) {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
            Utils.putActiveEffects(var, true);
        } else {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
            Utils.putActiveEffects(var, false);
        }
    }

    public static Color getColor(int i) {
        return switch (i) {
            case 1 -> Color.AQUA;
            case 2 -> Color.BLACK;
            case 3 -> Color.BLUE;
            case 4 -> Color.FUCHSIA;
            case 5 -> Color.GRAY;
            case 6 -> Color.GREEN;
            case 7 -> Color.LIME;
            case 8 -> Color.MAROON;
            case 9 -> Color.NAVY;
            case 10 -> Color.OLIVE;
            case 11 -> Color.ORANGE;
            case 12 -> Color.PURPLE;
            case 13 -> Color.RED;
            case 14 -> Color.SILVER;
            case 15 -> Color.TEAL;
            case 16 -> Color.WHITE;
            case 17 -> Color.YELLOW;
            default -> null;
        };
    }

}
