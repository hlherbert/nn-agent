package org.hl.nnagent.bug;

import org.hl.nnagent.core.Feeling;

class BugFeeling implements Feeling {
    /**
     * 5个方向的食物气味浓度
     * [本身，上，右，下，做]
     */
    double[] smell = new double[5];
}
