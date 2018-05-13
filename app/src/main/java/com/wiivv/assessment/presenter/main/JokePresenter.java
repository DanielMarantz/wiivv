package com.wiivv.assessment.presenter.main;

import com.wiivv.assessment.di.scope.PerSection;
import com.wiivv.assessment.presenter.base.BasePresenter;
import com.wiivv.assessment.view.panel.JokePanel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * JokePresenter is the presentation layer for the JokeFragment.
 *
 * Note: Presenters keep the heavy processing off of the view layer
 * and delegate data to other areas of the application based on business
 * requirements.
 */
@PerSection
public class JokePresenter extends BasePresenter<JokePanel> {

    private static final String TAG = JokePresenter.class.getSimpleName();


    private CompositeDisposable mDisposables;

    private JokePanel mPanel;

    @Inject
    public JokePresenter() {
        this.mDisposables = new CompositeDisposable();
    }

    @Override
    public void setPanel(JokePanel panel) {

    }
}
