package kr.kro.bukgeukwild;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class ConnectEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(ChatColor.GRAY + p.getName() + "님이 접속하셨습니다!");

        String filename = BukgeukWild.Dir + File.separator + "Users" + File.separator + p.getUniqueId().toString() + ".udat";
        File file = new File(filename);

        if (!file.exists()) {
            UserData data = new UserData(p.getName(), p.getUniqueId());
            FileIO.Write(data);
            Map.Data.put(p.getUniqueId(), data);
            p.getInventory().addItem(GuideBook.getGuideBook());
        } else {
            UserData data = FileIO.Read(p.getUniqueId());
            if (data != null) Map.Data.put(p.getUniqueId(), data);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(ChatColor.GRAY + p.getName() + "님이 서버를 나가셨습니다.");

        FileIO.Write(Map.Data.get(p.getUniqueId()));
    }
}
