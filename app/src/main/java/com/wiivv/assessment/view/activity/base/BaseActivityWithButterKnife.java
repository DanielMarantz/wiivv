package com.wiivv.assessment.view.activity.base;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activities that utilize ButterKnife bindings need to extended this class
 * to bind and unbind the ButterKnife references.
 */
public class BaseActivityWithButterKnife extends BaseActivity {

    private Unbinder unbinder;

    @Override
    public void setContentView(final int layoutResourceId) {
        super.setContentView(layoutResourceId);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
