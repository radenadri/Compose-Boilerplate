package xyz.radenadri.boilerplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import xyz.radenadri.boilerplate.ui.theme.NoteMasterTheme
import xyz.radenadri.notes.screen.NoteScreen

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
