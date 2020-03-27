package tat.mukhutdinov.challenger.infrastructure

import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.host
import io.ktor.client.request.port
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.provider
import io.ktor.client.features.json.GsonSerializer

expect fun provideSqlDriver(): SqlDriver

object InfrastructureModule {

    val module = Kodein.Module("CommonInfrastructureModule") {

        bind<SqlDriver>() with provider {
            provideSqlDriver()
        }

        bind<HttpClient>() with provider {
            HttpClient {
//                install(JsonFeature) {
//                    serializer = KotlinxSerializer(Json.nonstrict)
//                }

                install(JsonFeature) {
                    serializer = GsonSerializer {
                        // Configurable .GsonBuilder
                        serializeNulls()
                        disableHtmlEscaping()
                    }
                }

                install(Logging) {
                    level = LogLevel.ALL
                }

                defaultRequest {
                    host = "192.168.1.11"
                    port = 8080
                }
            }
        }
    }
}