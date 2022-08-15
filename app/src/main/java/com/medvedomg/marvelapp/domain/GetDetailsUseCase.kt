package com.medvedomg.marvelapp.domain

import com.medvedomg.marvelapp.data.DetailsRepository
import com.medvedomg.marvelapp.presentation.details.DetailsModel
import com.medvedomg.marvelapp.domain.util.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetDetailsUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    val detailsRepository: DetailsRepository
) : CoroutineUseCase<Unit, DetailsModel>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): DetailsModel {
        val response = detailsRepository.getDetails().data.results.first()
        return DetailsModel(
            title = response.title,
            description = response.description,
            imageUrl = response.thumbnail.path.replace("http", "https")
                    + "."
                    + response.thumbnail.extension
        )
    }
}