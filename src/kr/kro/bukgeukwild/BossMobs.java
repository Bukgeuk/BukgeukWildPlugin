package kr.kro.bukgeukwild;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class BossMobs {
    private static HashMap<Player, Double> EnderDragon = new HashMap<>();
    private static double EnderDragonHealth;
    private static ArrayList<HashMap<Player, Double>> Wither = new ArrayList<>();
    private static ArrayList<Double> WitherHealth = new ArrayList<>();
    private static long WitherCount = 0;

    public static double getEnderDragonDamage(Player p) { return EnderDragon.get(p); }
    public static double getWitherDamage(int num, Player p) { return Wither.get(num).get(p); }

    public static void clearEnderDragonMap() { EnderDragon.clear(); }
    public static void removeWitherMap(int num) { Wither.set(num, null); }

    public static void addEnderDragonDamage(Player p, double Damage) {
        if (EnderDragon.get(p) == null) EnderDragon.put(p, Damage);
        else {
            double value = EnderDragon.get(p);
            Double newValue = value + Damage;
            EnderDragon.replace(p, newValue);
        }

        EnderDragonHealth += Damage;
    }

    public static void addWitherDamage(int num, Player p, double Damage) {
        if (Wither.get(num).get(p) == null) Wither.get(num).put(p, Damage);
        else {
            double value = Wither.get(num).get(p);
            Double newValue = value + Damage;
            Wither.get(num).replace(p, newValue);
        }

        Double newHealth = WitherHealth.get(num) + Damage;
        WitherHealth.set(num, newHealth);
    }

    public static long getWitherCount() {
        Wither.add(new HashMap<>());
        WitherHealth.add(0.0);
        return WitherCount;
    }
    public static void addWitherCount() { WitherCount++; }

    public static ArrayList<Player> getAllEnderDragonDamager() {
        ArrayList<Player> array = new ArrayList<>();
        for (Player p : EnderDragon.keySet()) array.add(p);

        return array;
    }

    public static ArrayList<Player> getAllWitherDamager(int num) {
        ArrayList<Player> array = new ArrayList<>();
        HashMap<Player, Double> map = Wither.get(num);
        for (Player p : map.keySet()) array.add(p);

        return array;
    }

    public static double getEnderDragonHealth() { return EnderDragonHealth; }
    public static double getWitherHealth(int num) { return WitherHealth.get(num); }
}
