package me.giacomozama.adventofcode2022.days

import java.io.File
import java.util.*

class Day11 : Day() {

    private class Monkey(
        val startingItems: List<Long>,
        val operation: (Long) -> Long,
        val test: IntArray
    )

    private lateinit var input: List<Monkey>

    private fun parseOperation(s: String): (Long) -> Long {
        val operation: (Long, Long) -> Long = when (s[0]) {
            '+' -> Long::plus
            '-' -> Long::minus
            '*' -> Long::times
            else -> Long::div
        }
        val operand = s.drop(2)
        if (operand == "old") {
            return { operation(it, it) }
        } else {
            val parsedOperand = operand.toLong()
            return { operation(it, parsedOperand) }
        }
    }

    override fun parseInput(inputFile: File) {
        input = inputFile.useLines { lines ->
            lines.chunked(7).map { chunk ->
                Monkey(
                    startingItems = chunk[1].substringAfter(": ").split(", ").map { it.toLong() },
                    operation = parseOperation(chunk[2].substringAfter("= old ")),
                    test = IntArray(3) { chunk[it + 3].substringAfter("y ").toInt() }
                )
            }.toList()
        }
    }

    // n = number of rounds, m = number of items, o = number of monkeys
    // time: O(n * m), space: O(m + o)
    override fun solveFirstPuzzle(): Int {
        val activity = IntArray(input.size)
        val held = Array(input.size) { LinkedList(input[it].startingItems) }
        for (round in 1..20) {
            for (i in input.indices) {
                activity[i] += held[i].size
                var cur = held[i].poll()
                while (cur != null) {
                    val mk = input[i]
                    val upd = mk.operation(cur) / 3
                    held[mk.test[if (upd % mk.test[0] == 0L) 1 else 2]].offer(upd)
                    cur = held[i].poll()
                }
            }
        }
        var top1 = 0
        var top2 = 0
        for (c in activity) {
            if (c >= top1) {
                top2 = top1
                top1 = c
            } else if (c > top2) {
                top2 = c
            }
        }
        return top1 * top2
    }

    // time: O(n * m), space: O(m + o)
    override fun solveSecondPuzzle(): Long {
        val modulo = input.fold(1) { p, m -> p * m.test[0] }
        val activity = IntArray(input.size)
        val held = Array(input.size) { LinkedList(input[it].startingItems) }
        for (round in 1..10000) {
            for (i in input.indices) {
                activity[i] += held[i].size
                var cur = held[i].poll()
                while (cur != null) {
                    val mk = input[i]
                    val upd = mk.operation(cur) % modulo
                    held[mk.test[if (upd % mk.test[0] == 0L) 1 else 2]].offer(upd)
                    cur = held[i].poll()
                }
            }
        }
        var top1 = 0
        var top2 = 0
        for (c in activity) {
            if (c >= top1) {
                top2 = top1
                top1 = c
            } else if (c > top2) {
                top2 = c
            }
        }
        return top1 * top2.toLong()
    }
}