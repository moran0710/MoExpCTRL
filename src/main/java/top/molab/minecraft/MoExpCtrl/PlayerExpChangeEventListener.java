package top.molab.minecraft.MoExpCtrl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerExpChangeEventListener implements Listener {
    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        Group group = RuntimeDataManager.getInstance().getPlayerGroup(player);
        if (group != null) {
            int exp = (int) (event.getAmount() * group.exp());
            event.setAmount(exp);
        }
    }
}
