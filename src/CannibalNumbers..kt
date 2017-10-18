fun main(args: Array<String>) {
    val values = args[0].toInt()
    val queries = args[1].toInt()
    val queryList = args.copyOfRange(args.size - queries, args.size).map { it.toInt() }
    val argsWithoutControls = args.copyOfRange(2, args.size - queries)
            .map { it.toInt() }

    for (i in queryList) {
        var num = argsWithoutControls.count { it >= i }
        val filtered = argsWithoutControls.filter { it < i }.toMutableList()

        var max = filtered.max()
        while (max != null) {
            filtered.remove(max)
            while (max < i && filtered.isNotEmpty()) {
                max++
                with(filtered) {
                    if (min()!! < max!!) remove(min()!!)
                }
            }
            if (max == i) num++
            max = filtered.max()
        }
        print(num.toString() + " ")
    }
}