package com.thaddycat.gradletest.backend;

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
        this.sp = 0;
    }

    public ResourcePoints(int hp, int mp, int ap) {
        this.hp = hp;
        this.mp = mp;
        this.ap = ap;
        this.xp = 0;
        this.sp = 0;
    }

    public ResourcePoints(int hp, int mp) {
        this.hp = hp;
        this.mp = mp;
        this.ap = 0;
        this.xp = 0;
        this.sp = 0;
    }

    public ResourcePoints(int hp) {
        this.hp = hp;
        this.mp = 0;
        this.ap = 0;
        this.xp = 0;
        this.sp = 0;
    }

    public ResourcePoints() {
        this.hp = 100;
        this.mp = 0;
        this.ap = 0;
        this.xp = 0;
        this.sp = 0;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return this.mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getAp() {
        return this.ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getSp() {
        return this.sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }
}
