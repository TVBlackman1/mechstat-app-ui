package repository.experiments.mock

import repository.experiments.dto.*

fun generateListElements(): ArrayList<ListItem>{
    val length = 50
    val data = ArrayList<ListItem>(length)
    for (i in 1..length) {
        data.add(
            ListItem(
                number = i,
                responsible = "Анатолий Н. П.",
                experimentType = "Сжатие",
                material = "Материал название",
            )
        )
    }
    return data
}

object DetailCategoryName {
    const val Material = "Материал"
    const val Rod = "Стержень"
}

fun generateDetails(experimentId: Int): ExperimentDetailsResponse {
    return ExperimentDetailsResponse(
        data = arrayOf(
            generateMaterialDetails(experimentId),
            generateRodDetails()
        )
    )
}

fun generateMaterialDetails(experimentId: Int): DetailsCategory {

    val subcategories = arrayOf(
        DetailsSubcategory(
        null,
            arrayOf(
                DetailsItem("Название", "Композит Э-133-01-4026 ($experimentId)"),
                DetailsItem("Дата поступления", "5 апреля 2017"),
                DetailsItem("Партия", "Б-03-100-12"),
                DetailsItem("Завод", "Завод “Березка”"),
            )
        )
    )
    return DetailsCategory(
        name = DetailCategoryName.Material,
        subcategories = subcategories
    )
}

fun generateRodDetails(): DetailsCategory {
    val subcategories = arrayOf(
        DetailsSubcategory(
            null,
            arrayOf(
                DetailsItem("Материал", "Береза"),
            )
        ),
        DetailsSubcategory(
            "Форма и размеры",
            arrayOf(
                DetailsItem("Размеры", "500мм x 0.5мм"),
                DetailsItem("Внутренний диаметр", "-"),
            )
        ),
        DetailsSubcategory(
            "Физические характеристики",
            arrayOf(
                DetailsItem("Модуль упругости", "71000 МПа"),
                DetailsItem("Скорость звука", "5050 м/с"),
            )
        ),
    )
    return DetailsCategory(
        name = DetailCategoryName.Rod,
        subcategories = subcategories
    )
}