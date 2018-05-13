package com.wiivv.assessment;

import android.app.Application;

import com.wiivv.assessment.di.component.BaseWiivvComponent;
import com.wiivv.assessment.di.component.DaggerBaseWiivvComponent;
import com.wiivv.assessment.di.module.BaseWiivvModule;
import com.wiivv.assessment.di.module.MainApplicationModule;

public class MainApplication extends Application {

    private BaseWiivvComponent baseWiivvComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialInjector();
    }

    /**
     * This method will implement BaseWiivvComponent which provides main
     * singleton dependencies in application life scope.
     */
    private void initialInjector() {
        baseWiivvComponent = DaggerBaseWiivvComponent.builder()
                .mainApplicationModule(new MainApplicationModule(this))
                .baseWiivvModule(new BaseWiivvModule(this))
                .build();
    }

    public BaseWiivvComponent getMainComponent() {
        return baseWiivvComponent;
    }
}
