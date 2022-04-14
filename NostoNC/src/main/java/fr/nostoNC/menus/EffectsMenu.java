package fr.nostoNC.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nostoNC.Utils;
import fr.nostoNC.tasks.EffectsManager;
import fr.nostoNC.tasks.effects.LaserUpDown;
import fr.nostoNC.tasks.effects.StrobeEffect;
import fr.nostoNC.tasks.effects.WallLighting;

public class EffectsMenu implements Listener {

	private static final String title = "§2§lConsole | Effets";

	@SuppressWarnings("deprecation")
	public static void openMenu(Player player) {

		Inventory inv = Bukkit.createInventory(null, 54, title);

		// ----------------------------------------------------------------------------------------------------------------------

		Utils.createAndCheckActiveEffectItem(inv, Material.FIREWORK_ROCKET, "§8§lFeux d'artifices", null, 10);
		Utils.createAndCheckActiveEffectItem(inv, Material.PUMPKIN_SEEDS, "§f§lParticules aléatoires", null, 19);
		Utils.createAndCheckActiveEffectItem(inv, Material.CREEPER_HEAD, "§2§lCreeper Firework", null, 11);
		Utils.createAndCheckActiveEffectItem(inv, Material.NETHER_STAR, "§e§lStar Firework", null, 20);

		inv.setItem(13, Utils.createItem(Material.REDSTONE_BLOCK, "§c§lStop"));
		inv.setItem(22, Utils.createItem(Material.COMPASS, "§e§lTiming",
				"§7La vitesse est actuellement à §6§l" + WallLighting.timing,
				"§8Click droit: §a+1",
				"§8Click gauche: §c-1"));
		inv.setItem(14, Utils.createItem(Material.GLOWSTONE, "§e§lStrobe"));
		inv.setItem(23, Utils.createItem(Material.GLOWSTONE, "§e§lAlternation"));
		Utils.createAndCheckActiveEffectItem(inv, Material.STRING, "§7§lFloor Smoke", "floorSmoke", 16);
		Utils.createAndCheckActiveEffectItem(inv, Material.REDSTONE_LAMP, "§8§lStrobe", "strobe", 25);
		inv.setItem(25, Utils.createItem(Material.CLOCK, "§e§lTiming",
				"§7La vitesse est actuellement à §6§l" + StrobeEffect.timing,
				"§8Click droit: §a+1",
				"§8Click gauche: §c-1"));

		Utils.createAndCheckActiveEffectItem(inv, Material.SHROOMLIGHT, "§e§lLights top", "topLights", 37);
		inv.setItem(39, Utils.createItem(Material.ICE, "§a§lLaser Up/Down", LaserUpDown.isStarted() ? Utils.getOnLore().get(0) : Utils.getOffLore().get(0)));

		inv.setItem(53, Utils.createItem(Material.ARROW, "§2§lLasers"));

		Utils.fillEmptyItem(inv);

		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent event) {

		ItemStack current = event.getCurrentItem();
		if(current == null) return;

		Player player = (Player) event.getWhoClicked();

		if(event.getView().getTitle().equalsIgnoreCase(title)) {
			event.setCancelled(true);

			if (event.getClick() == ClickType.DOUBLE_CLICK) return;

			switch (current.getType()) {

				case FIREWORK_ROCKET -> EffectsManager.firework();
				case PUMPKIN_SEEDS -> EffectsManager.randomParticle();
				case CREEPER_HEAD -> EffectsManager.creeperFirework();
				case NETHER_STAR -> EffectsManager.starFirework();

				case REDSTONE_BLOCK -> EffectsManager.wallLightingStop();
				case COMPASS -> EffectsManager.wallLightingTimingChange(player, event.getClick());
				case GLOWSTONE -> {
					if (current.getItemMeta().getDisplayName().equals("§e§lStrobe")) EffectsManager.wallLightingStrobe();
					else if (current.getItemMeta().getDisplayName().equals("§e§lAlternation")) EffectsManager.wallLightingAlternation();
				}
				case STRING -> EffectsManager.floorSmoke(player);
				case REDSTONE_LAMP -> EffectsManager.strobe(player);
				case CLOCK -> EffectsManager.strobeTimingChange(player, event.getClick());

				case SHROOMLIGHT -> EffectsManager.topLights(player);

				case ICE -> EffectsManager.laserUpDown();

				case ARROW -> {
					EffectsMenu2.openMenu(player);
					return;
				}
			}
			openMenu(player);
		}
	}
}
