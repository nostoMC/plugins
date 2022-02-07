package fr.nosto;

import java.util.*;
import java.util.List;

import fr.nosto.mysql.DatabaseManager;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.jetbrains.annotations.NotNull;

public class Utils {

    public static ArrayList<Player> vanishList = new ArrayList<>();

    public static FileConfiguration messageConfig;

    public static DatabaseManager databaseManager;

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

        if (customName != null) itM.setDisplayName(customName);

        List<String> itemLore = new ArrayList<>();
        Collections.addAll(itemLore, lore);
        itM.setLore(itemLore);

        itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        it.setItemMeta(itM);
        return it;
    }

    public static void fillEmptyItem(Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, getClearSlot());
            }
        }
    }

    public static void sendMessageToSurvival(String message) {
        for (String name : survies_names) {
            sendMessageToWorld(Objects.requireNonNull(Bukkit.getWorld(name)), message);
        }
    }

    public static void sendMessageToWorld(World world, String message) {
        for (Player player : world.getPlayers()) {
            player.sendMessage(message);
        }
    }

    public static void sendTextComponentToSurvival(TextComponent textComponent) {
        for (String name : survies_names) {
            sendTextComponentToWorld(Objects.requireNonNull(Bukkit.getWorld(name)), textComponent);
        }
    }

    public static void sendTextComponentToWorld(World world, TextComponent textComponent) {
        for (Player player : world.getPlayers()) {
            player.sendMessage(textComponent);
        }
    }

    public static ItemStack getClearSlot() {
        return clearSlot;
    }

    public static Set<String> getSurviesNames() {
        return survies_names;
    }

    @NotNull
    public static ChatColor getGradeColor(Player player) {
        ChatColor color = ChatColor.GRAY;
        if (player.hasPermission("group.administrateur")) color = ChatColor.RED;
        else if (player.hasPermission("group.dev")) color = ChatColor.AQUA;
        else if (player.hasPermission("group.buildeur")) color = ChatColor.GREEN;
        return color;
    }

    @NotNull
    public static String getGroupDiscord(Player player) {
        String groupDiscord = "";
        if (player.hasPermission("group.administrateur")) groupDiscord = "Administrateur | ";
        else if (player.hasPermission("group.dev")) groupDiscord = "Developpeur | ";
        else if (player.hasPermission("group.buildeur")) groupDiscord = "Buildeur | ";
        return groupDiscord;
    }
}
