package fr.nostoS.listeners;

import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.kyori.adventure.text.Component;

import fr.nostoS.Utils;

public class OnPlayerChangeBiome implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        if (!Utils.getSurviesWorlds().contains(event.getPlayer().getWorld())) return;

        Biome fromBiome = event.getFrom().getBlock().getBiome();
        Biome toBiome = event.getTo().getBlock().getBiome();

        if (fromBiome != toBiome) event.getPlayer().sendActionBar(Component.text(getBiomeName(toBiome)));

    }

    private static String getBiomeName(Biome biome) {
        String result = "§7???";
        switch (biome) {
            case BADLANDS, ERODED_BADLANDS -> result = "§6Messa";
            case BAMBOO_JUNGLE -> result = "§aJungle de bamboo";
            case BASALT_DELTAS -> result = "§8Bastion";
            case BEACH -> result = "§ePlage";
            case BIRCH_FOREST -> result = "§fForêt de bouleux";
            case COLD_OCEAN -> result = "§bOcéan froid";
            case CRIMSON_FOREST -> result = "§cForêt Carmin";
            case DARK_FOREST -> result = "§8Forêt sombre";
            case DEEP_COLD_OCEAN -> result = "§1Océan froid profond";
            case DEEP_FROZEN_OCEAN -> result = "§3Océan gelé profond";
            case DEEP_LUKEWARM_OCEAN -> result = "§bOcéan tiède profond";
            case DEEP_OCEAN -> result = "§9Océan profond";
            case DESERT -> result = "§eDésert";
         // case DRIPSTONE_CAVES -> result = "§7Grottes";
            case END_BARRENS, END_HIGHLANDS, END_MIDLANDS -> result = "§5???";
            case FLOWER_FOREST -> result = "§2Forêt de fleurs";
            case FOREST -> result = "§2Forêt";
            case FROZEN_OCEAN -> result = "§bOcéan gelé";
         // case FROZEN_PEAKS -> result = "§fPointes gelées";
            case FROZEN_RIVER -> result = "§bRivière gelée";
         // case GROVE -> "§fMontagne";
            case ICE_SPIKES -> result = "§bStalagmites de glace";
         // case JAGGED_PEAKS -> result = "§f???";
            case JUNGLE -> result = "§2Jungle";
            case LUKEWARM_OCEAN -> result = "§bOcéan tiède";
         // case LUSH_CAVES -> result = "§6Grotte luxuriante";
         // case MEADOW -> result = "§fMontagne";
            case MUSHROOM_FIELDS -> result = "§cÎle champignon";
            case NETHER_WASTES -> result = "§cNether";
            case OCEAN -> result = "§9Océan";
            case PLAINS -> result = "§aPlaine";
            case RIVER -> result = "§3Rivière";
            case SAVANNA, SAVANNA_PLATEAU -> result = "§2Savane";
            case SMALL_END_ISLANDS -> result = "§5Petite île de l'end";
            case SNOWY_BEACH -> result = "§fPlage enneigée";
         // case SNOWY_PLAINS -> result = "§fPlaine enneigée";
         // case SNOWY_SLOPES -> result = "§fPente enneigée";
            case SNOWY_TAIGA -> result = "§fTaïga enneigée";
            case SOUL_SAND_VALLEY -> result = "§4Vallée des âmes";
         // case SPARSE_JUNGLE -> result = "§2Jungle";
         // case STONY_PEAKS -> result = "§7Pique de roche";
         // case STONY_SHORE -> result = "§7Plage rocheuse";
            case SUNFLOWER_PLAINS -> result = "§ePlaine de tournesols";
            case SWAMP -> result = "§2Maraicage";
            case TAIGA -> result = "§2Taïga";
            case THE_END -> result = "§5L'end";
            case THE_VOID -> result = "§0Le vide";
            case WARM_OCEAN -> result = "§bOcéan chaud";
            case WARPED_FOREST -> result = "§aForêt Crimson";
         // case WINDSWEPT_FOREST, WINDSWEPT_GRAVELLY_HILLS, WINDSWEPT_HILLS, WINDSWEPT_SAVANNA -> result = "§7Colline venteuses";
         // case WOODED_BADLANDS - > result = "§6Plateau de messa boisées";
        }
        return result;
    }
}
