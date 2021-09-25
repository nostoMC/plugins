package fr.nosto.tasks.particles;

public enum SmallEffect {
	FROST_WALKER(0), FLAMES(0), FIREWORKS(0);

	public int price;

	private SmallEffect(int price) {
		this.price = price;
	}
}
