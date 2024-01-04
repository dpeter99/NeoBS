
Project("proj01", ExternalProject) {
    type = "cli"

    Target("build") {
        Action = "echo asd"
    }

}