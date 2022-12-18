package me.giacomozama.adventofcode2022.days

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class DayTest {

    private fun testDay(dayNumber: Int, expectedOutput1: Any, expectedOutput2: Any) {
        val dayNumberStr = dayNumber.toString().padStart(2, '0')
        val day = Class.forName(Day::class.qualifiedName + dayNumberStr).constructors[0].newInstance() as Day
        day.parseInput(File("input/Day$dayNumberStr.txt"))
        assertEquals(expectedOutput1, day.solveFirstPuzzle())
        assertEquals(expectedOutput2, day.solveSecondPuzzle())
    }

    @Test
    fun `test Day1`() {
        testDay(1, 67_450, 199_357)
    }

    @Test
    fun `test Day2`() {
        testDay(2, 9_759, 12_429)
    }

    @Test
    fun `test Day3`() {
        testDay(3, 7_763, 2_569)
    }

    @Test
    fun `test Day4`() {
        testDay(4, 433, 852)
    }

    @Test
    fun `test Day5`() {
        testDay(5, "SHMSDGZVC", "VRZGHDFBQ")
    }

    @Test
    fun `test Day6`() {
        testDay(6, 1_929, 3_298)
    }

    @Test
    fun `test Day7`() {
        testDay(7, 1_749_646, 1_498_966)
    }

    @Test
    fun `test Day8`() {
        testDay(8, 1_823, 211_680)
    }

    @Test
    fun `test Day9`() {
        testDay(9, 5_883, 2_367)
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
        testDay(10, 12_640, expectedOutput2)
    }

    @Test
    fun `test Day11`() {
        testDay(11, 118_674, 32_333_418_600)
    }

    @Test
    fun `test Day12`() {
        testDay(12, 504, 500)
    }

    @Test
    fun `test Day13`() {
        testDay(13, 4_894, 24_180)
    }

    @Test
    fun `test Day14`() {
        testDay(14, 901, 24_589)
    }

    @Test
    fun `test Day15`() {
        testDay(15, 5_100_463, 11_557_863_040_754L)
    }

    @Test
    fun `test Day16`() {
        testDay(16, 2_359, 0)
    }

    @Test
    fun `test Day17`() {
        testDay(17, 3_235, 0)
    }

    @Test
    fun `test Day18`() {
        testDay(18, 3_610, 2_082)
    }
}