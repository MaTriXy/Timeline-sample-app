package `in`.developingdeveloper.timeline.add.tag.ui.models

data class AddTagViewState(
    val form: NewTagForm,
) {
    companion object {
        val Initial = AddTagViewState(form = NewTagForm.Initial)
    }
}
