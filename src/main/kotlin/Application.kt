package com.avcoding.scribblesphere

import com.mongodb.reactivestreams.client.MongoClients
import io.ktor.server.application.*
import io.ktor.server.config.tryGetString
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val uri = "mongodb://localhost:27017"
    val mongoClient = MongoClients.create(uri)
    val database = mongoClient.getDatabase("scribblesphere")
    configureSecurity()
    configureRouting()

    routing {


        get("/dbCall") {
            call.respondText("Ktor with MongoDB!")
        }

        // Example route using the injected MongoDatabase
        get("/databaseName") {
            call.respondText("Database Name: ${database.name}")
        }

        get("/CreateCollection") {
            if (database.getCollection("users") == null){

            }
            database.createCollection("users")
            call.respondText("CreateCollection Name: ${database.listCollectionNames()}")
        }

        // ... your other routes and application logic
    }
}
