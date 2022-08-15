package com.medvedomg.marvelapp.data

import com.medvedomg.marvelapp.data.Const.PRIVATE_API_KEY

interface DetailsRepository {

    suspend fun getDetails(): ComicsDetailsResponse
}

class DetailsRepositoryImpl(
    val retrofitService: ComicsDetailsService,
    val hashGenerator: HashGenerator
) : DetailsRepository {

    override suspend fun getDetails(): ComicsDetailsResponse {
        val time = System.currentTimeMillis().toString()
        val hash = "$time${PRIVATE_API_KEY}${Const.PUBLIC_API_KEY}"
        return retrofitService.getComicDetails(
            ts = time,
            hash = hashGenerator.buildMD5Digest(hash)
        )
    }
}