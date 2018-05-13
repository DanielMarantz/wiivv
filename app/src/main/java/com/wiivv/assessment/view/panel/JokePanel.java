package com.wiivv.assessment.view.panel;

/**
 * JokePanel interfaces the callback methods
 * for the JokeFragment.
 *
 * This is the communication technique for presentation layer to
 * the view layer.
 */
public interface JokePanel {
    void renderJoke(String joke);
    void renderError();
}
