package fr.nostoNC;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;

import fr.nostoNC.tasks.BottomLaser;
import fr.nostoNC.tasks.VIPpass;
import fr.nostoNC.tasks.effects.*;

public class Startup {

	public static void startup(Main main) {

		Main.activeEffects.put("floorSmoke", false);
		Main.activeEffects.put("strobe", false);
		Main.activeEffects.put("lightBottom", false);
		Main.activeEffects.put("lightTop", false);
		Main.activeEffects.put("randomBeacon", false);
		Main.activeEffects.put("sphere", false);
		Main.activeEffects.put("wave", false);
		Main.activeEffects.put("djLaser", false);
		Main.activeEffects.put("goboLaser", false);
		Main.activeEffects.put("randomLaser", false);

		new FloorSmokeEffect(main);
		new StrobeEffect(main);
		new SphereEffect(main);
		new WaveEffect(main);

		new LightTop(main);
		new LightBottom(main);
		new RandomBeaconEffect(main);

		new DjLaserEffect(main);
		new GoboLaserEffect(main);
		new RandomLaserEffect(main);

		new DjGlowing(main);

		new VIPpass(main);

		Set<String> commands = new HashSet<String>();

		commands.add("summon armor_stand 9.5 63.6 11.5 {Rotation:[-170f],Pose:{Head:[316f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand 5.5 63.6 11.5 {Rotation:[-170f],Pose:{Head:[316f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand 2.5 63.6 10.5 {Rotation:[-177f],Pose:{Head:[316f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -8.5 63.6 11.5 {Rotation:[170f],Pose:{Head:[316f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -4.5 63.6 11.5 {Rotation:[170f],Pose:{Head:[316f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -1.5 63.6 10.5 {Rotation:[177f],Pose:{Head:[316f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");

		commands.add("summon armor_stand 6.5 75.8 10.5 {Rotation:[-164f],Pose:{Head:[34f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand 2.5 75.8 9.5 {Rotation:[-164f],Pose:{Head:[34f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -5.5 75.8 10.5 {Rotation:[164f],Pose:{Head:[34f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -1.5 75.8 9.5 {Rotation:[164f],Pose:{Head:[34f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");

		commands.add("summon armor_stand 9.5 75.5 14.5 {Rotation:[-164f],Pose:{Head:[60f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand 6.5 75.5 14.5 {Rotation:[164f],Pose:{Head:[60f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -8.5 75.5 14.5 {Rotation:[164f],Pose:{Head:[60f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -5.5 75.5 14.5 {Rotation:[-164f],Pose:{Head:[60f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");

		commands.add("summon armor_stand 2.5 66.7 16.5 {Rotation:[-154f],Pose:{Head:[-25f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand 1.5 66.7 16.5 {Rotation:[-170f],Pose:{Head:[-30f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand 0.5 66.7 16.5 {Rotation:[180f],Pose:{Head:[-25f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -0.5 66.7 16.5 {Rotation:[170f],Pose:{Head:[-30f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");
		commands.add("summon armor_stand -1.5 66.7 16.5 {Rotation:[170f],Pose:{Head:[-30f,0f,0f]},Tags:[\"spot\"],Invisible:1b,ArmorItems:[{},{},{},{id:\"player_head\",Count:1b,tag:{SkullOwner:{Id:[I;2025786527,510413711,-1233390505,38966179],Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU2NmRkMmU0YzJlMGU4N2I0ZmQ3N2U5MDY1YTEwYTY1YWMyMmMxNmU5Zjg2ODFlYzc1YjE5ODE3N2Y2OWYzNSJ9fX0=\"}]}}}}],NoGravity:1}");

		for(String command : commands) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run " + command);
		}

		BottomLaser.setup();
		
	}

}
