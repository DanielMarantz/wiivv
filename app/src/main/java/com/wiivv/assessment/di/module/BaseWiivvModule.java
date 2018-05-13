package com.wiivv.assessment.di.module;

import com.test.realtor.assessment.MainApplication;

import dagger.Module;

/**
 * BaseWiivvModule will be providing application level (singleton) dependencies which can be
 * instanced when MainApplication get started.
 *
 * Dagger will use BaseWiivvModule to implement BaseWiivvComponent which actually takes charge of performing injection
 *
 * @Module: Modules are classes whose methods provide dependencies, so we define a class and annotate it with @Module,
 * thus, Dagger will know where to find the dependencies in order to satisfy them when constructing class instances.
 * One important feature of modules is that they have been designed to be partitioned and composed together
 * for instance we will see that in our apps we can have multiple composed modules.
 */
@Module
public class BaseWiivvModule {

    MainApplication application;

    public BaseWiivvModule(MainApplication application){
        this.application = application;
    }
}
