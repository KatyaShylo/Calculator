package com.example.calculator.developCalculator

class Lexeme(val value: String = "", val type: LexemeType = LexemeType.NUMBER) {
    override fun toString(): String {
        return value
    }
}