package com.wiivv.assessment.di.module;

import dagger.Module;

/**
 * ApiModule will be used by Dagger to implement JokeViewComponent.
 *
 * @Module: Modules are classes whose methods provide dependencies, so we define a class and annotate it with @Module,
 * thus, Dagger will know where to find the dependencies in order to satisfy them when constructing class instances.
 * One important feature of modules is that they have been designed to be partitioned and composed together
 * for instance we will see that in apps we can have multiple composed modules.
 */
@Module
public class ApiModule {

}
