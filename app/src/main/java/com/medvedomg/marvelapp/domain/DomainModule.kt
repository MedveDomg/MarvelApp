package com.medvedomg.marvelapp.domain

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DispatchersName {
    const val IO = "DispatcherIO"
    const val Main = "DispatcherMain"
    const val Immediate = "DispatcherImmediate"
}

internal val domainModule = module {

    single(named(DispatchersName.IO)) { Dispatchers.IO }

    single(named(DispatchersName.Main)) { Dispatchers.Main }

    single(named(DispatchersName.Immediate)) { Dispatchers.Main.immediate }

    factory {
        GetDetailsUseCase(
            coroutineDispatcher = get(qualifier = named(DispatchersName.IO)),
            detailsRepository = get()
        )
    }
}