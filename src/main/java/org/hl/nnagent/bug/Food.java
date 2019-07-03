package org.hl.nnagent.bug;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 食物包含的能量
 */
public class Food {

    private static AtomicInteger idCount = new AtomicInteger();

    /**
     * 坐标
     */
    private int posX;
    /**
     * 坐标
     */
    private int posY;
    /**
     * 包含的能量
     */
    private int energy;

    private int id;


    public Food(int energy) {
        this.energy = energy;
        this.id = idCount.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
}
