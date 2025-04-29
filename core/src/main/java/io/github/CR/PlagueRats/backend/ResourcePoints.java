package io.github.CR.PlagueRats.backend;

public class ResourcePoints {
    private int hp, mp, ap, xp, sp;

    public ResourcePoints(int hp, int mp, int ap, int xp, int sp) {
        this.hp = hp;
        this.mp = mp;
        this.ap = ap;
        this.xp = xp;
        this.sp = sp;
    }

    public ResourcePoints(int hp, int mp, int ap, int xp) {
        this.hp = hp;
        this.mp = mp;
        this.ap = ap;
        this.xp = xp;
        sp = 0;
    }

    public ResourcePoints(int hp, int mp, int ap) {
        this.hp = hp;
        this.mp = mp;
        this.ap = ap;
        xp = 0;
        sp = 0;
    }

    public ResourcePoints(int hp, int mp) {
        this.hp = hp;
        this.mp = mp;
        ap = 0;
        xp = 0;
        sp = 0;
    }

    public ResourcePoints(int hp) {
        this.hp = hp;
        mp = 0;
        ap = 0;
        xp = 0;
        sp = 0;
    }

    public ResourcePoints() {
        hp = 100;
        mp = 0;
        ap = 0;
        xp = 0;
        sp = 0;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }
}
