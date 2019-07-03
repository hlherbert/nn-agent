package org.hl.nnagent.simple;

import org.hl.nnagent.core.Environment;
import org.hl.nnagent.core.Feeling;
import org.hl.nnagent.core.Idea;
import org.hl.nnagent.core.Movement;

/**
 * 简化个体模型抽象实现类
 * 环境e->p(e)感受->感觉(x) -> f(x) 思考 -> 想法(y)-g(y)学习->h(y)行动->行为(z) ->q(z)改变->环境(e)
 */
public abstract class AbstractSimpleAgent implements SimpleAgent {
    protected Environment e;
    protected Feeling x;
    protected Idea y;
    protected Movement z;

    @Override
    public abstract boolean isLive();

    @Override
    public abstract Environment impact(Environment environment, Movement movement);

    @Override
    public abstract void learn(Idea oldIdea, Idea idea);

    @Override
    public abstract Movement move(Idea idea);

    @Override
    public abstract Feeling perceive(Environment e);

    @Override
    public abstract Idea think(Feeling feeling, Idea idea);

    private void iFeel() {
        x = perceive(e);
    }

    private void iThink() {
        Idea y1 = think(x, y);
        learn(y, y1);
        y = y1;
    }

    private void iMove() {
        z = move(y);
        e = impact(e, z);
    }

    @Override
    public void live() {
        while (isLive()) {
            iFeel();
            iThink();
            iMove();
        }
    }
}
