package fr.nostoNC.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.nostoNC.tasks.effects.RandomParticleEffect;
import fr.nostoNC.tasks.effects.StrobeEffect;
import fr.nostoNC.tasks.effects.TopLaser;
import fr.nostoNC.tasks.effects.WallLaser;

public class EffectsMenu implements Listener {

	private static final String title = "§2§lGestioraire des effets (page 1)";

	@SuppressWarnings("deprecation")
	public static void openMenu(Player player) {

		Inventory inv = Bukkit.createInventory(null, 54, title);

		// ----------------------------------------------------------------------------------------------------------------------

		Utils.createAndCheckActiveEffectItem(inv, Material.STRING, "§7§lFloor Smoke", "floorSmoke", 10);

		inv.setItem(18, Utils.createItem(Material.CLOCK, "§e§lTiming",
				"§7La vitesse est actuellement à §6§l" + StrobeEffect.timing,
				"§8Click droit: §a+1",
				"§8Click gauche: §c-1"));
		if (Utils.getActiveEffects("strobe")) {
			inv.setItem(19, Utils.createItem(Material.REDSTONE_LAMP, "§8§lStrobe", "§a§lon"));
		} else {
			inv.setItem(19, Utils.createItem(Material.REDSTONE_LAMP, "§8§lStrobe", "§c§loff"));
		}

		Utils.createAndCheckActiveEffectItem(inv, Material.FIREWORK_ROCKET, "§8§lFeux d'artifices", null, 12);

		Utils.createAndCheckActiveEffectItem(inv, Material.PUMPKIN_SEEDS, "§f§lParticules aléatoires", null, 21);

		inv.setItem(5, Utils.createItem(Material.END_CRYSTAL, "§f§eLights Top"));
		inv.setItem(14, Utils.createItem(Material.REDSTONE_TORCH, "§f§eDown en alternance"));
		inv.setItem(23, Utils.createItem(Material.STICK, "§f§eRandom Moving"));
		inv.setItem(32, Utils.createItem(Material.RED_CONCRETE, "§f§eSTOP"));

		inv.setItem(7, Utils.createItem(Material.END_CRYSTAL, "§f§eLights Wall"));
		inv.setItem(16, Utils.createItem(Material.STONE_BUTTON, "§f§eFront"));
		inv.setItem(25, Utils.createItem(Material.REPEATER, "§f§eWave"));
		inv.setItem(34, Utils.createItem(Material.COMPARATOR, "§f§eEdge"));
		inv.setItem(43, Utils.createItem(Material.RED_CONCRETE_POWDER, "§f§eSTOP"));

		inv.setItem(53, Utils.createItem(Material.ARROW, "Page 2"));

		Utils.fillEmptyItem(inv);

		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent event) {

		World world = Utils.getDefaultWorld();

		ItemStack current = event.getCurrentItem();
		if(current == null) return;

		Player player = (Player) event.getWhoClicked();

		if(event.getView().getTitle().equalsIgnoreCase(title)) {
			event.setCancelled(true);

			if (event.getClick() == ClickType.DOUBLE_CLICK) return;

			switch (current.getType()) {
				case STRING -> {
					Utils.checkActiveEffectItem(player, "floorSmoke");
					openMenu(player);
				}

				case CLOCK -> {
					if (event.getClick() == ClickType.RIGHT) {
						if (StrobeEffect.timing >= 20) {
							player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
							break;
						}
						StrobeEffect.timing++;
					}
					else if (event.getClick() == ClickType.LEFT) {
						if (StrobeEffect.timing <= 1) {
							player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
							break;
						}
						StrobeEffect.timing--;
					}
					if (event.getClick() != ClickType.DOUBLE_CLICK) openMenu(player);
				}

				case REDSTONE_LAMP -> {
					Utils.checkActiveEffectItem(player, "strobe");
					openMenu(player);
				}

				case FIREWORK_ROCKET -> {
					List<Firework> fireworks = new ArrayList<>();

					fireworks.add((Firework) world.spawnEntity(new Location(world, 4.5, 103.4, 148.5), EntityType.FIREWORK));
					fireworks.add((Firework) world.spawnEntity(new Location(world, 2.5, 103.4, 149.5), EntityType.FIREWORK));
					fireworks.add((Firework) world.spawnEntity(new Location(world, -0.5, 103.4, 150.5), EntityType.FIREWORK));
					fireworks.add((Firework) world.spawnEntity(new Location(world, -3.5, 103.4, 150.5), EntityType.FIREWORK));
					fireworks.add((Firework) world.spawnEntity(new Location(world, -6.5, 103.4, 149.5), EntityType.FIREWORK));
					fireworks.add((Firework) world.spawnEntity(new Location(world, -8.5, 103.4, 148.5), EntityType.FIREWORK));

					FireworkMeta fwm = fireworks.get(0).getFireworkMeta();

					fwm.setPower(2);
					fwm.addEffect(FireworkEffect.builder()
							.withColor(Color.WHITE)
							.withFade(Color.AQUA, Color.ORANGE)
							.with(Type.BURST)
							.flicker(true).trail(true)
							.build());

					for (Firework fw : fireworks) {
						fw.setVelocity(new Vector(0, 1, -0.2));
						fw.setFireworkMeta(fwm);
						fw.detonate();
					}
				}

				case PUMPKIN_SEEDS -> new RandomParticleEffect(Main.getInstance());

				case REDSTONE_TORCH -> {
					TopLaser.hideAll();
					TopLaser.showAll();
					TopLaser.moveToDown();
					TopLaser.alternance = true;
				}

				case STICK -> {
					TopLaser.hideAll();
					TopLaser.showAll();
					TopLaser.moveRandom();
					TopLaser.alternance = false;
				}

				case RED_CONCRETE -> {
					TopLaser.hideAll();
					TopLaser.alternance = false;
				}

				case STONE_BUTTON -> {
					WallLaser.moveFront();
					WallLaser.showAll();
				}

				case REPEATER -> {
					WallLaser.moveWave();
					WallLaser.showAll();
				}

				case COMPARATOR -> {
					WallLaser.moveEdge();
					WallLaser.showAll();
				}

				case RED_CONCRETE_POWDER -> WallLaser.hideAll();

				case ARROW -> EffectsMenu2.openMenu(player);

				default -> {}
			}
		}
	}
}
