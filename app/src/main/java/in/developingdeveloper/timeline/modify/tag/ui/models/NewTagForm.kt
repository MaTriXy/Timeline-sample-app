package `in`.developingdeveloper.timeline.modify.tag.ui.models

data class NewTagForm(
    val label: String,
) {
    companion object {
        val Initial = NewTagForm(label = "")
    }
}
