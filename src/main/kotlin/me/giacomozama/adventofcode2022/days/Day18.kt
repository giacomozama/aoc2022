package me.giacomozama.adventofcode2022.days

import java.io.File

class Day18 : Day() {

    private lateinit var input: List<IntArray>

    override fun parseInput(inputFile: File) {
        input = inputFile.useLines { lines ->
            lines.map { it.split(',') }
                .map { s -> IntArray(3) { s[it].toInt() } }
                .toList()
        }
    }

    // n = side of total space
    // time: O(n ^ 3), space: O(n ^ 3)
    override fun solveFirstPuzzle(): Int {
        val isFilled = Array(20) { Array(20) { BooleanArray(20) } }
        for ((x, y, z) in input) isFilled[x][y][z] = true
        var surface = 0
        for (x in 0..19) {
            for (y in 0..19) {
                for (z in 0..19) {
                    if (!isFilled[x][y][z]) continue
                    if (x == 0 || !isFilled[x - 1][y][z]) surface++
                    if (y == 0 || !isFilled[x][y - 1][z]) surface++
                    if (z == 0 || !isFilled[x][y][z - 1]) surface++
                    if (x == 19 || !isFilled[x + 1][y][z]) surface++
                    if (y == 19 || !isFilled[x][y + 1][z]) surface++
                    if (z == 19 || !isFilled[x][y][z + 1]) surface++
                }
            }
        }
        return surface
    }

    // time: O(n ^ 3), space: O(n ^ 3)
    override fun solveSecondPuzzle(): Int {
        val isFilledOrExposed = Array(20) { Array(20) { CharArray(20) } }
        for ((x, y, z) in input) isFilledOrExposed[x][y][z] = 'F'

        fun dfs(x: Int, y: Int, z: Int) {
            if (isFilledOrExposed[x][y][z] != '\u0000') return
            isFilledOrExposed[x][y][z] = 'E'
            if (x > 0) dfs(x - 1, y, z)
            if (y > 0) dfs(x, y - 1, z)
            if (z > 0) dfs(x, y, z - 1)
            if (x < 19) dfs(x + 1, y, z)
            if (y < 19) dfs(x, y + 1, z)
            if (z < 19) dfs(x, y, z + 1)
        }

        for (x in 0..19) {
            for (y in 0..19) {
                dfs(x, y, 0)
                dfs(x, y, 19)
            }
        }

        for (x in 0..19) {
            for (z in 0..19) {
                dfs(x, 0, z)
                dfs(x, 19, z)
            }
        }

        for (y in 0..19) {
            for (z in 0..19) {
                dfs(0, y, z)
                dfs(19, y, z)
            }
        }

        var surface = 0
        for (x in 0..19) {
            for (y in 0..19) {
                for (z in 0..19) {
                    if (isFilledOrExposed[x][y][z] != 'F') continue
                    if (x == 0 || isFilledOrExposed[x - 1][y][z] == 'E') surface++
                    if (y == 0 || isFilledOrExposed[x][y - 1][z] == 'E') surface++
                    if (z == 0 || isFilledOrExposed[x][y][z - 1] == 'E') surface++
                    if (x == 19 || isFilledOrExposed[x + 1][y][z] == 'E') surface++
                    if (y == 19 || isFilledOrExposed[x][y + 1][z] == 'E') surface++
                    if (z == 19 || isFilledOrExposed[x][y][z + 1] == 'E') surface++
                }
            }
        }

        return surface
    }
}