package com.medvedomg.marvelapp.data

import com.medvedomg.marvelapp.data.Const.PUBLIC_API_KEY
import retrofit2.http.*

interface ComicsDetailsService {

    @GET("comics/{comicId}")
    suspend fun getComicDetails(
        @Path("comicId") id: String = "1308",
        @Query("apikey") apiKey: String = PUBLIC_API_KEY,
        @Query("ts") ts: String,
        @Query("hash") hash: String
    ): ComicsDetailsResponse
}