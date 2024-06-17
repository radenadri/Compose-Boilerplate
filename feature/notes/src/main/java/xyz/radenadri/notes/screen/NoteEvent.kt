package xyz.radenadri.notes.screen

sealed class NoteEvent {
    data class EnteredTitle(val value: String) : NoteEvent()
    object SaveNote : NoteEvent()
}