package me.benhunter.pipes

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


class GitlabApi {

    companion object {

        // Keep the base URL simple
        //private const val BASE_URL = "https://www.reddit.com/"

        fun create(server: String): GitlabApi {

            var httpUrl = HttpUrl.Builder().scheme("https").host(server).build()
            val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                // Enable basic HTTP logging to help with debugging.
                this.level = HttpLoggingInterceptor.Level.BASIC
            }).build()
            return Retrofit.Builder().baseUrl(httpUrl).client(client).build()
                .create(GitlabApi::class.java)
        }
    }
}