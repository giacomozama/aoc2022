package me.giacomozama.adventofcode2022.days

import java.io.File

class Day01 : Day() {

    private lateinit var input: List<IntArray>

    override fun parseInput(inputFile: File) {
        val result = mutableListOf<IntArray>()
        var cur = mutableListOf<Int>()
        inputFile.useLines { lines ->
            for (line in lines) {
                if (line.isEmpty()) {
                    result.add(cur.toIntArray())
                    cur = mutableListOf()
                } else {
                    cur.add(line.toInt())
                }
            }
        }
        result.add(cur.toIntArray())
        input = result
    }

    // time: O(n), space: O(1)
    override fun solveFirstPuzzle(): Int = input.fold(0) { max, cur -> maxOf(max, cur.sum()) }

    // time: O(n), space: O(1)
    override fun solveSecondPuzzle(): Int {
        var top1 = 0
        var top2 = 0
        var top3 = 0
        for (item in input) {
            val sum = item.sum()
            when {
                sum >= top1 -> {
                    top3 = top2
                    top2 = top1
                    top1 = sum
                }
                sum >= top2 -> {
                    top3 = top2
                    top2 = sum
                }
                sum > top3 -> {
                    top3 = sum
                }
            }
        }
        return top1 + top2 + top3
    }
}