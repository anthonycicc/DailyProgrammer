import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ASCII85Test {
    @Test
    fun sure(): Unit {
        val input = Pair('e', "sure")
        val expectedOutput = "F*2M7"
        assertEquals(mapPair(input), expectedOutput)
    }

    @Test
    fun reverseSure(): Unit {
        val input = Pair('d', "F*2M7")
        val output = "sure"
        assertEquals(mapPair(input), output)
    }

    @Test
    fun `Attack at dawn`(): Unit {
        val input = Pair('e', "Attack at dawn")
        val expectedOutput = "6$.3W@r!2qF<G+&GA["
        assertEquals(mapPair(input), expectedOutput)
    }

    @Test
    fun `Hello, world!`(): Unit {
        val input = Pair('d', "87 cURD_ *#TDfTZ)+T")
        val expectedOutput = "Hello, world!"
        assertEquals(mapPair(input), expectedOutput)
    }

    @Test
    fun `|r|dailyprogrammer`(): Unit {
        val input = Pair('d', "06/^V@;0 P 'E,ol0Ea`g%AT@")
        val expectedOutput = "/r/dailyprogrammer"
        assertEquals(mapPair(input), expectedOutput)
    }

    @Test
    fun `Four score and seven years ago`(): Unit {
        val input = Pair('d', "7W3Ei+EM%2Eb-A%DIal2AThX&+F.O, EcW@3B5\\nF/hR")
        val expectedOutput = "Four score and seven years ago ..."
        assertEquals(mapPair(input), expectedOutput)
    }

    @Test
    fun `Mom, send dollars!`(): Unit {
        val input = Pair('e', "Mom, send dollars!")
        val expectedOutput = "9l Fl \"+EM+3A0>E\$Ci!O#F!1"
        assertEquals(mapPair(input), expectedOutput)
    }
    
    @Test
    fun `All your base belong to us`(): Unit {
        val input = Pair('d', "6#:?H$@-Q4EX`@b@<5ud@V'@oDJ'8tD[CQ-+T")
        val expectedOutput = "All\r\nyour\r\nbase\tbelong\tto\tus!)"
        assertEquals(mapPair(input), expectedOutput)
    }
}
