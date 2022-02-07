package fr.nostoNC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import net.kyori.adventure.text.Component;

public class Utils {

    public static final HashMap<String, Boolean> activeEffects = new HashMap<>();

    public static World defaultWorld;

    public static final ItemStack clearSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    static {
        ItemMeta clearSlotMeta = clearSlot.getItemMeta();
        clearSlotMeta.displayName(Component.empty());
        clearSlot.setItemMeta(clearSlotMeta);
    }

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

}
