package com.wiivv.assessment.di.module;

import android.content.Context;

import com.wiivv.assessment.di.scope.PerSection;
import com.wiivv.assessment.helper.ApiHelper;

import dagger.Module;
import dagger.Provides;

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

    private ApiHelper apiHelper;

    public ApiModule(Context context) {
        this.apiHelper = new ApiHelper(context);
    }

    @Provides
    @PerSection
    public ApiHelper providesApiHelper() {
        return apiHelper;
    }
}
