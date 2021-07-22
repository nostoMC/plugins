package fr.djredstone.nostoMCNF.fleches;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import fr.djredstone.nostoMCNF.Main;

public class rose {
	
	@SuppressWarnings("deprecation")
	public static void simple(Location location) {
		ArmorStand simple_rose = (ArmorStand) Bukkit.getWorld("McNightFunkin").spawnEntity(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()), EntityType.ARMOR_STAND);
		simple_rose.setGravity(false);
		simple_rose.setArms(true);
		simple_rose.setVisible(false);
		String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDJlZTU5N2FiNWJkZTk4YTc1Mzk2MjViNDE0Yzg3ZGIzODZmZjdmOWQ1MjZmZjk4MzllOTk2OTYzMDhiNGE5ZSJ9fX0=";
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures", new Property("textures", value));

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, (short) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, gameProfile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
        skull.setItemMeta(skullMeta);
        simple_rose.setHelmet(skull);
		
		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {

				simple_rose.teleport(new Location(Bukkit.getWorld("McNightFunkin"), simple_rose.getLocation().getX(), simple_rose.getLocation().getY() + 0.1, simple_rose.getLocation().getZ()));
				
				i++;
				
				if(i == 80) {
					simple_rose.remove();
					cancel();
				}
				
			}
		}.runTaskTimer(Main.getInstance(), 0, 0);
	}

}
