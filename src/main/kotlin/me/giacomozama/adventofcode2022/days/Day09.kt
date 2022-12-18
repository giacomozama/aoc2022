package me.giacomozama.adventofcode2022.days

import java.io.File
import kotlin.math.abs
import kotlin.math.sign

class Day09 : Day() {

    private lateinit var input: List<IntArray>

    override fun parseInput(inputFile: File) {
        input = inputFile.useLines { lines ->
            lines.map {
                intArrayOf(
                    when (it[0]) {
                        'U' -> U
                        'R' -> R
                        'D' -> D
                        else -> L
                    },
                    it.drop(2).toInt()
                )
            }.toList()
        }
    }

    // time: O(n), space: O(n)
    override fun solveFirstPuzzle(): Int {
        val visited = hashSetOf<Int>()
        var headX = 0
        var headY = 0
        var tailX = 0
        var tailY = 0
        for ((dir, dist) in input) {
            repeat(dist) {
                when (dir) {
                    U -> headY--
                    R -> headX++
                    D -> headY++
                    L -> headX--
                }
                if (abs(headX - tailX) > 1 || abs(headY - tailY) > 1) {
                    tailX += (headX - tailX).sign
                    tailY += (headY - tailY).sign
                }
                visited += tailX * 10007 + tailY
            }
        }
        return visited.size
    }

    // time: O(n), space: O(n)
    override fun solveSecondPuzzle(): Int {
        val visited = hashSetOf<Int>()
        val state = Array(10) { IntArray(2) }
        for ((dir, dist) in input) {
            repeat(dist) {
                when (dir) {
                    U -> state[0][1]--
                    R -> state[0][0]++
                    D -> state[0][1]++
                    L -> state[0][0]--
                }
                for (i in 1..9) {
                    if (abs(state[i - 1][0] - state[i][0]) > 1 || abs(state[i - 1][1] - state[i][1]) > 1) {
                        state[i][0] += (state[i - 1][0] - state[i][0]).sign
                        state[i][1] += (state[i - 1][1] - state[i][1]).sign
                    }
                }
                visited += state[9][0] * 10007 + state[9][1]
            }
        }
        return visited.size
    }

    companion object {

        private const val U = 0
        private const val R = 1
        private const val D = 2
        private const val L = 3
    }
}