import java.math.BigInteger

fun main(args: Array<String>) {
    var inputNum: BigInteger = BigInteger.ZERO

    try {
        inputNum = BigInteger(args[0])
    } catch (e: java.lang.ArrayIndexOutOfBoundsException){
        println("Put an argument in")
    }

    val factorPairList = mutableListOf<Pair<BigInteger,BigInteger>>()

    println("inpNum = $inputNum")

    val sqrt = kotlin.math.sqrt(inputNum.toDouble()).toInt()

    for (i in 1..sqrt){
        if (inputNum.rem(i.toBigInteger()) == BigInteger.ZERO){
            factorPairList.add(Pair(i.toBigInteger(), inputNum/i.toBigInteger()))
        }
    }

    val min = factorPairList.minBy { it.first + it.second }

    println("min is: $min for a sum of ${(min?.first ?: BigInteger.ZERO) + (min?.second ?: BigInteger.ZERO)}")
}