import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ASCII85Test {
    @Test
    fun TestCase(): Unit {
        val input = listOf(Pair('e', "sure"),
                Pair('e', "Attack at dawn"),
                Pair('d', "87 cURD_ *#TDfTZ)+T"),
                Pair('d', "06/^V@;0 P 'E,ol0Ea`g%AT@"),
                Pair('d', "7W3Ei+EM%2Eb-A%DIal2AThX&+F.O, EcW@3B5\\nF/hR"),
                Pair('e', "Mom, send dollars!"),
                Pair('d', "6#:?H$@-Q4EX`@b@<5ud@V'@oDJ'8tD[CQ-+T"))
        val expectedOutput = listOf("F*2M7",
                "6$.3W@r!2qF<G+&GA[",
                "Hello, world!",
                "/r/dailyprogrammer",
                "Four score and seven years ago ...",
                "9l Fl \"+EM+3A0>E\$Ci!O#F!1",
                "All\r\nyour\r\nbase\tbelong\tto\tus!)")
        assertEquals(expectedOutput, ASCII85.runTest(input))
    }
}
