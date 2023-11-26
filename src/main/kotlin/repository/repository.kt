package repository

import repository.experiments.Experiments
import repository.experiments.ExperimentsMock
import repository.experiments.ListResponse

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
    suspend fun getExperiments(page: Int, limit: Int): ArrayList<ListResponse>
}