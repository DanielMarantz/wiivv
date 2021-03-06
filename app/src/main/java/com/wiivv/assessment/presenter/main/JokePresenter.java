package com.wiivv.assessment.presenter.main;

import android.util.Log;

import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.apollographql.apollo.sample.SearchJoke;
import com.wiivv.assessment.di.scope.PerSection;
import com.wiivv.assessment.helper.ApiHelper;
import com.wiivv.assessment.presenter.base.BasePresenter;
import com.wiivv.assessment.view.panel.JokePanel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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

    private ApiHelper mApiHelper;
    private CompositeDisposable mDisposables;

    private JokePanel mPanel;

    @Inject
    public JokePresenter(ApiHelper apiHelper) {
        this.mApiHelper = apiHelper;
        this.mDisposables = new CompositeDisposable();
    }

    @Override
    public void setPanel(JokePanel panel) {
        this.mPanel = panel;
    }

    public void searchJoke(final String keyword) {

        mDisposables.add(Rx2Apollo.from(mApiHelper.searchJoke(keyword))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<SearchJoke.Data>>() {

                    @Override
                    public void onSuccess(Response<SearchJoke.Data> dataResponse) {
                        SearchJoke.Data data = dataResponse.data();
                        SearchJoke.Joke jokeData = data.joke();

                        if(jokeData != null) {
                            Log.d(TAG, "searchJoke onSuccess");
                            mPanel.renderJoke(jokeData.joke());
                        } else {
                            Log.e(TAG, "searchJoke No Result");
                            mPanel.renderError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                        mPanel.renderError();
                    }
                }));
    }
}
