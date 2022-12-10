package me.giacomozama.adventofcode2022.days

class Day10 : Day() {

    private val input: Array<String> = arrayOf(
        "addx 1",
        "noop",
        "addx 2",
        "addx 5",
        "addx 2",
        "noop",
        "noop",
        "noop",
        "addx 5",
        "noop",
        "noop",
        "addx 1",
        "addx 2",
        "addx -5",
        "addx 12",
        "addx 1",
        "addx 4",
        "addx 2",
        "noop",
        "addx -1",
        "addx 4",
        "noop",
        "noop",
        "addx -37",
        "addx 21",
        "addx -13",
        "addx -3",
        "noop",
        "addx 3",
        "addx 2",
        "addx 5",
        "addx -2",
        "addx 7",
        "addx -2",
        "addx 2",
        "addx 11",
        "addx -4",
        "addx 3",
        "noop",
        "addx -18",
        "addx 7",
        "addx 14",
        "addx 2",
        "addx 5",
        "addx -39",
        "addx 1",
        "addx 5",
        "noop",
        "noop",
        "noop",
        "addx 1",
        "addx 4",
        "noop",
        "addx 12",
        "addx 10",
        "addx -17",
        "addx 5",
        "addx -17",
        "addx 14",
        "addx 6",
        "noop",
        "addx 3",
        "addx 7",
        "noop",
        "noop",
        "addx 2",
        "addx 3",
        "noop",
        "addx -40",
        "addx 40",
        "addx -33",
        "addx -2",
        "noop",
        "addx 3",
        "addx 2",
        "addx 5",
        "addx -7",
        "addx 8",
        "noop",
        "addx 6",
        "addx 8",
        "addx -11",
        "addx 8",
        "noop",
        "addx -19",
        "addx 27",
        "noop",
        "addx -2",
        "addx 4",
        "noop",
        "addx -10",
        "addx -27",
        "noop",
        "noop",
        "addx 7",
        "addx 4",
        "addx -3",
        "addx 2",
        "addx 5",
        "addx 2",
        "addx -4",
        "addx -3",
        "addx 10",
        "addx 15",
        "addx -8",
        "addx 2",
        "addx 3",
        "addx -2",
        "addx 5",
        "addx 2",
        "addx 2",
        "addx -39",
        "addx 1",
        "addx 3",
        "addx 3",
        "addx 3",
        "noop",
        "addx 2",
        "addx 1",
        "addx 4",
        "addx -1",
        "addx 2",
        "addx 4",
        "addx 1",
        "noop",
        "noop",
        "addx 2",
        "addx 5",
        "addx 3",
        "noop",
        "noop",
        "addx -27",
        "addx 29",
        "noop",
        "addx 3",
        "noop",
        "noop"
    )

    // n = numbers of cycles
    // time: O(n), space: O(1)
    override fun solveFirstPuzzle(): Int {
        var value: Int? = null
        var reg = 1
        var result = 0
        var pc = 0
        for (cycle in 1..220) {
            if (cycle % 40 == 20) result += reg * cycle
            if (value != null) {
                reg += value
                value = null
            } else {
                val op = input[pc++]
                if (op != "noop") value = op.substringAfter(' ').toInt()
            }
        }
        return result
    }

    // time: O(n), space: O(n)
    override fun solveSecondPuzzle(): String {
        val crt = Array(6) { CharArray(40) { '░' } }
        var value: Int? = null
        var reg = 1
        var pc = 0
        for (cycle in 0..239) {
            val x = cycle % 40
            if (x in reg - 1..reg + 1) crt[cycle / 40][x] = '█'
            if (value != null) {
                reg += value
                value = null
            } else {
                val op = input[pc++]
                if (op != "noop") value = op.substringAfter(' ').toInt()
            }
        }
        return "\n" + crt.joinToString("\n") { String(it) }
    }
}