package repository.experiments

import repository.ExperimentsRequester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.ceil

val experimentsMock = generateMockData()
class ExperimentsMock: ExperimentsRequester {
    override suspend fun getExperiments(page: Int, limit: Int): ListResponse = withContext(Dispatchers.IO){
        val count = experimentsMock.size
        val allPages = ceil(count / limit.toDouble()).toInt()

        val start = limit*(page-1)
        val end = limit*page - 1
        val batch: ArrayList<ListItem> = ArrayList()
        for (i in start .. end) {
            if (experimentsMock.size <= i) {
                break
            }
            batch.add(experimentsMock[i])
        }

        return@withContext ListResponse(
            data = batch,
            meta = Meta(
                count = count,
                page = page,
                limit = limit,
                allPages = allPages,
            )
        )
    }
}

fun generateMockData(): ArrayList<ListItem>{
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