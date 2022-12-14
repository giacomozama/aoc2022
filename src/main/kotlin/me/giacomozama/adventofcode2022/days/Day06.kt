package me.giacomozama.adventofcode2022.days

import java.io.File

class Day06 : Day() {

    private lateinit var input: String

    override fun parseInput(inputFile: File) {
        input = inputFile.readText()
    }

    // time: O(n), space: O(1)
    override fun solveFirstPuzzle(): Int {
        var result = 4
        while (
            input[result - 1] == input[result - 4] ||
            input[result - 1] == input[result - 3] ||
            input[result - 1] == input[result - 2] ||
            input[result - 2] == input[result - 4] ||
            input[result - 2] == input[result - 3] ||
            input[result - 3] == input[result - 4]
        ) result++
        return result
    }

    // n = number of characters in input, m = size of input alphabet
    // for m = 26, when the length of the sequence to find exceeds 7, using a dictionary for counting the characters
    // becomes better than checking every 2-combination of elements in the sequence. Proof: nCk(n = X, k = 2) > 26
    // is true for every integer X greater than 7.
    // time: O(n), space: O(m)
    override fun solveSecondPuzzle(): Int {
        val count = IntArray(26)
        for (i in 0..13) count[input[i] - 'a']++
        var result = 14
        while (count.any { it > 1 }) {
            count[input[result - 14] - 'a']--
            count[input[result++] - 'a']++
        }
        return result
    }
}