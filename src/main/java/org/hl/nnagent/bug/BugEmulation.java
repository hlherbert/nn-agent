package org.hl.nnagent.bug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 虫子找食物的模拟
 */
public class BugEmulation {

    private static final Logger logger = LoggerFactory.getLogger(BugEmulation.class);


    private static Random random = new Random();

    /**
     * 模拟一个虫子一个食物
     */
    private static void oneBugOneFood() {
        BugMap bugMap = new BugMap(100,100);
        Bug bug = new Bug(bugMap,1000);
        bug.setPosition(random.nextInt(bugMap.getWidth()), random.nextInt(bugMap.getHeight()));

        Food food = new Food(100);
        food.setPosition(bugMap.getWidth()-1, bugMap.getHeight()-1);

        bugMap.addFood(food);
        bugMap.addBug(bug);

        logger.info("bug living...");
        bug.live();
        logger.info("bug die at: x={},y={},energy={}", bug.getPosX(), bug.getPosY(), bug.getEnergy());
        BugDraw.drawMap(bugMap);
    }

    /**
     * 模拟一个虫子一个食物
     */
    private static void oneBugManyFood(int nFood) {
        BugMap bugMap = new BugMap(100,100);
        Bug bug = new Bug(bugMap,1000);
        bug.setPosition(random.nextInt(bugMap.getWidth()), random.nextInt(bugMap.getHeight()));

        for (int i=0;i<nFood;i++) {
            Food food = new Food(100);
            food.setPosition(random.nextInt(bugMap.getWidth()), random.nextInt(bugMap.getHeight()));
            bugMap.addFood(food);
            bugMap.addBug(bug);
        }

        logger.info("bug living...");
        bug.live();
        logger.info("bug die at: x={},y={},energy={}", bug.getPosX(), bug.getPosY(), bug.getEnergy());
        BugDraw.drawMap(bugMap);
    }

    public static void main(String[] args) {
        //oneBugOneFood();
        oneBugManyFood(5);
    }
}
