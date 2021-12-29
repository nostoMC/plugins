package fr.nostoNC.customDrinks;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.nostoNC.Main;

public class DrinkListener implements Listener {

    @EventHandler
    public void onDrink(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        if (!(event.getItem().getItemMeta() instanceof PotionMeta meta)) return;

        PersistentDataContainer data = meta.getPersistentDataContainer();

        String drinkId = data.get(new NamespacedKey(Main.instance, "drinkId"), PersistentDataType.STRING);
        if (drinkId == null) return;

        Drink drink = Drink.valueOf(drinkId);
        drink.onDrink(player);
    }
}
