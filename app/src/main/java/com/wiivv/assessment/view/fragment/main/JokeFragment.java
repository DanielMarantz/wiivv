package com.wiivv.assessment.view.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;

import com.wiivv.assessment.presenter.main.JokePresenter;
import com.wiivv.assessment.view.activity.main.MainActivity;
import com.wiivv.assessment.view.fragment.base.BaseFragmentWithButterKnife;
import com.wiivv.assessment.view.panel.JokePanel;

import javax.inject.Inject;


/**
 * JokeFragment displays and searches for jokes.
 * <p>
 * JokeViewComponent is built in the parent activity
 * and injected into the fragment.
 * <p>
 * Note: Fragments are the interaction with the user and the
 * view layer. User interactions are propagated to the presentation
 * layer for heavier processing.
 */
public class JokeFragment extends BaseFragmentWithButterKnife implements JokePanel {

    @Inject
    JokePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialInjector();
        initialPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialView();
    }

    @Override
    public int getLayoutResId() {
        return 0;
    }

    private void initialInjector() {
        ((MainActivity) getActivity()).getJokeViewComponent().inject(this);
    }

    private void initialPresenter() {
        presenter.setPanel(this);
    }

    private void initialView() {

    }


    @Override
    public void renderJoke(String joke) {

    }

    @Override
    public void renderError() {

    }
}
