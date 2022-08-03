package com.example.calculator.developCalculator

fun main() {
    val expression = "100 + 2 * ( 3 - 4 ) + 57 / 7 + ( 5 * (55-5) + 1)"
    val lexAnalyze = LexAnalyze()
    val lexemes = lexAnalyze.lexemParse(expression)
    val lexemeBuffer = LexemeBuffer(lexemes)
    println(
        """
        $expression
        Результат: ${expr(lexemeBuffer)}  
    """.trimIndent()
    )
}