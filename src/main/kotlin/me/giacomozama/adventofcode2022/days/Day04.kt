package me.giacomozama.adventofcode2022.days

import java.io.File

class Day04 : Day() {

    private lateinit var input: List<IntArray>

    override fun parseInput(inputFile: File) {
        val regex = """(\d+)-(\d+),(\d+)-(\d+)""".toRegex()
        input = inputFile.useLines { lines ->
            lines.map {
                val match = regex.matchEntire(it)
                requireNotNull(match)
                intArrayOf(
                    match.groupValues[1].toInt(),
                    match.groupValues[2].toInt(),
                    match.groupValues[3].toInt(),
                    match.groupValues[4].toInt()
                )
            }.toList()
        }
    }

    // time: O(n), space: O(1)
    override fun solveFirstPuzzle(): Int {
        var result = 0
        for ((a, b, c, d) in input) {
            if (c >= a && d <= b || a >= c && b <= d) result++
        }
        return result
    }

    // time: O(n), space: O(1)
    override fun solveSecondPuzzle(): Int {
        var result = 0
        for ((a, b, c, d) in input) {
            if (a < d && b >= c || c < b && d >= a) result++
        }
        return result
    }
}