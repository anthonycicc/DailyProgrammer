fun main(args: Array<String>) {
    val inputNums = listOf<Int>(100, 20, 18, 12, 5, 5)
    val maxNumChange = 5
    val changeTotal = 130

    println(changeCalc(inputNums, maxNumChange, changeTotal))
}

fun changeCalc(inpList: List<Int>, maxNumChange: Int, changeTotal: Int): Int {
    val changeList = mutableListOf<Int>()
    val mutableInpList = inpList.sorted().reversed().toMutableList()

    while (changeTotal > changeList.sum()
        && mutableInpList.isNotEmpty()
        && changeList.size <= maxNumChange
    ) {
        changeList.add(mutableInpList.maxBy { coin -> changeList.sum() + coin <= changeTotal} ?: 0)
        mutableInpList.remove(changeList.last())
    }

    println(changeList)
    return changeList.size
}