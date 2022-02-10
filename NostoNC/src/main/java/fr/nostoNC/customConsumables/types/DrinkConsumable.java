package fr.nostoNC.customConsumables.types;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.nostoNC.Main;
import fr.nostoNC.customConsumables.Consumable;
import fr.nostoNC.customConsumables.EffectRunnable;

public class DrinkConsumable implements Consumable {

    private final ItemStack potion;
    private final EffectRunnable effectRunnable;

    @SuppressWarnings("deprecation")
    public DrinkConsumable(String drinkName, int color, EffectRunnable effectRunnable) {
        this.effectRunnable = effectRunnable;

        potion = new ItemStack(Material.POTION);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();

        meta.setDisplayName("§b" + drinkName);
        meta.setColor(Color.fromRGB(color));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(new NamespacedKey(Main.getInstance(), "consumableId"), PersistentDataType.STRING, drinkName.toLowerCase().replace(" ", ""));

        potion.setItemMeta(meta);
    }

    @Override
    public ItemStack getItem() {
        return potion;
    }

    @Override
    public void onConsume(Player player) {
        effectRunnable.run(player);
    }
}
