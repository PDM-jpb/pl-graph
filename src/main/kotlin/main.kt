import java.io.File

fun main() {
    val depFilename = "lp-dep.txt"
    val dotFilename = "lp-dep.dot"
    val lines = File(depFilename).readLines()
    writeDot(dotFilename, lines)
    Runtime.getRuntime().exec("dot -Tpdf  ${dotFilename} -o out.pdf")
}

fun writeDot(filename: String, lines: List<String>) {
    val file = File(filename)
    var dot ="digraph PLDEP {\n"
    for(line in lines) {
        //val pls = line.split(",").map { it -> it.replace(" ", "") }
        var pls = line.split("""[\s]*,[\s]*""".toRegex())

        for(pl in pls.slice(1.. pls.lastIndex)) {
            dot += "\t" + """"${pl}" -> "${pls[0]}";""" + "\n"
        }
    }
    dot += "}\n"
    file.writeText(dot)
}



