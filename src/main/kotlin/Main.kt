import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.http.Context
import io.javalin.http.Header
import io.javalin.http.HttpStatus
import io.javalin.http.RedirectResponse

fun main() {
    val app = Javalin.create { config ->
        config.routing.ignoreTrailingSlashes = false
//        config.routing.treatMultipleSlashesAsSingleSlash = true
    }
    app.routes {
        get("/", ::handleRootUrl)
        get("/home", ::handleHomeUrl)
    }
    app.start(7070)
}

fun handleRootUrl(ctx: Context) {
    ctx.res().setHeader(Header.LOCATION, "/home")
    throw RedirectResponse(HttpStatus.FOUND)
}

fun handleHomeUrl(ctx: Context) {
    ctx.contentType("text/plain; charset=utf-8")
    ctx.result("Home Sweet Home")
}
