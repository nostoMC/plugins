package fr.nostoNC;

import fr.nostoNC.tasks.BottomLaser;
import fr.nostoNC.tasks.GolemPass;
import fr.nostoNC.tasks.effects.FloorSmokeEffect;
import fr.nostoNC.tasks.effects.StrobeEffect;

public class Startup {

	public static void startup(Main main) {

		Main.activeEffects.put("floorSmoke", false);
		Main.activeEffects.put("strobe", false);

		new FloorSmokeEffect(main);
		new StrobeEffect(main);

		GolemPass.init(main);

		BottomLaser.setup();
		
	}

}
