package me.giacomozama.adventofcode2022

import me.giacomozama.adventofcode2022.days.AdventCalendar
import kotlin.time.Duration
import kotlin.time.measureTime

fun main() {
    println("=== Advent of Code 2022 - Giacomo Zama ===")

    var globalTiming = Duration.ZERO
    for (i in 1..25) {
        val day = AdventCalendar.getForDay(i) ?: continue
        println("\n-- Solving day $i:")

        val firstSolution: Any
        val firstTiming = measureTime { firstSolution = day.solveFirstPuzzle() }
        println("---- First puzzle solution: $firstSolution. Took $firstTiming.")

        val secondSolution: Any
        val secondTiming = measureTime { secondSolution = day.solveSecondPuzzle() }
        println("---- Second puzzle solution: $secondSolution. Took $secondTiming.")

        globalTiming += firstTiming + secondTiming
    }

    println("\nAll done! Took $globalTiming.")
}