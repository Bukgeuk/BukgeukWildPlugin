package kr.kro.bukgeukwild;

import com.google.gson.Gson;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BukgeukWild extends JavaPlugin implements CommandExecutor {
    public static String Dir;
    public static final Runnable addPlayTimeTask = new Runnable() {
        @Override
        public void run() {
            Bukkit.getOnlinePlayers().forEach(player -> {
                Map.Data.get(player.getUniqueId()).addPlayTime(10);
                player.sendMessage("" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "접속 시간 포인트 +10");
            });
        }
    };
    public static ScheduledExecutorService addPlayTimeService;

    public void addRecipes() {
        ItemStack i1 = new ItemStack(Material.GOLDEN_APPLE);
        NamespacedKey k1 = new NamespacedKey(this, "GOLDEN_APPLE");
        ShapedRecipe r1 = new ShapedRecipe(k1, i1);

        r1.shape("GGG", "GAG", "GGG");
        r1.setIngredient('G', Material.GOLD_INGOT);
        r1.setIngredient('A', Material.APPLE);

        Bukkit.addRecipe(r1);

        ItemStack i2 = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
        NamespacedKey k2 = new NamespacedKey(this, "ENCHANTED_GOLDEN_APPLE");
        ShapedRecipe r2 = new ShapedRecipe(k2, i2);

        r2.shape("GGG", "GAG", "GGG");
        r2.setIngredient('G', Material.GOLD_INGOT);
        r2.setIngredient('A', Material.GOLDEN_APPLE);

        Bukkit.addRecipe(r2);

        ItemStack ChestplateItem1 = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta ChestplateMeta1 = ChestplateItem1.getItemMeta();
        ChestplateMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        ChestplateMeta1.setDisplayName(ChatColor.LIGHT_PURPLE + "단단한 다이아몬드 흉갑");
        ChestplateItem1.setItemMeta(ChestplateMeta1);
        NamespacedKey ChestplateKey1 = new NamespacedKey(this, "GOOD_DIAMOND_CHESTPLATE");
        ShapedRecipe ChestplateRecipe1 = new ShapedRecipe(ChestplateKey1, ChestplateItem1);

        ChestplateRecipe1.shape("DDD", "DED", "DDD");
        ChestplateRecipe1.setIngredient('D', Material.DIAMOND);
        ChestplateRecipe1.setIngredient('E', Material.DIAMOND_CHESTPLATE);

        Bukkit.addRecipe(ChestplateRecipe1);

        ItemStack LeggingsItem1 = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta LeggingsMeta1 = LeggingsItem1.getItemMeta();
        LeggingsMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        LeggingsMeta1.setDisplayName(ChatColor.LIGHT_PURPLE + "단단한 다이아몬드 각반");
        LeggingsItem1.setItemMeta(LeggingsMeta1);
        NamespacedKey LeggingsKey1 = new NamespacedKey(this, "GOOD_DIAMOND_LEGGINGS");
        ShapedRecipe LeggingsRecipe1 = new ShapedRecipe(LeggingsKey1, LeggingsItem1);

        LeggingsRecipe1.shape("DDD", "DED", "DDD");
        LeggingsRecipe1.setIngredient('D', Material.DIAMOND);
        LeggingsRecipe1.setIngredient('E', Material.DIAMOND_LEGGINGS);

        Bukkit.addRecipe(LeggingsRecipe1);

        ItemStack BootsItem1 = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta BootsMeta1 = BootsItem1.getItemMeta();
        BootsMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        BootsMeta1.setDisplayName(ChatColor.LIGHT_PURPLE + "단단한 다이아몬드 부츠");
        BootsItem1.setItemMeta(BootsMeta1);
        NamespacedKey BootsKey1 = new NamespacedKey(this, "GOOD_DIAMOND_BOOTS");
        ShapedRecipe BootsRecipe1 = new ShapedRecipe(BootsKey1, BootsItem1);

        BootsRecipe1.shape("DDD", "DED", "DDD");
        BootsRecipe1.setIngredient('D', Material.DIAMOND);
        BootsRecipe1.setIngredient('E', Material.DIAMOND_BOOTS);

        Bukkit.addRecipe(BootsRecipe1);

        ItemStack HelmetItem1 = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta HelmetMeta1 = HelmetItem1.getItemMeta();
        HelmetMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
        HelmetMeta1.setDisplayName(ChatColor.LIGHT_PURPLE + "단단한 다이아몬드 투구");
        HelmetItem1.setItemMeta(HelmetMeta1);
        NamespacedKey HelmetKey1 = new NamespacedKey(this, "GOOD_DIAMOND_HELMET");
        ShapedRecipe HelmetRecipe1 = new ShapedRecipe(HelmetKey1, HelmetItem1);

        HelmetRecipe1.shape("DDD", "DED", "DDD");
        HelmetRecipe1.setIngredient('D', Material.DIAMOND);
        HelmetRecipe1.setIngredient('E', Material.DIAMOND_HELMET);

        Bukkit.addRecipe(HelmetRecipe1);

        ItemStack SwordItem1 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta SwordMeta1 = SwordItem1.getItemMeta();
        SwordMeta1.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
        SwordMeta1.setDisplayName(ChatColor.LIGHT_PURPLE + "날카로운 다이아몬드 검");
        SwordItem1.setItemMeta(SwordMeta1);
        NamespacedKey SwordKey1 = new NamespacedKey(this, "GOOD_DIAMOND_SWORD");
        ShapedRecipe SwordRecipe1 = new ShapedRecipe(SwordKey1, SwordItem1);

        SwordRecipe1.shape("DDD", "DED", "DDD");
        SwordRecipe1.setIngredient('D', Material.DIAMOND);
        SwordRecipe1.setIngredient('E', Material.DIAMOND_SWORD);

        Bukkit.addRecipe(SwordRecipe1);

        ItemStack BowItem1 = new ItemStack(Material.BOW);
        ItemMeta BowMeta1 = BowItem1.getItemMeta();
        BowMeta1.addEnchant(Enchantment.ARROW_DAMAGE, 6, true);
        BowMeta1.setDisplayName(ChatColor.LIGHT_PURPLE + "강력한 활");
        BowItem1.setItemMeta(BowMeta1);
        NamespacedKey BowKey1 = new NamespacedKey(this, "GOOD_BOW");
        ShapedRecipe BowRecipe1 = new ShapedRecipe(BowKey1, BowItem1);

        BowRecipe1.shape("DDD", "DED", "DDD");
        BowRecipe1.setIngredient('D', Material.DIAMOND);
        BowRecipe1.setIngredient('E', Material.BOW);

        Bukkit.addRecipe(BowRecipe1);
    }

    public void onEnable() {
        Dir = getDataFolder().getAbsolutePath();

        if (!getDataFolder().exists()) getDataFolder().mkdir();
        File folder = new File(Dir + File.separator + "Users/");
        if (!folder.exists()) folder.mkdir();

        Map.Data = FileIO.ReadAll();

        getServer().getPluginManager().registerEvents(new ConnectEvent(), this);
        getServer().getPluginManager().registerEvents(new PointEvent(this), this);
        getCommand("ranking").setExecutor(new RankingCommands());
        getCommand("dev").setExecutor(this);

        Iterator<Recipe> it = getServer().recipeIterator();
        Recipe target;
        while(it.hasNext()) {
            target = it.next();
            if(target != null && target.getResult().getType() == Material.GOLDEN_APPLE) {
                it.remove();
                break;
            }
        }

        addRecipes();

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
                p.sendMessage(ChatColor.RED + "권한이 부족합니다.");
                return true;
            }
        }

        if (args.length > 0) {
            switch (args[0]) {
                case "disable":
                    Disable();
                    return true;
                case "map":
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(Map.Data);
                    sender.sendMessage(jsonStr);
                    return true;
                case "score":
                    if (args.length >= 5) {
                        UUID UUID = null;
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.getName().equals(args[2])) UUID = p.getUniqueId();
                        }
                        if (UUID == null) {
                            sender.sendMessage(ChatColor.RED + args[2] + "님을 찾을 수 없습니다");
                            return false;
                        }
                        switch (args[1].toLowerCase()) {
                            case "add":
                                if (args[3].toLowerCase().equals("wild")) Map.Data.get(UUID).addWildPoint(Long.parseLong(args[4]));
                                else if (args[3].toLowerCase().equals("pvp")) Map.Data.get(UUID).addPvpPoint(Long.parseLong(args[4]));
                                else if (args[3].toLowerCase().equals("playtime")) Map.Data.get(UUID).addPlayTime(Long.parseLong(args[4]));
                                sender.sendMessage("Add " + args[4] + " points to " + args[2] + " (Type:\"" + args[3] + "\")");
                                return true;
                            case "sub":
                                if (args[3].toLowerCase().equals("wild")) Map.Data.get(UUID).addWildPoint((-1) * Long.parseLong(args[4]));
                                else if (args[3].toLowerCase().equals("pvp")) Map.Data.get(UUID).addPvpPoint((-1) * Long.parseLong(args[4]));
                                else if (args[3].toLowerCase().equals("playtime")) Map.Data.get(UUID).addPlayTime((-1) * Long.parseLong(args[4]));
                                sender.sendMessage("Remove " + args[4] + " points to " + args[2] + " (Type:\"" + args[3] + "\")");
                                return true;
                            case "set":
                                if (args[3].toLowerCase().equals("wild")) Map.Data.get(UUID).setWildPoint(Long.parseLong(args[4]));
                                else if (args[3].toLowerCase().equals("pvp")) Map.Data.get(UUID).setPvpPoint(Long.parseLong(args[4]));
                                else if (args[3].toLowerCase().equals("playtime")) Map.Data.get(UUID).setPlayTime(Long.parseLong(args[4]));
                                sender.sendMessage("Set " + args[2] + "'s points to " + args[4] + " (Type:\"" + args[3] + "\")");
                                return true;
                        }
                    }
                    sender.sendMessage(ChatColor.RED + "Usage : /dev score [ add | sub | set ] <Nickname> <type> <score>");
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "Usage : /dev [ disable | map ]");
            }
        } else sender.sendMessage(ChatColor.RED + "Usage : /dev [ disable | map ]");
        return true;
    }
}