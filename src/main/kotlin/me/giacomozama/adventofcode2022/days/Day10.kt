package me.giacomozama.adventofcode2022.days

import java.io.File

class Day10 : Day() {

    private lateinit var input: List<String>

    override fun parseInput(inputFile: File) {
        input = inputFile.readLines()
    }

    // n = numbers of cycles
    // time: O(n), space: O(1)
    override fun solveFirstPuzzle(): Int {
        var value: Int? = null
        var reg = 1
        var result = 0
        var pc = 0
        for (cycle in 1..220) {
            if (cycle % 40 == 20) result += reg * cycle
            if (value != null) {
                reg += value
                value = null
            } else {
                val op = input[pc++]
                if (op != "noop") value = op.substringAfter(' ').toInt()
            }
        }
        return result
    }

    // time: O(n), space: O(n)
    override fun solveSecondPuzzle(): String {
        val crt = Array(6) { CharArray(40) { '░' } }
        var value: Int? = null
        var reg = 1
        var pc = 0
        for (cycle in 0..239) {
            val x = cycle % 40
            if (x in reg - 1..reg + 1) crt[cycle / 40][x] = '█'
            if (value != null) {
                reg += value
                value = null
            } else {
                val op = input[pc++]
                if (op != "noop") value = op.substringAfter(' ').toInt()
            }
        }
        return "\n" + crt.joinToString("\n") { String(it) }
    }
}