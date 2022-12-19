package me.giacomozama.adventofcode2022.days

import java.io.File

class Day16 : Day() {

    private lateinit var inputFlowRates: IntArray
    private lateinit var inputGraph: Array<IntArray>

    override fun parseInput(inputFile: File) {
        val inputLines = inputFile.readLines()
        var lastIndex = 0
        val indices = hashMapOf("AA" to 0)
        val regex = """Valve ([A-Z]{2}) has flow rate=([0-9]+); tunnels? leads? to valves? ([A-Z, ]+)""".toRegex()

        val flowRates = IntArray(inputLines.size)
        val graph = Array(inputLines.size) { IntArray(inputLines.size) { Int.MAX_VALUE } }

        for (line in inputLines) {
            val result = regex.matchEntire(line)!!
            val valve = indices.getOrPut(result.groupValues[1]) { ++lastIndex }
            flowRates[valve] = result.groupValues[2].toInt()
            graph[valve][valve] = 0
            for (nb in result.groupValues[3].split(", ")) {
                graph[valve][indices.getOrPut(nb) { ++lastIndex }] = 1
            }
        }

        for (k in graph.indices) {
            for (i in graph.indices) {
                for (j in graph.indices) {
                    if (graph[i][j] - graph[k][j] > graph[i][k]) {
                        graph[i][j] = graph[i][k] + graph[k][j]
                    }
                }
            }
        }

        inputFlowRates = flowRates
        inputGraph = graph
    }

    // time: O(n ^ 3), space: O(n ^ 2)
    override fun solveFirstPuzzle(): Int {
        val flowRates = inputFlowRates
        val graph = inputGraph
        var result = 0

        fun dfs(
            valve: Int,
            visited: Long,
            pressure: Int,
            timeLeft: Int
        ) {
            var cantMove = true
            for (nb in 1 until graph.size) {
                if (flowRates[nb] == 0 || visited and (1L shl nb) != 0L || graph[valve][nb] > timeLeft - 1) continue
                cantMove = false
                val newTimeLeft = timeLeft - graph[valve][nb] - 1
                dfs(
                    valve = nb,
                    visited = visited or (1L shl nb),
                    pressure = pressure + flowRates[nb] * newTimeLeft,
                    timeLeft = newTimeLeft
                )
            }
            if (cantMove) result = maxOf(result, pressure)
        }

        dfs(0, 1L, 0, 30)

        return result
    }

    // time: O(n ^ 3), space: O(n ^ 2)
    override fun solveSecondPuzzle(): Int {
        val flowRates = inputFlowRates
        val distances = inputGraph
        var result = 0

        val bestSoloPathIn30Minutes = solveFirstPuzzle()

        fun dfs(
            myValve: Int,
            eleValve: Int,
            visited: Long,
            pressure: Int,
            myTimeLeft: Int,
            eleTimeLeft: Int
        ) {
            // prune this branch if we could have done better by going without the elephant
            // we have to assume going with the elephant can always yield better results if we take the right path
            if (myTimeLeft + eleTimeLeft <= 30 && pressure < bestSoloPathIn30Minutes) return

            var cantMove = true
            for (mnb in 1 until distances.size) {
                for (enb in 1 until distances.size) {
                    if (mnb == enb ||
                        flowRates[enb] == 0 ||
                        flowRates[mnb] == 0 ||
                        visited and (1L shl mnb) != 0L ||
                        visited and (1L shl enb) != 0L
                    ) continue
                    val canIMakeIt = distances[myValve][mnb] <= myTimeLeft - 1
                    val canEleMakeIt = distances[eleValve][enb] <= eleTimeLeft - 1
                    when {
                        canIMakeIt && canEleMakeIt -> {
                            val mt = myTimeLeft - distances[myValve][mnb] - 1
                            val et = eleTimeLeft - distances[eleValve][enb] - 1
                            cantMove = false
                            dfs(
                                myValve = mnb,
                                eleValve = enb,
                                visited = visited or (1L shl mnb) or (1L shl enb),
                                pressure = pressure + flowRates[mnb] * mt + flowRates[enb] * et,
                                myTimeLeft = mt,
                                eleTimeLeft = et
                            )
                        }
                        canIMakeIt -> {
                            val mt = myTimeLeft - distances[myValve][mnb] - 1
                            cantMove = false
                            dfs(
                                myValve = mnb,
                                eleValve = eleValve,
                                visited = visited or (1L shl mnb),
                                pressure = pressure + flowRates[mnb] * mt,
                                myTimeLeft = mt,
                                eleTimeLeft = eleTimeLeft
                            )
                        }
                        canEleMakeIt -> {
                            val et = eleTimeLeft - distances[eleValve][enb] - 1
                            cantMove = false
                            dfs(
                                myValve = myValve,
                                eleValve = enb,
                                visited = visited or (1L shl enb),
                                pressure = pressure + flowRates[enb] * et,
                                myTimeLeft = myTimeLeft,
                                eleTimeLeft = et
                            )
                        }
                    }
                }
            }
            if (cantMove) result = maxOf(result, pressure)
        }

        dfs(0, 0, 1L, 0, 26, 26)

        return result
    }
}

