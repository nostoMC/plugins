package fr.nostoNC.menus;

import java.util.ArrayList;
import java.util.List;

import fr.nostoNC.tasks.effects.TopLaser;
import fr.nostoNC.tasks.effects.WallLaser;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.nostoNC.tasks.effects.RandomParticleEffect;
import fr.nostoNC.tasks.effects.StrobeEffect;

public class EffectsMenu implements Listener {

	private static final ArrayList<String> on = new ArrayList<>(), off = new ArrayList<>();
	static {
		off.add("§c§loff");
		on.add("§a§lon");
	}

	@SuppressWarnings("deprecation")
	public static void openMenu(Player player) {

		Inventory inv = Bukkit.createInventory(null, 54, "§2§lGestioraire des effets");

		// ----------------------------------------------------------------------------------------------------------------------

		createAndCheckActiveEffectItem(inv, Material.STRING, "§7§lFloor Smoke", "floorSmoke", 10);

		inv.setItem(18, Utils.createItem(Material.CLOCK, "§e§lTiming",
				"§7La vitesse est actuellement à §6§l" + StrobeEffect.timing,
				"§8Click droit: §a+1",
				"§8Click gauche: §c-1"));
		if (Utils.getActiveEffects("strobe")) {
			inv.setItem(19, Utils.createItem(Material.REDSTONE_LAMP, "§8§lStrobe", "§a§lon"));
		} else {
			inv.setItem(19, Utils.createItem(Material.REDSTONE_LAMP, "§8§lStrobe", "§c§loff"));
		}

		createAndCheckActiveEffectItem(inv, Material.FIREWORK_ROCKET, "§8§lFeux d'artifices", null, 12);

		createAndCheckActiveEffectItem(inv, Material.PUMPKIN_SEEDS, "§f§lParticules aléatoires", null, 21);

		inv.setItem(5, Utils.createItem(Material.END_CRYSTAL, "§f§eLights Top"));
		inv.setItem(14, Utils.createItem(Material.REDSTONE_TORCH, "§f§eDown en alternance"));
		inv.setItem(23, Utils.createItem(Material.STICK, "§f§eRandom Moving"));
		inv.setItem(32, Utils.createItem(Material.RED_CONCRETE, "§f§eSTOP"));

		inv.setItem(7, Utils.createItem(Material.END_CRYSTAL, "§f§eLights Wall"));
		inv.setItem(16, Utils.createItem(Material.STONE_BUTTON, "§f§eFront"));
		inv.setItem(25, Utils.createItem(Material.REPEATER, "§f§eWave"));
		inv.setItem(34, Utils.createItem(Material.COMPARATOR, "§f§eEdge"));
		inv.setItem(43, Utils.createItem(Material.RED_CONCRETE_POWDER, "§f§eSTOP"));

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

		if(event.getView().getTitle().equalsIgnoreCase("§2§lGestioraire des effets")) {
			event.setCancelled(true);

			if (event.getClick() == ClickType.DOUBLE_CLICK) return;

			switch (current.getType()) {
				case STRING -> checkActiveEffectItem(player, "floorSmoke");

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

				case REDSTONE_LAMP -> checkActiveEffectItem(player, "strobe");

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

				default -> {}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static void createAndCheckActiveEffectItem(Inventory inv, Material material, String itName, String var, int slot) {
		ItemStack it = new ItemStack(material);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(itName);

		if(var != null) {
			if(!Utils.getActiveEffects(var)) {
				itM.setLore(off);
			} else {
				itM.setLore(on);
				itM.addEnchant(Enchantment.LUCK, 1, false);
			}
		}

		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		it.setItemMeta(itM);
		inv.setItem(slot, it);
	}

	private static void checkActiveEffectItem(Player player, String var) {
		if (!Utils.getActiveEffects(var)) {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
			Utils.putActiveEffects(var, true);
		} else {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
			Utils.putActiveEffects(var, false);
		}
		openMenu(player);
	}

}
