package gaur.himanshu.common.Navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
        )

}