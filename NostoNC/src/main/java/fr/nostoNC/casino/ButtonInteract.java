package fr.nostoNC.casino;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;

public class ButtonInteract implements Listener {

    private static final ArrayList<Vector> slotMachine = new ArrayList<>();

    public ButtonInteract() {

        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());

        slotMachine.add(new Vector(-1, 110, 199));
        slotMachine.add(new Vector(-1, 110, 197));
        slotMachine.add(new Vector(-1, 110, 195));
        slotMachine.add(new Vector(-1, 110, 193));

        slotMachine.add(new Vector(-3, 110, 199));
        slotMachine.add(new Vector(-3, 110, 197));
        slotMachine.add(new Vector(-3, 110, 195));
        slotMachine.add(new Vector(-3, 110, 193));

        slotMachine.add(new Vector(-9, 110, 200));
        slotMachine.add(new Vector(-9, 110, 198));
        slotMachine.add(new Vector(-9, 110, 196));

        slotMachine.add(new Vector(-11, 110, 200));
        slotMachine.add(new Vector(-11, 110, 198));
        slotMachine.add(new Vector(-11, 110, 196));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            assert block != null;
            if (block.getWorld() != Utils.getDefaultWorld()) return;
            if (block.getType() == Material.POLISHED_BLACKSTONE_BUTTON) {
                if (slotMachine.contains(new Vector(block.getX(), block.getY(), block.getZ()))) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage("\n§eVous démarez une machine !");
                }
            }
        }
    }

}
