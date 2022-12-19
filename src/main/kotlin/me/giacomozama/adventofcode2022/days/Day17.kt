package me.giacomozama.adventofcode2022.days

import java.io.File

class Day17 : Day() {

    private lateinit var input: String

    override fun parseInput(inputFile: File) {
        input = inputFile.readText()
    }

    private sealed interface Rock {

        var left: Int

        var bottom: Int

        fun pushLeft(grid: Array<BooleanArray>)
        fun pushRight(grid: Array<BooleanArray>)
        fun pushDown(grid: Array<BooleanArray>): Int

        // ####
        class HorizontalLineShape(override var bottom: Int) : Rock {

            override var left = 2

            override fun pushLeft(grid: Array<BooleanArray>) {
                if (left > 0 && !grid[bottom][left - 1]) left--
            }

            override fun pushRight(grid: Array<BooleanArray>) {
                if (left < 3 && !grid[bottom][left + 4]) left++
            }

            override fun pushDown(grid: Array<BooleanArray>): Int {
                return if (bottom == 0 ||
                    grid[bottom - 1][left] ||
                    grid[bottom - 1][left + 1] ||
                    grid[bottom - 1][left + 2] ||
                    grid[bottom - 1][left + 3]
                ) {
                    grid[bottom][left] = true
                    grid[bottom][left + 1] = true
                    grid[bottom][left + 2] = true
                    grid[bottom][left + 3] = true
                    bottom
                } else {
                    bottom--
                    -1
                }
            }
        }

        // .#.
        // ###
        // .#.
        class CrossShape(override var bottom: Int) : Rock {

            override var left = 2

            override fun pushLeft(grid: Array<BooleanArray>) {
                if (left > 0 &&
                    !grid[bottom][left] &&
                    !grid[bottom + 1][left - 1] &&
                    !grid[bottom + 2][left]
                ) left--
            }

            override fun pushRight(grid: Array<BooleanArray>) {
                if (left < 4 &&
                    !grid[bottom][left + 2] &&
                    !grid[bottom + 1][left + 3] &&
                    !grid[bottom + 2][left + 2]
                ) {
                    left++
                }
            }

            override fun pushDown(grid: Array<BooleanArray>): Int {
                return if (bottom == 0 ||
                    grid[bottom][left] ||
                    grid[bottom - 1][left + 1] ||
                    grid[bottom][left + 2]
                ) {
                    grid[bottom][left + 1] = true
                    grid[bottom + 1][left] = true
                    grid[bottom + 1][left + 2] = true
                    grid[bottom + 2][left + 1] = true
                    bottom + 2
                } else {
                    bottom--
                    -1
                }
            }
        }

        // ..#
        // ..#
        // ###
        class LShape(override var bottom: Int) : Rock {

            override var left = 2

            override fun pushLeft(grid: Array<BooleanArray>) {
                if (left > 0 &&
                    !grid[bottom][left - 1] &&
                    !grid[bottom + 1][left + 1] &&
                    !grid[bottom + 2][left + 1]
                ) left--
            }

            override fun pushRight(grid: Array<BooleanArray>) {
                if (left < 4 &&
                    !grid[bottom][left + 3] &&
                    !grid[bottom + 1][left + 3] &&
                    !grid[bottom + 2][left + 3]
                ) left++
            }

            override fun pushDown(grid: Array<BooleanArray>): Int {
                return if (bottom == 0 ||
                    grid[bottom - 1][left] ||
                    grid[bottom - 1][left + 1] ||
                    grid[bottom - 1][left + 2]
                ) {
                    grid[bottom][left] = true
                    grid[bottom][left + 1] = true
                    grid[bottom][left + 2] = true
                    grid[bottom + 1][left + 2] = true
                    grid[bottom + 2][left + 2] = true
                    bottom + 2
                } else {
                    bottom--
                    -1
                }
            }
        }

        // #
        // #
        // #
        // #
        class VerticalLineShape(override var bottom: Int) : Rock {

            override var left = 2

            override fun pushLeft(grid: Array<BooleanArray>) {
                if (left > 0 &&
                    !grid[bottom][left - 1] &&
                    !grid[bottom + 1][left - 1] &&
                    !grid[bottom + 2][left - 1] &&
                    !grid[bottom + 3][left - 1]
                ) left--
            }

            override fun pushRight(grid: Array<BooleanArray>) {
                if (left < 6 &&
                    !grid[bottom][left + 1] &&
                    !grid[bottom + 1][left + 1] &&
                    !grid[bottom + 2][left + 1] &&
                    !grid[bottom + 3][left + 1]
                ) left++
            }

            override fun pushDown(grid: Array<BooleanArray>): Int {
                return if (bottom == 0 || grid[bottom - 1][left]) {
                    grid[bottom][left] = true
                    grid[bottom + 1][left] = true
                    grid[bottom + 2][left] = true
                    grid[bottom + 3][left] = true
                    bottom + 3
                } else {
                    bottom--
                    -1
                }
            }
        }

