package me.giacomozama.adventofcode2022.days

import java.io.File

class Day08 : Day() {

    private lateinit var input: List<IntArray>

    override fun parseInput(inputFile: File) {
        input = inputFile.useLines { lines ->
            lines.map { line -> IntArray(line.length) { line[it].digitToInt() } }.toList()
        }
    }

    // n = number of trees
    // time: O(n), space: O(n)
    override fun solveFirstPuzzle(): Int {
        val m = input.size
        val n = input[0].size
        val isVisible = Array(m) { BooleanArray(n) }
        for (x in 0 until n) {
            isVisible[0][x] = true
            var tallest = input[0][x]
            for (y in 1 until m) {
                if (input[y][x] > tallest) {
                    isVisible[y][x] = true
                    tallest = input[y][x]
                }
            }
            isVisible[m - 1][x] = true
            tallest = input[m - 1][x]
            for (y in m - 2 downTo 0) {
                if (input[y][x] > tallest) {
                    isVisible[y][x] = true
                    tallest = input[y][x]
                }
            }
        }
        for (y in 0 until m) {
            isVisible[y][0] = true
            var tallest = input[y][0]
            for (x in 1 until n) {
                if (input[y][x] > tallest) {
                    isVisible[y][x] = true
                    tallest = input[y][x]
                }
            }
            isVisible[y][n - 1] = true
            tallest = input[y][n - 1]
            for (x in n - 2 downTo 0) {
                if (input[y][x] > tallest) {
                    isVisible[y][x] = true
                    tallest = input[y][x]
                }
            }
        }
        return isVisible.sumOf { r -> r.count { it } }
    }

    // n = number of trees, d = number of possible tree heights
    // time: O(nd), space: O(n + d)
    override fun solveSecondPuzzle(): Int {
        val m = input.size
        val n = input[0].size
        val scores = Array(m) { IntArray(n) { 1 } }
        var result = 0
        for (x in 0 until n) {
            val dp = IntArray(10)
            for (y in 1 until m) {
                var closest = Int.MIN_VALUE
                for (i in input[y][x]..9) closest = maxOf(closest, dp[i])
                scores[y][x] *= y - closest
                dp[input[y][x]] = y
            }
            dp.fill(m - 1)
            for (y in m - 2 downTo 0) {
                var closest = Int.MAX_VALUE
                for (i in input[y][x]..9) closest = minOf(closest, dp[i])
                scores[y][x] *= closest - y
                dp[input[y][x]] = y
            }
        }
        for (y in 0 until m) {
            val dp = IntArray(10)
            for (x in 1 until n) {
                var closest = Int.MIN_VALUE
                for (i in input[y][x]..9) closest = maxOf(closest, dp[i])
                scores[y][x] *= x - closest
                dp[input[y][x]] = x
            }
            dp.fill(n - 1)
            for (x in n - 2 downTo 0) {
                var closest = Int.MAX_VALUE
                for (i in input[y][x]..9) closest = minOf(closest, dp[i])
                result = maxOf(result, scores[y][x] * (closest - x))
                dp[input[y][x]] = x
            }
        }
        return result
    }
}