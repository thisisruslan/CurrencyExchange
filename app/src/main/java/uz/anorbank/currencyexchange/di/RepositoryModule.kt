package uz.anorbank.currencyexchange.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorbank.currencyexchange.domain.repository.MainRepository
import uz.anorbank.currencyexchange.domain.repository.impl.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun getAppRepository(impl: MainRepositoryImpl): MainRepository

}
