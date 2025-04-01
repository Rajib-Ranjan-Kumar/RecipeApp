package gaur.himanshu.navigation

import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import gaur.himanshu.common.Navigation.FeatureApi
import gaur.himanshu.common.Navigation.NavigationRoute
import gaur.himanshu.common.Navigation.NavigationRoute.RecipeDetails.route
import gaur.himanshu.common.Navigation.NavigationSubGraphRoute

interface SearchFeatureApi:FeatureApi

class SearchFeatureApiIml:SearchFeatureApi {
    override fun registerGraph
         (
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navHostController: androidx.navigation.NavHostController
    ) {
        navGraphBuilder.navigation(
            route = NavigationSubGraphRoute.Search.route,
            startDestination = NavigationRoute.RecipeList.route
        )
        {

            composable(route = NavigationRoute.RecipeList.route) {  }
            composable(route = NavigationRoute.RecipeDetails.route) {  }

        }
    }
}