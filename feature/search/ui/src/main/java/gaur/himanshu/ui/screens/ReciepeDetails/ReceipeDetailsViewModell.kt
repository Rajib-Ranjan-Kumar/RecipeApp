package gaur.himanshu.ui.screens.ReciepeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search.domain.model.RecipeDetails
import com.example.search.domain.use_cases.GetAllRecipeDetailsUseCase
import gaur.himanshu.common.utils.NetworkResult
import gaur.himanshu.common.utils.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ReceipeDetailsViewModell(
    private val getAllRecipeDetailsUseCase: GetAllRecipeDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReceipeDetails.UiState())
    val uiState: StateFlow<ReceipeDetails.UiState> get() = _uiState.asStateFlow()

    fun onEvent(event: ReceipeDetails.Event) {
        when (event) {
            is ReceipeDetails.Event.Details -> fetchDetails(event.q)
        }
    }

    private fun fetchDetails(id: String) = getAllRecipeDetailsUseCase.invoke(id)
        .onEach { result ->
            when (result) {
                is NetworkResult.Error -> _uiState.update {
                    ReceipeDetails.UiState(
                        Error = UiText.RemoteString(
                            result.message.toString()
                        )
                    )
                }

                is NetworkResult.Loading -> _uiState.update { ReceipeDetails.UiState(isLoading = true) }
                is NetworkResult.Success -> _uiState.update { ReceipeDetails.UiState(data = result.data) }
            }
        }.launchIn(viewModelScope)
}

object ReceipeDetails {
    data class UiState(
        val isLoading: Boolean = false,
        val Error: UiText = UiText.Idle,
        val data: RecipeDetails? = null
    )

    sealed interface Event {
        data class Details(val q: String) : Event
    }
}