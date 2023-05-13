package `in`.developingdeveloper.timeline.modify.tag.ui.models

data class ModifyTagForm(
    val label: String,
) {
    companion object {
        val Initial = ModifyTagForm(
            label = "",
        )
    }
}
