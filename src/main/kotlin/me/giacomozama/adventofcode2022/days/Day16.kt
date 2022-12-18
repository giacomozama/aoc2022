package me.giacomozama.adventofcode2022.days

import java.io.File
import java.util.LinkedList

class Day16 : Day() {

    private lateinit var input: List<String>

    override fun parseInput(inputFile: File) {
        input = inputFile.readLines()
    }

    private class Graph(
        val flowRates: IntArray,
        val distances: Array<IntArray>
    )

    private fun parseGraph(): Graph {
        var lastIndex = 0
        val indices = hashMapOf("AA" to 0)
        val regex = """Valve ([A-Z]{2}) has flow rate=([0-9]+); tunnels? leads? to valves? ([A-Z, ]+)""".toRegex()

        val flowRates = IntArray(input.size)
        val distances = Array(input.size) { IntArray(input.size) { Int.MAX_VALUE } }

        for (line in input) {
            val result = regex.matchEntire(line)!!
            val valve = indices.getOrPut(result.groupValues[1]) { ++lastIndex }
            flowRates[valve] = result.groupValues[2].toInt()
            distances[valve][valve] = 0
            for (nb in result.groupValues[3].split(", ")) {
                distances[valve][indices.getOrPut(nb) { ++lastIndex }] = 1
            }
        }

        for (k in distances.indices) {
            for (i in distances.indices) {
                for (j in distances.indices) {
                    if (distances[i][j] - distances[k][j] > distances[i][k]) {
                        distances[i][j] = distances[i][k] + distances[k][j]
                    }
                }
            }
        }

        return Graph(flowRates, distances)
    }

    // time: O(n ^ 3), space: O(n ^ 2)
    override fun solveFirstPuzzle(): Int {
        val graph = parseGraph()
        val flowRates = graph.flowRates
        val distances = graph.distances

        class State(
            val valve: Int,
            val visited: Long,
            val pressure: Int,
            val timeLeft: Int
        )

        val queue = LinkedList<State>()
        var cur: State? = State(0, 1L, 0, 30)
        var result = 0

        while (cur != null) {
            var cantMove = true
            for (nb in 1 until distances.size) {
                if (flowRates[nb] == 0 ||
                    cur.visited and (1L shl nb) != 0L ||
                    distances[cur.valve][nb] > cur.timeLeft - 1
                ) continue
                cantMove = false
                val t = cur.timeLeft - distances[cur.valve][nb] - 1
                queue.offer(
                    State(
                        valve = nb,
                        visited = cur.visited or (1L shl nb),
                        pressure = cur.pressure + flowRates[nb] * t,
                        timeLeft = t
                    )
                )
            }
            if (cantMove) result = maxOf(result, cur.pressure)
            cur = queue.poll()
        }

        return result
    }

    // time: O(n ^ 3), space: O(n ^ 2)
    override fun solveSecondPuzzle(): Any {
        return "Not yet completed"
    }
}

