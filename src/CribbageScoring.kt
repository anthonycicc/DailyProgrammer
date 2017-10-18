data class Card(val value: Value, val suit: Suit) {
    val numericValue: Int = if (value.ordinal + 1 > 10) {
        10
    } else {
        value.ordinal + 1
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
        return with(cardList) {
            var score = 0
            score += calcAddToFifteen(lastCard)
            score += calcRuns(lastCard)
            score += calcPairs(lastCard)
            score += calcFlushes(lastCard)
            score += calcNobs(lastCard)
            score
        }
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
    val combinations = this.fold(mutableListOf(), { acc: MutableList<Int>, card: Card -> acc.add(card.numericValue); acc }).plus(lastCard.numericValue).combinations()
    return 2 * combinations.count { combo -> combo.sum() == 15 }
}

fun List<Card>.calcRuns(lastCard: Card): Int {
    val numberList: List<Int> = this.fold(mutableListOf(), { acc: MutableList<Int>, card: Card -> acc.add(card.value.ordinal + 1); acc }).plus(lastCard.value.ordinal + 1)
    var score = 0
    for (i in numberList) {
        if (numberList.contains(i + 1) && numberList.contains(i + 2) && numberList.contains(i + 3) && numberList.contains(i + 4)) {
            score += 5
        } else if (numberList.contains(i + 1) && numberList.contains(i + 2) && numberList.contains(i + 3)) {
            score += 4
        } else if (numberList.contains(i + 1) && numberList.contains(i + 2)) {
            score += 3
        }
    }
    return score
}

fun List<Card>.calcPairs(lastCard: Card): Int {
    val numberList: List<Int> = this.fold(mutableListOf(), { acc: MutableList<Int>, card: Card -> acc.add(card.value.ordinal); acc }).plus(lastCard.value.ordinal)
    var score = 0
    numberList.forEach { testNum ->
        score += when (numberList.count { testNum == it }) {
            1 -> 0
            2 -> 2
            3 -> 6
            4 -> 12
            else -> throw Exception("This is impossible")
        }
    }
    return score
}

fun List<Card>.calcFlushes(lastCard: Card): Int {
    val suitList: List<Suit> = this.fold(kotlin.collections.mutableListOf(), { acc, card -> acc.add(card.suit); acc })
    val suitListPlusLast = suitList.plus(lastCard.suit)
    return when {
        suitListPlusLast.all { it == suitListPlusLast.first() } -> 5
        suitList.all { it == suitList.first() } -> 4
        else -> 0
    }
}

fun List<Card>.calcNobs(lastCard: Card): Int {
    return this.count { it.suit == lastCard.suit && it.value == Value.JACK }
}

fun <T> List<T>.combinations(): List<List<T>> {
    val comboList = mutableListOf<List<T>>()
    val len = this.size

    for (i in 0 until Math.pow(2.0, len.toDouble()).toInt()) {
        val str = Integer.toBinaryString(i)
        val value = str.length
        var pset = str

        for (k in value until len) {
            pset = "0" + pset
        }

        val set = mutableListOf<T>()
        for (j in 0 until pset.length) {
            if (pset[j] == '1')
                set.add(this[j])
        }

        comboList.add(set)
    }
    return comboList
}