package fr.nostoNC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.kyori.adventure.text.Component;

public class Utils {

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

        ItemStack clearSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta clearSlotMeta = clearSlot.getItemMeta();
        clearSlotMeta.displayName(Component.empty());
        clearSlot.setItemMeta(clearSlotMeta);

        for(int i = 0; i < inv.getSize(); i++) {
            if(inv.getItem(i) == null) {
                inv.setItem(i, clearSlot);
            }
        }
    }

    public static boolean isInClub(Entity entity) {
        return entity.getWorld().getName().equals(Main.defaultWorld.getName());
    }

}
