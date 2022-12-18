package me.giacomozama.adventofcode2022.days

import java.io.File
import java.util.PriorityQueue
import kotlin.math.abs

class Day15 : Day() {

    private lateinit var input: List<IntArray>

    override fun parseInput(inputFile: File) {
        val regex = """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)""".toRegex()
        input = inputFile.useLines { lines ->
            lines.map { line ->
                val match = requireNotNull(regex.matchEntire(line))
                intArrayOf(
                    match.groupValues[1].toInt(),
                    match.groupValues[2].toInt(),
                    match.groupValues[3].toInt(),
                    match.groupValues[4].toInt()
                )
            }.toList()
        }
    }

    // time: O(n * log(n), space: O(n)
    override fun solveFirstPuzzle(): Int {
        val segments = mutableListOf<IntArray>()
        val line = 2_000_000
        val beaconsOnLine = hashSetOf<Int>()
        for ((sensorX, sensorY, beaconX, beaconY) in input) {
            if (beaconY == line) beaconsOnLine += beaconX
            val distToBeacon = abs(sensorX - beaconX) + abs(sensorY - beaconY)
            val distToLine = abs(line - sensorY)
            if (distToLine > distToBeacon) continue
            segments.add(intArrayOf(sensorX - distToBeacon + distToLine, sensorX + distToBeacon - distToLine))
        }
        segments.sortBy { it[0] }
        var result = 1
        var end = segments[0][0]
        for ((from, to) in segments) {
            if (to > end) {
                result += if (from <= end) to - end else 1 + from - to
                end = to
            }
        }
        return result - beaconsOnLine.size
    }

    // q = 4_000_000
    // time: O(n * q * log(n)), space = O(n)
    override fun solveSecondPuzzle(): Long {
        val queue = PriorityQueue<IntArray> { a, b -> a[0].compareTo(b[0]) }
        for (y in 0..4_000_000) {
            for ((sensorX, sensorY, beaconX, beaconY) in input) {
                val distToBeacon = abs(sensorX - beaconX) + abs(sensorY - beaconY)
                val distToY = abs(y - sensorY)
                if (distToY <= distToBeacon) {
                    queue.add(intArrayOf(sensorX - distToBeacon + distToY, sensorX + distToBeacon - distToY))
                }
            }
            var end = -1
            var cur = queue.poll()
            while (cur != null) {
                if (cur[0] > end + 1) return (end + 1) * 4_000_000L + y
                end = maxOf(end, cur[1])
                cur = queue.poll()
            }
            if (end == 3_999_999) return 16_000_000_000_000L + y
        }
        error("Beacon not found.")
    }
}