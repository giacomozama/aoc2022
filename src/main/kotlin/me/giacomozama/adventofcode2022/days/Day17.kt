package me.giacomozama.adventofcode2022.days

import java.io.File

class Day17 : Day() {

    private lateinit var input: String

    override fun parseInput(inputFile: File) {
        input = inputFile.readText()
    }

    private sealed interface Rock {

        fun pushLeft(grid: Array<BooleanArray>)
        fun pushRight(grid: Array<BooleanArray>)
        fun pushDown(grid: Array<BooleanArray>): Int

        // ####
        class HorizontalLineShape(private var bottom: Int) : Rock {

            private var left = 2

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
        class CrossShape(private var bottom: Int) : Rock {

            private var left = 2

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
        class LShape(private var bottom: Int) : Rock {

            private var left = 2

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
        class VerticalLineShape(private var bottom: Int) : Rock {

            private var left = 2

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
        class SquareShape(private var bottom: Int) : Rock {

            private var left = 2

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

    override fun solveSecondPuzzle(): Any {
        return "Not yet completed"
    }
}