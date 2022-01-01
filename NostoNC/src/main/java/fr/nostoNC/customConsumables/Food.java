package fr.nostoNC.customConsumables;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.nostoNC.Main;

public class Food implements Consumable {

    private final ItemStack item;
    private final EffectRunnable effectRunnable;

    public Food(String foodName, String id, int customModelData, EffectRunnable effectRunnable) {
        this.effectRunnable = effectRunnable;

        item = new ItemStack(Material.GOLDEN_APPLE);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("§b" + foodName);
        meta.setCustomModelData(customModelData);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(new NamespacedKey(Main.instance, "consumableId"), PersistentDataType.STRING, id);

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
