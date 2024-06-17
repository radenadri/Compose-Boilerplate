package xyz.radenadri.boilerplate.screen.notes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.radenadri.boilerplate.data.Note

@Composable
fun NoteList(notes: List<Note>?) {
    if (!notes.isNullOrEmpty()) {
        LazyColumn {
            items(notes) { note ->
                NoteItem(note = note)
            }
        }
    } else {
        Text(text = "No notes found")
    }
}

@Composable
fun NoteItem(note: Note) {
    ListItem(
        modifier = Modifier.padding(8.dp),
        headlineContent = { Text(text = note.title) },
        supportingContent = { Text(text = note.content) }
    )
}
