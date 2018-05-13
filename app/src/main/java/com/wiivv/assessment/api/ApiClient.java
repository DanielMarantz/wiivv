package com.wiivv.assessment.api;

import android.content.Context;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Field;
import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.cache.normalized.CacheKey;
import com.apollographql.apollo.cache.normalized.CacheKeyResolver;
import com.apollographql.apollo.cache.normalized.NormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy;
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory;
import com.apollographql.apollo.cache.normalized.sql.ApolloSqlHelper;
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * ApiClient instantiates the service connection.
 */
public class ApiClient {

    private static final String TAG = ApiClient.class.getSimpleName();
    private static final String SQL_CACHE_NAME = "wiivvdb";

    private NormalizedCacheFactory mNormalizedCacheFactory;
    private CacheKeyResolver mCacheKeyResolver;
    private ApolloClient mApolloClient;

    private Context mContext;

    public ApiClient(Context context) {
        this.mContext = context;
        configureClient();
    }

    private void configureClient() {
        configureOkHttp(null);
        configureApolloCache();
        configureApollo();
    }

    private OkHttpClient configureOkHttp(final Map<String, String> header) {

        Interceptor interceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder builder = chain.request().newBuilder();
                if (header != null && header.size() > 0) {
                    for (String key : header.keySet()) {
                        builder.addHeader(key, String.valueOf(header.get(key)));
                    }
                }
                //   builder.header("User-Agent", "android-" + DeviceUtil.getVersionName());
                //  builder.header("DEVTOKEN", DeviceUtil.getAndroidID());

                Request request = builder.build();
                return chain.proceed(request);
            }
        };
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor).addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private void configureApolloCache() {
        ApolloSqlHelper apolloSqlHelper = new ApolloSqlHelper(mContext, SQL_CACHE_NAME);
        mNormalizedCacheFactory = new LruNormalizedCacheFactory(EvictionPolicy.NO_EVICTION,
                new SqlNormalizedCacheFactory(apolloSqlHelper));

        mCacheKeyResolver = new CacheKeyResolver() {

            @Nonnull
            @Override
            public CacheKey fromFieldRecordSet(@Nonnull Field field, @Nonnull Map<String, Object> map) {
                String typeName = (String) map.get("__typename");
                if ("User".equals(typeName)) {
                    String userKey = typeName + "." + map.get("login");
                    return CacheKey.from(userKey);
                }
                if (map.containsKey("id")) {
                    String typeNameAndIDKey = map.get("__typename") + "." + map.get("id");
                    return CacheKey.from(typeNameAndIDKey);
                }
                return CacheKey.NO_KEY;
            }

            @Nonnull
            @Override
            public CacheKey fromFieldArguments(@Nonnull Field field, @Nonnull Operation.Variables variables) {
                return CacheKey.NO_KEY;
            }
        };
    }

    private void configureApollo() {
        mApolloClient = ApolloClient.builder()
                .serverUrl(ApiConfig.BASE_URL)
                .okHttpClient(configureOkHttp(null))
                .normalizedCache(mNormalizedCacheFactory, mCacheKeyResolver)
                .build();
    }

    public ApolloClient getApolloClient() {
        return mApolloClient;
    }
}
