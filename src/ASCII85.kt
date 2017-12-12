import java.math.BigInteger

fun main(args: Array<String>) {
    for (i in args) {
        println("$i encoded is ${i.encodeASCII85()}")
        println("${i.encodeASCII85()} decoded is ${i.encodeASCII85().decodeASCII85()}")
    }
}

fun String.encodeASCII85(): String{
    var l = Integer.valueOf(this.map { it -> Integer.toBinaryString(it.toInt()) }
            .fold("", { acc, s -> acc.plus(s.padStart(8, '0')) }), 2)
    val retList = mutableListOf<Int>()
    while (l != 0) {
        retList.add(l.rem(85))
        l /= 85
    }

    return retList.reversed().map { (it + 33).toChar() }.joinToString(separator = "")
}

fun String.decodeASCII85(): String{
    return ""
}