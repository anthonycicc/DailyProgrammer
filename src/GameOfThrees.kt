fun main(args: Array<String>) {
    for (i in args) {
        var input = i.toInt()
        val builder = StringBuilder()

        while (input != 0) {
            when (input % 3) {
                0 -> {
                    builder.appendln("$input 0")
                    input /= 3
                }
                1 -> {
                    builder.appendln("$input -1")
                    input -= 1
                    input /= 3
                }
                2 -> {
                    builder.appendln("$input 1")
                    input += 1
                    input /= 3
                }
            }
        }

        println(builder.toString())
        println("=================================")
    }
}
