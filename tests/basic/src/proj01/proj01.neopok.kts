import com.aperlab.neopok.kts.definition.CmdAction
import com.aperlab.neopok.kts.definition.Target

Project("proj01") {

    Target("Build") {
        CmdAction { Command = "echo asd" }
    }
}