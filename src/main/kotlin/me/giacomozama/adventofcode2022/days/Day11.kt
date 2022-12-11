package me.giacomozama.adventofcode2022.days

import java.util.*

class Day11 : Day() {

    private class Monkey(
        val startingItems: List<Long>,
        val operation: (Long) -> Long,
        val test: List<Int>
    )

    private val input: Array<Monkey> = arrayOf(
        Monkey(
            startingItems = listOf(85, 79, 63, 72),
            operation = { it * 17 },
            test = listOf(2, 2, 6)
        ),
        Monkey(
            startingItems = listOf(53, 94, 65, 81, 93, 73, 57, 92),
            operation = { it * it },
            test = listOf(7, 0, 2)
        ),
        Monkey(
            startingItems = listOf(62, 63),
            operation = { it + 7 },
            test = listOf(13, 7, 6)
        ),
        Monkey(
            startingItems = listOf(57, 92, 56),
            operation = { it + 4 },
            test = listOf(5, 4, 5)
        ),
        Monkey(
            startingItems = listOf(67),
            operation = { it + 5 },
            test = listOf(3, 1, 5)
        ),
        Monkey(
            startingItems = listOf(85, 56, 66, 72, 57, 99),
            operation = { it + 6 },
            test = listOf(19, 1, 0)
        ),
        Monkey(
            startingItems = listOf(86, 65, 98, 97, 69),
            operation = { it * 13 },
            test = listOf(11, 3, 7)
        ),
        Monkey(
            startingItems = listOf(87, 68, 92, 66, 91, 50, 68),
            operation = { it + 2 },
            test = listOf(17, 4, 3)
        )
    )

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