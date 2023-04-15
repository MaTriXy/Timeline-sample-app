package `in`.developingdeveloper.timeline.taglist.ui.models

data class TagListViewState(
    val tags: List<UITag>,
) {
    companion object {
        val Initial = TagListViewState(tags = emptyList())
    }
}
