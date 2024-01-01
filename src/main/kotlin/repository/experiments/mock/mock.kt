package repository.experiments.mock

import repository.ExperimentsRequester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import repository.experiments.dto.ExperimentDetailsResponse
import repository.experiments.dto.ListItem
import repository.experiments.dto.ListResponse
import repository.experiments.dto.Meta
import kotlin.math.ceil

val experimentsMock = generateListElements()
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

    override suspend fun getExperimentDetails(experimentId: Int): ExperimentDetailsResponse = withContext(Dispatchers.IO){
        return@withContext generateDetails(experimentId)

    }
}