fun main(args: Array<String>) {
    val numberSequences = args[0].toInt()
    val lengthSequences = args[1].toInt()

    val minusControls = args.slice(2..args.size - 1)
    val sequences = mutableListOf<List<Int>>()
    for (i in 0..numberSequences - 1){
        sequences.add(minusControls.slice(i * lengthSequences .. lengthSequences + i * lengthSequences - 1).map { it.toInt() })
    }

    val distances = mutableListOf<Int>()
    for (testList in sequences){
        var tempDistance = 0
        for ((index, j) in testList.withIndex()){
            if (j + 1 in testList){
                tempDistance += Math.abs(testList.indexOf(j + 1) - index)
            }
        }
        distances.add(tempDistance)
    }

    distances.forEach { println(it) }
}