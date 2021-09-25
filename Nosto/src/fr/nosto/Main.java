package fr.nosto;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nosto.tasks.particles.PlayerTrailsStats;

public class Main extends JavaPlugin {

	public static ArrayList<Player> frozen = new ArrayList<Player>();
	public static ArrayList<Player> menuPlayers = new ArrayList<Player>();
	public static ArrayList<Player> vanishList = new ArrayList<Player>();
	public static ArrayList<Player> afks = new ArrayList<Player>();
	static HashMap<Player, PlayerTrailsStats> playerTrails = new HashMap<Player, PlayerTrailsStats>();
	
	public static Main instance;
	
	public static JavaPlugin getInstance() {
		return instance;
	}
	
	public boolean isAReload = false;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		new Setup(this);
		new DiscordSetup(this);
		
	}

	@Override
	public void onDisable() {
		
		new Shutdown(this);
		new DiscordShutdown(this);
		
	}
	
	public static void setPlayerTrailStats(Player player, PlayerTrailsStats stats) {
		playerTrails.put(player, stats);
	}

	public static HashMap<Player, PlayerTrailsStats> getPlayerTrailsMap() {
		return playerTrails;
	}
	
	public static Inventory fillEmplyItem(Inventory inv) {
		ItemStack clearSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta clearSlotMeta = clearSlot.getItemMeta();
		clearSlotMeta.setDisplayName(" ");
		clearSlot.setItemMeta(clearSlotMeta);
		
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) == null) {
				inv.setItem(i, clearSlot);
			}
		}
		return inv;
	}
	
	public static ItemStack createItem(Material material, String customName) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		if(customName != null) itM.setDisplayName(customName);
		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		it.setItemMeta(itM);
		return it;
	}
	
}
