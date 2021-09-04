package com.nab.weatherforecast.data.di

import com.google.gson.Gson
import com.nab.weatherforecast.data.remote.RemoteSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

internal const val API_KEY_CONFIG = "DATA_PROVIDER_API_CONFIG"
private const val URL_CONFIG = "DATA_PROVIDER_URL_CONFIG"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
//        sslCertificates: SSLCertificates
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
//            .certificatePinner(
//                CertificatePinner.Builder()
//                    .add(sslCertificates.domainPattern, sslCertificates.cert1)
//                    .add(sslCertificates.domainPattern, sslCertificates.cert2)
//                    .add(sslCertificates.domainPattern, sslCertificates.cert3)
//                    .build()
//            )
            .build()
    }

    @Provides
    @Singleton
    @Named(URL_CONFIG)
    fun provideBaseUrl(dataConfigs: DataConfigs): String = dataConfigs.baseUrl

    @Provides
    @Singleton
    @Named(API_KEY_CONFIG)
    fun provideApiKey(dataConfigs: DataConfigs): String = dataConfigs.apiKey

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @Named(URL_CONFIG) baseUrl: String,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteSource(retrofit: Retrofit): RemoteSource {
        return retrofit.create(RemoteSource::class.java)
    }

}