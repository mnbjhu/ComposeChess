package net.malkowscy.application.data.db

import data.db.Database
import uk.gibby.redis.core.RedisGraph
import net.malkowscy.application.util.Config
import net.malkowscy.application.util.config

class DatabaseImpl: Database {
    override val graph = RedisGraph(
        name = "ChessServerGraph",
        host = config[Config.server_host],
        port = config[Config.server_port],
        password = config[Config.server_pass],
    )
}