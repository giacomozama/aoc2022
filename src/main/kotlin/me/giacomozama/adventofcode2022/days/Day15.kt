package me.giacomozama.adventofcode2022.days

import java.util.PriorityQueue
import kotlin.math.abs

class Day15 : Day() {

    private val input: Array<IntArray> = arrayOf(
        intArrayOf(2483411, 3902983, 2289579, 3633785),
        intArrayOf(3429446, 303715, 2876111, -261280),
        intArrayOf(666423, 3063763, 2264411, 2779977),
        intArrayOf(3021606, 145606, 2876111, -261280),
        intArrayOf(2707326, 2596893, 2264411, 2779977),
        intArrayOf(3103704, 1560342, 2551409, 2000000),
        intArrayOf(3497040, 3018067, 3565168, 2949938),
        intArrayOf(1708530, 855013, 2551409, 2000000),
        intArrayOf(3107437, 3263465, 3404814, 3120160),
        intArrayOf(2155249, 2476196, 2264411, 2779977),
        intArrayOf(3447897, 3070850, 3404814, 3120160),
        intArrayOf(2643048, 3390796, 2289579, 3633785),
        intArrayOf(3533132, 3679388, 3404814, 3120160),
        intArrayOf(3683790, 3017900, 3565168, 2949938),
        intArrayOf(1943208, 3830506, 2289579, 3633785),
        intArrayOf(3940100, 3979653, 2846628, 4143786),
        intArrayOf(3789719, 1225738, 4072555, 1179859),
        intArrayOf(3939775, 578381, 4072555, 1179859),
        intArrayOf(3880152, 3327397, 3404814, 3120160),
        intArrayOf(3280639, 2446475, 3565168, 2949938),
        intArrayOf(2348869, 2240374, 2551409, 2000000),
        intArrayOf(3727441, 2797456, 3565168, 2949938),
        intArrayOf(3973153, 2034945, 4072555, 1179859),
        intArrayOf(38670, 785556, 311084, -402911),
        intArrayOf(3181909, 2862960, 3565168, 2949938),
        intArrayOf(3099490, 3946226, 2846628, 4143786)
    )

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
            if (end == 3_999_999) return 4_000_000L * 4_000_000L + y
        }
        error("Beacon not found.")
    }
}