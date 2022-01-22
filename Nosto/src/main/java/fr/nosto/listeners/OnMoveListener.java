package fr.nosto.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.nosto.commands.CommandFreeze;
import fr.nosto.tasks.CosmeticEffectTask;

public class OnMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if (CommandFreeze.frozen.contains(player)) {
			player.sendMessage("§cVous êtes freeze, vous ne pouvez pas bouger !");
			event.setCancelled(true);
		}

		if (event.getTo() != null && !event.getFrom().toVector().equals(event.getTo().toVector())
				&& CosmeticEffectTask.effectRenderers.containsKey(player.getUniqueId())) {
			CosmeticEffectTask.effectRenderers.get(player.getUniqueId()).startCoolDown();
		}
	}

}
