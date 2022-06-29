package uz.anorbank.currencyexchange.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.anorbank.currencyexchange.BuildConfig.ANORBANK_URL
import uz.anorbank.currencyexchange.data.remote.Apis
import uz.anorbank.currencyexchange.utils.addLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val timeOut = 30L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @[Provides Singleton]
    fun getGsonObject(): Gson = Gson()

    @[Provides Singleton]
    fun getApis(retrofit: Retrofit): Apis = retrofit.create(Apis::class.java)

    @[Provides Singleton]
    fun getAnorBankRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ANORBANK_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()

    @[Provides Singleton]
    fun getOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
//            .addLoggingInterceptor(context)
            .connectTimeout(timeout = timeOut, TimeUnit.SECONDS)
            .readTimeout(timeout = timeOut, TimeUnit.SECONDS)
            .writeTimeout(timeout = timeOut, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()


}