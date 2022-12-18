package me.giacomozama.adventofcode2022.days

import java.io.File
import java.util.*

class Day05 : Day() {

    private lateinit var inputState: Array<String>

    private lateinit var inputMoves: List<IntArray>

    override fun parseInput(inputFile: File) {
        inputFile.useLines { lines ->
            val iterator = lines.iterator()
            var cur = iterator.next()
            val stack = LinkedList<String>()
            while (!cur.startsWith(' ')) {
                stack.push(cur)
                cur = iterator.next()
            }
            val inputStateBuilder = Array(9) { StringBuilder() }
            while (stack.isNotEmpty()) {
                cur = stack.pop()
                for (i in cur.indices step 4) {
                    if (cur[i + 1] != ' ') inputStateBuilder[i / 4].append(cur[i + 1])
                }
            }
            inputState = Array(9) { inputStateBuilder[it].toString() }
            iterator.next()
            val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()
            val result = mutableListOf<IntArray>()
            while (iterator.hasNext()) {
                cur = iterator.next()
                val match = requireNotNull(regex.matchEntire(cur))
                result.add(IntArray(3) { match.groupValues[it + 1].toInt() })
            }
            inputMoves = result
        }
    }

    // n = total number of crates, m = number of individual crate movements
    // time: O(m), space: O(n)
    override fun solveFirstPuzzle(): String {
        val state = Array(inputState.size) {
            LinkedList<Char>().apply { inputState[it].forEach(::push) }
        }
        for ((qty, from, to) in inputMoves) {
            repeat(qty) { state[to - 1].push(state[from - 1].pop()) }
        }
        val sb = StringBuilder()
        for (stack in state) sb.append(stack.pop())
        return sb.toString()
    }

    // time: O(m), space: O(n)
    override fun solveSecondPuzzle(): String {
        val state = Array(inputState.size) {
            LinkedList<Char>().apply { inputState[it].forEach(::push) }
        }
        val temp = LinkedList<Char>()
        for ((qty, from, to) in inputMoves) {
            repeat(qty) { temp.push(state[from - 1].pop()) }
            repeat(qty) { state[to - 1].push(temp.pop()) }
        }
        val sb = StringBuilder()
        for (stack in state) sb.append(stack.pop())
        return sb.toString()
    }
}