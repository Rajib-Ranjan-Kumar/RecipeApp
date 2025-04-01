package gaur.himanshu.ui.screens.ReceipeList  // Declares the package where this ViewModel class resides.

import androidx.lifecycle.ViewModel  // Imports ViewModel to manage UI-related data in a lifecycle-conscious way.
import androidx.lifecycle.viewModelScope // Imports viewModelScope, which provides a CoroutineScope tied to the ViewModel's lifecycle.
import com.example.search.domain.model.Recipe  // Imports Recipe model from the domain layer.
import com.example.search.domain.use_cases.GetAllRecipeUseCase  // Imports the use case responsible for fetching recipes.
import gaur.himanshu.common.utils.NetworkResult  // Imports NetworkResult, a sealed class handling API responses.
import gaur.himanshu.common.utils.UiText  // Imports UiText, a utility for managing UI-related text dynamically.
import kotlinx.coroutines.flow.MutableStateFlow  // Imports MutableStateFlow, which allows state management in a reactive way.
import kotlinx.coroutines.flow.StateFlow  // Imports StateFlow, which provides an immutable state holder.
import kotlinx.coroutines.flow.asStateFlow  // Converts MutableStateFlow into a read-only StateFlow.
import kotlinx.coroutines.flow.launchIn  // Launches a flow in a given CoroutineScope.
import kotlinx.coroutines.flow.onEach  // Performs an action for each emitted value in a Flow.
import kotlinx.coroutines.flow.update  // Provides a convenient way to update MutableStateFlow values.

// ViewModel class for managing the Recipe List screen.
class ReciepeListViewModel(
    private val getAllRecipeUseCase: GetAllRecipeUseCase // Injected use case responsible for fetching recipes.
) : ViewModel() {  // Extends ViewModel to handle UI-related data in a lifecycle-aware way.

    private val _uiState = MutableStateFlow(RecipeList.UiState())  // Holds the UI state using MutableStateFlow.
    val uiState: StateFlow<RecipeList.UiState> get() = _uiState.asStateFlow()  // Exposes a read-only version of UI state.

    // Handles different user interactions or events.
    fun onEven(event: RecipeList.Event){
        when(event){
            is RecipeList.Event.SearchRecipe -> search(event.q)  // Calls the search function when a search event occurs.
        }
    }

    // Function to perform a search operation.
    private fun search(q: String) = getAllRecipeUseCase.invoke(q)  // Invokes the use case to fetch recipes based on query.
        .onEach { result ->  // Observes each emitted result from the use case.
            when (result) {
                // Handles error state, updating the UI state with an error message.
                is NetworkResult.Error -> _uiState.update {
                    RecipeList.UiState(
                        Error = UiText.RemoteString(
                            result.message.toString() // Converts error message to a UI-friendly text format.
                        )
                    )
                }
                // Handles loading state, setting isLoading to true in UI state.
                is NetworkResult.Loading -> {
                    _uiState.update { RecipeList.UiState(isLoading = true) }
                }

                // Handles success state, updating the UI with the fetched recipe data.
                is NetworkResult.Success -> {
                    _uiState.update { RecipeList.UiState(data = result.data) }
                }
            }
        }.launchIn(viewModelScope) // Launches the flow inside viewModelScope for lifecycle management.
}

// Object to define UI state, events, and navigation for RecipeList.
object RecipeList {

    // Navigation sealed interface for defining navigation-related actions.
    sealed interface Navigation {
        data class goToReciepeDetails(val id: String) : Navigation // Represents navigation action to Recipe Details screen.
    }

    // Data class representing UI state for the Recipe List screen.
    data class UiState(
        val isLoading: Boolean = false, // Indicates whether data is being loaded.
        val Error: UiText = UiText.Idle, // Holds any error message for UI feedback.
        val data: List<Recipe>? = null // Holds the list of fetched recipes.
    )

    // Sealed interface for handling different UI events.
    sealed interface Event {
        data class SearchRecipe(val q: String) : Event // Event triggered when searching for a recipe.
    }
}
