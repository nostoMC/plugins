package fr.djredstone.nostoDuel.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.djredstone.nostoDuel.Main;

public class onDisconnect implements Listener {
	
	static ArrayList<Player> duelLobby = Main.getDuelLobbyList();
	static ArrayList<Player> duel = Main.getDuelList();

	public onDisconnect(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onDisconnectEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer(); 
		if(duel.contains(player)) {
			duel.remove(player);
			duelLobby.add(player);
			event.setQuitMessage("§6§l" + player.getName() + " §es'est déconnecté ! §6§lMatch nul !");
			Player playerWin = duel.get(0);
			duel.remove(playerWin);
			playerWin.teleport(new Location(Bukkit.getWorld("duel"), 136.5, 71.0, -230.5, 0f, 0f));
			playerWin.getInventory().clear();
			ItemStack seriousPVP = new ItemStack(Material.DIAMOND_SWORD, 1);
			ItemMeta seriousPVPMeta = seriousPVP.getItemMeta();
			seriousPVPMeta.setDisplayName("§2§lPvP Sérieux");
			seriousPVPMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
			seriousPVPMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			seriousPVP.setItemMeta(seriousPVPMeta);
			playerWin.getInventory().setItem(2, seriousPVP);
			ItemStack funPVP = new ItemStack(Material.TNT, 1);
			ItemMeta funPVPMeta = funPVP.getItemMeta();
			funPVPMeta.setDisplayName("§a§lPvP Fun§8(WIP)");
			funPVPMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
			funPVPMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			funPVP.setItemMeta(funPVPMeta);
			playerWin.getInventory().setItem(6, funPVP);
			player.updateInventory();
			playerWin.updateInventory();
			duelLobby.add(playerWin);
			playerWin.setHealth(20);
		}
	}
	
}
