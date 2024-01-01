package repository.experiments.dto

import kotlinx.serialization.Serializable

@Serializable
class ExperimentDetailsResponse(
    val data: Array<DetailsCategory>
)

@Serializable
class DetailsCategory(
    val name: String,
    val subcategories: Array<DetailsSubcategory>,
)

@Serializable
class DetailsSubcategory(
    val name: String?,
    val content: Array<DetailsItem>,
)

@Serializable
data class DetailsItem(
    val name: String,
    val value: String,
)