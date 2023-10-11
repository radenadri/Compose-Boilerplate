package xyz.radenadri.notemaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import xyz.radenadri.notemaster.screen.notes.NoteScreen
import xyz.radenadri.notemaster.ui.theme.NoteMasterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteMasterTheme {
                Surface(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    NoteScreen()
                }
            }
        }
    }
}
