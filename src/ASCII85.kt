import java.math.BigInteger

class ASCII85{
    companion object {
        fun runTest(input: List<Pair<Char, String>>): List<String>{
            return input.map { mapPair(it) }
        }
    }
}

fun main(args: Array<String>) {
    val input = listOf(Pair('e', "sure"),
            Pair('e', "Attack at dawn"),
            Pair('d', "87 cURD_ *#TDfTZ)+T"),
            Pair('d', "06/^V@;0 P 'E,ol0Ea`g%AT@"),
            Pair('d', "7W3Ei+EM%2Eb-A%DIal2AThX&+F.O, EcW@3B5\\nF/hR"),
            Pair('e', "Mom, send dollars!"),
            Pair('d', "6#:?H$@-Q4EX`@b@<5ud@V'@oDJ'8tD[CQ-+T"))
    for (i in input) {
        when(i.first){
            'e' -> println("${i.second} encoded is ${i.second.encodeASCII85()}")
            'd' -> println("${i.second} decoded is ${i.second.decodeASCII85()}")
        }
    }
}

fun mapPair(x: Pair<Char, String>): String{
    return when (x.first){
        'e' -> x.second.encodeASCII85()
        'd' -> x.second.decodeASCII85()
        else -> throw RuntimeException("Invalid Input")
    }
}

fun String.encodeASCII85(): String {
    var l = BigInteger(this.map { c -> Integer.toBinaryString(c.toInt()) }
            .fold("", { acc, s -> acc.plus(s.padStart(8, '0')) }), 2)
    val retList = mutableListOf<BigInteger>()
    while (!l.equals(0.toBigInteger())) {
        retList.add(l.rem(85.toBigInteger()))
        l /= 85.toBigInteger()
    }

    return retList.reversed().map { (it.toInt() + 33).toChar() }.joinToString(separator = "")
}

fun String.decodeASCII85(): String {
    val l = this.map { it.toInt() - 33 }

    val sum = l.reversed()
            .asSequence()
            .mapIndexed { index, c -> (c * (Math.pow(85.0, index.toDouble()))).toInt() }
            .sum()

    var binString = Integer.toBinaryString(sum)

    val listOfStrings = mutableListOf<String>()
    while (binString != "") {
        listOfStrings.add(binString.takeLast(8).padStart(8, padChar = '0'))
        binString = binString.dropLast(8)
    }

    return listOfStrings.map { binChar -> binChar.toInt(2).toChar() }.joinToString(separator = "").reversed()
}