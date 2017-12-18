import java.io.BufferedReader
import java.io.FileReader
import java.util.*

data class Pitch(private val name: String, private val octave: Int) {
    constructor(pitchString: String) : this(
            name = pitchString.take(pitchString.length - 1),
            octave = pitchString.last().toInt() - 48)

    override fun toString(): String {
        return "$name$octave"
    }
}

data class Note(val pitch: Pitch, val measure: Int, val beat: Double, val length: Double) {
    override fun toString(): String {
        return "$pitch ${measure * 3 + beat} $length"
    }

    fun toNewMeasure(x: Int): Note {
        return this.copy(measure = x)
    }
}

val choiceTable = listOf(listOf(96, 32, 69, 40, 148, 104, 152, 119, 98, 3, 54),
        listOf(22, 6, 95, 17, 74, 157, 60, 84, 142, 87, 130),
        listOf(141, 128, 158, 113, 163, 27, 171, 114, 42, 165, 10),
        listOf(41, 63, 13, 85, 45, 167, 53, 50, 156, 61, 103),
        listOf(105, 146, 153, 161, 80, 154, 99, 140, 75, 135, 28),
        listOf(122, 46, 55, 2, 97, 68, 133, 86, 129, 47, 37),
        listOf(11, 134, 110, 159, 36, 118, 21, 169, 62, 147, 106),
        listOf(30, 81, 24, 100, 107, 91, 127, 94, 123, 33, 5),
        listOf(70, 117, 66, 90, 25, 138, 16, 120, 65, 102, 35),
        listOf(121, 39, 136, 176, 143, 71, 155, 88, 77, 4, 20),
        listOf(26, 126, 15, 7, 64, 150, 57, 48, 19, 31, 108),
        listOf(9, 56, 132, 34, 125, 29, 175, 166, 82, 164, 92),
        listOf(112, 174, 73, 67, 76, 101, 43, 51, 137, 144, 12),
        listOf(49, 18, 58, 160, 136, 162, 168, 115, 38, 59, 124),
        listOf(109, 116, 145, 52, 1, 23, 89, 72, 149, 173, 44),
        listOf(14, 83, 79, 170, 93, 151, 172, 111, 8, 78, 131))

fun main(args: Array<String>) {
    val r = Random()
    val br = BufferedReader(FileReader("problems/MozartsMusicalDiceStartingComposition.txt"))
    val choices = choiceTable.map { it[(r.nextInt(6)) + (r.nextInt(6))] }

    val oldNoteMap = br.readLines().asSequence()
            .map { s -> s.toNote() }
            .fold(initial = mutableMapOf<Int, MutableList<Note>>()) { acc, note -> acc.getOrPut(note.measure, defaultValue = { mutableListOf() }).add(note); acc }

    val newNoteList = choices.foldIndexed(initial = mutableListOf()) { newMeasure: Int, acc: MutableList<Note>, oldMeasure: Int ->
        acc.addAll(oldNoteMap.getOrDefault(oldMeasure, defaultValue = listOf<Note>()).map { note -> note.toNewMeasure(newMeasure) }); acc
    }

    newNoteList.forEach { println(it) }
}

fun String.toNote(): Note {
    val split = this.split(' ')
    return Note(
            pitch = Pitch(split[0]),
            measure = (split[1].toDouble() / 3.0).toInt(),
            beat = split[1].toDouble().rem(3),
            length = split[2].toDouble()
    )
}