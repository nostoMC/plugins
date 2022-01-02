package fr.nostoNC.customConsumables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.nostoNC.customConsumables.types.DrinkConsumable;
import fr.nostoNC.customConsumables.types.GappleConsumable;
import fr.nostoNC.customConsumables.types.ItemConsumable;

public class Products {

    public static final Map<String, Consumable> products = new HashMap<>();

    static {
        // **** DRINKS ****

        products.put("calvados", new DrinkConsumable("Calvados", 0x943f00, player -> {
            PotionEffect effect = new PotionEffect(PotionEffectType.CONFUSION, 160, 0, false, false, false);
            player.addPotionEffect(effect);
        }));

        products.put("gin", new DrinkConsumable("Gin", 0xf0e4a3, player -> {
            int duration = ((int) (Math.random() * 20)) + 10; // durée random entre 10 et 30 sec
            duration *= 20; // conversion: secondes => ticks

            PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, duration, 0, false, false, false);
            PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, (int) (duration * .8), 0, false, false, false);

            player.addPotionEffects(List.of(speed, blindness));
        }));

        products.put("jet27", new DrinkConsumable("Jet 27", 0xe9e00, player -> {
            int duration = ((int) (Math.random() * 20)) + 10; // durée random entre 10 et 30 sec
            duration *= 20; // conversion: secondes => ticks

            PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, duration, 1, false, false, false);
            PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, (int) (duration * .9), 0, false, false, false);

            player.addPotionEffects(List.of(speed, blindness));
        }));

        products.put("rhum", new DrinkConsumable("Rhum", 0xa8905d, player -> {
            int duration = 300; // 15 sec

            PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, duration, 1, false, false, false);
            PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, duration, 0, false, false, false);

            player.addPotionEffects(List.of(slowness, blindness));
        }));

        products.put("vodka", new DrinkConsumable("Vodka", 0xffffff, player -> {
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
        }));

        // **** GAPPLE FOOD ****

        products.put("menthe", new GappleConsumable("Menthe", "menthe", 1, player -> {}));
        products.put("double", new GappleConsumable("Double menthe" ,"double", 2, player -> {}));
        products.put("bicarbonate", new GappleConsumable("Bicarbonate de soude", "bicarbonate", 3, player -> {}));

        // **** FOOD ****

        products.put("cookie", new ItemConsumable(Utils.createItem(Material.COOKIE, "§rCookie"), "cookie", null));
        products.put("honey", new ItemConsumable(Utils.createItem(Material.HONEY_BOTTLE, "§rJus d'orange"), "honey", null));
    }
}
