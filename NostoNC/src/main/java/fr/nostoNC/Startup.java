package fr.nostoNC;

import fr.nostoNC.tasks.BottomLaser;
import fr.nostoNC.tasks.ElevatorTask;
import fr.nostoNC.tasks.GolemPass;
import fr.nostoNC.tasks.effects.FloorSmokeEffect;
import fr.nostoNC.tasks.effects.StrobeEffect;

public class Startup {

	public static void startup(Main main) {

		Main.activeEffects.put("floorSmoke", false);
		Main.activeEffects.put("strobe", false);

		FloorSmokeEffect.init(main);
		StrobeEffect.init(main);

		GolemPass.init(main);
		ElevatorTask.init(main);

		BottomLaser.setup();
		
	}

}
