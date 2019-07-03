package org.hl.nnagent.core;

/**
 * 学习函数 w' = g(y, y')
 */
public interface Learnable {
    void learn(Idea oldIdea, Idea idea);
}
