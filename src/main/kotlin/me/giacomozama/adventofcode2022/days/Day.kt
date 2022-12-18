package me.giacomozama.adventofcode2022.days

import java.io.File

abstract class Day {

    abstract fun parseInput(inputFile: File)

    abstract fun solveFirstPuzzle(): Any

    abstract fun solveSecondPuzzle(): Any
}