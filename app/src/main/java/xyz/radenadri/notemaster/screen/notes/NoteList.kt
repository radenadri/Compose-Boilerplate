package xyz.radenadri.notemaster.screen.notes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.radenadri.notemaster.data.Note

@Composable
fun NoteList(notes: List<Note>?) {
    if (notes != null) {
        LazyColumn {
            items(notes) { note ->
                NoteItem(note = note)
            }
        }
    } else {
        Text(text = "No Notes")
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
