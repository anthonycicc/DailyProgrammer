import java.math.BigInteger

class ASCII85 {
    companion object {
        fun runTest(input: List<Pair<Char, String>>): List<String> {
            return input.map { mapPair(it) }
        }
    }
}

fun encode(input: String): String {
    val length = input.length
    var g = 0
    val inputs = (0 until length step 4).map { if (it + 4 < length) input.subSequence(it, it + 4) else input.subSequence(it, length) }
    val s = inputs.map {
        g = 4 - it.length
        var n = it.foldIndexed(0) { index, acc, c -> acc + c.toByte() * Math.pow(256.0, 3.0 - index).toInt() }

        generateSequence {
            if (n != 0) {
                val x = n % 85; n /= 85; x
            } else {
                null
            }
        }.toList().reversed().map { (it + 33).toChar() }.joinToString("")
    }.joinToString("")

    return s.substring(0, s.length - g)
}


fun decode(input: String): String {
    val length = input.length
    val inputs = (0 until length step 5).map { if (it + 5 < length) input.subSequence(it, it + 5) else input.subSequence(it, length) }
    var g = 0
    val s = inputs.map {
        g = 5 - it.length
        val x = it.padEnd(5, 'u')
        var n = x.mapIndexed { index, c -> (c.toByte() - 33) * Math.pow(85.0, 4.0 - index) }.sum().toInt()

        generateSequence {
            if (n != 0) {
                val x = n % 256; n /= 256; x
            } else {
                null
            }
        }.toList().reversed().map { it.toChar() }.joinToString("")
    }.joinToString("")

    return s.substring(0, s.length - g)
}

fun main(args: Array<String>) {
    print(encode("Attack at dawn"))
}

fun mapPair(x: Pair<Char, String>): String {
    return when (x.first) {
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