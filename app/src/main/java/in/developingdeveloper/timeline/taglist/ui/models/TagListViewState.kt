package `in`.developingdeveloper.timeline.taglist.ui.models

data class TagListViewState(
    val tags: List<UITag>,
    val isLoading: Boolean,
    val errorMessage: String?,
) {
    companion object {
        val Initial = TagListViewState(
            tags = emptyList(),
            isLoading = false,
            errorMessage = null,
        )
    }
}
