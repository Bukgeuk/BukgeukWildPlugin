package kr.kro.bukgeukwild;

import com.google.gson.Gson;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BukgeukWild extends JavaPlugin implements CommandExecutor {
    public static String Dir;
    public static final Runnable addPlayTimeTask = new Runnable() {
        @Override
        public void run() {
            for (UUID key : Map.Data.keySet()) {
                Map.Data.get(key).addPlayTime(10);
            }
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "접속 시간 점수 +10");
            });
        }
    };
    public static ScheduledExecutorService addPlayTimeService;

    public void onEnable() {
        Dir = getDataFolder().getAbsolutePath();

        if (!getDataFolder().exists()) getDataFolder().mkdir();
        File folder = new File(Dir + File.separator + "Users/");
        if (!folder.exists()) folder.mkdir();

        Map.Data = FileIO.ReadAll();

        getServer().getPluginManager().registerEvents(new ConnectEvent(), this);
        getServer().getPluginManager().registerEvents(new PointEvent(), this);
        getCommand("ranking").setExecutor(new RankingCommands());
        getCommand("dev").setExecutor(this);

        addPlayTimeService = Executors.newSingleThreadScheduledExecutor();
        addPlayTimeService.scheduleAtFixedRate(addPlayTimeTask, 0, 10, TimeUnit.MINUTES);
    }

    public void onDisable() {
        FileIO.WriteAll();
    }

    public void Disable() {
        FileIO.WriteAll();
        Bukkit.getPluginManager().disablePlugin(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if(!p.isOp()) {
                p.sendMessage(ChatColor.RED + "[System] 권한이 부족합니다.");
                return false;
            }
        }

        if (args.length > 0) {
            switch (args[0]) {
                case "disable":
                    Disable();
                    break;
                case "map":
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(Map.Data);
                    sender.sendMessage(jsonStr);
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "[System] Usage : /dev [ disable | map ]");
                    break;
            }
        } else sender.sendMessage(ChatColor.RED + "[System] Usage : /dev [ disable | map ]");
        return false;
    }
}