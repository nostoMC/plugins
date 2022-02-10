package fr.nostoNC.customConsumables.types;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.nostoNC.Main;
import fr.nostoNC.customConsumables.Consumable;
import fr.nostoNC.customConsumables.EffectRunnable;

public class GappleConsumable implements Consumable {

    private final ItemStack item;
    private final EffectRunnable effectRunnable;

    @SuppressWarnings("deprecation")
    public GappleConsumable(String foodName, String id, int customModelData, EffectRunnable effectRunnable) {
        this.effectRunnable = effectRunnable;

        item = new ItemStack(Material.GOLDEN_APPLE);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§b" + foodName);
        meta.setCustomModelData(customModelData);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(new NamespacedKey(Main.getInstance(), "consumableId"), PersistentDataType.STRING, id);

        item.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void onConsume(Player player) {
        effectRunnable.run(player);
    }
}
