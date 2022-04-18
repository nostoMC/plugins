package fr.nostoNC.tasks.effects;

import java.util.*;

import fr.nostoNC.Utils;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.skytasul.guardianbeam.Laser;
import org.javatuples.Pair;

public class TopLaser {

	private static final Random random = new Random();

	private static final int duration = -1;
	private static final int distance = -1;

	public static String move = "down";
	public static int moveTiming = 10;
	public static Boolean alternance = false;
	public static int timing = 10;

	public static final LinkedHashMap<Laser, Pair<Location, Boolean>> all = new LinkedHashMap<>();
	
	public static void init() {
		
		addLaser(5.5, 150.5);
		addLaser(5.5, 154.5);
		addLaser(5.5, 158.5);
		addLaser(5.5, 162.5);
		addLaser(5.5, 166.5);
		addLaser(-2.5, 154.5);
		addLaser(-2.5, 158.5);
		addLaser(-2.5, 162.5);
		addLaser(-10.5, 150.5);
		addLaser(-10.5, 154.5);
		addLaser(-10.5, 158.5);
		addLaser(-10.5, 162.5);
		addLaser(-10.5, 166.5);

		new BukkitRunnable() {
			int t, j = 0;
			@Override
			public void run() {
				if (t >= timing) {
					t = 0;
					if (alternance) {
						all.replaceAll((l, v) -> all.get(l).setAt1(new Random().nextBoolean()));
					}
				}
				t++;

				if (j >= moveTiming) {
					j = 0;
					if (move.equalsIgnoreCase("random")) {
						for (Laser laser : all.keySet()) {
							Pair<Location, Boolean> info = new Pair<>(new Location(Utils.getDefaultWorld(), random.nextInt(41) - 25, 100, random.nextInt(35) + 144),
									all.get(laser).getValue1());
							all.put(laser, info);
							try {
								updateLaser(laser);
							} catch (ReflectiveOperationException e) { e.printStackTrace(); }
						}
					}
				}
				j++;

				for (Laser laser : all.keySet()) {

					Location loc = laser.getStart();
					Pair<Location, Boolean> info = new Pair<>(new Location(Utils.getDefaultWorld(), loc.getX(), 100, loc.getZ()),
							all.get(laser).getValue1());

					if (move.equalsIgnoreCase("down")) {
						all.put(laser, info);
						try {
							updateLaser(laser);
						} catch (ReflectiveOperationException e) { e.printStackTrace(); }
					}
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, 1);

	}

	private static void addLaser(double x, double z) {
		try {
			Location loc = new Location(Utils.getDefaultWorld(), x, 114.0, z);
			Pair<Location, Boolean> info = new Pair<>(loc, true);
			Laser laser = new Laser.GuardianLaser(loc, loc, duration, distance);
			all.put(laser, info);
			laser.start(Main.getInstance());
		} catch (ReflectiveOperationException e) { e.printStackTrace(); }
	}

	private static void updateLaser(Laser laser) throws ReflectiveOperationException {

		Pair<Location, Boolean> info = all.get(laser);

		if (info.getValue1())
			laser.moveEnd(new Location(Utils.getDefaultWorld(), laser.getStart().getX(), laser.getStart().getY() - 0.1, laser.getStart().getZ()));
		else
			laser.moveEnd(info.getValue0());

	}

	public static void showOrHideAll(Boolean hide) {
		alternance = false;
		for (Laser laser : all.keySet()) {
			Pair<Location, Boolean> info = all.get(laser).setAt1(hide);
			all.put(laser, info);
		}
	}

	public static void alternance() { alternance = true; }
	
	public static void moveDown() { move = "down"; }

	public static void moveRandom() { move = "random"; }

}
