package xyz.radenadri.boilerplate.screen.notes

sealed class NoteEvent {
    data class EnteredTitle(val value: String) : NoteEvent()
    object SaveNote : NoteEvent()
}