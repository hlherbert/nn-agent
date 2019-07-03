package org.hl.nnagent.bug;

import java.awt.*;

enum BugMarkType {
    FOOD('F', Color.cyan),     //食物
    FOOD_EATEN('E', Color.red),     //已经被吃了的食物
    BUG('.', Color.green),      //虫子
    BUG_START('S', Color.blue); //虫子起点

    private char symbol;
    private Color color;


    BugMarkType(char symbol, Color color) {
        this.symbol = symbol;
        this.color = color;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}