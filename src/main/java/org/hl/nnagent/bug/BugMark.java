package org.hl.nnagent.bug;

/**
 * 虫子的标记，用于画图
 */
public class BugMark {
    int x;
    int y;
    int foodId;
    int bugId;
    BugMarkType type;

    public BugMark(int x, int y, int bugId, BugMarkType type) {
        this.x = x;
        this.y = y;
        this.bugId = bugId;
        this.type = type;
    }
}
