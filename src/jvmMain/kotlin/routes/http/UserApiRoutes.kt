package routes.http

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import domain.repo.UserRepo
import model.UserCredentials
import org.koin.ktor.ext.inject


fun Application.configureUserRoutes(){
    val repo by inject<UserRepo>()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
        authenticate("auth-session") {
            get("/home"){
                call.respond("You are logged in")
            }
        }
        post("/api/register"){
            val (username, password) = call.receive<UserCredentials>()
            val session = repo.createUser(username, password)
            if(session != null) { call.sessions.set(session); call.respond(HttpStatusCode.OK) }
            else call.respond(HttpStatusCode.Conflict)
        }
        post("/api/login"){
            val (username, password) = call.receive<UserCredentials>()
            val session = repo.loginUser(username, password)
            if(session != null) { call.sessions.set(session);  call.respond(HttpStatusCode.OK) }
            else call.respond(HttpStatusCode.Conflict)
        }
    }
}