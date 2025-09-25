abstract class SimplePrintingTask : DefaultTask() {
    @get:OutputFile
    abstract val messageFile: RegularFileProperty

    @get:OutputFile
    abstract val audienceFile: RegularFileProperty

    @TaskAction
    fun run() {
        messageFile.get().asFile.writeText("Hello")
        audienceFile.get().asFile.writeText("World")
    }
}

tasks.register<SimplePrintingTask>("helloWorld") {
        messageFile.set(layout.buildDirectory.file("message.txt"))
        audienceFile.set(layout.buildDirectory.file("audience.txt"))
        }