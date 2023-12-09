package repository.experiments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListResponse(
    val data: ArrayList<ListItem>,
    val meta: Meta,
)

@Serializable
data class ListItem(
    val number: Int = 0,
    val responsible: String = "",
    @SerialName("experiment_type") val experimentType: String = "",
    val material: String = "",
)

@Serializable
data class Meta(
    val count: Int,
    val page: Int,
    val limit: Int,
    @SerialName("all_pages") val allPages: Int
)