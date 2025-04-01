package gaur.himanshu.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.navigation.SearchFeatureApi
import gaur.himanshu.navigation.SearchFeatureApiIml



@InstallIn(SingletonComponent::class)
@Module
object UiModule {
    @Provides
    fun provideSearchFeatureApi(): SearchFeatureApi{
        return SearchFeatureApiIml()
    }
}