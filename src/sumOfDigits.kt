import java.math.BigInteger

fun main(args: Array<String>) {
    println("2**1234 = " + compute(2, 1234).toString())
    println("11**4000 = " + compute(11, 4000).toString())
    println("50**3000 = " + compute(50, 3000).toString())

}

fun compute(base: Int, exp: Int): Int {
    return BigInteger.valueOf(base.toLong()).pow(exp).toString().sumBy { digit -> digit.toInt() - 48 }
}