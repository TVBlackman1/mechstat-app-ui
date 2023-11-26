package repository.experiments

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListResponse(
    val number: Int = 0,
    val responsible: String = "",

    @SerialName("experiment_type")
    val experimentType: String = "",
    val material: String = "",
)