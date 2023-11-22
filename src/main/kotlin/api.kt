import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


object ApiRoutes {
    private const val BASE_URL = "http://localhost:8080"
    const val EXPERIMENTS = "http://172.20.180.208:8080/experiments"
}

@Serializable
data class ListResponse(
    val number: Int = 0,
    val responsible: String = "",

    @SerialName("experiment_type")
    val experimentType: String = "",
    val material: String = "",
)

suspend fun getData(): ArrayList<ListResponse> = withContext(Dispatchers.IO){
    val client = HttpClient(CIO)
    client.use {
        val resp = it.get<HttpResponse>(ApiRoutes.EXPERIMENTS)
        println(resp.request.url)
        println(resp.status)
        println(resp.readText())

        val data = Json.decodeFromString<ArrayList<ListResponse>>(resp.readText())
        println(data[0].material)
        return@withContext data
    }
}