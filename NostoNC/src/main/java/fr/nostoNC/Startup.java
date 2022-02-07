package fr.nostoNC;

import fr.nostoNC.tasks.*;
import fr.nostoNC.tasks.effects.*;

public class Startup {

	public static void startup(Main main) {

		Utils.activeEffects.put("floorSmoke", false);
		Utils.activeEffects.put("strobe", false);

		FloorSmokeEffect.init(main);
		StrobeEffect.init(main);

		GolemPass.init(main);
		ElevatorTask.init(main);

		TopLaser.setup();
		WallLaser.setup();
		GlowingLamp.setup();
		
	}

}
