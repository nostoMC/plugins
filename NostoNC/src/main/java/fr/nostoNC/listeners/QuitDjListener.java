package fr.nostoNC.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.nostoNC.DjManager;

public class QuitDjListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (event.getPlayer().getUniqueId().equals(DjManager.DjID)) {
            DjManager.leaveDj();
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getUniqueId().equals(DjManager.DjID)) {
            DjManager.leaveDj();
            player.sendMessage("\n§cVous avez quitté le club, vous n'êtes plus DJ !");
        }
    }
}
