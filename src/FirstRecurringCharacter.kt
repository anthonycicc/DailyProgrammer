fun recurs(x: String): Pair<Char?, Int?>{
    val usedChars = mutableListOf<Char>()

    for ((index, char) in x.withIndex()){
        if (char in usedChars){
            return Pair(char, index)
        }
        else usedChars.add(char)
    }

    return Pair(null, null)
}

fun main(args: Array<String>) {
    for (i in args){
        println(recurs(i))
    }
}