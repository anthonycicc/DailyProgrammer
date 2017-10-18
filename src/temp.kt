fun main(args: Array<String>) {
    val numbers = "5 2 2 1 1".split(" ".toRegex()).map { it.toInt() }.toMutableList()
    val queries = "5".split(" ".toRegex()).map { it.toInt() }
    val results = mutableListOf<Int>()

    queries.forEach {
        var i = 0
        val ns = ArrayList(numbers)

        while (i < ns.size) {
            var j = 0

            if (numbers.filter { it < ns[i] }.size + ns[i] < it) { i++; continue }
            while (j < ns.size && ns[i] < it ) {
                if (ns[i] > ns[j]) {
                    ns[i]++
                    ns.removeAt(j)
                    j--
                    i--
                }
                j++
            }
            i++
        }
        results.add(ns.filter { n -> n >= it }.size)
    }
    println(results)
}