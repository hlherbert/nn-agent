package org.hl.nnagent.bug;

import org.hl.nnagent.core.Movement;

class BugMovement implements Movement {
    int direction = 0;

    public BugMovement() {

    }

    public BugMovement(int direction) {
        this.direction = direction;
    }
}
