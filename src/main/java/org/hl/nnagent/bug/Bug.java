package org.hl.nnagent.bug;

import org.hl.nnagent.core.Environment;
import org.hl.nnagent.core.Feeling;
import org.hl.nnagent.core.Idea;
import org.hl.nnagent.core.Movement;
import org.hl.nnagent.simple.AbstractSimpleAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 虫子
 */
public class Bug extends AbstractSimpleAgent {

    private static final int THINK_ENERGY_COST = 1;
    private static final int MOVE_ENERGY_COST = 1;
    private static final int PERCEPT_ENERGY_COST = 1;

    private static final Logger logger = LoggerFactory.getLogger(Bug.class);

    private static AtomicInteger idCount = new AtomicInteger();

    /**
     * 能量
     */
    private double energy = 1000;

    /**
     * 当前坐标X
     */
    private int posX;

    /**
     * 当前坐标Y
     */
    private int posY;

    /**
     * id
     */
    private int id;


    public Bug(BugMap bugMap, int energy) {
        this.e = bugMap;
        this.x = new BugFeeling();
        this.y = new BugIdea();
        this.z = new BugMovement();
        this.energy = energy;
        this.id = idCount.getAndIncrement();
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;

        // 留下标记
        BugMap map = (BugMap) e;
        map.addMark(new BugMark(posX, posY, id, BugMarkType.BUG_START));
    }


    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
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

    @Override
    public boolean isLive() {
        return energy > 0;
    }

    @Override
    public Environment impact(Environment environment, Movement movement) {
        Position2D newPos = new Position2D(posX, posY);
        BugMovement movement1 = (BugMovement) movement;
        double dx = 0;
        double dy = 0;
        switch (movement1.direction) {
            case 1:
                dy = -1;
                break;
            case 2:
                dx = 1;
                break;
            case 3:
                dy = 1;
                break;
            case 4:
                dx = -1;
                break;
        }
        newPos.x += dx;
        newPos.y += dy;

        // 移动虫子
        BugMap map = (BugMap) environment;
        if (map.inBound(newPos.x, newPos.y)) {
            posX = newPos.x;
            posY = newPos.y;
            if (dx != 0 || dy != 0) {
                logger.info("bug {} moved to ({},{})", id, posX, posY);
                // 留下标记
                map.addMark(new BugMark(posX, posY, id, BugMarkType.BUG));
            } else {
                logger.info("bug {} stay.", id);
            }
        }

        // 吃东西
        Iterator<Food> foodIterator = map.getFoods().iterator();
        while (foodIterator.hasNext()) {
            Food food = foodIterator.next();
            if (food.getPosX() == posX && food.getPosY() == posY) {
                //该食物被虫子吃了, 吸收食物的能量
                this.energy += food.getEnergy();
                foodIterator.remove();
                logger.info("bug {} eat food {}, get energy {}", id, food.getId(), food.getEnergy());
                // 留下标记
                map.addMark(new BugMark(posX, posY, id, BugMarkType.FOOD));
            }
        }

        return map;
    }

    @Override
    public void learn(Idea oldIdea, Idea idea) {
        // do nothing
    }

    @Override
    public Movement move(Idea idea) {
        BugIdea bugIdea = (BugIdea) idea;

        this.energy -= MOVE_ENERGY_COST; //减少能量
        return new BugMovement(bugIdea.direction);
    }

    @Override
    public Feeling perceive(Environment e) {
        BugMap map = (BugMap) e;
        List<Food> foods = map.getFoods();

        Position2D p0 = new Position2D(posX, posY);
        Position2D p1 = new Position2D(posX, posY - 1);
        Position2D p2 = new Position2D(posX + 1, posY);
        Position2D p3 = new Position2D(posX, posY + 1);
        Position2D p4 = new Position2D(posX - 1, posY);
        Position2D[] poss = {p0, p1, p2, p3, p4};

        BugFeeling feeling = (BugFeeling) x;
        Arrays.fill(feeling.smell, 0);

        // 闻本身位置以及上下左右4个方向位置的气味浓度
        for (int i = 0; i <= 4; i++) {
            //对5个候选行动位置，累加每个食物的气味浓度
            if (foods.isEmpty()) {
                break;
            }
            for (Food food : foods) {
                double distToFood = poss[i].distTo(food.getPosX(), food.getPosY());
                double smell = food.getEnergy() * Math.exp(-distToFood);
                feeling.smell[i] += smell;
            }
        }

        //logger.info("bug {} feel ({})",id,feeling.smell);
        this.energy -= PERCEPT_ENERGY_COST;
        return feeling;
    }

    @Override
    public Idea think(Feeling feeling, Idea idea) {
        BugFeeling feeling1 = (BugFeeling) feeling;
        BugIdea bugIdea = new BugIdea();
        int direction = 0;
        double maxSmell = 0;
        for (int i = 0; i <= 4; i++) {
            if (feeling1.smell[i] > maxSmell) {
                direction = i;
                maxSmell = feeling1.smell[i];
            }
        }
        bugIdea.direction = direction;

        this.energy -= THINK_ENERGY_COST;
        return bugIdea;
    }
}
