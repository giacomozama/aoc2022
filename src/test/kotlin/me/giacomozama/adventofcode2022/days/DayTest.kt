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
        testDay(Day1(), 67_450, 199_357)
    }

    @Test
    fun `test Day2`() {
        testDay(Day2(), 9_759, 12_429)
    }

    @Test
    fun `test Day3`() {
        testDay(Day3(), 7_763, 2_569)
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
        testDay(Day6(), 1_929, 3_298)
    }

    @Test
    fun `test Day7`() {
        testDay(Day7(), 1_749_646, 1_498_966)
    }

    @Test
    fun `test Day8`() {
        testDay(Day8(), 1_823, 211_680)
    }

    @Test
    fun `test Day9`() {
        testDay(Day9(), 5_883, 2_367)
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
        testDay(Day10(), 12_640, expectedOutput2)
    }

    @Test
    fun `test Day11`() {
        testDay(Day11(), 118_674, 32_333_418_600)
    }

    @Test
    fun `test Day12`() {
        testDay(Day12(), 504, 500)
    }

    @Test
    fun `test Day13`() {
        testDay(Day13(), 4_894, 24_180)
    }

    @Test
    fun `test Day14`() {
        testDay(Day14(), 901, 24_589)
    }

    @Test
    fun `test Day15`() {
        testDay(Day15(), 5_100_463, 11_557_863_040_754L)
    }

    @Test
    fun `test Day16`() {
        testDay(Day16(), 2359, 0)
    }
}