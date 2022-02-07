package fr.nostoNC;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DjManager {

    private static final Location PLATEAU = new Location(Utils.defaultWorld, -2.0, 103.0, 147, 0, 0); // sur la scène
    private static final Location FOSSE = new Location(Utils.defaultWorld, -2.0, 101.0, 152.99, 0, 0); // en bas de la scène

    private static final ItemStack HEADPHONES = Utils.createItem(Material.CARVED_PUMPKIN, "§3Casque de DJ");
    static {
        ItemMeta meta = HEADPHONES.getItemMeta();
        meta.setCustomModelData(1);
        HEADPHONES.setItemMeta(meta);
    }

    public static UUID DjID = null;

    public static void tryJoinDj(Player player) {
        if (DjID == null) {
            DjID = player.getUniqueId();

            player.teleport(PLATEAU);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 1);
            player.getInventory().setHelmet(HEADPHONES);

            Utils.sendMessageToClub("\n§6" + player.getName() + "§e est notre nouveau DJ !");
        }
        else if (DjID == player.getUniqueId()) {
            player.teleport(PLATEAU);
        }
        else {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 1);
            player.sendMessage("\n§cIl y a déja un dj !");
        }
    }

    public static void leaveDj() {
        Player player = Bukkit.getPlayer(DjID);
        DjID = null;

        if (player != null) {
            player.teleport(FOSSE);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 1);
            player.getInventory().setHelmet(null);

            Utils.sendMessageToClub("\n§6" + player.getName() + "§e quitte son poste de DJ !");
        }

    }

}
