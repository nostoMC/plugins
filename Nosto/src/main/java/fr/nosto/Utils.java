package fr.nosto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    private static final ItemStack clearSlot = createItem(Material.BLACK_STAINED_GLASS_PANE, " ");

    public static ItemStack createItem(Material material, String customName, String... lore) {
        ItemStack it = new ItemStack(material);
        ItemMeta itM = Objects.requireNonNull(it.getItemMeta());

        if(customName != null) itM.setDisplayName(customName);

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
                inv.setItem(i, getClearSlot());
            }
        }
    }

    public static ItemStack getClearSlot() {
        return clearSlot;
    }
}
