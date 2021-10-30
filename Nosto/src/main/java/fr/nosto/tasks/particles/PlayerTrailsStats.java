package fr.nosto.tasks.particles;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.nosto.tasks.ParticleEffectTask;
import fr.nosto.tasks.particles.big.FireCrown;
import fr.nosto.tasks.particles.big.WitchCircle;
import fr.nosto.tasks.particles.small.Fireworks;
import fr.nosto.tasks.particles.small.Flames;
import fr.nosto.tasks.particles.small.FrostWalker;

public class PlayerTrailsStats {

	public UUID uuid;
	private final Set<BigEffect> unlockedBig = new HashSet<>();
	private final Set<SmallEffect> unlockedSmall = new HashSet<>();
	private BigEffect equipedBig;
	private SmallEffect equipedSmall;

	public PlayerTrailsStats(Player player) {
		this.uuid = player.getUniqueId();
	}

	public boolean unlock(BigEffect effect) {
		return unlockedBig.add(effect);
	}

	public boolean unlock(SmallEffect effect) {
		return unlockedSmall.add(effect);
	}

	public boolean lock(BigEffect effect) {
		return unlockedBig.remove(effect);
	}

	public boolean lock(SmallEffect effect) {
		return unlockedSmall.remove(effect);
	}

	public boolean equip(BigEffect effect) {
		if (unlockedBig.contains(effect)) {
			equipedBig = effect;
			
			Player player = Bukkit.getPlayer(uuid);
			BigEffectRenderer renderer = null;
			switch (effect) {
			case FIREWORK_CAPE:
				break;
			case FIRE_CROWN:
				renderer = new FireCrown(player);
				break;
			case WITCH_CIRCLE:
				renderer = new WitchCircle(player);
				break;
			}
			if (renderer == null)
				ParticleEffectTask.bigEffects.remove(uuid);
			else
				ParticleEffectTask.bigEffects.put(uuid, renderer);

			return true;
		}
		return false;
	}

	public boolean equip(SmallEffect effect) {
		if (unlockedSmall.contains(effect)) {
			equipedSmall = effect;

			Player player = Bukkit.getPlayer(uuid);
			SmallEffectRenderer renderer = null;
			switch (effect) {
			case FIREWORKS:
				renderer = new Fireworks(player);
				break;
			case FLAMES:
				renderer = new Flames(player);
				break;
			case FROST_WALKER:
				renderer = new FrostWalker(player);
				break;
			}
			if (renderer == null)
				ParticleEffectTask.smallEffects.remove(uuid);
			else
				ParticleEffectTask.smallEffects.put(uuid, renderer);

			return true;
		}
		return false;
	}

	public void unequipBig() {
		equipedBig = null;
		ParticleEffectTask.bigEffects.remove(uuid);
	}

	public void unequipSmall() {
		equipedSmall = null;
		ParticleEffectTask.smallEffects.remove(uuid);
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	public BigEffect getEquipedBig() {
		return equipedBig;
	}

	public SmallEffect getEquipedSmall() {
		return equipedSmall;
	}

	public Set<BigEffect> getUnlockedBig() {
		return unlockedBig;
	}

	public Set<SmallEffect> getUnlockedSmall() {
		return unlockedSmall;
	}

}
