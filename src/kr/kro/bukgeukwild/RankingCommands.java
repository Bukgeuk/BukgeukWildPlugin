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
                switch (args[0].toLowerCase()) {
                    case "playtime":
                        return PlayTime(p);
                    case "wild":
                        return Wild(p);
                    case "pvp":
                        return PVP(p);
                    case "score":
                        if (args.length > 1) {
                            for (UUID key : Map.Data.keySet()) {
                                if (Map.Data.get(key).getNickname().equals(args[1])) {

                                    p.sendMessage("");
                                    p.sendMessage(ChatColor.BOLD + args[1] + "님의 포인트 :\n" +
                                            ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "  접속 시간 포인트" + ChatColor.RESET + "  " + Map.Data.get(key).getPlayTime() +
                                            ChatColor.DARK_GREEN + ChatColor.BOLD + "\n  야생 포인트" + ChatColor.RESET + "  " + Map.Data.get(key).getWildPoint() +
                                            ChatColor.DARK_RED + ChatColor.BOLD + "\n  PVP 포인트" + ChatColor.RESET + "  " + Map.Data.get(key).getPvpPoint());
                                    return true;
                                }
                            }
                            p.sendMessage(ChatColor.RED + args[1] + "님을 찾을 수 없습니다");
                        } else p.sendMessage(ChatColor.RED + "사용법 : /ranking score <Nickname>");
                    default:
                        p.sendMessage(ChatColor.RED + "사용법 : /ranking [ playtime | wild | pvp | score ]");
                }
            } else p.sendMessage(ChatColor.RED + "사용법 : /ranking [ playtime | wild | pvp | score ]");
        } else {
            sender.sendMessage("Cannot use this command in console");
        }
        return true;
    }

    private boolean PlayTime(Player player) {
        HashMap<UUID, UserData> list = Map.sortByPlayTime();

        player.sendMessage("");
        player.sendMessage(ChatColor.BOLD + "    [" + ChatColor.RESET + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "접속" + ChatColor.RESET + ChatColor.BOLD + " 시간 랭킹]");
        player.sendMessage("");

        int count = 1;
        long prev = -1;
        int pcount = 1;
        String msg;
        boolean ranker = false;

        for (UUID key : list.keySet()) {
            if (prev != list.get(key).getPlayTime()) {
                pcount = count;
                prev = list.get(key).getPlayTime();
            }

            if (count == 4) {
                if (player.getUniqueId().equals(key)) {
                    player.sendMessage(pcount + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getPlayTime()));
                    if (list.size() > 4)
                        player.sendMessage("...");
                    return true;
                } else {
                    player.sendMessage("...");
                    if (ranker)
                        return true;
                    count++;
                    continue;
                }
            }

            msg = "";
            if (player.getUniqueId().equals(key)) {
                if (pcount == 1)
                    ranker = true;
                msg += (pcount + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
            } else if (pcount == 1) {
                msg += ("1. " + ChatColor.GOLD + ChatColor.BOLD + list.get(key).getNickname());
            } else msg += (pcount + ". " + list.get(key).getNickname());

            if (count < 4 || player.getUniqueId().equals(key))
                player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPlayTime()));

            count++;
        }

        /*int i = 1;
        long previous = -1;
        int previous_i = 1;
        String msg = "";
        boolean top = false;
        for (UUID key : list.keySet()) {
            if (i == 4) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    player.sendMessage(i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getPlayTime()));
                    break;
                } else {
                    player.sendMessage("...");
                    if (top) return true;
                    i++;
                    continue;
                }
            }
            msg = "";
            if (previous == list.get(key).getPlayTime()) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (previous_i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else msg += previous_i + ". " + list.get(key).getNickname();

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPlayTime()));
                i++;
            } else {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else if (i == 1) msg += (i + ". " + ChatColor.GOLD + ChatColor.BOLD + list.get(key).getNickname());
                else msg += (i + ". " + list.get(key).getNickname());

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPlayTime()));

                previous = list.get(key).getPlayTime();
                i++;
                previous_i = i;
            }
        }*/

        return true;
    }

    private boolean Wild(Player player) {
        HashMap<UUID, UserData> list = Map.sortByWildPoint();

        player.sendMessage("");
        player.sendMessage(ChatColor.BOLD + "    [" + ChatColor.RESET + ChatColor.DARK_GREEN + ChatColor.BOLD + "야생" + ChatColor.RESET + ChatColor.BOLD + " 랭킹]");
        player.sendMessage("");

        int count = 1;
        long prev = -1;
        int pcount = 1;
        String msg;
        boolean ranker = false;

        for (UUID key : list.keySet()) {
            if (prev != list.get(key).getWildPoint()) {
                pcount = count;
                prev = list.get(key).getWildPoint();
            }

            if (count == 4) {
                if (player.getUniqueId().equals(key)) {
                    player.sendMessage(pcount + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getWildPoint()));
                    if (list.size() > 4)
                        player.sendMessage("...");
                    return true;
                } else {
                    player.sendMessage("...");
                    if (ranker)
                        return true;
                    count++;
                    continue;
                }
            }

            msg = "";
            if (player.getUniqueId().equals(key)) {
                if (pcount == 1)
                    ranker = true;
                msg += (pcount + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
            } else if (pcount == 1) {
                msg += ("1. " + ChatColor.GOLD + ChatColor.BOLD + list.get(key).getNickname());
            } else msg += (pcount + ". " + list.get(key).getNickname());

            if (count < 4 || player.getUniqueId().equals(key))
                player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getWildPoint()));

            count++;
        }

        /*int i = 1;
        long previous = -1;
        int previous_i = 1;
        String msg = "";
        boolean top = false;
        for (UUID key : list.keySet()) {
            if (i == 4) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    player.sendMessage(i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getWildPoint()));
                    if (list.size() > 4) player.sendMessage("...");
                    break;
                } else {
                    player.sendMessage("...");
                    if (top) return true;
                    i++;
                    continue;
                }
            }
            msg = "";
            if (previous == list.get(key).getWildPoint()) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (previous_i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else msg += previous_i + ". " + list.get(key).getNickname();

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getWildPoint()));
                i++;
            } else {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else if (i == 1) msg += (i + ". " + ChatColor.GOLD + ChatColor.BOLD + list.get(key).getNickname());
                else msg += (i + ". " + list.get(key).getNickname());

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getWildPoint()));

                previous = list.get(key).getWildPoint();
                i++;
                previous_i = i;
            }
        }*/
        
        return true;
    }

    private boolean PVP(Player player) {
        HashMap<UUID, UserData> list = Map.sortByPvpPoint();

        player.sendMessage("");
        player.sendMessage(ChatColor.BOLD + "    [" + ChatColor.RESET + ChatColor.DARK_RED + ChatColor.BOLD + "PVP" + ChatColor.RESET + ChatColor.BOLD + " 랭킹]");
        player.sendMessage("");

        int count = 1;
        long prev = -1;
        int pcount = 1;
        String msg;
        boolean ranker = false;

        for (UUID key : list.keySet()) {
            if (prev != list.get(key).getPvpPoint()) {
                pcount = count;
                prev = list.get(key).getPvpPoint();
            }

            if (count == 4) {
                if (player.getUniqueId().equals(key)) {
                    player.sendMessage(pcount + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getPvpPoint()));
                    if (list.size() > 4)
                        player.sendMessage("...");
                    return true;
                } else {
                    player.sendMessage("...");
                    if (ranker)
                        return true;
                    count++;
                    continue;
                }
            }

            msg = "";
            if (player.getUniqueId().equals(key)) {
                if (pcount == 1)
                    ranker = true;
                msg += (pcount + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
            } else if (pcount == 1) {
                msg += ("1. " + ChatColor.GOLD + ChatColor.BOLD + list.get(key).getNickname());
            } else msg += (pcount + ". " + list.get(key).getNickname());

            if (count < 4 || player.getUniqueId().equals(key))
                player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPvpPoint()));

            count++;
        }

        /*int i = 1;
        long previous = -1;
        int previous_i = 1;
        String msg = "";
        boolean top = false;
        for (UUID key : list.keySet()) {
            if (i == 4) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    player.sendMessage(i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You) " + ChatColor.RESET + formatter.format(list.get(key).getPvpPoint()));
                    break;
                } else {
                    player.sendMessage("...");
                    if (top) return true;
                    i++;
                    continue;
                }
            }
            msg = "";
            if (previous == list.get(key).getPvpPoint()) {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    msg += (previous_i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else msg += previous_i + ". " + list.get(key).getNickname();

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPvpPoint()));
                i++;
            } else {
                if (player.getUniqueId().equals(list.get(key).getUUID())) {
                    top = true;
                    msg += (i + ". " + ChatColor.BLUE + ChatColor.BOLD + list.get(key).getNickname() + "(You)");
                } else if (i == 1) msg += (i + ". " + ChatColor.GOLD + ChatColor.BOLD + list.get(key).getNickname());
                else msg += (i + ". " + list.get(key).getNickname());

                if (i < 4 ||(i > 4 && player.getUniqueId().equals(list.get(key).getUUID()))) player.sendMessage(msg + " " + ChatColor.RESET + formatter.format(list.get(key).getPvpPoint()));

                previous = list.get(key).getPvpPoint();
                i++;
                previous_i = i;
            }
        }*/
        
        return true;
    }
}
