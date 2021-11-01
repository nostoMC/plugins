package fr.nosto;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    private static final ItemStack clearSlot = createItem(Material.BLACK_STAINED_GLASS_PANE, " ");

    private static final Set<String> survies_names = new HashSet<>();
    static {
        survies_names.add("survie");
        survies_names.add("survie_the_end");
        survies_names.add("survie_nether");
    }

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

    public static void sendMessageToSurvival(String message) {
        for(String name : survies_names) {
            sendMessageToWorld(Objects.requireNonNull(Bukkit.getWorld(name)), message);
        }
    }

    public static void sendMessageToWorld(World world, String message) {
        for(Player player : world.getPlayers()) {
            player.sendMessage(message);
        }
    }

    public static ItemStack getClearSlot() {
        return clearSlot;
    }

    public static Set<String> getSurviesNames() {
        return survies_names;
    }
}
