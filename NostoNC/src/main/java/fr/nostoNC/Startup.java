package fr.nostoNC;

import fr.nostoNC.tasks.BottomLaser;
import fr.nostoNC.tasks.StaffPass;
import fr.nostoNC.tasks.VIPpass;
import fr.nostoNC.tasks.effects.FloorSmokeEffect;
import fr.nostoNC.tasks.effects.StrobeEffect;

public class Startup {

	public static void startup(Main main) {

		Main.activeEffects.put("floorSmoke", false);
		Main.activeEffects.put("strobe", false);

		new FloorSmokeEffect(main);
		new StrobeEffect(main);

		new VIPpass(main);
		new StaffPass(main);

		BottomLaser.setup();
		
	}

}
