package org.hl.nnagent.bug;

import org.hl.nnagent.core.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * 虫子地图
 */
public class BugMap implements Environment {
    private List<Food> foods = new ArrayList<>();
    private List<Bug> bugs = new ArrayList<>();
    private List<BugMark> marks = new ArrayList<>();

    private int width;
    private int height;

    public BugMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }

    public List<BugMark> getMarks() {
        return marks;
    }

    public void setMarks(List<BugMark> marks) {
        this.marks = marks;
    }

    public void addBug(Bug bug) {
        this.bugs.add(bug);
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }

    public void addMark(BugMark mark) {
        this.marks.add(mark);
    }

    /**
     * 判断位置是否在地图范围内
     * @param x
     * @param y
     * @return
     */
    public boolean inBound(int x, int y) {
        return x>=0 && y>=0 && x<width && y<height;
    }
}
