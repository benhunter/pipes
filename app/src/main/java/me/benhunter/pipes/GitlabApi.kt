package me.benhunter.pipes

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface GitlabApi {

    @GET("/api/v4/groups\\?all_available\\=true\\&top_level_only\\=false\\&per_page\\=100\\&page=0")
    suspend fun getGroups(): GitlabGroupsResponse
    // HEADERS=$(http --print=h GET https://gitlab.create.army.mil/api/v4/groups\?all_available\=true\&top_level_only\=false\&per_page\=100\&page=0 PRIVATE-TOKEN:"$GITLAB_TOKEN")

    class GitlabGroupsResponse(val data: GitlabGroupsData)
    class GitlabGroupsData(
        val children: List<GitlabGroup>
    )

    companion object {

        fun create(account: Account): GitlabApi {
            val httpUrl = HttpUrl.Builder().scheme("https").host(account.server).build()
            val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                // Enable basic HTTP logging to help with debugging.
                this.level = HttpLoggingInterceptor.Level.BASIC
            }).addInterceptor(Interceptor {
                it.run {
                    proceed(
                        request().newBuilder().addHeader("PRIVATE-TOKEN", account.token).build()
                    )
                }
            })
                .build()
            return Retrofit.Builder().baseUrl(httpUrl).client(client)
                // testing json conversion here
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitlabApi::class.java)
        }
    }
}