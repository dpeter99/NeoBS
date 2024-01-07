
Project("proj01") {
    Target ("build") {
        CmdAction{Command = "echo asd1"}
        CmdAction{Command = "echo asd2"}
    }
}