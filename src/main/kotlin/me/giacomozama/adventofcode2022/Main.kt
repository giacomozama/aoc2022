package me.giacomozama.adventofcode2022

import me.giacomozama.adventofcode2022.days.Day
import me.giacomozama.adventofcode2022.days.Day1
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    println("=== Advent of Code 2022 - Giacomo Zama ===")

    val days = listOf<() -> Day>(::Day1)

    var globalTiming = Duration.ZERO
    for (day in days) {
        val solution = day()
        println("\n-- Solving day ${solution.number}:")

        val firstSolution: Any
        val firstTiming = measureTime { firstSolution = solution.solveFirstPuzzle() }
        println("---- First puzzle solution: $firstSolution. Took $firstTiming.")

        val secondSolution: Any
        val secondTiming = measureTime { secondSolution = solution.solveSecondPuzzle() }
        println("---- Second puzzle solution: $secondSolution. Took $secondTiming.")

        globalTiming += firstTiming + secondTiming
    }

    println("\nAll done! Took $globalTiming.")
}