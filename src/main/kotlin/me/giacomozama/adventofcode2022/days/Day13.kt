package me.giacomozama.adventofcode2022.days

import java.io.File
import java.util.*
import kotlin.collections.MutableList
import kotlin.collections.indices
import kotlin.collections.plus
import kotlin.collections.sliceArray
import kotlin.collections.toMutableList

class Day13 : Day() {

    private lateinit var input: List<Array<String>>

    override fun parseInput(inputFile: File) {
        input = inputFile.useLines { lines -> lines.chunked(3).map { (l1, l2) -> arrayOf(l1, l2) }.toList() }
    }

    private sealed interface Signal : Comparable<Signal> {

        class Value(val value: Int) : Signal {

            override fun compareTo(other: Signal) = when (other) {
                is List -> List(this).compareTo(other)
                is Value -> value.compareTo(other.value)
            }
        }

        class List(vararg values: Signal) : Signal, MutableList<Signal> by values.toMutableList() {

            private fun compareToList(other: List): Int {
                for (i in 0 until minOf(size, other.size)) {
                    val c = get(i).compareTo(other[i])
                    if (c != 0) return c
                }
                return size - other.size
            }

            override fun compareTo(other: Signal): Int = when (other) {
                is List -> compareToList(other)
                is Value -> compareTo(List(other))
            }
        }
    }

    private fun parseSignals(): Array<Signal.List> {
        val result = Array(input.size * 2) { Signal.List() }
        for (i in input.indices) {
            for (j in input[i].indices) {
                val raw = input[i][j].removeSurrounding("[", "]")
                val stack = LinkedList<Signal.List>()
                var counter: Int? = null
                stack.push(result[i * 2 + j])
                for (c in raw) {
                    when (c) {
                        ',' -> {
                            if (counter != null) {
                                stack.peek().add(Signal.Value(counter))
                                counter = null
                            }
                        }
                        '[' -> {
                            val list = Signal.List()
                            stack.peek().add(list)
                            stack.push(list)
                        }
                        ']' -> {
                            if (counter != null) {
                                stack.peek().add(Signal.Value(counter))
                                counter = null
                            }
                            stack.pop()
                        }
                        else -> {
                            counter = (counter ?: 0) * 10 + c.digitToInt()
                        }
                    }
                }
            }
        }
        return result
    }

    private fun mergeSort(arr: Array<Signal.List>) {
        if (arr.size == 1) return
        val left = arr.sliceArray(0 until arr.size / 2).also(::mergeSort)
        val right = arr.sliceArray(arr.size / 2 until arr.size).also(::mergeSort)
        var i = 0
        var j = 0
        while (i < left.size && j < right.size) {
            arr[i + j] = if (left[i] <= right[j]) left[i++] else right[j++]
        }
        while (i < left.size) arr[i + j] = left[i++]
        while (j < right.size) arr[i + j] = right[j++]
    }

    // time: O(n), space: O(n)
    override fun solveFirstPuzzle(): Int {
        var result = 0
        val parsedInput = parseSignals()
        for (i in parsedInput.indices step 2) {
            if (parsedInput[i].compareTo(parsedInput[i + 1]) < 1) {
                result += i / 2 + 1
            }
        }
        return result
    }

    // time: O(n * log(n)), space: O(n)
    override fun solveSecondPuzzle(): Int {
        val divider1 = Signal.List(Signal.List(Signal.Value(2)))
        val divider2 = Signal.List(Signal.List(Signal.Value(6)))
        val signals = parseSignals() + arrayOf(divider1, divider2)
        mergeSort(signals)
        var idx1 = 0
        var idx2 = 0
        for (i in signals.indices) {
            if (signals[i] === divider1) {
                idx1 = i + 1
            } else if (signals[i] === divider2) {
                idx2 = i + 1
            }
        }
        return idx1 * idx2
    }
}