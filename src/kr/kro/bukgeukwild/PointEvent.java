package kr.kro.bukgeukwild;

import net.minecraft.server.v1_15_R1.DoubleBlockFinder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Furnace;
import org.bukkit.craftbukkit.Main;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class PointEvent implements Listener {
    private Plugin plugin;

    public PointEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    public String getKoreanNameByEntityType(EntityType type) {
        switch (type) {
            case ZOMBIE:
                return "좀비";
            case ZOMBIE_VILLAGER:
                return "주민 좀비";
            case CREEPER:
                return "크리퍼";
            case SKELETON:
                return "스켈레톤";
            case SPIDER:
                return "거미";
            case CAVE_SPIDER:
                return "동굴 거미";
            case HUSK:
                return "허스크";
            case SLIME:
                return "슬라임";
            case DROWNED:
                return "드라운드";
            case BLAZE:
                return "블레이즈";
            case GHAST:
                return "가스트";
            case GUARDIAN:
                return "가디언";
            case MAGMA_CUBE:
                return "마그마 큐브";
            case STRAY:
                return "스트레이";
            case VEX:
                return "벡스";
            case VINDICATOR:
                return "변명자";
            case EVOKER:
                return "소환사";
            case WITCH:
                return "마녀";
            case WITHER_SKELETON:
                return "위더 스켈레톤";
            case PHANTOM:
                return "팬텀";
            case SHULKER:
                return "셜커";
            case ELDER_GUARDIAN:
                return "엘더 가디언";
            case RAVAGER:
                return "파괴수";
            case PIG_ZOMBIE:
                return "좀비 피그맨";
            case ENDERMAN:
                return "엔더맨";
            case CHICKEN:
                return "닭";
            case COW:
                return "소";
            case PIG:
                return "돼지";
            case RABBIT:
                return "토끼";
            case MUSHROOM_COW:
                return "버섯소";
            case HORSE:
                return "말";
            case SHEEP:
                return "양";
            case SALMON:
                return "연어";
            case PUFFERFISH:
                return "복어";
            case TROPICAL_FISH:
                return "열대어";
            case COD:
                return "대구";
            case BAT:
                return "박쥐";
            case SQUID:
                return "오징어";
            case IRON_GOLEM:
                return "철 골렘";
            case PANDA:
                return "판다";
            default:
                return "Unknown Entity";
        }
    }

    public String getKoreanNameByBlockType(Material type) {
        switch (type) {
            case WHEAT:
                return "밀";
            case POTATOES:
                return "감자";
            case CARROTS:
                return "당근";
            case BEETROOTS:
                return "사탕무";
            case PUMPKIN:
                return "호박";
            case MELON:
                return "수박";
            case NETHER_WART:
                return "네더 사마귀";
            case SWEET_BERRY_BUSH:
                return "달콤한 열매";
            case EMERALD_ORE:
                return "에메랄드 원석";
            case DIAMOND_ORE:
                return "다이아몬드 원석";
            case GOLD_ORE:
                return "금광석";
            case IRON_ORE:
                return "철광석";
            case COAL_ORE:
                return "석탄 광석";
            case REDSTONE_ORE:
                return "레드스톤 광석";
            case LAPIS_ORE:
                return "청금석 원석";
            case NETHER_QUARTZ_ORE:
                return "네더 석영 원석";
            default:
                return "Unknown Block";
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Entity killer = e.getEntity().getKiller();

        if (killer != null) {
            Map.Data.get(killer.getUniqueId()).addPvpPoint(5);
            Map.Data.get(killer.getUniqueId()).addWildPoint(200);
            killer.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + player.getName() + " 처치 :");
            killer.sendMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "    PVP 포인트 +5\n" + ChatColor.RESET + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +200");
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity.getKiller() != null) {
            Player p = (Player) entity.getKiller();
            switch (entity.getType()) {
                case CHICKEN: case COW:
                case PIG: case RABBIT:
                case MUSHROOM_COW: case HORSE:
                case SHEEP: case SALMON:
                case PUFFERFISH: case TROPICAL_FISH:
                case BAT: case SQUID:
                case COD:
                    p.sendMessage("" + ChatColor.AQUA + ChatColor.BOLD + getKoreanNameByEntityType(entity.getType()) + " 사냥 :");
                    p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +10");
                    Map.Data.get(p.getUniqueId()).addWildPoint(10);
                    break;
                case ZOMBIE: case ZOMBIE_VILLAGER:
                case CREEPER: case SKELETON:
                case SPIDER: case CAVE_SPIDER:
                case HUSK: case SLIME:
                case DROWNED: case PANDA:
                    p.sendMessage("" + ChatColor.BLUE + ChatColor.BOLD + getKoreanNameByEntityType(entity.getType()) + " 처치 :");
                    p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +30");
                    Map.Data.get(p.getUniqueId()).addWildPoint(30);
                    break;
                case BLAZE: case GHAST:
                case GUARDIAN: case MAGMA_CUBE:
                case STRAY: case VEX: case VINDICATOR: case EVOKER:
                case WITCH: case WITHER_SKELETON:
                case PHANTOM: case SHULKER:
                case ENDERMAN: case PIG_ZOMBIE:
                case POLAR_BEAR:
                    p.sendMessage("" + ChatColor.DARK_AQUA + ChatColor.BOLD + getKoreanNameByEntityType(entity.getType()) + " 처치 :");
                    p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +80");
                    Map.Data.get(p.getUniqueId()).addWildPoint(80);
                    break;
                case ELDER_GUARDIAN: case RAVAGER: case IRON_GOLEM:
                    p.sendMessage("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + getKoreanNameByEntityType(entity.getType()) + " 처치 :");
                    p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +500");
                    Map.Data.get(p.getUniqueId()).addWildPoint(500);
                    break;
                case WITHER:
                    int num = e.getEntity().getMetadata("Number").get(0).asInt();
                    for (Player damager : BossMobs.getAllWitherDamager(num)) {
                        if (damager.getUniqueId().equals(p.getUniqueId())) {
                            double percent = (BossMobs.getWitherDamage(num, damager) / BossMobs.getWitherHealth(num));
                            int point = (int) (percent * 5000);
                            damager.sendMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "위더 처치 :");
                            damager.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "    기여도 " + (int) (percent * 100) + "%");
                            damager.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +" + point);
                            Map.Data.get(damager.getUniqueId()).addWildPoint(point);
                        } else {
                            double percent = (BossMobs.getWitherDamage(num, damager) / BossMobs.getWitherHealth(num));
                            int point = (int) (percent * 5000);
                            damager.sendMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "위더 처치를 도움 :");
                            damager.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "    기여도 " + (int) (percent * 100) + "%");
                            damager.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +" + point);
                            Map.Data.get(damager.getUniqueId()).addWildPoint(point);
                        }
                    }
                    BossMobs.removeWitherMap(num);
                case ENDER_DRAGON:
                    for (Player damager : BossMobs.getAllEnderDragonDamager()) {
                        if (damager.getUniqueId().equals(p.getUniqueId())) {
                            double percent = (BossMobs.getEnderDragonDamage(damager) / BossMobs.getEnderDragonHealth());
                            int point = (int) (percent * 5000);
                            damager.sendMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "엔더드래곤 처치 :");
                            damager.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "    기여도 " + (int) (percent * 100) + "%");
                            damager.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +" + point);
                            Map.Data.get(damager.getUniqueId()).addWildPoint(point);
                        } else {
                            double percent = (BossMobs.getEnderDragonDamage(damager) / BossMobs.getEnderDragonHealth());
                            int point = (int) (percent * 5000);
                            damager.sendMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "엔더드래곤 처치를 도움 :");
                            damager.sendMessage("" + ChatColor.GOLD + ChatColor.BOLD + "    기여도 " + (int) (percent * 100) + "%");
                            damager.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +" + point);
                            Map.Data.get(damager.getUniqueId()).addWildPoint(point);
                        }
                    }
                    BossMobs.clearEnderDragonMap();
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        BlockData data;
        int a;
        Player p = e.getPlayer();
        Material m;
        switch (e.getBlock().getType()) {
            case WHEAT: case POTATOES: case CARROTS:
                data = e.getBlock().getBlockData();
                a = data.getAsString().indexOf("[age=");
                if (a != -1) {
                    String str = data.getAsString().substring(a + 5);
                    str = str.substring(0, str.length() - 1);
                    if (Integer.parseInt(str) == 7) {
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + getKoreanNameByBlockType(e.getBlock().getType()) + " 수확 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +10");
                        Map.Data.get(p.getUniqueId()).addWildPoint(10);
                    }
                }
                break;
            case BEETROOTS: case NETHER_WART:
            case SWEET_BERRY_BUSH:
                data = e.getBlock().getBlockData();
                a = data.getAsString().indexOf("[age=");
                if (a != -1) {
                    String str = data.getAsString().substring(a + 5);
                    str = str.substring(0, str.length() - 1);
                    if (Integer.parseInt(str) == 3) {
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + getKoreanNameByBlockType(e.getBlock().getType()) + " 수확 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +10");
                        Map.Data.get(p.getUniqueId()).addWildPoint(10);
                    }
                }
                break;
            case PUMPKIN: case MELON:
                p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + getKoreanNameByBlockType(e.getBlock().getType()) + " 수확 :");
                p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +10");
                Map.Data.get(p.getUniqueId()).addWildPoint(10);
                break;
            case EMERALD_ORE:
                m = p.getInventory().getItemInMainHand().getType();
                if (m.equals(Material.IRON_PICKAXE) || m.equals(Material.DIAMOND_PICKAXE)) {
                    if (!p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "에메랄드 원석 채굴 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +500");
                        Map.Data.get(p.getUniqueId()).addWildPoint(500);
                    }
                } else
                    e.setDropItems(false);
                break;
            case DIAMOND_ORE:
                m = p.getInventory().getItemInMainHand().getType();
                if (m.equals(Material.IRON_PICKAXE) || m.equals(Material.DIAMOND_PICKAXE)) {
                    if (!p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "다이아몬드 원석 채굴 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +150");
                        Map.Data.get(p.getUniqueId()).addWildPoint(150);
                    }
                } else
                    e.setDropItems(false);
                break;
            case GOLD_ORE:
                e.setDropItems(false);
                m = p.getInventory().getItemInMainHand().getType();
                if (m.equals(Material.IRON_PICKAXE) || m.equals(Material.DIAMOND_PICKAXE)) {
                    if (p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        int random = ((int) (Math.random() * 10)) + 1;
                        if (random > 0 && random <= 4)
                            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.STONE));
                        else
                            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_ORE));
                    } else {
                        int random = ((int) (Math.random() * 10)) + 1;
                        if (random > 0 && random <= 2)
                            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                        else {
                            int amount = ((int) Math.random()) + 1;
                            ItemStack Nugget = new ItemStack(Material.GOLD_NUGGET);
                            if (random > 2 && random <= 4) Nugget.setAmount(amount);
                            else if (random > 4 && random <= 8) Nugget.setAmount(amount + 3);
                            else if (random > 8 && random <= 10) Nugget.setAmount(amount + 6);

                            e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Nugget);
                        }
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "금광석 채굴 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +80");
                        Map.Data.get(p.getUniqueId()).addWildPoint(500);
                    }
                }
                break;
            case IRON_ORE:
                e.setDropItems(false);
                m = p.getInventory().getItemInMainHand().getType();
                if (m.equals(Material.STONE_PICKAXE) || m.equals(Material.IRON_PICKAXE) || m.equals(Material.DIAMOND_PICKAXE)) {
                    if (p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_ORE));
                    } else {
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "철광석 채굴 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +50");
                        Map.Data.get(p.getUniqueId()).addWildPoint(50);
                    }
                }
                break;
            case LAPIS_ORE:
                m = p.getInventory().getItemInMainHand().getType();
                if (m.equals(Material.IRON_PICKAXE) || m.equals(Material.DIAMOND_PICKAXE) ||  m.equals(Material.STONE_PICKAXE)) {
                    if (!p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "청금석 원석 채굴 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +50");
                        Map.Data.get(p.getUniqueId()).addWildPoint(50);
                    }
                } else
                    e.setDropItems(false);
                break;
            case NETHER_QUARTZ_ORE:
                m = p.getInventory().getItemInMainHand().getType();
                if (m.equals(Material.IRON_PICKAXE) || m.equals(Material.DIAMOND_PICKAXE) || m.equals(Material.WOODEN_PICKAXE) || m.equals(Material.STONE_PICKAXE) || m.equals(Material.GOLDEN_PICKAXE)) {
                    if (!p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "네더 석영 원석 채굴 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +30");
                        Map.Data.get(p.getUniqueId()).addWildPoint(30);
                    }
                } else
                    e.setDropItems(false);
                break;
            case REDSTONE_ORE:
                m = p.getInventory().getItemInMainHand().getType();
                if (m.equals(Material.IRON_PICKAXE) || m.equals(Material.DIAMOND_PICKAXE)) {
                    if (!p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + "레드스톤 원석 채굴 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +15");
                        Map.Data.get(p.getUniqueId()).addWildPoint(15);
                    }
                } else
                    e.setDropItems(false);
                break;
            case COAL_ORE:
                m = p.getInventory().getItemInMainHand().getType();
                if (m.equals(Material.IRON_PICKAXE) || m.equals(Material.DIAMOND_PICKAXE) || m.equals(Material.WOODEN_PICKAXE) || m.equals(Material.STONE_PICKAXE) || m.equals(Material.GOLDEN_PICKAXE)) {
                    if (!p.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + getKoreanNameByBlockType(e.getBlock().getType()) + " 채굴 :");
                        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +15");
                        Map.Data.get(p.getUniqueId()).addWildPoint(15);
                    }
                } else
                    e.setDropItems(false);
                break;
        }
    }

    @EventHandler
    public void onFurnaceExtract(FurnaceExtractEvent e) {
        int point = e.getItemAmount() * 5;
        Player p = e.getPlayer();
        String FurnaceName = "Unknown Furnace";

        switch (e.getBlock().getType()) {
            case FURNACE:
                FurnaceName = "화로";
                break;
            case SMOKER:
                FurnaceName = "훈연기";
                break;
            case BLAST_FURNACE:
                FurnaceName = "용광로";
                break;
        }

        p.sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + FurnaceName + " 사용 x " + e.getItemAmount() + " :");
        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +" + point);
        Map.Data.get(p.getUniqueId()).addWildPoint(point);
    }

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent e) {
        Player p = e.getPlayer();
        String str = e.getAdvancement().getKey().toString();

        if (!str.startsWith("minecraft:recipes/") && !str.contains("/root")) {
            p.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "발전 과제 달성! :");
            p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +1000");
            Map.Data.get(p.getUniqueId()).addWildPoint(1000);
        }
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent e) {
        int cost = e.getExpLevelCost();
        int point = 0;

        if (cost > 0 && cost <= 10) point = 10;
        else if (cost > 10 && cost <= 20) point = 20;
        else if (cost > 20 && cost <= 30) point = 30;

        Player p = e.getEnchanter();

        p.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + cost + " 레벨 인챈트 :");
        p.sendMessage("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "    야생 포인트 +" + point);
        Map.Data.get(p.getUniqueId()).addWildPoint(point);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            Entity entity = e.getEntity();
            if (entity instanceof Wither) BossMobs.addWitherDamage(e.getEntity().getMetadata("Number").get(0).asInt(), p, e.getDamage());
            else if (entity instanceof EnderDragon) BossMobs.addEnderDragonDamage(p, e.getDamage());
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntityType().equals(EntityType.WITHER)) {
            e.getEntity().setMetadata("Number", new FixedMetadataValue(plugin, BossMobs.getWitherCount()));
            BossMobs.addWitherCount();
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType().equals(Material.PUMPKIN)) e.setCancelled(true);
    }

}
