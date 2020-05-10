package kr.kro.bukgeukwild;

import java.io.Serializable;
import java.util.UUID;

public class UserData implements Serializable {
    private long WildPoint;
    private long pvpPoint;
    private long PlayTime;

    private UUID UUID;
    private String Nickname;

    public UserData(long WildPoint, long pvpPoint, long PlayTime, String Nickname, UUID UUID) {
        this.WildPoint = WildPoint;
        this.pvpPoint = pvpPoint;
        this.PlayTime = PlayTime;
        this.Nickname = Nickname;
        this.UUID = UUID;
    }

    public UserData(String Nickname, UUID UUID) {
        this.WildPoint = 0;
        this.pvpPoint = 0;
        this.pvpPoint = 0;
        this.Nickname = Nickname;
        this.UUID = UUID;
    }

    public long getPlayTime() { return PlayTime; }
    public long getPvpPoint() { return pvpPoint; }
    public long getWildPoint() { return WildPoint; }
    public String getNickname() { return Nickname; }
    public UUID getUUID() { return UUID; }

    public void addPlayTime(long time) { this.PlayTime += time; }
    public void addPvpPoint(long point) { this.pvpPoint += point; }
    public void addWildPoint(long point) { this.WildPoint += point; }
    public void setNickname(String Nickname) { this.Nickname = Nickname; }
    public void setUUID(UUID UUID) { this.UUID = UUID; }
}
