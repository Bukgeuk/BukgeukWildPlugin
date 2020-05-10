package kr.kro.bukgeukwild;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class RankingCommands implements CommandExecutor {
    public static DecimalFormat formatter = new DecimalFormat("###,###");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length > 0) {
                String arg1 = args[0].toLowerCase();
                switch (arg1) {
                    case "playtime":
                        PlayTime(p);
                        break;
                    case "wild":
                        Wild(p);
                        break;
                    case "pvp":
                        PVP(p);
                        break;
                    default:
                        for (UUID key : Map.Data.keySet()) {
                            if (Map.Data.get(key).getNickname().equals(args[0])) {
                                p.sendMessage("[System] " + ChatColor.BOLD + args[0] + "님의 점수\n" +
                                        ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "접속 시간" + ChatColor.RESET + " " + Map.Data.get(key).getPlayTime() +
                                        ChatColor.DARK_GREEN + ChatColor.BOLD + "\n야생 점수" + ChatColor.RESET + " " + Map.Data.get(key).getWildPoint() +
                                        ChatColor.DARK_RED + ChatColor.BOLD + "\nPVP 점수" + ChatColor.RESET + " " + Map.Data.get(key).getPvpPoint());
                                return false;
                            }
                        }
                        p.sendMessage(ChatColor.RED + "[System] " + args[0] + "님을 찾을 수 없습니다");
                }
            } else p.sendMessage(ChatColor.RED + "[System] 사용법 : /ranking [ playtime | wild | pvp | <UserName> ]");
        } else {
            sender.sendMessage("[System] Cannot use this command in console");
        }
        return false;
    }

    private boolean PlayTime(Player player) {
        HashMap<UUID, UserData> list = Map.sortByPlayTime();

        player.sendMessage("");
        player.sendMessage(ChatColor.BOLD + "    [" + ChatColor.RESET.toString() + ChatColor.BOLD.toString() + ChatColor.LIGHT_PURPLE.toString() + "접속" + ChatColor.RESET + ChatColor.BOLD + " 시간 랭킹]");
        player.sendMessage("");

        int i = 1;
        long previous = -1;
        int previous_i = 1;
        String msg = "";
        boolean top = false;
        for (UUID key : list.keySet()) {
            if (i == 4) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    player.sendMessage(i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getPlayTime()));
                    break;
                } else {
                    player.sendMessage("...");
                    if (top) return false;
                    i++;
                    continue;
                }
            }
            msg = "";
            if (previous == list.get(key).getPlayTime()) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (previous_i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else msg += previous_i + ". " + list.get(key).getNickname();

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPlayTime()));
                i++;
            } else {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else if (i == 1) msg += (i + ". " + ChatColor.GOLD + "" + ChatColor.BOLD + list.get(key).getNickname());
                else msg += (i + ". " + list.get(key).getNickname());

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPlayTime()));

                previous = list.get(key).getPlayTime();
                i++;
                previous_i = i;
            }
        }

        return false;
    }

    private boolean Wild(Player player) {
        HashMap<UUID, UserData> list = Map.sortByWildPoint();

        player.sendMessage("");
        player.sendMessage(ChatColor.BOLD + "    [" + ChatColor.DARK_GREEN + "야생" + ChatColor.RESET + ChatColor.BOLD + " 랭킹]");
        player.sendMessage("");

        int i = 1;
        long previous = -1;
        int previous_i = 1;
        String msg = "";
        boolean top = false;
        for (UUID key : list.keySet()) {
            if (i == 4) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    player.sendMessage(i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getWildPoint()));
                    if (list.size() > 4) player.sendMessage("...");
                    break;
                } else {
                    player.sendMessage("...");
                    if (top) return false;
                    i++;
                    continue;
                }
            }
            msg = "";
            if (previous == list.get(key).getWildPoint()) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (previous_i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else msg += previous_i + ". " + list.get(key).getNickname();

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getWildPoint()));
                i++;
            } else {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else if (i == 1) msg += (i + ". " + ChatColor.GOLD + "" + ChatColor.BOLD + list.get(key).getNickname());
                else msg += (i + ". " + list.get(key).getNickname());

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getWildPoint()));

                previous = list.get(key).getWildPoint();
                i++;
                previous_i = i;
            }
        }
        
        return false;
    }

    private boolean PVP(Player player) {
        HashMap<UUID, UserData> list = Map.sortByPvpPoint();

        player.sendMessage("");
        player.sendMessage(ChatColor.BOLD + "    [" + ChatColor.DARK_RED + "PVP" + ChatColor.RESET + ChatColor.BOLD + " 랭킹]");
        player.sendMessage("");

        int i = 1;
        long previous = -1;
        int previous_i = 1;
        String msg = "";
        boolean top = false;
        for (UUID key : list.keySet()) {
            if (i == 4) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    player.sendMessage(i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getPvpPoint()));
                    break;
                } else {
                    player.sendMessage("...");
                    if (top) return false;
                    i++;
                    continue;
                }
            }
            msg = "";
            if (previous == list.get(key).getPvpPoint()) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    msg += (previous_i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else msg += previous_i + ". " + list.get(key).getNickname();

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPvpPoint()));
                i++;
            } else {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (i + ". " + ChatColor.BLUE + "" + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else if (i == 1) msg += (i + ". " + ChatColor.GOLD + "" + ChatColor.BOLD + list.get(key).getNickname());
                else msg += (i + ". " + list.get(key).getNickname());

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPvpPoint()));

                previous = list.get(key).getPvpPoint();
                i++;
                previous_i = i;
            }
        }
        
        return false;
    }
}
