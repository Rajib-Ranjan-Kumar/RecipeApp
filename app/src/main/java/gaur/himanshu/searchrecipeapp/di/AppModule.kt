package gaur.himanshu.searchrecipeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gaur.himanshu.navigation.SearchFeatureApi
import gaur.himanshu.searchrecipeapp.navigation.NavigationSubgraph
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideNavigationSubGraphs(searchFeatureApi: SearchFeatureApi):NavigationSubgraph{
        return NavigationSubgraph(searchFeatureApi)
    }

}