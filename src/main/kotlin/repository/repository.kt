package repository

import repository.experiments.dto.ExperimentDetailsResponse
import repository.experiments.Experiments
import repository.experiments.mock.ExperimentsMock
import repository.experiments.dto.ListResponse

val repository = Repository(type = RepositoryType.MOCK)
enum class RepositoryType {
    MOCK,
    WITH_SERVER
}
class Repository(
    private val type: RepositoryType = RepositoryType.WITH_SERVER,

    val experiments: ExperimentsRequester =
        if(type == RepositoryType.WITH_SERVER) Experiments() else ExperimentsMock()
)
interface ExperimentsRequester {
    suspend fun getExperiments(page: Int, limit: Int): ListResponse

    suspend fun getExperimentDetails(experimentId: Int): ExperimentDetailsResponse
}