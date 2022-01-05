package fr.nostoNC;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class DjManager {

    private static final Location PLATEAU = new Location(Main.defaultWorld, -2.0, 103.0, 147, 0, 0); // sur la scène
    private static final Location FOSSE = new Location(Main.defaultWorld, -2.0, 101.0, 152.99, 0, 0); // en bas de la scène

    public static UUID DjID = null;

    public static void tryJoinDj(Player player) {
        if (DjID == null) {
            DjID = player.getUniqueId();

            player.teleport(PLATEAU);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 1);

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

            Utils.sendMessageToClub("\n§6" + player.getName() + "§e quitte son poste de DJ !");
        }

    }

}
