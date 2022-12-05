package me.giacomozama.adventofcode2022.days

object AdventCalendar {

    private val days = mapOf(
        1 to ::Day1,
        2 to ::Day2,
        3 to ::Day3,
        4 to ::Day4,
        5 to ::Day5,
        // 6 to ::Day6,
        // 7 to ::Day7,
        // 8 to ::Day8,
        // 9 to ::Day9,
        // 10 to ::Day10,
        // 11 to ::Day11,
        // 12 to ::Day12,
        // 13 to ::Day13,
        // 14 to ::Day14,
        // 15 to ::Day15,
        // 16 to ::Day16,
        // 17 to ::Day17,
        // 18 to ::Day18,
        // 19 to ::Day19,
        // 20 to ::Day20,
        // 21 to ::Day21,
        // 22 to ::Day22,
        // 23 to ::Day23,
        // 24 to ::Day24,
        // 25 to ::Day25
    )

    fun getForDay(day: Int) = days[day]?.invoke()
}