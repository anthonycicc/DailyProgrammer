data class Card(val value: Value, val suit: Suit) {
    val numericValue: Int

    init {
        numericValue = if (value.ordinal + 1 > 10) {
            10
        } else {
            value.ordinal + 1
        }
    }
}

enum class Value {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

enum class Suit {
    CLUB, SPADE, HEART, DIAMOND
}

class CribbageScoring(args: Array<String>) {
    val cardList: List<Card>
    val lastCard: Card

    init {
        cardList = args.slice(0..args.size - 2).map { it -> it.toCard() }.toMutableList()
        lastCard = args.last().toCard()
    }

    fun score(): Int {
        var score = 0
        println("CalcToFifteen = ${cardList.calcAddToFifteen(lastCard)}")
        score += cardList.calcAddToFifteen(lastCard)
        println("Calcruns = ${cardList.calcRuns(lastCard)}")
        score += cardList.calcRuns(lastCard)
        println("CalcPairs = ${cardList.calcPairs(lastCard)}")
        score += cardList.calcPairs(lastCard)
        println("CalcFlushes = ${cardList.calcFlushes(lastCard)}")
        score += cardList.calcFlushes(lastCard)
        println("CalcNobs = ${cardList.calcNobs(lastCard)}")
        score += cardList.calcNobs(lastCard)

        return score
    }
}

fun String.toCard(): Card {
    val value = when (this.substring(0..this.length - 2)) {
        "A" -> Value.ACE
        "2" -> Value.TWO
        "3" -> Value.THREE
        "4" -> Value.FOUR
        "5" -> Value.FIVE
        "6" -> Value.SIX
        "7" -> Value.SEVEN
        "8" -> Value.EIGHT
        "9" -> Value.NINE
        "10" -> Value.TEN
        "J" -> Value.JACK
        "Q" -> Value.QUEEN
        "K" -> Value.KING
        else -> throw Exception("Input list is bad (value)")
    }
    val suit = when (this.last()) {
        'C' -> Suit.CLUB
        'D' -> Suit.DIAMOND
        'H' -> Suit.HEART
        'S' -> Suit.SPADE
        else -> throw Exception("Input list is bad (suit)")
    }
    return Card(value, suit)
}

fun List<Card>.calcAddToFifteen(lastCard: Card): Int {
    return 6
}

fun List<Card>.calcRuns(lastCard: Card): Int {
    val numberList: List<Int> = this.fold(mutableListOf(), { acc: MutableList<Int>, card: Card -> acc.add(card.numericValue); acc }).plus(lastCard.numericValue)
    return 3 * this.count { number -> numberList.contains(number.numericValue + 1) && numberList.contains(number.numericValue + 2) }
}

fun List<Card>.calcPairs(lastCard: Card): Int {
    val numberList: List<Int> = this.fold(mutableListOf(), { acc: MutableList<Int>, card: Card -> acc.add(card.numericValue); acc }).plus(lastCard.numericValue)
    var score = 0
    numberList.forEach { testNum ->
        when (numberList.count { testNum == it }) {
            1 -> score += 0
            2 -> score += 2
            3 -> score += 6
            4 -> score += 12
            else -> throw Exception("Mawp")
        }
    }
    return score
}

fun List<Card>.calcFlushes(lastCard: Card): Int {
    val suitList: List<Suit> = this.fold(kotlin.collections.mutableListOf(), { acc, card -> acc.add(card.suit); acc })
    val suitListPlusLast = suitList.plus(lastCard.suit)
    if (suitListPlusLast.all { it == suitListPlusLast.first() }) {
        return 5
    } else if (suitList.all { it == suitList.first() }) {
        return 4
    } else return 0
}

fun List<Card>.calcNobs(lastCard: Card): Int {
    return this.count { it.suit == lastCard.suit && it.value == Value.JACK }
}