        // ##
        // ##
        class SquareShape(override var bottom: Int) : Rock {

            override var left = 2

            override fun pushLeft(grid: Array<BooleanArray>) {
                if (left > 0 &&
                    !grid[bottom][left - 1] &&
                    !grid[bottom + 1][left - 1]
                ) left--
            }

            override fun pushRight(grid: Array<BooleanArray>) {
                if (left < 5 &&
                    !grid[bottom][left + 2] &&
                    !grid[bottom + 1][left + 2]
                ) left++
            }

            override fun pushDown(grid: Array<BooleanArray>): Int {
                return if (bottom == 0 ||
                    grid[bottom - 1][left] ||
                    grid[bottom - 1][left + 1]
                ) {
                    grid[bottom][left] = true
                    grid[bottom][left + 1] = true
                    grid[bottom + 1][left] = true
                    grid[bottom + 1][left + 1] = true
                    bottom + 1
                } else {
                    bottom--
                    -1
                }
            }
        }
    }

    private val shapeSequence = arrayOf<(Int) -> Rock>(
        Rock::HorizontalLineShape,
        Rock::CrossShape,
        Rock::LShape,
        Rock::VerticalLineShape,
        Rock::SquareShape,
    )

    // n = number of fallen rocks
    // time: O(n), space: O(n)
    override fun solveFirstPuzzle(): Int {
        val grid = Array(5_500) { BooleanArray(7) }
        var rocksSettled = 0
        var highestPoint = -1
        var sidePushIndex = 0
        var currentRock: Rock? = null
        var shouldFall = false
        while (rocksSettled < 2022) {
            if (currentRock == null) {
                currentRock = shapeSequence[rocksSettled % shapeSequence.size](highestPoint + 4)
            }
            if (shouldFall) {
                val h = currentRock.pushDown(grid)
                if (h >= 0) {
                    highestPoint = maxOf(highestPoint, h)
                    currentRock = null
                    rocksSettled++
                }
            } else {
                if (input[sidePushIndex++] == '<') {
                    currentRock.pushLeft(grid)
                } else {
                    currentRock.pushRight(grid)
                }
                sidePushIndex %= input.length
            }
            shouldFall = !shouldFall
        }
        return highestPoint + 1
    }

    // m = some arbitrary number of rocks within which we expect to find the sequence
    // not sure if it works for all inputs
    // time: O(m), space: O(m)
    override fun solveSecondPuzzle(): Long {
        val grid = Array(6_500) { BooleanArray(7) }
        val indicesOfBottomsAtHeight = hashMapOf<Int, Int>()

        var rocksSettled = 0
        var highestPoint = -1
        var sidePushIndex = 0
        var currentRock: Rock? = null
        var shouldFall = false

        while (rocksSettled < 4_000) {
            if (currentRock == null) {
                currentRock = shapeSequence[rocksSettled % shapeSequence.size](highestPoint + 4)
            }
            if (shouldFall) {
                val h = currentRock.pushDown(grid)
                if (h >= 0) {
                    highestPoint = maxOf(highestPoint, h)
                    indicesOfBottomsAtHeight[currentRock.bottom] = ++rocksSettled
                    currentRock = null
                }
            } else {
                if (input[sidePushIndex++] == '<') {
                    currentRock.pushLeft(grid)
                } else {
                    currentRock.pushRight(grid)
                }
                sidePushIndex %= input.length
            }
            shouldFall = !shouldFall
        }

        // we expect the sequence to appear before height 3500, this was tested empirically
        var r = 3_500
        var l = 0
        outer@ while (l < r) {
            while (!(grid[l] contentEquals grid[r])) l++

            var j = l
            var k = r
            while (j < r) {
                if (!(grid[j++] contentEquals grid[k++])) {
                    l++
                    continue@outer
                }
            }

            break
        }

        while (grid[--l] contentEquals grid[--r]) {
            // do nothing
        }

        var firstRock = indicesOfBottomsAtHeight[++l]
        while (firstRock == null) {
            firstRock = indicesOfBottomsAtHeight[--l]
        }

        var lastRock = indicesOfBottomsAtHeight[r]
        while (lastRock == null) {
            lastRock = indicesOfBottomsAtHeight[--r]
        }

        val rocksLeft = ((1_000_000_000_001L - firstRock) % (lastRock - firstRock)).toInt()
        for (i in l + 1..r) {
            val p = indicesOfBottomsAtHeight[i] ?: continue
            if (p - firstRock == rocksLeft) {
                return i + (1_000_000_000_001L - firstRock) / (lastRock - firstRock) * (r - l)
            }
        }

        error("Sequence not found")
    }
}