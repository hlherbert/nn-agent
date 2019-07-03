package org.hl.nnagent.bug;

/**
 * 虫子的标记，用于画图
 */
public class BugMark {
    int x;
    int y;
    int id;
    BugMarkType type;

    public BugMark(int x, int y, int id, BugMarkType type) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.type = type;
    }
}
