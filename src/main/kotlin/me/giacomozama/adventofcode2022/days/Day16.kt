package me.giacomozama.adventofcode2022.days

import java.util.LinkedList

class Day16 : Day() {

    private val input = """
        Valve ED has flow rate=0; tunnels lead to valves PS, AW
        Valve SI has flow rate=0; tunnels lead to valves AA, HX
        Valve LX has flow rate=22; tunnels lead to valves DY, YH
        Valve CR has flow rate=0; tunnels lead to valves BE, HX
        Valve BI has flow rate=0; tunnels lead to valves GC, AY
        Valve PB has flow rate=4; tunnels lead to valves IX, YG, RI, KR, BV
        Valve YY has flow rate=0; tunnels lead to valves PH, GJ
        Valve PH has flow rate=11; tunnels lead to valves YY, VE, ZG, MM
        Valve DY has flow rate=0; tunnels lead to valves LX, AW
        Valve SD has flow rate=0; tunnels lead to valves AY, EC
        Valve SV has flow rate=24; tunnels lead to valves CC, GF
        Valve RL has flow rate=0; tunnels lead to valves OW, IN
        Valve GF has flow rate=0; tunnels lead to valves RQ, SV
        Valve BE has flow rate=5; tunnels lead to valves CR, JC, MF, IT
        Valve PR has flow rate=0; tunnels lead to valves BV, GJ
        Valve AW has flow rate=21; tunnels lead to valves VE, DY, TR, ED
        Valve FY has flow rate=17; tunnels lead to valves GG, KJ
        Valve GC has flow rate=0; tunnels lead to valves BI, GJ
        Valve RI has flow rate=0; tunnels lead to valves PB, AY
        Valve RQ has flow rate=0; tunnels lead to valves HH, GF
        Valve IT has flow rate=0; tunnels lead to valves MZ, BE
        Valve XG has flow rate=0; tunnels lead to valves BL, AA
        Valve MK has flow rate=0; tunnels lead to valves HX, DV
        Valve IX has flow rate=0; tunnels lead to valves PB, JC
        Valve BV has flow rate=0; tunnels lead to valves PR, PB
        Valve TR has flow rate=0; tunnels lead to valves CD, AW
        Valve PS has flow rate=0; tunnels lead to valves ED, AY
        Valve HH has flow rate=12; tunnels lead to valves RQ, NL, ZQ
        Valve AA has flow rate=0; tunnels lead to valves KR, SI, XG, EC, ZG
        Valve FT has flow rate=0; tunnels lead to valves IN, YH
        Valve YG has flow rate=0; tunnels lead to valves PB, HX
        Valve HX has flow rate=14; tunnels lead to valves MK, ZQ, YG, SI, CR
        Valve DV has flow rate=0; tunnels lead to valves MK, QR
        Valve GJ has flow rate=3; tunnels lead to valves PR, CD, YY, GC, BL
        Valve BL has flow rate=0; tunnels lead to valves GJ, XG
        Valve CD has flow rate=0; tunnels lead to valves TR, GJ
        Valve GG has flow rate=0; tunnels lead to valves FY, NL
        Valve JC has flow rate=0; tunnels lead to valves IX, BE
        Valve JN has flow rate=0; tunnels lead to valves OW, QR
        Valve RM has flow rate=18; tunnel leads to valve KJ
        Valve NL has flow rate=0; tunnels lead to valves GG, HH
        Valve QR has flow rate=20; tunnels lead to valves CC, DV, PN, JN
        Valve ZG has flow rate=0; tunnels lead to valves AA, PH
        Valve AY has flow rate=6; tunnels lead to valves RI, PS, SD, BI, MM
        Valve VE has flow rate=0; tunnels lead to valves PH, AW
        Valve OW has flow rate=25; tunnels lead to valves MZ, RL, JN
        Valve MM has flow rate=0; tunnels lead to valves AY, PH
        Valve KJ has flow rate=0; tunnels lead to valves RM, FY
        Valve MF has flow rate=0; tunnels lead to valves BE, PN
        Valve YH has flow rate=0; tunnels lead to valves LX, FT
        Valve ZQ has flow rate=0; tunnels lead to valves HX, HH
        Valve KR has flow rate=0; tunnels lead to valves AA, PB
        Valve PN has flow rate=0; tunnels lead to valves MF, QR
        Valve CC has flow rate=0; tunnels lead to valves SV, QR
        Valve MZ has flow rate=0; tunnels lead to valves OW, IT
        Valve EC has flow rate=0; tunnels lead to valves SD, AA
        Valve IN has flow rate=16; tunnels lead to valves RL, FT
    """.trimIndent()

    private class Graph(
        val flowRates: IntArray,
        val distances: Array<IntArray>
    )

    private fun parseGraph(): Graph {
        var lastIndex = 0
        val indices = hashMapOf("AA" to 0)
        val regex = """Valve ([A-Z]{2}) has flow rate=([0-9]+); tunnels? leads? to valves? ([A-Z, ]+)""".toRegex()
        val lines = input.lines()

        val flowRates = IntArray(lines.size)
        val distances = Array(lines.size) { IntArray(lines.size) { Int.MAX_VALUE } }

        for (line in lines) {
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

