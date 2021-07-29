package fr.djredstone.nostoDuel.tasks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoDuel.Main;
import fr.djredstone.nostoDuel.commands.CommandDuel;
import fr.djredstone.nostoDuel.listeners.OnInventoryClick;
import fr.djredstone.nostoDuel.listeners.OnplayerDeathListener;

public class DuelStart extends BukkitRunnable {

	static int kitS = OnInventoryClick.getKitS();
	static ArrayList<Player> duelLobby = Main.getDuelLobbyList();
	static ArrayList<Player> duel = Main.getDuelList();
	static Boolean endDuel = OnplayerDeathListener.getEndDuel();
	int timer = 0;
	Player player1 = duel.get(0);
	Player player2 = duel.get(1);
	
	@Override
	public void run() {
		endDuel = OnplayerDeathListener.getEndDuel();
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(duel.contains(players)) {
				if(timer == 0) {
					players.sendMessage("");
					players.sendMessage("§a§lDébut de la partie !");
					Player player1 = duel.get(0);
					Player player2 = duel.get(1);
					duelLobby.remove(players);
					player1.teleport(new Location(Bukkit.getWorld("duel"), 136.5, 26, -145.5, 180, 0));
					player2.teleport(new Location(Bukkit.getWorld("duel"), 136.5, 26, -315.5, 180, 0));
					CommandDuel.resetDemandeAccepted();
					players.getInventory().clear();
				}
				if(timer == 20) {
					players.sendTitle("§2§l3", "", 10, 0, 10);
				}
				if(timer == 40) {
					players.sendTitle("§6§l2", "", 10, 0, 10);
				}
				if(timer == 60) {
					players.sendTitle("§c§l1", "", 10, 0, 10);
				}
				if(timer == 80) {
					players.sendTitle("§8§lFight !!", "", 10, 0, 10);
				}
				if(timer <= 80) {
					player1.teleport(new Location(Bukkit.getWorld("duel"), 136.5, 26, -146.5, 180, 0));
					player2.teleport(new Location(Bukkit.getWorld("duel"), 136.5, 26, -316.5, 180, 0));
				}
				kitS = OnInventoryClick.getKitS();
				if(kitS == 1) {// KIT NORMAL
					players.getInventory().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
					players.getInventory().setItem(EquipmentSlot.CHEST, new ItemStack(Material.IRON_CHESTPLATE));
					players.getInventory().setItem(EquipmentSlot.LEGS, new ItemStack(Material.DIAMOND_LEGGINGS));
					players.getInventory().setItem(EquipmentSlot.FEET, new ItemStack(Material.IRON_BOOTS));
					players.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
					players.getInventory().setItem(1, new ItemStack(Material.COOKED_BEEF));
				} else if(kitS == 2) {// KIT OP
					players.getInventory().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
					players.getInventory().setItem(EquipmentSlot.CHEST, new ItemStack(Material.DIAMOND_CHESTPLATE));
					players.getInventory().setItem(EquipmentSlot.LEGS, new ItemStack(Material.DIAMOND_LEGGINGS));
					players.getInventory().setItem(EquipmentSlot.FEET, new ItemStack(Material.DIAMOND_BOOTS));
					players.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
					players.getInventory().setItem(1, new ItemStack(Material.GOLDEN_APPLE));
				} else if(kitS == 3) {// KIT ARCHER
					players.getInventory().setItem(EquipmentSlot.HEAD, new ItemStack(Material.LEATHER_HELMET));
					players.getInventory().setItem(EquipmentSlot.CHEST, new ItemStack(Material.LEATHER_CHESTPLATE));
					players.getInventory().setItem(EquipmentSlot.LEGS, new ItemStack(Material.LEATHER_LEGGINGS));
					players.getInventory().setItem(EquipmentSlot.FEET, new ItemStack(Material.LEATHER_BOOTS));
					players.getInventory().setItem(0, new ItemStack(Material.BOW));
					players.getInventory().setItem(1, new ItemStack(Material.ARROW));
				} else if(kitS == 4) {// KIT INTENSSE
					players.getInventory().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET));
					players.getInventory().setItem(EquipmentSlot.CHEST, new ItemStack(Material.DIAMOND_CHESTPLATE));
					players.getInventory().setItem(EquipmentSlot.LEGS, new ItemStack(Material.DIAMOND_LEGGINGS));
					players.getInventory().setItem(EquipmentSlot.FEET, new ItemStack(Material.DIAMOND_BOOTS));
					players.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
					ItemStack potion = new ItemStack(Material.SPLASH_POTION);
					PotionMeta pm = (PotionMeta) potion.getItemMeta();
					pm.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
					potion.setItemMeta(pm);
					players.getInventory().setItem(1, potion);
					
				}
				if(endDuel == true) {
					OnplayerDeathListener.resetEndDuel();
					cancel();
				}
			}
		}
	
	timer++;
	}
	
}
