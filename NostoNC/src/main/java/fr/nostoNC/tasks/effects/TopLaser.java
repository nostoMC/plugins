package fr.nostoNC.tasks.effects;

import java.util.*;

import fr.nostoNC.Utils;
import fr.nostoNC.tasks.Laser;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class TopLaser {

	private static final Random random = new Random();
	
	public static int affichageTimer = 5;

	private static final int duration = -1;
	private static final int distance = -1;

	public static Boolean alternance = false;

	public static String move = "down";

	public static final List<Laser> all = new ArrayList<>();
	
	public static final Set<Laser> group1 = new HashSet<>();
	public static final Set<Laser> group2 = new HashSet<>();
	public static final Set<Laser> group3 = new HashSet<>();
	public static final Set<Laser> group4 = new HashSet<>();
	
	public static void setup() {
		
		addLaser(6, 150);
		addLaser(6, 154);
		addLaser(6, 158);
		addLaser(6, 162);
		addLaser(6, 166);
		addLaser(-2, 154);
		addLaser(-2, 158);
		addLaser(-2, 162);
		addLaser(-10, 150);
		addLaser(-10, 154);
		addLaser(-10, 158);
		addLaser(-10, 162);
		addLaser(-10, 166);

		group1.add(all.get(3));
		group1.add(all.get(11));
		group1.add(all.get(7));
		
		group2.add(all.get(4));
		group2.add(all.get(0));
		group2.add(all.get(2));
		group2.add(all.get(10));
		
		group3.add(all.get(12));
		group3.add(all.get(1));
		group3.add(all.get(6));

		group4.add(all.get(7));
		group4.add(all.get(9));
		group4.add(all.get(8));
		group4.add(all.get(5));
		
		new BukkitRunnable() {
			int t = 0;
			int stade = 1;
			@Override
			public void run() {
				if(alternance) {
					if(t >= affichageTimer) {
						if(stade == 1) {
							hideGroup(group4);
							showGroup(group1);
							stade = 2;
						} else if(stade == 2) {
							hideGroup(group1);
							showGroup(group2);
							stade = 3;
						} else if(stade == 3) {
							hideGroup(group2);
							showGroup(group3);
							stade = 4;
						} else {
							hideGroup(group3);
							showGroup(group4);
							stade = 1;
						}
						t = 0;
					}
					t++;
				}
			}
		}.runTaskTimer(Main.instance, 0, 1);

		new BukkitRunnable() {
			@Override
			public void run() {
				if (move.equalsIgnoreCase("random")) {
					for (Laser laser : all) {
						laser.moveEnd(new Location(Utils.defaultWorld, random.nextInt(41) - 25, 100, random.nextInt(35) + 144), 39, null);
					}
				}
			}
		}.runTaskTimer(Main.instance, 0, 40);

	}
	
	public static void showAll() {
		for(Laser laser : all) {
			try {
				laser.start(Main.instance);
			} catch (IllegalArgumentException ignored) {
			}
			updateMovement(laser);

		}
	}
	
	public static void hideAll() {
		for(Laser laser : all) {
			try {
				laser.stop();
			} catch (IllegalArgumentException ignored) {
			}
		}
	}
	
	public static void showGroup(Set<Laser> group) {
		for(Laser laser : group) {
			try {
				laser.start(Main.instance);
			} catch (IllegalArgumentException ignored) {
			}
			updateMovement(laser);
		}
	}
	
	public static void hideGroup(Set<Laser> group) {
		for(Laser laser : group) {
			try {
				laser.stop();
			} catch (IllegalArgumentException ignored) {
			}
		}
	}
	
	public static void moveToDown() {
		move = "down";
		for(Laser laser : all) {
			updateMovement(laser);
		}
	}

	public static void moveRandom() {
		move = "random";
		for (Laser laser : all) {
			updateMovement(laser);
		}
	}

	private static void addLaser(double x, double z) {
		try {
			all.add(new Laser.GuardianLaser(new Location(Utils.defaultWorld, x, 114.0, z), new Location(Utils.defaultWorld, x, 100, z), duration, distance));
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

	private static void updateMovement(Laser laser) {
		try {
			if ("down".equals(move)) {
				Location loc = laser.getStart();
				double x = loc.getX();
				double z = loc.getZ();
				laser.moveEnd(new Location(Utils.defaultWorld, x, 100, z));
			}
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

}
