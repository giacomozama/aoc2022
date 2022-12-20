package me.giacomozama.adventofcode2022.days

import java.io.File

class Day20 : Day() {

    private lateinit var input: List<Int>

    override fun parseInput(inputFile: File) {
        input = inputFile.useLines { it.map(String::toInt).toList() }
    }

    private inner class Node(val value: Long) {

        lateinit var prev: Node
        lateinit var next: Node

        fun moveIntoPosition() {
            if (value > 0) {
                repeat((value % (input.size - 1)).toInt()) {
                    val p = prev
                    val n = next
                    n.prev = p
                    p.next = n
                    next = n.next
                    n.next.prev = this
                    n.next = this
                    prev = n
                }
            } else if (value < 0) {
                repeat((-value % (input.size - 1)).toInt()) {
                    val p = prev
                    val n = next
                    p.next = n
                    n.prev = p
                    prev = p.prev
                    p.prev.next = this
                    p.prev = this
                    next = p
                }
            }
        }
    }

    // n = number of items, s = number of 1 unit movements
    // time: O(n * s), space: O(n)
    override fun solveFirstPuzzle(): Int {
        val nodes = Array(input.size) { Node(input[it].toLong()) }

        for (i in nodes.indices) {
            nodes[i].next = nodes[(i + 1) % input.size]
            nodes[i].prev = nodes[(i - 1 + input.size) % input.size]
        }

        for (node in nodes) node.moveIntoPosition()

        var a = nodes.first { it.value == 0L }
        var b = a
        var c = a
        for (d in 1..1000) {
            a = a.next
            b = b.next.next
            c = c.next.next.next
        }

        return (a.value + b.value + c.value).toInt()
    }

    // time: O(n ^ 2), space: O(n)
    override fun solveSecondPuzzle(): Long {
        val nodes = Array(input.size) { Node(input[it] * 811_589_153L) }

        for (i in nodes.indices) {
            nodes[i].next = nodes[(i + 1) % input.size]
            nodes[i].prev = nodes[(i - 1 + input.size) % input.size]
        }

        repeat(10) {
            for (node in nodes) node.moveIntoPosition()
        }

        var a = nodes.first { it.value == 0L }
        var b = a
        var c = a
        for (d in 1..1000) {
            a = a.next
            b = b.next.next
            c = c.next.next.next
        }

        return a.value + b.value + c.value
    }
}