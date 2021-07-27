package fr.djredstone.nostoDuel.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import fr.djredstone.nostoDuel.Main;
import fr.djredstone.nostoDuel.commands.CommandDuel;
import fr.djredstone.nostoDuel.tasks.duelStart;

public class onInventoryClick implements Listener {
	
	static Boolean duelStart = Main.getDuelStart();
	static ArrayList<Player> duel = Main.getDuelList();
	static ArrayList<Player> duelLobby = Main.getDuelLobbyList();
	static String demandeCancel = onInteractListener.getDemandeCancel();
	static String demandeAccepted = CommandDuel.getDemandeAccepted();
	static int kitS;
	
	static BukkitRunnable demandeExpire;
	
	public onInventoryClick(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(event.getView().getTitle().equalsIgnoreCase("§9§lChoisissez votre kit !")) {
			event.setCancelled(true);
			
			switch(current.getType()){
				
			case BLACK_STAINED_GLASS_PANE: break;
			
			case IRON_LEGGINGS:
				event.getView().setItem(40, getItem(Material.IRON_LEGGINGS, "§b§lKit sélectionné : §7§lNormal"));
				break;
				
			case GOLDEN_APPLE:
				event.getView().setItem(40, getItem(Material.GOLDEN_APPLE, "§b§lKit sélectionné : §6§lOP"));
				break;
				
			case BOW:
				event.getView().setItem(40, getItem(Material.BOW, "§b§lKit sélectionné : §2§lArcher"));
				break;
				
			case SPLASH_POTION:
				event.getView().setItem(40, getItem(Material.SPLASH_POTION, "§b§lKit sélectionné : §d§lIntense"));
				break;
				
			case ENDER_EYE:
				event.getView().close();
				player.sendMessage("");
				if(event.getInventory().getItem(40).getItemMeta().getDisplayName().equalsIgnoreCase("§b§lKit sélectionné : §8§lAucun")) {
					player.sendMessage("");
					player.sendMessage("§cVous n'avez pas choisis de kit !");
				} else if(!duel.isEmpty()){
					player.sendMessage("");
					player.sendMessage("§cUne partie est déjà en cours !");
				} else {
					duel.add(player);
					if(event.getInventory().getItem(40).getItemMeta().getDisplayName().equalsIgnoreCase("§b§lKit sélectionné : §7§lNormal")) {
						kitS = 1;
						Bukkit.broadcastMessage("§8[§6DUEL§8] §6§l" + player.getName() + " §edéfi qui conque dans un combat §6§lsérieux §eavec le kit §7§lNormal §e!");
					} else if(event.getInventory().getItem(40).getItemMeta().getDisplayName().equalsIgnoreCase("§b§lKit sélectionné : §6§lOP")) {
						kitS = 2;
						Bukkit.broadcastMessage("§8[§6DUEL§8] §6§l" + player.getName() + " §edéfi qui conque dans un combat §6§lsérieux §eavec le kit §6§lOP §e!");
					} else if(event.getInventory().getItem(40).getItemMeta().getDisplayName().equalsIgnoreCase("§b§lKit sélectionné : §2§lArcher")) {
						kitS = 3;
						Bukkit.broadcastMessage("§8[§6DUEL§8] §6§l" + player.getName() + " §edéfi qui conque dans un combat §6§lsérieux §eavec le kit §2§lArcher §e!");
					} else if(event.getInventory().getItem(40).getItemMeta().getDisplayName().equalsIgnoreCase("§b§lKit sélectionné : §d§lIntense")) {
						kitS = 4;
						Bukkit.broadcastMessage("§8[§6DUEL§8] §6§l" + player.getName() + " §edéfi qui conque dans un combat §6§lsérieux §eavec le kit §d§lIntense §e!");
					}
					player.getInventory().clear();
					player.getInventory().setItem(8, getItem(Material.BARRIER, "§6§lAnnuler la demande"));
					player.updateInventory();
					new BukkitRunnable() {
						double i = 200;
						@Override
						public void run() {
						demandeCancel = onInteractListener.getDemandeCancel();
						demandeAccepted = CommandDuel.getDemandeAccepted();
						i = i - 1;
						for(Player players : Bukkit.getOnlinePlayers()) {
							if(duelLobby.contains(players)) {
								players.setExp((float) (i/200f));
								players.setLevel((int) (i/20f));
							}
						}
						if(i == 0 || demandeCancel.equalsIgnoreCase("true")) {
							player.sendMessage("");
				        	player.sendMessage("§c§lLa demande a expiré !");
				        	player.getInventory().clear();
				            duel.remove(player);
				            ItemStack seriousPVP = new ItemStack(Material.DIAMOND_SWORD, 1);
							ItemMeta seriousPVPMeta = seriousPVP.getItemMeta();
							seriousPVPMeta.setDisplayName("§2§lPvP Sérieux");
							seriousPVPMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
							seriousPVPMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							seriousPVP.setItemMeta(seriousPVPMeta);
							player.getInventory().setItem(2, seriousPVP);
							ItemStack funPVP = new ItemStack(Material.TNT, 1);
							ItemMeta funPVPMeta = funPVP.getItemMeta();
							funPVPMeta.setDisplayName("§a§lPvP Fun §8(WIP)");
							funPVPMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
							funPVPMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							funPVP.setItemMeta(funPVPMeta);
							player.getInventory().setItem(6, funPVP);
							player.updateInventory();
							duelLobby.add(player);
							onInteractListener.reset();
							for(Player players : Bukkit.getOnlinePlayers()) {
								if(duelLobby.contains(players)) {
									players.setExp(0);
								}
							}
							cancel();
						}
						if(demandeAccepted.equalsIgnoreCase("true")) {
							duelStart task = new duelStart();
							task.runTaskTimer(Main.getInstance(), 0, 0);
							for(Player players : Bukkit.getOnlinePlayers()) {
								if(duelLobby.contains(players)) {
									players.setExp(1);
									players.setLevel(0);
								}
							}
							cancel();
						}
					}
						
					}.runTaskTimer(Main.getInstance(), 0, 0);
				}
				break;
				
			default: break;
			}
		}
		
		if(event.getView().getTitle().equalsIgnoreCase("§2§lTP > Mini jeux")) {
			event.setCancelled(true);
			if(current.getType() == Material.IRON_SWORD) {
				if(!player.hasPermission("server.duelAcces")) return;
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
				duelLobby.add(player);
				player.setHealth(20);
			}
		}
	}
	
	public ItemStack getItem(Material material, String customName) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		if(customName != null) itM.setDisplayName(customName);
		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		it.setItemMeta(itM);
		return it;
	}
	
	public static BukkitTask getDemandeExpire() {
		return (BukkitTask) demandeExpire;
	}
	
	public static int getKitS() {
		return kitS;
	}
}
