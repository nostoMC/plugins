package fr.nostoNC.customConsumables.types;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.nostoNC.Main;
import fr.nostoNC.customConsumables.Consumable;
import fr.nostoNC.customConsumables.EffectRunnable;
import org.jetbrains.annotations.Nullable;

public class ItemConsumable implements Consumable {

    private final ItemStack item;
    private final EffectRunnable effectRunnable;

    public ItemConsumable(ItemStack item, String id, @Nullable EffectRunnable effectRunnable) {
        this.item = item;
        this.effectRunnable = effectRunnable;

        ItemMeta meta = item.getItemMeta();

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
        if (effectRunnable != null) effectRunnable.run(player);
    }
}
