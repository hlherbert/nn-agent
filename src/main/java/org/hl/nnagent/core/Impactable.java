package org.hl.nnagent.core;

/**
 * 环境影响函数 e = q(z)
 */
public interface Impactable {
    Environment impact(Environment environment, Movement movement);
}
