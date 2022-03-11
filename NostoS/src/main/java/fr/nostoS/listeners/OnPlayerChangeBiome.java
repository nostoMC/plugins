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
        String result;
        result = switch (biome) {
            case BADLANDS, ERODED_BADLANDS -> "§6Messa";
            case BAMBOO_JUNGLE -> "§aJungle de bamboo";
            case BASALT_DELTAS -> "§8Bastion";
            case BEACH -> "§ePlage";
            case BIRCH_FOREST -> "§fForêt de bouleux";
            case COLD_OCEAN -> "§bOcéan froid";
            case CRIMSON_FOREST -> "§cForêt Carmin";
            case DARK_FOREST -> "§8Forêt sombre";
            case DEEP_COLD_OCEAN -> "§1Océan froid profond";
            case DEEP_FROZEN_OCEAN -> "§3Océan gelé profond";
            case DEEP_LUKEWARM_OCEAN -> "§bOcéan tiède profond";
            case DEEP_OCEAN -> "§9Océan profond";
            case DESERT -> "§eDésert";
         // case DRIPSTONE_CAVES -> "§7Grottes";
            case END_BARRENS, END_HIGHLANDS, END_MIDLANDS -> "§5???";
            case FLOWER_FOREST -> "§2Forêt de fleurs";
            case FOREST -> "§2Forêt";
            case FROZEN_OCEAN -> "§bOcéan gelé";
         // case FROZEN_PEAKS -> "§fPointes gelées";
            case FROZEN_RIVER -> "§bRivière gelée";
         // case GROVE -> "§fMontagne";
            case ICE_SPIKES -> "§bStalagmites de glace";
         // case JAGGED_PEAKS -> "§f???";
            case JUNGLE -> "§2Jungle";
            case LUKEWARM_OCEAN -> "§bOcéan tiède";
         // case LUSH_CAVES -> "§6Grotte luxuriante";
         // case MEADOW -> "§fMontagne";
            case MUSHROOM_FIELDS -> "§cÎle champignon";
            case NETHER_WASTES -> "§cNether";
            case OCEAN -> "§9Océan";
            case PLAINS -> "§aPlaine";
            case RIVER -> "§3Rivière";
            case SAVANNA, SAVANNA_PLATEAU -> "§2Savane";
            case SMALL_END_ISLANDS -> "§5Petite île de l'end";
            case SNOWY_BEACH -> "§fPlage enneigée";
         // case SNOWY_PLAINS -> "§fPlaine enneigée";
         // case SNOWY_SLOPES -> "§fPente enneigée";
            case SNOWY_TAIGA -> "§fTaïga enneigée";
            case SOUL_SAND_VALLEY -> "§4Vallée des âmes";
         // case SPARSE_JUNGLE -> "§2Jungle";
         // case STONY_PEAKS -> "§7Pique de roche";
         // case STONY_SHORE -> "§7Plage rocheuse";
            case SUNFLOWER_PLAINS -> "§ePlaine de tournesols";
            case SWAMP -> "§2Maraicage";
            case TAIGA -> "§2Taïga";
            case THE_END -> "§5L'end";
            case THE_VOID -> "§0Le vide";
            case WARM_OCEAN -> "§bOcéan chaud";
            case WARPED_FOREST -> "§aForêt Crimson";
         // case WINDSWEPT_FOREST, WINDSWEPT_GRAVELLY_HILLS, WINDSWEPT_HILLS, WINDSWEPT_SAVANNA -> "§7Colline venteuses";
         // case WOODED_BADLANDS - > "§6Plateau de messa boisées";
            default -> "§7???";
        };
        return result;
    }
}
