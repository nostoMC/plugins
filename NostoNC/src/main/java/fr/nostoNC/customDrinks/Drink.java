package fr.nostoNC.customDrinks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public enum Drink {

    CALVADOS("Calvados", 0x943f00, player -> {
        PotionEffect effect = new PotionEffect(PotionEffectType.CONFUSION, 160, 0, false, false, false);
        player.addPotionEffect(effect);
    }),
    GIN("Gin", 0xf0e4a3, player -> {
        int duration = ((int) (Math.random() * 20)) + 10; // durée random entre 10 et 30 sec
        duration *= 20; // conversion: secondes => ticks

        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, duration, 0, false, false, false);
        PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, (int) (duration * .8), 0, false, false, false);

        player.addPotionEffects(List.of(speed, blindness));
    }),
    JET27("Jet 27", 0xe9e00, player -> {
        int duration = ((int) (Math.random() * 20)) + 10; // durée random entre 10 et 30 sec
        duration *= 20; // conversion: secondes => ticks

        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, duration, 1, false, false, false);
        PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, (int) (duration * .9), 0, false, false, false);

        player.addPotionEffects(List.of(speed, blindness));
    }),
    RHUM("Rhum", 0xa8905d, player -> {
        int duration = 300; // 15 sec

        PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, duration, 1, false, false, false);
        PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, duration, 0, false, false, false);

        player.addPotionEffects(List.of(slowness, blindness));
    }),
    VODKA("Vodka", 0xffffff, player -> {
        int buffDuration = 800; // 40 sec
        List<PotionEffect> buffs = new ArrayList<>();

        buffs.add(new PotionEffect(PotionEffectType.SPEED, buffDuration, 1, false, false, false));
        buffs.add(new PotionEffect(PotionEffectType.JUMP, buffDuration, 1, false, false, false));
        buffs.add(new PotionEffect(PotionEffectType.HEALTH_BOOST, buffDuration, 200, false, false, false));
        buffs.add(new PotionEffect(PotionEffectType.REGENERATION, buffDuration, 10, false, false, false));
        buffs.add(new PotionEffect(PotionEffectType.ABSORPTION, buffDuration, 200, false, false, false));
        buffs.add(new PotionEffect(PotionEffectType.SATURATION, buffDuration, 0, false, false, false));

        int debuffDuration = 400; // 20 sec
        List<PotionEffect> debuffs = new ArrayList<>();

        debuffs.add(new PotionEffect(PotionEffectType.SLOW, debuffDuration, 1, false, false, false));
        debuffs.add(new PotionEffect(PotionEffectType.HUNGER, debuffDuration, 100, false, false, false));
        debuffs.add(new PotionEffect(PotionEffectType.POISON, debuffDuration, 0, false, false, false));
        debuffs.add(new PotionEffect(PotionEffectType.BLINDNESS, debuffDuration / 2, 0, false, false, false));

        int periodTime = 40; // 2 sec
        int buffDurationInPeriods = buffDuration / periodTime;

        new BukkitRunnable() {

            int period = 0;

            @Override
            public void run() {
                int debuffPeriod = period - buffDurationInPeriods;

                if (period < buffs.size())
                    player.addPotionEffect(buffs.get(period));
                else if (debuffPeriod >= 0 && debuffPeriod < debuffs.size())
                    player.addPotionEffect(debuffs.get(debuffPeriod));

                period++;
            }
        }.runTaskTimer(Main.instance, 0, periodTime);
    });

    public interface EffectRunnable {

        void run(Player player);

    }

    private final ItemStack potion;
    private final EffectRunnable effectRunnable;

    Drink(String drinkName, int color, EffectRunnable effectRunnable) {
        this.effectRunnable = effectRunnable;

        potion = new ItemStack(Material.POTION);

        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        
        meta.setDisplayName("§b" + drinkName);
        meta.setColor(Color.fromRGB(color));
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(new NamespacedKey(Main.instance, "drinkId"), PersistentDataType.STRING, this.name());

        potion.setItemMeta(meta);
    }

    public ItemStack getPotion() {
        return potion;
    }

    public void onDrink(Player player) {
        effectRunnable.run(player);
    }
}
