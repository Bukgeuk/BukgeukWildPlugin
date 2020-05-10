package kr.kro.bukgeukwild;

import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;

import java.util.*;

public class Map {
    public static HashMap<UUID, UserData> Data;

    public static HashMap<UUID, UserData> sortByPlayTime() {
        List<HashMap.Entry<UUID, UserData>> list = new LinkedList<HashMap.Entry<UUID, UserData>>(Data.entrySet());

        Collections.sort(list, new Comparator<HashMap.Entry<UUID, UserData>>() {
            @Override
            public int compare(HashMap.Entry<UUID, UserData> o1, HashMap.Entry<UUID, UserData> o2) {
                if (o1.getValue().getPlayTime() < o2.getValue().getPlayTime()) return 1;
                else if (o1.getValue().getPlayTime() > o2.getValue().getPlayTime()) return -1;
                else return 0;
            }
        });

        HashMap<UUID, UserData> sortedMap = new LinkedHashMap<>();
        for(Iterator<HashMap.Entry<UUID, UserData>> iter = list.iterator(); iter.hasNext();){
            HashMap.Entry<UUID, UserData> entry = iter.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static HashMap<UUID, UserData> sortByWildPoint() {
        List<HashMap.Entry<UUID, UserData>> list = new LinkedList<HashMap.Entry<UUID, UserData>>(Data.entrySet());

        Collections.sort(list, new Comparator<HashMap.Entry<UUID, UserData>>() {
            @Override
            public int compare(HashMap.Entry<UUID, UserData> o1, HashMap.Entry<UUID, UserData> o2) {
                if (o1.getValue().getWildPoint() < o2.getValue().getWildPoint()) return 1;
                else if (o1.getValue().getWildPoint() > o2.getValue().getWildPoint()) return -1;
                else return 0;
            }
        });

        HashMap<UUID, UserData> sortedMap = new LinkedHashMap<>();
        for(Iterator<HashMap.Entry<UUID, UserData>> iter = list.iterator(); iter.hasNext();){
            HashMap.Entry<UUID, UserData> entry = iter.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static HashMap<UUID, UserData> sortByPvpPoint() {
        List<HashMap.Entry<UUID, UserData>> list = new LinkedList<HashMap.Entry<UUID, UserData>>(Data.entrySet());

        Collections.sort(list, new Comparator<HashMap.Entry<UUID, UserData>>() {
            @Override
            public int compare(HashMap.Entry<UUID, UserData> o1, HashMap.Entry<UUID, UserData> o2) {
                if (o1.getValue().getPvpPoint() < o2.getValue().getPvpPoint()) return 1;
                else if (o1.getValue().getPvpPoint() > o2.getValue().getPvpPoint()) return -1;
                else return 0;
            }
        });

        HashMap<UUID, UserData> sortedMap = new LinkedHashMap<>();
        for(Iterator<HashMap.Entry<UUID, UserData>> iter = list.iterator(); iter.hasNext();){
            HashMap.Entry<UUID, UserData> entry = iter.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
