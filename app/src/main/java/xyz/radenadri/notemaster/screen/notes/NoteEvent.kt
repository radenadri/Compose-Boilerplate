package xyz.radenadri.notemaster.screen.notes

sealed class NoteEvent {
    data class EnteredTitle(val value: String) : NoteEvent()
    object SaveNote : NoteEvent()
}