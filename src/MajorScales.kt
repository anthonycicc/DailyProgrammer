enum class Note(val number: Int) {
    C(0), `C#`(1), D(2), `D#`(3),
    E(4), F(5), `F#`(6), G(7),
    `G#`(8), A(9), `A#`(10), B(11);

    companion object {
        fun from(findValue: Int): Note = Note.values().first { it.ordinal == findValue }
    }

    operator fun plus(x: Int): Note {
        return Note.from((this.ordinal + x).rem(12))
    }
}

enum class ScaleName {
    Do, Re, Mi, Fa, So, La, Ti
}

fun note(x: Note, y: ScaleName): Note{
    return x + when(y){
        ScaleName.Do -> 0
        ScaleName.Re -> 2
        ScaleName.Mi -> 4
        ScaleName.Fa -> 5
        ScaleName.So -> 7
        ScaleName.La -> 9
        ScaleName.Ti -> 11
    }
}