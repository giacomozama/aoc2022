package me.giacomozama.adventofcode2022.days

import java.io.File
import kotlin.math.sign

class Day14 : Day() {

    private lateinit var input: List<List<IntArray>>

    override fun parseInput(inputFile: File) {
        input = inputFile.useLines { lines ->
            lines.map { line ->
                line.split(" -> ").map { coords ->
                    val commaIndex = coords.indexOf(',')
                    intArrayOf(coords.take(commaIndex).toInt(), coords.substring(commaIndex + 1).toInt())
                }
            }.toList()
        }
    }

    private fun drawRockFormationsAndGetMaxY(target: Array<CharArray>): Int {
        var maxY = 0
        for (formation in input) {
            val cur = formation[0].copyOf()
            maxY = maxOf(maxY, cur[1])
            target[cur[0]][cur[1]] = '#'
            for (i in 1 until formation.size) {
                val (x, y) = formation[i]
                maxY = maxOf(maxY, y)
                if (cur[0] == x) {
                    val d = (y - cur[1]).sign
                    while (cur[1] != y) {
                        cur[1] += d
                        target[cur[0]][cur[1]] = '#'
                    }
                } else {
                    val d = (x - cur[0]).sign
                    while (cur[0] != x) {
                        cur[0] += d
                        target[cur[0]][cur[1]] = '#'
                    }
                }
            }
        }
        return maxY
    }

    // time: O((h ^ 2) * w), space: O(h * w)
    override fun solveFirstPuzzle(): Int {
        val state = Array(560) { CharArray(170) { '.' } }
        val maxY = drawRockFormationsAndGetMaxY(state)
        var settled = 0
        while (true) {
            val cur = intArrayOf(500, 0)
            var isFalling = true
            while (isFalling) {
                if (cur[1] > maxY) return settled
                when {
                    state[cur[0]][cur[1] + 1] == '.' -> {
                    }
                    state[cur[0] - 1][cur[1] + 1] == '.' -> {
                        cur[0]--
                    }
                    state[cur[0] + 1][cur[1] + 1] == '.' -> {
                        cur[0]++
                    }
                    else -> {
                        state[cur[0]][cur[1]] = 'O'
                        settled++
                        isFalling = false
                    }
                }
                cur[1]++
            }
        }
    }

    // time: O((h ^ 2) * w), space: O(h * w)
    override fun solveSecondPuzzle(): Int {
        val state = Array(670) { CharArray(170) { '.' } }
        val floorY = drawRockFormationsAndGetMaxY(state) + 2
        for (i in state.indices) state[i][floorY] = '#'
        var settled = 0
        while (true) {
            val cur = intArrayOf(500, 0)
            var isFalling = true
            while (isFalling) {
                when {
                    state[cur[0]][cur[1] + 1] == '.' -> {
                    }
                    state[cur[0] - 1][cur[1] + 1] == '.' -> {
                        cur[0]--
                    }
                    state[cur[0] + 1][cur[1] + 1] == '.' -> {
                        cur[0]++
                    }
                    else -> {
                        state[cur[0]][cur[1]] = 'O'
                        settled++
                        if (cur[1] == 0) return settled
                        isFalling = false
                    }
                }
                cur[1]++
            }
        }
    }
}