import org.junit.jupiter.api.Test

import kotlin.test.assert

internal class CribbageScoringTest {

    @Test
    fun score1() {
        val testScorer = CribbageScoring(arrayOf("5D", "QS", "JC", "KH", "AC"))

        assert(testScorer.cardList.calcAddToFifteen(testScorer.lastCard) == 6)
        assert(testScorer.cardList.calcRuns(testScorer.lastCard) == 3)
        assert(testScorer.cardList.calcNobs(testScorer.lastCard) == 1)

        assert(10 == testScorer.score())
    }

    @Test
    fun score2(): Unit {
        val testScorer = CribbageScoring(arrayOf("8C", "AD", "10C", "6H", "7S"))

        assert(testScorer.cardList.calcAddToFifteen(testScorer.lastCard) == 4)
        assert(testScorer.cardList.calcRuns(testScorer.lastCard) == 3)

        assert(7 == testScorer.score())
    }

    @Test
    fun score3(): Unit {
        val testScorer = CribbageScoring(arrayOf("AC", "6D", "5C", "10C", "8C"))

        assert(testScorer.cardList.calcAddToFifteen(testScorer.lastCard) == 4)

        assert(4 == testScorer.score())
    }

}