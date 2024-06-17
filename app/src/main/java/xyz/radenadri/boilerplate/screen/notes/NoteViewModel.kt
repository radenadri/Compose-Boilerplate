package xyz.radenadri.boilerplate.screen.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.radenadri.boilerplate.data.Note
import xyz.radenadri.boilerplate.utils.UiState

class NoteViewModel : ViewModel() {
    val notes = mutableListOf<Note>()

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> = _state

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    var isSnackBarShowing: Boolean by mutableStateOf(false)

    init {
        viewModelScope.launch {
            getNotes()
        }
    }

    private suspend fun getNotes() {
        try {
            notes.add(Note(1, "Title 1", "Content 1"))
            notes.add(Note(2, "Title 2", "Content 2"))

            delay(3000)

            viewModelScope.launch {
                _uiState.value = UiState.Success(notes)
            }
        } catch (e: Exception) {
            viewModelScope.launch {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.EnteredTitle -> {
                _state.value = state.value.copy(title = event.value)
            }

            is NoteEvent.SaveNote -> {

                if (state.value.title.isBlank()) {
                    showSnackBar(true)
                    return
                }

                viewModelScope.launch {
                    val newNote = Note(
                        notes.size + 1,
                        state.value.title,
                        "-"
                    )
                    notes.add(newNote)

                    showSnackBar(false)

                    _state.value = state.value.copy(title = "")
                    _state.value = state.value.copy(content = "")
                }
            }
        }
    }

    private fun showSnackBar(show: Boolean) {
        isSnackBarShowing = show
    }
}