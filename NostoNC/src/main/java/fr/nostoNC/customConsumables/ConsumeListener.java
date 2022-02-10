package fr.nostoNC.customConsumables;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;

public class ConsumeListener implements Listener {

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        if (!Utils.isInClub(player)) return;

        ItemMeta meta = event.getItem().getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        String consumableId = data.get(new NamespacedKey(Main.getInstance(), "consumableId"), PersistentDataType.STRING);
        if (consumableId == null) return;

        Consumable consumable = Products.products.get(consumableId);
        if (consumable == null) return;
        
        consumable.onConsume(player);
    }
}
