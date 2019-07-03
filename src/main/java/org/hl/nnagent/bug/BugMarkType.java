package org.hl.nnagent.bug;

enum BugMarkType {
    FOOD('F'),     //食物
    BUG('.'),      //虫子
    BUG_START('S'); //虫子起点

    private char symbol;


    BugMarkType(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

}