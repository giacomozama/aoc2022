package me.giacomozama.adventofcode2022.days

import java.io.File

class Day03 : Day() {

    private lateinit var input: List<String>

    override fun parseInput(inputFile: File) {
        input = inputFile.readLines()
    }

    // time: O(n), space: O(1)
    override fun solveFirstPuzzle(): Int {
        var result = 0
        for (rucksack in input) {
            val mask = getCharBitmask(rucksack.take(rucksack.length / 2))
            for (i in rucksack.length / 2 until rucksack.length) {
                val c = rucksack[i]
                if (1L shl (c - 'A') and mask != 0L) {
                    result += c - if (c in 'a'..'z') '`' else '&'
                    break
                }
            }
        }
        return result
    }

    // time: O(n), space: O(1)
    override fun solveSecondPuzzle(): Int {
        var result = 0
        for (i in input.indices step 3) {
            val rs1 = getCharBitmask(input[i])
            val rs2 = getCharBitmask(input[i + 1])
            val mask = rs1 and rs2
            for (c in input[i + 2]) {
                if (1L shl (c - 'A') and mask != 0L) {
                    result += c - if (c in 'a'..'z') '`' else '&'
                    break
                }
            }
        }
        return result
    }

    private fun getCharBitmask(s: String) = s.fold(0L) { acc, c -> acc or (1L shl (c.code - 65)) }
}
