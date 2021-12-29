package fr.nostoNC.tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class BottomLaser {
	
	public static int affichageTimer = 20;
	
	private static final Location defaultLoc = new Location(Main.defaultWorld, -1.99, 101.0, 155.5);
	private static final int duration = -1;
	private static final int distance = -1;
	
	public static Boolean strobe = false;
	public static Boolean alternance = false;
	
	public static final List<Laser> all = new ArrayList<>();
	
	public static final Set<Laser> group1 = new HashSet<>();
	public static final Set<Laser> group2 = new HashSet<>();
	public static final Set<Laser> group3 = new HashSet<>();
	public static final Set<Laser> group4 = new HashSet<>();
	
	public static void setup() {
		
		addLaser(13.5, 147.5);
		addLaser(15.5, 153.5);
		addLaser(17.5, 160.5);
		addLaser(16.5, 168.5);
		addLaser(12.5, 172.5);
		addLaser(6.5, 170.5);
		addLaser(4.5, 167.5);
		addLaser(0.5, 165.5);
		addLaser(-4.5, 165.5);
		addLaser(-8.5, 167.5);
		addLaser(-10.5, 170.5);
		addLaser(-13.5, 172.5);
		addLaser(-19.5, 171.5);
		addLaser(-18.5, 165.5);
		addLaser(-16.5, 160.5);
		addLaser(-18.5, 154.5);

		group1.add(all.get(3));
		group1.add(all.get(11));
		group1.add(all.get(7));
		group1.add(all.get(15));
		
		group2.add(all.get(4));
		group2.add(all.get(0));
		group2.add(all.get(13));
		group2.add(all.get(2));
		
		group3.add(all.get(12));
		group3.add(all.get(1));
		group3.add(all.get(6));
		group3.add(all.get(10));
		
		group4.add(all.get(14));
		group4.add(all.get(7));
		group4.add(all.get(9));
		group4.add(all.get(8));
		
		new BukkitRunnable() {
			
			int t = 0;
			Boolean show = false;
			
			@Override
			public void run() {

				if(strobe) {
					
					if(t >= affichageTimer) {
						
						if(show) {
							showAll();
							show = false;
						} else {
							hideAll();
							show = true;
						}
						
						t = 0;
						
					}
					
					t++;
					
				}
				
			}
		}.runTaskTimer(Main.instance, 0, 1);
		
		new BukkitRunnable() {
			
			int t = 0;
			int stade = 1;
			
			@Override
			public void run() {

				if(alternance) {
					
					if(t >= affichageTimer) {
						
						if(stade == 1) {
							hideGroup(group3);
							showGroup(group1);
							stade = 2;
						} else if(stade == 2) {
							hideGroup(group1);
							showGroup(group2);
							stade = 3;
						} else {
							hideGroup(group2);
							showGroup(group3);
							stade = 1;
						}
						
						t = 0;
						
					}
					
					t++;
					
				}
				
			}
		}.runTaskTimer(Main.instance, 0, 1);
		
	}
	
	public static void showAll() {
		
		for(Laser laser : all) {
			
			try {
				laser.start(Main.instance);
			} catch (IllegalArgumentException ignored) {
			}
			
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
		
		for(Laser laser : all) {
			
			Location loc = laser.getStart();
			double x = loc.getX();
			double y = loc.getY() - 6.5;
			double z = loc.getZ();
			try {
				laser.moveEnd(new Location(Main.defaultWorld, x, y, z));
			} catch (ReflectiveOperationException ignored) {
			}

		}
		
	}
	
	public static void moveToUp() {
		
		for(Laser laser : all) {

			try {
				laser.moveEnd(new Location(Main.defaultWorld, -2.0, 101.0, 154.5));
			} catch (ReflectiveOperationException ignored) {
			}

		}
		
	}

	private static void addLaser(double x, double z) {
		try {
			all.add(new Laser.GuardianLaser(new Location(Main.defaultWorld, x, 107.0, z), defaultLoc, duration, distance));
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

}
