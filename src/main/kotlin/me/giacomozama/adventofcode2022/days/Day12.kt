package me.giacomozama.adventofcode2022.days

import java.io.File
import java.util.*

class Day12 : Day() {

    private lateinit var input: List<CharArray>

    override fun parseInput(inputFile: File) {
        input = inputFile.useLines { lines -> lines.map { it.toCharArray() }.toList() }
    }

    // n = numbers of squares
    // time: O(n), space: O(n)
    override fun solveFirstPuzzle(): Int {
        val dirs = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))

        var curChar = 'a'
        var cur: IntArray? = null
        y@ for (y in input.indices) {
            for (x in input[0].indices) {
                if (input[y][x] == 'S') {
                    cur = intArrayOf(y, x, 0)
                    break@y
                }
            }
        }
        cur ?: error("No square marked with 'S'")

        val visited = Array(input.size) { BooleanArray(input[0].size) }
        visited[cur[0]][cur[1]] = true
        val queue = LinkedList<IntArray>()

        while (cur != null) {
            val (y, x, d) = cur

            for ((dy, dx) in dirs) {
                val ty = y + dy
                val tx = x + dx
                if (ty !in input.indices || tx !in input[0].indices || visited[ty][tx]) continue

                val tc = input[ty][tx]
                if (tc != 'E' && tc <= curChar + 1 || tc == 'E' && curChar >= 'y') {
                    if (tc == 'E') return d + 1
                    queue.offer(intArrayOf(ty, tx, d + 1))
                    visited[ty][tx] = true
                }
            }

            cur = queue.poll()?.also { (cy, cx) -> curChar = input[cy][cx] }
        }

        error("No squares marked with 'E'")
    }

    // time: O(n), space: O(n)
    override fun solveSecondPuzzle(): Any {
        val dirs = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))

        var curChar = 'z'
        var cur: IntArray? = null
        y@ for (y in input.indices) {
            for (x in input[0].indices) {
                if (input[y][x] == 'E') {
                    cur = intArrayOf(y, x, 0)
                    break@y
                }
            }
        }
        cur ?: error("No square marked with 'E'")

        val visited = Array(input.size) { BooleanArray(input[0].size) }
        visited[cur[0]][cur[1]] = true
        val queue = LinkedList<IntArray>()

        while (cur != null) {
            val (y, x, d) = cur

            for ((dy, dx) in dirs) {
                val ty = y + dy
                val tx = x + dx
                if (ty !in input.indices || tx !in input[0].indices || visited[ty][tx]) continue

                val tc = input[ty][tx]
                if (tc >= curChar - 1 || tc == 'S' && curChar == 'b') {
                    if (tc == 'a' || tc == 'S') return d + 1
                    queue.offer(intArrayOf(ty, tx, d + 1))
                    visited[ty][tx] = true
                }
            }

            cur = queue.poll()?.also { (cy, cx) -> curChar = input[cy][cx] }
        }

        error("No squares marked with 'a' or 'S'")
    }
}