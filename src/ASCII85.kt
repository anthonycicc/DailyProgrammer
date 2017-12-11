import java.math.BigInteger

fun main(args: Array<String>) {
    for (i in args) {
        println("$i encoded is ${i.encodeASCII85()}")
        println("${i.encodeASCII85()} decoded is ${i.encodeASCII85().decodeASCII85()}")
    }
}

fun String.encodeASCII85(): String{
    val binList = mutableListOf<String>()
    for (i in this){
        binList.add(Integer.toBinaryString(i.toInt()))
    }
    val l = binList.fold("", { acc, s -> acc.plus(s) })

    return l.toInt(85).toString()
}

fun String.decodeASCII85(): String{
    return ""
}