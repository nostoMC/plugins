package fr.nosto.tasks.particles;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.nosto.tasks.CosmeticEffectTask;
import fr.nosto.tasks.particles.effects.FireCrown;
import fr.nosto.tasks.particles.effects.WitchCircle;

public class PlayerTrailsStats {

	public UUID uuid;
	private final Set<CosmeticEffect> unlocked = new HashSet<>();
	private CosmeticEffect equiped;

	public PlayerTrailsStats(Player player) {
		this.uuid = player.getUniqueId();
	}

	public boolean unlock(CosmeticEffect effect) {
		return unlocked.add(effect);
	}

	public boolean equip(CosmeticEffect effect) {
		if (unlocked.contains(effect)) {
			equiped = effect;
			
			Player player = Bukkit.getPlayer(uuid);
			CosmeticEffectRenderer renderer = null;
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
			if (renderer == null) CosmeticEffectTask.effectRenderers.remove(uuid);
			else CosmeticEffectTask.effectRenderers.put(uuid, renderer);

			return true;
		}
		return false;
	}

	public void unequip() {
		equiped = null;
		CosmeticEffectTask.effectRenderers.remove(uuid);
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	public CosmeticEffect getEquiped() {
		return equiped;
	}

	public Set<CosmeticEffect> getUnlocked() {
		return unlocked;
	}

}
