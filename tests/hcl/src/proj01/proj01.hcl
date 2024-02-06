

project "proj01" {
  lang = "cpp"

  target "build" {
    action target { target = "clean" }
    action cmd { CMD = "echo \"clang please build this\"" }
  }

  target "clean" {
    action cmd { CMD = "echo \"su-doo rm\"" }
  }

}

project npm "proj01" {
  packageFile = "./packages.json"

  target "clean" {
    action cmd { CMD = "echo \"su-doo rm\"" }
  }

}