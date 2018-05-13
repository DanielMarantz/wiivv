package com.wiivv.assessment.di.component;

import com.wiivv.assessment.di.module.ApiModule;
import com.wiivv.assessment.di.scope.PerSection;
import com.wiivv.assessment.view.activity.main.MainActivity;
import com.wiivv.assessment.view.fragment.main.JokeFragment;

import dagger.Component;

/**
 * JokeViewComponent will be used by Dagger to generate dependencies
 * for presentation layer.
 *
 * @Component: Used on an interface. This interface is used by Dagger 2
 * to generate code which uses the modules to fulfill the requested dependencies.
 */
@PerSection
@Component(dependencies = BaseWiivvComponent.class, modules = {
        ApiModule.class
})

public interface JokeViewComponent {

    void inject(MainActivity mainActivity);

    void inject(JokeFragment jokeFragment);
}
