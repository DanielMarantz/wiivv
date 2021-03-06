package com.wiivv.assessment.helper;

import android.content.Context;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.cache.normalized.CacheControl;
import com.apollographql.apollo.sample.SearchJoke;
import com.wiivv.assessment.api.ApiClient;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * ApiHelper is a com.wiivv.assessment.helper for the GraphQL queries.
 * <p>
 * NOTE: Helpers are injected into the presenters and should
 * not touch the view layer.
 */
@Singleton
public class ApiHelper {

    private ApiClient mApiClient;

    @Inject
    public ApiHelper(Context context) {
        mApiClient = new ApiClient(context);
    }

    public ApolloClient getApolloClient() {
        return mApiClient.getApolloClient();
    }

    public ApolloCall<SearchJoke.Data> searchJoke(String keyword) {
        return getApolloClient().query(new SearchJoke(keyword))
                .cacheControl(CacheControl.CACHE_FIRST);
    }
}
