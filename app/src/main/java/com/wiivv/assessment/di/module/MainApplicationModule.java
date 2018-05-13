package com.wiivv.assessment.di.module;

import com.test.realtor.assessment.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * MainApplicationModule will be used by Dagger to implement BaseWiivvComponent.
 *
 * @Module: Modules are classes whose methods provide dependencies, so we define a class and annotate it with @Module,
 * thus, Dagger will know where to find the dependencies in order to satisfy them when constructing class instances.
 * One important feature of modules is that they have been designed to be partitioned and composed together
 * for instance we will see that in apps we can have multiple composed modules.
 */
@Module
public class MainApplicationModule {

    private MainApplication application;

    public MainApplicationModule(MainApplication application) {
        this.application = application;
    }

    /**
     * @return MainApplication
     * @Provide: Inside modules we define methods containing this annotation which tells Dagger how we want to construct
     * and provide those mentioned dependencies.
     * @Singleton is annotation for scope control. The scope defines the alive range for the dependency. In here is Application scope.
     * The below method provides an singleton instance of MainApplication
     */
    @Provides
    @Singleton
    MainApplication providesApplication() {
        return application;
    }
}
