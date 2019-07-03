package org.hl.nnagent.simple;

import org.hl.nnagent.core.*;

/**
 * 简化个体模型接口
 * 环境e->p(e)感受->感觉(x) -> f(x) 思考 -> 想法(y)-g(y)学习->h(y)行动->行为(z) ->q(z)改变->环境(e)
 */
public interface SimpleAgent extends Livable, Perceivable, Thinkable, Learnable, Impactable, Moveable {

}
