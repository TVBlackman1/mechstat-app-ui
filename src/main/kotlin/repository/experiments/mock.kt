package repository.experiments

import repository.ExperimentsRequester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.exp

val experimentsMock = generateMockData()
class ExperimentsMock: ExperimentsRequester {
    override suspend fun getExperiments(page: Int, limit: Int): ArrayList<ListResponse> = withContext(Dispatchers.IO){
        val start = limit*(page-1)
        val end = limit*page - 1

        val batch: ArrayList<ListResponse> = ArrayList()
        if (experimentsMock.size < end) {
            return@withContext batch
        }
        for (i in start .. end) {
            batch.add(experimentsMock[i])
        }
        return@withContext batch
    }
}

fun generateMockData(): ArrayList<ListResponse>{
    val length = 50
    val data = ArrayList<ListResponse>(length)
    for (i in 1..length) {
        data.add(
            ListResponse(
                number = i,
                responsible = "Анатолий Н. П.",
                experimentType = "Сжатие",
                material = "Материал название",
            )
        )
    }
    return data
}