package kr.kro.bukgeukwild;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;

public class GuideBook {
    public static ItemStack getGuideBook() {
        ItemStack Book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta Meta = (BookMeta) Book.getItemMeta();
        if (Meta != null) {
            Meta.setTitle("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "가이드");
            Meta.setAuthor("Bukgeuk_");

            ArrayList<String> Pages = new ArrayList<>();

            Pages.add("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "       [ 목차 ]\n\n" + ChatColor.RESET +
                    "1. 밴 대상 ... 2p\n" +
                    "2. 서버 시스템 ... 3p\n" +
                    "3. 추가/변경된 점 ... 7p\n");

            Pages.add("" + ChatColor.DARK_GRAY + ChatColor.BOLD + "      『밴 대상』\n\n" + ChatColor.RESET +
                    "1. 티밍으로 특정 유저를 괴롭히는 유저\n" +
                    "2. 고의적으로 서버에 피해가 가는 행동을 하는 유저\n" +
                    "3. 채팅으로 다른 유저를 비하/비방 하거나 성적인 말을 하는 유저");

            Pages.add("" + ChatColor.DARK_GRAY + ChatColor.BOLD + "    『서버 시스템』\n\n" + ChatColor.RESET +
                    "서버에는 포인트를 통한 랭킹 시스템이 있습니다.\n" +
                    "포인트는 3가지가 있으며, 랭킹 1위를 하면 다음 시즌에서 보상을 받을 수 있습니다.\n" +
                    "랭킹은 " + ChatColor.BOLD + "/ranking" + ChatColor.RESET + " 명령어로 확인할 수 있습니다.");

            Pages.add("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    《야생 포인트》\n\n" + ChatColor.RESET +
                    "야생 포인트는\n" +
                    "농사/광질/사냥/화로사용/인챈트/발전과제 등의 활동으로 얻을 수 있습니다.\n" +
                    "일반 몬스터는 마지막으로 데미지를 입힌 유저에게 포인트가 들어오지만, 보스 몬스터는 입힌 데미지양에 따라 지급됩니다.");

            Pages.add("" + ChatColor.DARK_RED + ChatColor.BOLD + "    《PVP 포인트》\n\n" + ChatColor.RESET +
                    "PVP 포인트는\n" +
                    "다른 플레이어를 처치했을때 5포인트씩 얻을 수 있습니다.");

            Pages.add("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "   《접속 시간 포인트》\n\n" + ChatColor.RESET +
                    "접속 시간 포인트는\n" +
                    "서버에 접속해 있으면 10분마다 받을 수 있는 포인트 입니다.");

            Pages.add("" + ChatColor.DARK_BLUE + ChatColor.BOLD + "   『추가/변경된 점』\n\n" + ChatColor.RESET +
                    "기존 마인크래프트 야생과 다른 점이 몇 가지 있습니다.\n");

            Pages.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "     《황금 사과》\n\n" + ChatColor.RESET +
                    "인챈트된 황금 사과를 조합할 수 있습니다.\n" +
                    "황금사과를 금괴로 한번 더 두르게 되면 인챈트된 황금 사과가 나옵니다.\n\n조합법\n" + ChatColor.GOLD +
                    "G G G\n" +
                    "G " + ChatColor.RESET + ChatColor.DARK_AQUA + "A" + ChatColor.RESET + ChatColor.GOLD + " G\n" +
                    "G G G");

            Pages.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "   《다이아몬드 갑옷》\n\n" + ChatColor.RESET +
                    "다이아몬드 갑읏을 다이아몬드로 한번 더 두르게 되면 보호 5 갑옷이 나옵니다.\n\n조합법\n" + ChatColor.BLUE +
                    "D D D\n" +
                    "D " + ChatColor.RESET + ChatColor.DARK_AQUA + "E" + ChatColor.RESET + ChatColor.BLUE + " D\n" +
                    "D D D");

            Pages.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "    《다이아몬드 검》\n\n" + ChatColor.RESET +
                    "다이아몬드 검을 다이아몬드로 한번 더 두르게 되면 날카로움 6 검이 나옵니다.\n\n조합법\n" + ChatColor.BLUE +
                    "D D D\n" +
                    "D " + ChatColor.RESET + ChatColor.DARK_AQUA + "S" + ChatColor.RESET + ChatColor.BLUE + " D\n" +
                    "D D D");

            Pages.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "        《활》\n\n" + ChatColor.RESET +
                    "활을 다이아몬드로 두르게 되면 힘 6 활이 나옵니다.\n\n조합법\n" + ChatColor.BLUE +
                    "D D D\n" +
                    "D " + ChatColor.RESET + ChatColor.DARK_AQUA + "B" + ChatColor.BLUE + " D\n" +
                    "D D D");

            Pages.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "       《금광석》\n\n" + ChatColor.RESET +
                    "금광석을 캐면 금조각 1~9개/금괴 중 하나가 확률적으로 나옵니다.\n" +
                    "섬세한 손길이 붙어있는 곡괭이로 캐면 돌 또는 금광석이 확률적으로 나옵니다.");

            Pages.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "       《철광석》\n\n" + ChatColor.RESET +
                    "철광석을 캐면 철괴가 나옵니다.\n" +
                    "섬세한 손길이 붙어있는 곡괭이로 캐면 철광석이 나옵니다.");

            Meta.setPages(Pages);
            Book.setItemMeta(Meta);
        }

        return Book;
    }
}
