package me.giacomozama.adventofcode2022.days

import kotlinx.coroutines.*
import java.io.File

class Day19 : Day() {

    private lateinit var input: List<IntArray>

    override fun parseInput(inputFile: File) {
        val regex = Regex(
            """Blueprint (\d+): Each ore robot costs (\d+) ore\. Each clay robo""" +
                """t costs (\d+) ore\. Each obsidian robot costs (\d+) ore and (\d+) cla""" +
                """y\. Each geode robot costs (\d+) ore and (\d+) obsidian\."""
        )
        input = inputFile.useLines { lines ->
            lines.map { line ->
                val match = requireNotNull(regex.matchEntire(line))
                IntArray(7) { match.groupValues[it + 1].toInt() }
            }.toList()
        }
    }

    private fun dfs(
        blueprint: IntArray,
        timeAtState: HashMap<Long, Int>,
        timeLeft: Int,
        ore: Int = 0,
        clay: Int = 0,
        obsidian: Int = 0,
        geodes: Int = 0,
        oreBots: Int = 1,
        clayBots: Int = 0,
        obsidianBots: Int = 0,
        geodeBots: Int = 0,
        maxGeodes: Int = 0
    ): Int {
        if (timeLeft == 0) return maxOf(maxGeodes, geodes)

        val oreOreCost = blueprint[1]
        val clayOreCost = blueprint[2]
        val obsidianOreCost = blueprint[3]
        val obsidianClayCost = blueprint[4]
        val geodeOreCost = blueprint[5]
        val geodeObsidianCost = blueprint[6]
        val maxOreCost = maxOf(oreOreCost, clayOreCost, obsidianOreCost, geodeOreCost)

        val components = arrayOf(ore, clay, obsidian, geodes, oreBots, clayBots, obsidianBots, geodeBots)
        // 257 seems to be high enough to avoid collisions and low enough to avoid overflows
        val hash = components.fold(0L) { acc, n -> acc * 257L + n }

        // prune this branch if we've been in this state with at least the same time left
        val timeAtPrevOccurrence = timeAtState[hash]
        if (timeAtPrevOccurrence != null && timeAtPrevOccurrence >= timeLeft) return maxGeodes
        timeAtState[hash] = timeLeft

        // prune this branch if we can't beat maxGeodes in the best case scenario
        val t = 2 * geodeBots + timeLeft
        if (geodes + t * (timeLeft / 2) + (timeLeft and 1) * (t / 2) <= maxGeodes) return maxGeodes

        var result = maxGeodes
        if (ore >= geodeOreCost && obsidian >= geodeObsidianCost) {
            val r = dfs(
                blueprint = blueprint,
                timeAtState = timeAtState,
                timeLeft = timeLeft - 1,
                ore = ore + oreBots - geodeOreCost,
                clay = clay + clayBots,
                obsidian = obsidian + obsidianBots - geodeObsidianCost,
                geodes = geodes + geodeBots,
                oreBots = oreBots,
                clayBots = clayBots,
                obsidianBots = obsidianBots,
                geodeBots = geodeBots + 1,
                maxGeodes = result
            )
            result = maxOf(result, r)
        }

        if (ore >= oreOreCost && oreBots < maxOreCost) {
            val r = dfs(
                blueprint = blueprint,
                timeAtState = timeAtState,
                timeLeft = timeLeft - 1,
                ore = ore + oreBots - oreOreCost,
                clay = clay + clayBots,
                obsidian = obsidian + obsidianBots,
                geodes = geodes + geodeBots,
                oreBots = oreBots + 1,
                clayBots = clayBots,
                obsidianBots = obsidianBots,
                geodeBots = geodeBots,
                maxGeodes = result
            )
            result = maxOf(result, r)
        }

        if (ore >= clayOreCost && clayBots < obsidianClayCost) {
            val r = dfs(
                blueprint = blueprint,
                timeAtState = timeAtState,
                timeLeft = timeLeft - 1,
                ore = ore + oreBots - clayOreCost,
                clay = clay + clayBots,
                obsidian = obsidian + obsidianBots,
                geodes = geodes + geodeBots,
                oreBots = oreBots,
                clayBots = clayBots + 1,
                obsidianBots = obsidianBots,
                geodeBots = geodeBots,
                maxGeodes = result
            )
            result = maxOf(result, r)
        }

        if (ore >= obsidianOreCost && clay >= obsidianClayCost && obsidianBots < geodeObsidianCost) {
            val r = dfs(
                blueprint = blueprint,
                timeAtState = timeAtState,
                timeLeft = timeLeft - 1,
                ore = ore + oreBots - obsidianOreCost,
                clay = clay + clayBots - obsidianClayCost,
                obsidian = obsidian + obsidianBots,
                geodes = geodes + geodeBots,
                oreBots = oreBots,
                clayBots = clayBots,
                obsidianBots = obsidianBots + 1,
                geodeBots = geodeBots,
                maxGeodes = result
            )
            result = maxOf(result, r)
        }

        val r = dfs(
            blueprint = blueprint,
            timeAtState = timeAtState,
            timeLeft = timeLeft - 1,
            ore = ore + oreBots,
            clay = clay + clayBots,
            obsidian = obsidian + obsidianBots,
            geodes = geodes + geodeBots,
            oreBots = oreBots,
            clayBots = clayBots,
            obsidianBots = obsidianBots,
            geodeBots = geodeBots,
            maxGeodes = result
        )
        result = maxOf(result, r)

        return result
    }

    // time: O(all possible states), space: O(number of blueprints * recursion stack)
    override fun solveFirstPuzzle(): Int = runBlocking(Dispatchers.Default) {
        input.map { bp -> async { bp[0] * dfs(bp, hashMapOf(), 24) } }.sumOf { it.await() }
    }

    // time: O(all possible states), space: O(number of blueprints * recursion stack)
    override fun solveSecondPuzzle(): Int = runBlocking(Dispatchers.Default) {
        input.take(3)
            .map { bp -> async { dfs(bp, hashMapOf(), 32) } }
            .fold(1) { acc, n -> acc * n.await() }
    }
}