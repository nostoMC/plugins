package fr.nostoNC.customConsumables;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Consumable {

    ItemStack getItem();

    void onConsume(Player player);
    
}
