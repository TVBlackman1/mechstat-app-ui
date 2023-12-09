package repository.experiments

import repository.ApiRoutes
import repository.ExperimentsRequester
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Experiments: ExperimentsRequester {
    override suspend fun getExperiments(page: Int, limit: Int): ListResponse = withContext(Dispatchers.IO){
        val client = HttpClient(CIO)
        client.use {
            val resp = it.get<HttpResponse> {
                url(ApiRoutes.EXPERIMENTS)
                parameter("page", page.toString())
                parameter("limit", 16)
            }
            Json.decodeFromString<ListResponse>(resp.readText())
        }
    }
}