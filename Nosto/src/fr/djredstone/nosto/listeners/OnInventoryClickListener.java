package fr.djredstone.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.djredstone.nosto.Main;
import fr.djredstone.nosto.menus.EventMenu;
import fr.djredstone.nosto.menus.MainMenu;
import fr.djredstone.nosto.menus.MinijeuxMenu;
import fr.djredstone.nosto.menus.MondeOuvertMenu;
import fr.djredstone.nosto.menus.ShopMenu;
import fr.djredstone.nosto.menus.TpMenu;
import fr.djredstone.nosto.menus.TrailsMenu;
import fr.djredstone.nosto.menus.TrainingMenu;

public class OnInventoryClickListener implements Listener {

	public OnInventoryClickListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current.getType() == null) {
			return;
		}
		
		if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu")) {
			event.setCancelled(true);
			
			switch(current.getType()) {
			
			case COMPASS:
				TpMenu.openMenu(player);
				break;
				
			case BLAZE_POWDER:
				TrailsMenu.openMenu(player);
				break;
				
			case GOLD_INGOT:
				ShopMenu.openMenu(player);
				break;
			
			default:
				break;
			}
			
			
		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP")) {
			event.setCancelled(true);
			
			switch(current.getType()){
			
			case ARROW:
				MainMenu.openMenu(player);
				break;
			
			case GRASS_BLOCK:
				MondeOuvertMenu.openMenu(player);
				break;
				
			case IRON_SWORD:
				MinijeuxMenu.openMenu(player);
				break;
				
			case BOW:
				TrainingMenu.openMenu(player);
				break;
				
			case FIREWORK_ROCKET:
				EventMenu.openMenu(player);
				break;
				
			default:
				break;
			}

		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP > Monde Ouvert")) {
			event.setCancelled(true);
			
			switch(current.getType()) {
			
			case ARROW:
				TpMenu.openMenu(player);
				break;
				
			case IRON_SWORD:
				if(!player.hasPermission("server.survivalAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location SurvivalLobby = new Location(Bukkit.getWorld("survie"), -0.5, 63, 0.5, 180f, 0f);
				player.teleport(SurvivalLobby);
				player.setGameMode(GameMode.SURVIVAL);
				break;
				
			case GRASS_BLOCK:
				if(!player.hasPermission("server.skyblockAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location skyblockLobby = new Location(Bukkit.getWorld("skyworld"), 0.5, 150, 4.5, 0f, -10f);
				player.teleport(skyblockLobby);
				player.setGameMode(GameMode.SURVIVAL);
				break;
			
			default:
				break;
			
			}

		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP > Mini jeux")) {
			event.setCancelled(true);

			switch(current.getType()) {
			
			case ARROW:
				TpMenu.openMenu(player);
				break;
				
			case IRON_SWORD:
				if(!player.hasPermission("server.duelAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location duelLobby = new Location(Bukkit.getWorld("duel"), 136.5, 71.0, -230.5, 0f, 0f);
				player.teleport(duelLobby);
				player.teleport(duelLobby);
				player.setGameMode(GameMode.ADVENTURE);
				break;
				
			case ZOMBIE_HEAD:
				if(!player.hasPermission("server.dungeonsAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location dungeonLobby = new Location(Bukkit.getWorld("dungeonLobby"), 8.5, 11.5, 8.5, 0f, 0f);
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(dungeonLobby);
				player.teleport(dungeonLobby);
				break;
			
			default:
				break;
			
			}

		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP > Training")) {
			event.setCancelled(true);

			switch(current.getType()) {
			
			case ARROW:
				TpMenu.openMenu(player);
				break;
			
			default:
				break;
			
			}

		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP > Events")) {
			event.setCancelled(true);

			switch(current.getType()) {
			
			case ARROW:
				TpMenu.openMenu(player);
				break;
				
			case SHIELD:
				if(!player.hasPermission("server.PvPAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location pvpLobby = new Location(Bukkit.getWorld("event"), 136, 70, -231, 0f, 0f);
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(pvpLobby);
				break;
				
			case PLAYER_HEAD:
				if(!player.hasPermission("server.lgAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location lgLobby = new Location(Bukkit.getWorld("lg"), 2841, 72, 3536, 0f, 0f);
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(lgLobby);
				break;
				
			case DIAMOND:
				if(!player.hasPermission("server.roueDeLaChanceAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location roueLobby = new Location(Bukkit.getWorld("RoueDeLaChance"), 0, 229, 50, 0f, 0f);
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(roueLobby);
				break;
				
			case FIREWORK_ROCKET:
				if(!player.hasPermission("server.showAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location showLobby = new Location(Bukkit.getWorld("show"), 0.5, 65, 0.5, 0f, 0f);
				player.teleport(showLobby);
				player.teleport(showLobby);
				player.setGameMode(GameMode.ADVENTURE);
				break;
				
			case MUSIC_DISC_BLOCKS:
				if(!player.hasPermission("server.nightclubAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location nightClubLobby = new Location(Bukkit.getWorld("Nightclub"), 0.5, 64.0, 0.5, 0f, 0f);
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(nightClubLobby);
				break;
			
			default:
				break;
			
			}

		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP > Events > Hunt")) {
			event.setCancelled(true);

			if(current.getType() == Material.LEATHER_BOOTS || current.getType() == Material.IRON_SWORD || current.getType() == Material.ENDER_EYE) {
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
			}

//		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > Particules")) {
//			event.setCancelled(true);
//			
//			if(current.getType() == null) {
//				return;
//			}
//			
//			switch(current.getType()) {
//			
//			case ARROW:
//				MainMenu.openMenu(player);
//				break;
//				
//			case BLAZE_POWDER:
//				if(!player.hasPermission("nosto.trails.flame")) break;
//				int slot = event.getRawSlot();
//				ItemStack it = event.getView().getItem(slot);
//				ItemMeta itM = event.getCurrentItem().getItemMeta();
//				if(Main.getPlayerFlameTrails().get(player) != null && Main.getPlayerFlameTrails().get(player) == false) {
//					Main.setPlayerFlameTrails(player, true);
//					itM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
//					it.setItemMeta(itM);
//					event.getView().setItem(slot, it);
//				} else {
//					Main.setPlayerFlameTrails(player, false);
//					itM.removeEnchant(Enchantment.DAMAGE_ALL);
//					it.setItemMeta(itM);
//					event.getView().setItem(slot, it);
//				}
//				break;
//			
//			default:
//				break;
//			}
			
		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > Boutique")) {
			event.setCancelled(true);
			
			switch(current.getType()) {
			
			
			case ARROW:
				MainMenu.openMenu(player);
				break;
				
			default:
				break;
			}
		}
	}
	
	public static ItemStack getItem(Material material, String customName) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		if(customName != null) itM.setDisplayName(customName);
		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		it.setItemMeta(itM);
		return it;
	}
	
}
