package me.giacomozama.adventofcode2022.days

import java.io.File

class Day21 : Day() {

    private lateinit var input: List<String>

    override fun parseInput(inputFile: File) {
        input = inputFile.readLines()
    }

    // n = number of monkeys
    // time: O(n), space: O(n)
    override fun solveFirstPuzzle(): Long {
        abstract class MonkeyFunction {
            abstract fun evaluate(): Long
        }

        val functions = hashMapOf<String, MonkeyFunction>()

        class Immediate(val value: Long) : MonkeyFunction() {
            override fun evaluate(): Long = value
        }

        class Operation(
            val monkeyA: String, val monkeyB: String, val operation: (Long, Long) -> Long
        ) : MonkeyFunction() {
            override fun evaluate(): Long {
                return operation(functions[monkeyA]!!.evaluate(), functions[monkeyB]!!.evaluate())
            }
        }

        for (line in input) {
            val (key, value) = line.split(": ")
            functions[key] = if (value[0] in 'a'..'z') {
                val components = value.split(' ')
                Operation(
                    monkeyA = components[0], monkeyB = components[2], operation = when (components[1]) {
                        "+" -> Long::plus
                        "-" -> Long::minus
                        "*" -> Long::times
                        else -> Long::div
                    }
                )
            } else {
                Immediate(value.toLong())
            }
        }

        return functions["root"]!!.evaluate()
    }

    override fun solveSecondPuzzle(): Long {
        data class Fraction(val num: Long, val den: Long) {

            private fun simplified(num: Long, den: Long): Fraction {
                if (num == 0L) return Fraction(num, den)
                var a = if (num > 0) num else -num
                var b = if (den > 0) den else -den
                while (b != 0L) {
                    val t = a
                    a = b
                    b = t % b
                }
                return Fraction(num / a, den / a)
            }

            operator fun plus(other: Fraction): Fraction {
                val rDen = den * other.den
                val rNum = rDen / den * num + rDen / other.den * other.num
                return simplified(rNum, rDen)
            }

            operator fun minus(other: Fraction): Fraction {
                val rDen = den * other.den
                val rNum = rDen / den * num - rDen / other.den * other.num
                return simplified(rNum, rDen)
            }

            operator fun times(other: Fraction) = simplified(num * other.num, den * other.den)

            operator fun div(other: Fraction) = simplified(num * other.den, den * other.num)
        }

        class Binomial(val coeff: Fraction, val offset: Fraction) {

            operator fun plus(other: Binomial) = Binomial(coeff + other.coeff, offset + other.offset)

            operator fun minus(other: Binomial) = Binomial(coeff - other.coeff, offset - other.offset)

            operator fun times(other: Binomial): Binomial = when {
                coeff.num != 0L -> Binomial(other.offset * coeff, offset * other.offset)
                other.coeff.num != 0L -> Binomial(offset * other.coeff, offset * other.offset)
                else -> Binomial(Fraction(0, 1), offset * other.offset)
            }

            operator fun div(other: Binomial): Binomial = when {
                coeff.num != 0L -> Binomial(coeff / other.offset, offset / other.offset)
                other.coeff.num != 0L -> Binomial(offset / other.coeff, offset / other.offset)
                else -> Binomial(Fraction(0, 1), offset / other.offset)
            }
        }

        abstract class MonkeyFunction {
            abstract fun evaluate(): Binomial
        }

        val functions = hashMapOf<String, MonkeyFunction>()

        class Immediate(val value: Binomial) : MonkeyFunction() {
            override fun evaluate() = value
        }

        class Operation(
            val monkeyA: String,
            val monkeyB: String,
            val operation: (Binomial, Binomial) -> Binomial
        ) : MonkeyFunction() {
            override fun evaluate() = operation(functions[monkeyA]!!.evaluate(), functions[monkeyB]!!.evaluate())
        }

        val entryPoints = Array(2) { "" }

        for (line in input) {
            val (key, value) = line.split(": ")
            when {
                key == "root" -> {
                    val components = value.split(' ')
                    entryPoints[0] = components[0]
                    entryPoints[1] = components[2]
                }
                key == "humn" -> {
                    functions[key] = Immediate(
                        Binomial(
                            Fraction(1, 1),
                            Fraction(0, 1)
                        )
                    )
                }
                value[0] in 'a'..'z' -> {
                    val components = value.split(' ')
                    functions[key] = Operation(
                        monkeyA = components[0],
                        monkeyB = components[2],
                        operation = when (components[1]) {
                            "+" -> Binomial::plus
                            "-" -> Binomial::minus
                            "*" -> Binomial::times
                            else -> Binomial::div
                        }
                    )
                }
                else -> {
                    functions[key] = Immediate(Binomial(Fraction(0, 1), Fraction(value.toLong(), 1)))
                }
            }
        }

        var a = functions[entryPoints[0]]!!.evaluate()
        var b = functions[entryPoints[1]]!!.evaluate()
        if (a.coeff.num == 0L) {
            val t = a
            a = b
            b = t
        }
        val c = (b.offset - a.offset) / a.coeff
        return c.num / c.den
    }
}
