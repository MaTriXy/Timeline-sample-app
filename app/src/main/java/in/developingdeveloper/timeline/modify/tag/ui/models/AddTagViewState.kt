package `in`.developingdeveloper.timeline.modify.tag.ui.models

data class AddTagViewState(
    val form: NewTagForm,
    val isLoading: Boolean,
    val isFormEnabled: Boolean,
    val isCompleted: Boolean,
    val errorMessage: String?,
) {
    companion object {
        val Initial = AddTagViewState(
            form = NewTagForm.Initial,
            isLoading = false,
            isFormEnabled = true,
            isCompleted = false,
            errorMessage = null,
        )
    }
}