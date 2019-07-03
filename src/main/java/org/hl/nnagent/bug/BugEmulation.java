package org.hl.nnagent.bug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 虫子找食物的模拟
 */
public class BugEmulation {

    private static final Logger logger = LoggerFactory.getLogger(BugEmulation.class);


    private static Random random = new Random();

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        //oneBugOneFood();
        //oneBugManyFood(5);
        BugDraw2D draw2D = new BugDraw2D();
    }

    /**
     * 模拟一个虫子一个食物
     */
    public static void oneBugOneFood(BugDraw2D bugDraw2D) {
        BugMap bugMap = new BugMap(100, 100);
        bugMap.addObserver(bugDraw2D);
        bugMap.notifyObservers();

        Bug bug = new Bug(bugMap, 1000);
        bug.setPosition(random.nextInt(bugMap.getWidth()), random.nextInt(bugMap.getHeight()));

        Food food = new Food(100);
        food.setPosition(bugMap.getWidth() - 1, bugMap.getHeight() - 1);

        bugMap.addFood(food);
        bugMap.addBug(bug);

        logger.info("bug living...");
        executorService.execute(() -> bug.live());
        logger.info("bug die at: x={},y={},energy={}", bug.getPosX(), bug.getPosY(), bug.getEnergy());
        //BugDraw.drawMap(bugMap);
    }

    public static void oneBugManyFood(BugDraw2D bugDraw2D) {
        oneBugManyFood(5, bugDraw2D);
    }

    public static void manyBugManyFood(BugDraw2D bugDraw2D) {
        manyBugManyFood(3, 5, bugDraw2D);
    }

    /**
     * 模拟一个虫子多个食物
     */
    private static void oneBugManyFood(int nFood, BugDraw2D bugDraw2D) {
        BugMap bugMap = new BugMap(100, 100);
        bugMap.addObserver(bugDraw2D);
        bugMap.notifyObservers();

        Bug bug = new Bug(bugMap, 1000);
        bug.setPosition(random.nextInt(bugMap.getWidth()), random.nextInt(bugMap.getHeight()));
        bugMap.addBug(bug);

        for (int i = 0; i < nFood; i++) {
            Food food = new Food(100);
            food.setPosition(random.nextInt(bugMap.getWidth()), random.nextInt(bugMap.getHeight()));
            bugMap.addFood(food);
        }

        logger.info("bug living...");
        executorService.execute(() -> bug.live());
        logger.info("bug die at: x={},y={},energy={}", bug.getPosX(), bug.getPosY(), bug.getEnergy());
        //BugDraw.drawMap(bugMap);
    }

    /**
     * 模拟一个虫子一个食物
     */
    private static void manyBugManyFood(int nBug, int nFood, BugDraw2D bugDraw2D) {
        BugMap bugMap = new BugMap(100, 100);
        bugMap.addObserver(bugDraw2D);
        bugMap.notifyObservers();

        for (int i = 0; i < nFood; i++) {
            Food food = new Food(100);
            food.setPosition(random.nextInt(bugMap.getWidth()), random.nextInt(bugMap.getHeight()));
            bugMap.addFood(food);
        }

        for (int i = 0; i < nBug; i++) {
            Bug bug = new Bug(bugMap, 1000);
            bug.setPosition(random.nextInt(bugMap.getWidth()), random.nextInt(bugMap.getHeight()));
            bugMap.addBug(bug);

            logger.info("bug living...");
            executorService.execute(() -> bug.live());
            logger.info("bug die at: x={},y={},energy={}", bug.getPosX(), bug.getPosY(), bug.getEnergy());
        }
    }



}
