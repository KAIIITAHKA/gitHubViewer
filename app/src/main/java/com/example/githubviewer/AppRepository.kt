package com.example.githubviewer

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

class AppRepository(private val keyValueStorage: KeyValueStorage) {
    // suspend fun getRepositories(): List<Repo>{}
    //suspend fun getRepository(repoId: String): Repodetails{}
    // suspend fun getRepositoryReadme(ownerName: String,repositoryName:String,branchName:String):String{}

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(
            OkHttpClient.Builder().addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${keyValueStorage.authToken}").build()
                it.proceed(request)
            }.build()
        ).build()

    suspend fun signIn(token: String): UserInfo {
        keyValueStorage.authToken = token
        val gitHubClient = retrofitBuilder.create(GithubApi::class.java)
        try {
            return gitHubClient.signIn()
        } catch (error: Throwable) {
            keyValueStorage.authToken = ""
            throw error
        }

    }

}

// создали модель данных
class UserInfo(
    @SerializedName("id") //аннотация. Как имя поля в JSON
    val userId: Int,
    @SerializedName("login")
    val name: String
)

// интерфейс для ретрофита
interface GithubApi {
    @GET("user")
    suspend fun signIn(): UserInfo
}