package kr.kro.bukgeukwild;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PointEvent implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Entity killer = e.getEntity().getKiller();

        if (killer instanceof Player) {
            e.setDeathMessage(ChatColor.RED + player.getName() + "님이 " + killer.getName() + "님에게 죽었습니다.");

            Map.Data.get(killer.getUniqueId()).addPvpPoint(5);
            killer.sendMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "PVP 점수 +5");
        }
    }
}
