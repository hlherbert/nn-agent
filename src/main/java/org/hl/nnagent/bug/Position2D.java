package org.hl.nnagent.bug;

/**
 * 2D坐标
 */
class Position2D {
    int x;
    int y;

    Position2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 计算该点到另一点的距离
     * @param x
     * @param y
     * @return
     */
    double distTo(int x, int y) {
       return Math.sqrt( (this.x-x)*(this.x-x)+(this.y-y)*(this.y-y) );
    }
}
