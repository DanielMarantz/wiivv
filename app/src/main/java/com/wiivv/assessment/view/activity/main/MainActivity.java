package com.wiivv.assessment.view.activity.main;

import android.os.Bundle;
import com.test.realtor.assessment.R;
import com.wiivv.assessment.di.component.DaggerJokeViewComponent;
import com.wiivv.assessment.di.component.JokeViewComponent;
import com.wiivv.assessment.di.module.ApiModule;
import com.wiivv.assessment.view.activity.base.BaseActivity;
import com.wiivv.assessment.view.fragment.main.JokeFragment;

public class MainActivity extends BaseActivity {

    private JokeViewComponent mJokeViewComponent;

    private JokeFragment mJokeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialInjector();
    }

    public void initialInjector() {
        mJokeViewComponent = DaggerJokeViewComponent.builder()
                .baseWiivvComponent(getMainApplication().getMainComponent())
                .apiModule(new ApiModule(this))
                .build();
        mJokeViewComponent.inject(this);
    }

    public JokeViewComponent getJokeViewComponent() {
        return mJokeViewComponent;
    }

}
