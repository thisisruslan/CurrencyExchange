package uz.anorbank.currencyexchange.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorbank.currencyexchange.domain.usecase.MainFragmentUseCase
import uz.anorbank.currencyexchange.domain.usecase.impl.MainFragmentUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun getSplashUseCase(impl: MainFragmentUseCaseImpl): MainFragmentUseCase
}

