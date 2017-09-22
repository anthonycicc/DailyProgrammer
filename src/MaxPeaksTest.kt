import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MaxPeaksTest {


    @Test
    fun maxPeaks1(): Unit {
        val input = listOf<Int>(1, 2, 2, 5, 9, 5, 4, 4, 1, 6)
        val expectedOutput = listOf<Int>(1, 2, 4, 6)
        assertEquals(expectedOutput, MaxPeaks.solve(input))
    }

    @Test
    fun maxPeaks2(): Unit {
        val input = listOf<Int>(4, 9, 4, 9, 9, 8, 2, 9, 0, 1)
        val expectedOutput = listOf<Int>(4, 8, 9)
        assertEquals(expectedOutput, MaxPeaks.solve(input))
    }

    @Test
    fun maxPeaks3(): Unit{
        val input = listOf(0, 5, 4, 6, 9, 1, 7, 6, 7, 8)
        val expectedOutput = listOf(0, 1, 6, 7, 8)
        assertEquals(expectedOutput, MaxPeaks.solve(input))
    }

    @Test
    fun maxPeaks4(): Unit {
        val input = listOf(1, 2, 20, 13, 6, 15, 16, 0, 7, 9, 4, 0, 4, 6, 7, 8, 10, 18, 14, 10, 17, 15, 19, 0, 4, 2, 12,
                6, 10, 5, 12, 2, 1, 7, 12, 12, 10, 8, 9, 2, 20, 19, 20, 17, 5, 19, 0, 11, 5, 20)
        val expectedOutput = listOf(1, 2, 4, 6, 7, 8, 10, 14, 15, 17, 19, 20)
        assertEquals(expectedOutput, MaxPeaks.solve(input))
    }
}

