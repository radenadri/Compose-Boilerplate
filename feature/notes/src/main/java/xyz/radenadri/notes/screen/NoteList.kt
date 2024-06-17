package xyz.radenadri.notes.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import xyz.radenadri.notes.data.Note

@Composable
@Preview
fun NoteList(@PreviewParameter(NoteListPreviewProvider::class) notes: List<Note>?) {
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

class NoteListPreviewProvider : PreviewParameterProvider<List<Note>> {
    override val values = sequenceOf(
        listOf(Note(id= 1, title = "Note 1", content = "Content 1"), Note(id = 1, title = "Note 2", content = "Content 2"))
    )
}