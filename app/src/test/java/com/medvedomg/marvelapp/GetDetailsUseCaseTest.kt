package com.medvedomg.marvelapp

import com.medvedomg.marvelapp.data.*
import com.medvedomg.marvelapp.domain.GetDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import io.mockk.coEvery
import io.mockk.mockk
import com.medvedomg.marvelapp.domain.util.Result
import com.medvedomg.marvelapp.presentation.details.DetailsModel

class GetDetailsUseCaseTest : Spek({
    Feature(GetDetailsUseCase::class.java.simpleName) {
        Scenario("when repository returns not correct object") {
            var result: Result<DetailsModel> = runBlocking {
                GetDetailsUseCase(
                    coroutineDispatcher = Dispatchers.Default,
                    detailsRepository = mockErrorRepository()
                ).invoke(Unit)
            }
            Then("should receive Error") {
                Assert.assertTrue("result: $result", result is Result.Error)
            }
        }
        Scenario("when repository returns proper object") {
            lateinit var useCase: GetDetailsUseCase
            Given("usecase with success") {
                useCase = GetDetailsUseCase(
                    coroutineDispatcher = Dispatchers.Default,
                    detailsRepository = mockOkRepository()
                )
            }
            Then("should receive success") {
                runBlocking {
                    val result = useCase.invoke(Unit)
                    Assert.assertTrue("result: $result", result is Result.Success)
                }
            }
        }
    }
})

private fun mockErrorRepository(): DetailsRepository {
    val errorRepository = mockk<DetailsRepository>()
    coEvery {
        errorRepository.getDetails()
    }.throws(Throwable("404 exception"))
    return errorRepository
}

private fun mockOkRepository(): DetailsRepository {
    val errorRepository = mockk<DetailsRepository>()
    coEvery {
        errorRepository.getDetails()
    } returns ComicsDetailsResponse(
        data = DataResponse(
            results = listOf(
                ResultResponse(
                    title = "title",
                    description = "description",
                    thumbnail = ThumbnailResponse(
                        path = "path",
                        extension = "extension"
                    )
                )
            )
        )
    )
    return errorRepository
}