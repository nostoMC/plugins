package fr.nostoNC;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

}
