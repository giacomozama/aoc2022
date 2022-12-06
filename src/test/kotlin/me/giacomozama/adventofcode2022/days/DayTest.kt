package me.giacomozama.adventofcode2022.days

import kotlin.test.Test
import kotlin.test.assertEquals

class DayTest {

    private fun testDay(day: Day, expectedOutput1: Any, expectedOutput2: Any) {
        assertEquals(day.solveFirstPuzzle(), expectedOutput1)
        assertEquals(day.solveSecondPuzzle(), expectedOutput2)
    }

    @Test
    fun `test Day1`() {
        testDay(Day1(), 67450, 199357)
    }

    @Test
    fun `test Day2`() {
        testDay(Day2(), 9759, 12429)
    }

    @Test
    fun `test Day3`() {
        testDay(Day3(), 7763, 2569)
    }

    @Test
    fun `test Day4`() {
        testDay(Day4(), 433, 852)
    }

    @Test
    fun `test Day5`() {
        testDay(Day5(), "SHMSDGZVC", "VRZGHDFBQ")
    }

    @Test
    fun `test Day6`() {
        testDay(Day6(), 1929, 3298)
    }
}