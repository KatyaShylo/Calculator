package com.example.calculator.developCalculator

class LexemeBuffer(private var lexemes: List<Lexeme>) {
    var i = 0
        private set

    operator fun next(): Lexeme {
        return lexemes[i++]
    }

    fun back() {
        i--
    }
}