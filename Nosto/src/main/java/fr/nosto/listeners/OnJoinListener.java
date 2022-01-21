package fr.nosto.listeners;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.nosto.Main;
import fr.nosto.tasks.CosmeticEffectTask;
import fr.nosto.tasks.particles.PlayerTrailsStats;

public class OnJoinListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		event.setJoinMessage("");
		Player player = event.getPlayer();
		final UUID uuid = player.getUniqueId();

		File file = new File(Main.getInstance().getDataFolder(), "economy.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

		if(config.getConfigurationSection("money." + uuid) == null) {
			config.set("money." + uuid, 0);
			try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(Main.getInstance().getServer().getPluginManager().isPluginEnabled("pluginpv")) {
			Location lg = new Location(Bukkit.getWorld("lg"), 2841, 73, 3539);
			player.teleport(lg);
			player.teleport(lg);
			player.setGameMode(GameMode.ADVENTURE);
		} else {
			Location lobby = new Location(Bukkit.getWorld("MainLobby"), 0.5, 103.5, 0.5, 0f, 0f);
			player.teleport(lobby);
			player.teleport(lobby);
			player.setMaxHealth(20);
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1, 200));
			player.getInventory().clear();
			if (player.hasPermission("*")) {
				player.setGameMode(GameMode.CREATIVE);
			} else {
				player.setGameMode(GameMode.ADVENTURE);
			}
			player.sendMessage("");
			player.sendTitle("§l§3≪ §l§bNosto §l§3≫", "§f§k§l|| §l§7Bienvenue " + player.getName() +  " §f§k§l||", 0, 100, 5);
			ItemStack compassLobby = new ItemStack(Material.COMPASS, 1);
			ItemMeta compassLobbyMeta = compassLobby.getItemMeta();
			assert compassLobbyMeta != null;
			compassLobbyMeta.setDisplayName("§b§lClick pour ouvrir le menu de téléportation");
			compassLobbyMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
			compassLobbyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			compassLobby.setItemMeta(compassLobbyMeta);
			player.getInventory().setItem(4, compassLobby);
			player.updateInventory();
		}

		PlayerTrailsStats stats = new PlayerTrailsStats(player); // futur: chercher les stats dans un fichier yml
		CosmeticEffectTask.playerTrails.put(uuid, stats);
		stats.equip(stats.getEquiped()); // re-updating value

		// ADMIN MESSAGE
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(players.isOp()) {
				players.sendMessage("");
				players.sendMessage("§5[LOG] §d" + player.getName() + "§5 joined the server");
			}
		}
		
	}

}
