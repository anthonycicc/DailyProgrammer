data class Card(val value: Value, val suit: Suit)

enum class Value{
    A, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

enum class Suit{
    CLUB, SPADE, HEART, DIAMOND
}