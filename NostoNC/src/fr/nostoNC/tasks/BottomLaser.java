package fr.nostoNC.tasks;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class BottomLaser {
	
	public static Laser l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16;
	
	public static int affichageTimer = 20;
	
	private static Location defaultLoc = new Location(Main.defaultWorld, -1.99, 4.0, 155.5);
	private static final int duration = -1;
	private static final int distance = -1;
	
	public static Boolean strobe = false;
	public static Boolean alternance = false;
	
	public static Set<Laser> all = new HashSet<>();
	
	public static Set<Laser> group1 = new HashSet<>();
	public static Set<Laser> group2 = new HashSet<>();
	public static Set<Laser> group3 = new HashSet<>();
	public static Set<Laser> group4 = new HashSet<>();
	
	public static void setup() {
		
		try {
			l1 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 13.5, 10.0, 147.5), defaultLoc, duration, distance);
			l2 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 15.5, 10.0, 153.5), defaultLoc, duration, distance);
			l3 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 17.5, 10.0, 160.5), defaultLoc, duration, distance);
			l4 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 16.5, 10.0, 168.5), defaultLoc, duration, distance);
			l5 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 12.5, 10.0, 172.5), defaultLoc, duration, distance);
			l6 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 6.5, 10.0, 170.5), defaultLoc, duration, distance);
			l7 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 4.5, 10.0, 167.5), defaultLoc, duration, distance);
			l8 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 0.5, 10.0, 165.5), defaultLoc, duration, distance);
			l9 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -4.5, 10.0, 165.5), defaultLoc, duration, distance);
			l10 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -8.5, 10.0, 167.5), defaultLoc, duration, distance);
			l11 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -10.5, 10.0, 170.5), defaultLoc, duration, distance);
			l12 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -13.5, 10.0, 172.5), defaultLoc, duration, distance);
			l13 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -19.5, 10.0, 171.5), defaultLoc, duration, distance);
			l14 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -18.5, 10.0, 165.5), defaultLoc, duration, distance);
			l15 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -16.5, 10.0, 160.5), defaultLoc, duration, distance);
			l16 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -18.5, 10.0, 154.5), defaultLoc, duration, distance);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		
		all.add(l1);
		all.add(l2);
		all.add(l3);
		all.add(l4);
		all.add(l5);
		all.add(l6);
		all.add(l7);
		all.add(l8);
		all.add(l9);
		all.add(l10);
		all.add(l11);
		all.add(l12);
		all.add(l13);
		all.add(l14);
		all.add(l15);
		all.add(l16);
		
		group1.add(l4);
		group1.add(l12);
		group1.add(l6);
		group1.add(l16);
		group2.add(l5);
		group2.add(l1);
		group2.add(l14);
		group2.add(l3);
		group3.add(l13);
		group3.add(l2);
		group3.add(l7);
		group3.add(l11);
		group4.add(l15);
		group4.add(l8);
		group4.add(l10);
		group4.add(l9);
		
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
			} catch (IllegalArgumentException e) {
			}
			
		}
		
	}
	
	public static void hideAll() {
		
		for(Laser laser : all) {
		
			try {
				laser.stop();
			} catch (IllegalArgumentException e) {
			}
		
		}
		
	}
	
	public static void showGroup(Set<Laser> group) {
		
		for(Laser laser : group) {
		
			try {
				laser.start(Main.instance);
			} catch (IllegalArgumentException e) {
			}
		
		}
		
	}
	
	public static void hideGroup(Set<Laser> group) {
		
		for(Laser laser : group) {
		
			try {
				laser.stop();
			} catch (IllegalArgumentException e) {
			}
		
		}
		
	}
	
	public static void moveToDown() {
		
		for(Laser laser : all) {
			
			Location loc = laser.getEnd();
			double x = loc.getX();
			double y = loc.getY() - 8;
			double z = loc.getZ();
			if(laser.isStarted()) laser.moveEnd(new Location(Main.defaultWorld, x, y, z), 10, null);
			
		}
		
	}
	
	public static void moveToUp() {
		
		for(Laser laser : all) {
			
			if(laser.isStarted()) laser.moveEnd(new Location(Main.defaultWorld, -18.5, 10.0, 154.5), 10, null);
			
		}
		
	}

}
