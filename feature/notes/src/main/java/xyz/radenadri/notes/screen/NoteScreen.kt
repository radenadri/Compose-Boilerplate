package xyz.radenadri.notes.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.radenadri.notes.utils.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun NoteScreen() {
    val viewModel = remember { NoteViewModel() }
    val uiState = viewModel.uiState.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            AppBar(title = "Notes")
        },
        snackbarHost = {
            if (viewModel.isSnackBarShowing) {
                Snackbar(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("The title cannot be blank!")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
                }
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        },
        content = { innerPadding ->
            when (val currentState = uiState.value) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp, 40.dp)
                        )
                    }
                }

                is UiState.Success -> {
                    // fill the screen with notes
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        NoteList(
                            notes = viewModel.notes
                        )
                    }
                }

                is UiState.Error -> {
                    // fill the screen with error
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error: ${currentState.message}",
                        )
                    }
                }
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = viewModel.state.value.title,
                            onValueChange = {
                                viewModel.onEvent(NoteEvent.EnteredTitle(it))
                            },
                            label = { Text("Title") }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                viewModel.onEvent(NoteEvent.SaveNote)
                                showBottomSheet = false
                            }
                        ) {
                            Text("Save")
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) }
    )
}
