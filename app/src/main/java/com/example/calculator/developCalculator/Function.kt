package com.example.calculator.developCalculator

import java.lang.RuntimeException

fun expr(lexemes: LexemeBuffer): Double {
    val lexeme = lexemes.next()
    return if (lexeme.type == LexemeType.END_EXPRESSION) {
        0.0
    } else {
        lexemes.back()
        plusAndMinus(lexemes)
    }
}

fun plusAndMinus(lexemes: LexemeBuffer): Double {
    var value = multiplicationAndDivision(lexemes)
    while (true) {
        val lexeme = lexemes.next()
        when (lexeme.type) {
            LexemeType.PLUS -> value += multiplicationAndDivision(lexemes)
            LexemeType.MINUS -> value -= multiplicationAndDivision(lexemes)
            LexemeType.END_EXPRESSION, LexemeType.CLOSING_BRACKET -> {
                lexemes.back()
                return value
            }
            else -> throw RuntimeException(
                "Unexpected lexem: " + lexeme.value
                        + " at position: " + lexemes.i
            )
        }
    }
}

fun multiplicationAndDivision(lexemes: LexemeBuffer): Double {
    var value = factor(lexemes)
    while (true) {
        val lexeme = lexemes.next()
        when (lexeme.type) {
            LexemeType.MULTIPLICATION -> value *= factor(lexemes)
            LexemeType.DIVISION -> value /= factor(lexemes)
            LexemeType.END_EXPRESSION, LexemeType.CLOSING_BRACKET, LexemeType.PLUS, LexemeType.MINUS -> {
                lexemes.back()
                return value
            }
            else -> throw RuntimeException(
                "Unexpected lexem: " + lexeme.value
                        + " at position: " + lexemes.i
            )
        }
    }
}

fun factor(lexemes: LexemeBuffer): Double {
    var lexeme = lexemes.next()
    return when (lexeme.type) {
        LexemeType.NUMBER -> lexeme.value.toDouble()
        LexemeType.OPENING_BRACKET -> {
            val value = plusAndMinus(lexemes)
            lexeme = lexemes.next()
            if (lexeme.type != LexemeType.CLOSING_BRACKET) {
                throw RuntimeException(
                    "Unexpected lexem: " + lexeme.value
                            + " at position: " + lexemes.i
                )
            }
            value
        }
        else -> throw RuntimeException(
            "Unexpected lexem: " + lexeme.value
                    + " at position: " + lexemes.i
        )
    }
}