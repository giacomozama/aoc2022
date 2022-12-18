package me.giacomozama.adventofcode2022.days

import java.io.File

class Day02 : Day() {

    private lateinit var input: List<CharArray>

    override fun parseInput(inputFile: File) {
        val result = mutableListOf<CharArray>()
        inputFile.useLines { lines ->
            for (line in lines) {
                result.add(charArrayOf(line[0], line[2]))
            }
        }
        input = result
    }

    // time: O(n), space: O(1)
    override fun solveFirstPuzzle(): Int {
        val points = arrayOf(
            intArrayOf(4, 1, 7),
            intArrayOf(8, 5, 2),
            intArrayOf(3, 9, 6)
        )
        return input.fold(0) { s, (a, b) -> s + points[b - 'X'][a - 'A'] }
    }

    // time: O(n), space: O(1)
    override fun solveSecondPuzzle(): Int {
        val points = arrayOf(
            intArrayOf(3, 1, 2),
            intArrayOf(4, 5, 6),
            intArrayOf(8, 9, 7)
        )
        return input.fold(0) { s, (a, b) -> s + points[b - 'X'][a - 'A'] }
    }
}