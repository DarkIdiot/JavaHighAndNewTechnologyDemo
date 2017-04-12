package com.dark.java7;


enum State {
    /**
     * A service in this state is inactive. It does minimal work and consumes
     * minimal resources.
     */
    NEW {
      @Override boolean isTerminal() {
        return false;
      }
    },

    /**
     * A service in this state is transitioning to {@link #RUNNING}.
     */
    STARTING {
      @Override boolean isTerminal() {
        return false;
      }
    },

    /**
     * A service in this state is operational.
     */
    RUNNING {
      @Override boolean isTerminal() {
        return false;
      }
    },

    /**
     * A service in this state is transitioning to {@link #TERMINATED}.
     */
    STOPPING {
      @Override boolean isTerminal() {
        return false;
      }
    },

    /**
     * A service in this state has completed execution normally. It does minimal work and consumes
     * minimal resources.
     */
    TERMINATED {
      @Override boolean isTerminal() {
        return true;
      }
    },

    /**
     * A service in this state has encountered a problem and may not be operational. It cannot be
     * started nor stopped.
     */
    FAILED {
      @Override boolean isTerminal() {
        return true;
      }
    };
    
    /** Returns true if this state is terminal. */
    abstract boolean isTerminal();
  }
