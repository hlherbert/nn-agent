package org.hl.nnagent.core;

/**
 * 运动函数 z = h(y)
 */
public interface Moveable {
    Movement move(Idea idea);
}
