package com.example.calculator.developCalculator

class LexAnalyze {
    fun lexemParse(expText: String): List<Lexeme> {
        val lexemes: MutableList<Lexeme> = mutableListOf()
        var i = 0
        while (i < expText.length) {
            while (i < expText.length && expText[i] == ' ') {
                i++
                continue
            }
            var newLexemeName = ""
            if (expText[i] == '(') {
                lexemes += Lexeme(expText[i].toString(), LexemeType.OPENING_BRACKET)
                i++
            } else if (expText[i] == ')') {
                lexemes += Lexeme(expText[i].toString(), LexemeType.CLOSING_BRACKET)
                i++
            } else if (expText[i] == '+') {
                lexemes += Lexeme(expText[i].toString(), LexemeType.PLUS)
                i++
            } else if (expText[i] == '-') {
                lexemes += Lexeme(expText[i].toString(), LexemeType.MINUS)
                i++
            } else if (expText[i] == '*') {
                lexemes += Lexeme(expText[i].toString(), LexemeType.MULTIPLICATION)
                i++
            } else if (expText[i] == '/') {
                lexemes += Lexeme(expText[i].toString(), LexemeType.DIVISION)
                i++
            } else if (expText[i] in '0'..'9') {
                newLexemeName += expText[i]
                i++
                while (i < expText.length && expText[i] in '0'..'9') {
                    newLexemeName += expText[i]
                    i++
                }
                lexemes += Lexeme(newLexemeName, LexemeType.NUMBER)
            }
        }
        lexemes += Lexeme("", LexemeType.END_EXPRESSION)
        return lexemes
    }
}