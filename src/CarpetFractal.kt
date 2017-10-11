fun main(args: Array<String>) {
    val numberOfColors = args[0]
    val numberOfIterations = args[1]

    val withoutControls = args.slice(2..args.size - 1)
    val listOfRules = mutableListOf<CarpetRule>()

    for (i in 0..((withoutControls.size/9) - 1)){
        listOfRules.add(CarpetRule(withoutControls.subList(i * 9, i * 9 + 9).map { it.toInt() }))
    }

    listOfRules.forEach { println(it) }
}

data class CarpetRule(val ruleList: List<Int>){}

class CarpetFractal(val listOfRules: List<CarpetRule>) {
    val pixelList: MutableList<MutableList<Int>>

    init {
        pixelList = mutableListOf()
    }

    fun transform(){

    }
}