package com.robertvargic.githubusersearch.networking

import com.google.gson.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetrofitUtil {

    companion object {

        private const val BASE_URL = "https://api.github.com/"
        var retrofit: Retrofit? = null

        private val DATE_JSON_SERIALIZER = JsonSerializer<Date> { src, typeOfSrc, context -> if (src == null) null else JsonPrimitive(src.time) }
        private val DATE_JSON_DESERIALIZER = JsonDeserializer<Date> { json, typeOfT, context -> if (json == null) null else Date(json.asLong) }

        fun createRetrofit(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(getGson()))
                        .client(okHttpClient())
                        .build()
            }
            return retrofit
        }

        private fun getGson(): Gson {
            return getCommonBuilder()
                    .create()
        }

        private fun getCommonBuilder(): GsonBuilder {
            return GsonBuilder()
                    .registerTypeAdapter(Date::class.java, DATE_JSON_SERIALIZER)
                    .registerTypeAdapter(Date::class.java, DATE_JSON_DESERIALIZER)
        }

        private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private fun okHttpClient(): OkHttpClient =
                OkHttpClient.Builder().apply {
                    addInterceptor(provideLoggingInterceptor())
                }.build()
    }
}