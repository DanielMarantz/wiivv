package com.wiivv.assessment.view.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.realtor.assessment.R;
import com.wiivv.assessment.presenter.main.JokePresenter;
import com.wiivv.assessment.view.activity.main.MainActivity;
import com.wiivv.assessment.view.fragment.base.BaseFragmentWithButterKnife;
import com.wiivv.assessment.view.panel.JokePanel;

import javax.inject.Inject;

import butterknife.BindView;

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

    @BindView(R.id.joke_collapsing)
    CollapsingToolbarLayout collapsingToolbarView;
    @BindView(R.id.joke_search)
    SearchView searchView;
    @BindView(R.id.joke_fab)
    FloatingActionButton fabView;
    @BindView(R.id.joke_content)
    TextView questionTextView;

    @BindView(R.id.joke_progress_bar)
    ProgressBar progressBar;

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
        getDefaultJoke();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_joke;
    }

    private void initialInjector() {
        ((MainActivity) getActivity()).getJokeViewComponent().inject(this);
    }

    private void initialPresenter() {
        presenter.setPanel(this);
    }

    private void initialView() {
        initialToolbar();
        initialSearchView();
        initialListeners();
    }

    private void initialToolbar() {
        collapsingToolbarView.setTitle(getString(R.string.collapsing_toolbar_title));
    }

    private void initialSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                showProgressSpinner(true);
                presenter.searchJoke(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setIconified(false);
        searchView.requestFocus();
    }

    private void initialListeners() {
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            }
        });
        fabView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchView.setIconified(false);
                searchView.requestFocus();
            }
        });
    }

    private void getDefaultJoke() {
        presenter.searchJoke(getString(R.string.default_search_term));
    }

    private void showProgressSpinner(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void renderJoke(String joke) {
        showProgressSpinner(false);
        questionTextView.setText(joke);
    }

    @Override
    public void renderError() {
        showProgressSpinner(false);
        showSnackBar(getView(), getString(R.string.no_search_results));
    }
}
