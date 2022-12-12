package me.giacomozama.adventofcode2022.days

import kotlin.test.Test
import kotlin.test.assertEquals

class DayTest {

    private fun testDay(day: Day, expectedOutput1: Any, expectedOutput2: Any) {
        assertEquals(expectedOutput1, day.solveFirstPuzzle())
        assertEquals(expectedOutput2, day.solveSecondPuzzle())
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

    @Test
    fun `test Day7`() {
        testDay(Day7(), 1749646, 1498966)
    }

    @Test
    fun `test Day8`() {
        testDay(Day8(), 1823, 211680)
    }

    @Test
    fun `test Day9`() {
        testDay(Day9(), 5883, 2367)
    }

    @Test
    fun `test Day10`() {
        val expectedOutput2 = """
            
            ████░█░░█░███░░████░█░░░░███░░░░██░███░░
            █░░░░█░░█░█░░█░░░░█░█░░░░█░░█░░░░█░█░░█░
            ███░░████░███░░░░█░░█░░░░█░░█░░░░█░█░░█░
            █░░░░█░░█░█░░█░░█░░░█░░░░███░░░░░█░███░░
            █░░░░█░░█░█░░█░█░░░░█░░░░█░█░░█░░█░█░█░░
            ████░█░░█░███░░████░████░█░░█░░██░░█░░█░
        """.trimIndent()
        testDay(Day10(), 12640, expectedOutput2)
    }

    @Test
    fun `test Day11`() {
        testDay(Day11(), 118674, 32333418600)
    }

    @Test
    fun `test Day12`() {
        testDay(Day12(), 504, 500)
    }
}