package fr.djredstone.nostoDuel.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.djredstone.nostoDuel.Main;

public class onRespawn implements Listener {
	
	static ArrayList<Player> duelLobby = Main.getDuelLobbyList();
	static ArrayList<Player> duel = Main.getDuelList();

	public onRespawn(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onRespawnEvent(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if(duel.contains(player)) {
			duel.remove(player);
			event.setRespawnLocation(new Location(Bukkit.getWorld("duel"), 136.5, 71.0, -230.5, 0f, 0f));
			player.getInventory().clear();
			ItemStack seriousPVP = new ItemStack(Material.DIAMOND_SWORD, 1);
			ItemMeta seriousPVPMeta = seriousPVP.getItemMeta();
			seriousPVPMeta.setDisplayName("§2§lPvP Sérieux");
			seriousPVPMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
			seriousPVPMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			seriousPVP.setItemMeta(seriousPVPMeta);
			player.getInventory().setItem(2, seriousPVP);
			ItemStack funPVP = new ItemStack(Material.TNT, 1);
			ItemMeta funPVPMeta = funPVP.getItemMeta();
			funPVPMeta.setDisplayName("§a§lPvP Fun§8(WIP)");
			funPVPMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
			funPVPMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			funPVP.setItemMeta(funPVPMeta);
			player.getInventory().setItem(6, funPVP);
			player.updateInventory();
			player.updateInventory();
			duelLobby.add(player);
			player.setHealth(20);
		}
	}
	
}
