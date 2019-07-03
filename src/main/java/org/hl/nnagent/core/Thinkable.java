package org.hl.nnagent.core;

/**
 * 思考 y = f(x)
 */
public interface Thinkable {
    Idea think(Feeling feeling, Idea idea);
}
