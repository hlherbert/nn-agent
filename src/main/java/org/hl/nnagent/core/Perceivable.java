package org.hl.nnagent.core;

/**
 * 感知函数 x = p(e)
 */
public interface Perceivable {
    Feeling perceive(Environment e);
}
