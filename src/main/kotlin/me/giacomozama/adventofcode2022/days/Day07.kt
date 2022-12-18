package me.giacomozama.adventofcode2022.days

import java.io.File
import java.util.*

class Day07 : Day() {

    private lateinit var input: List<String>

    override fun parseInput(inputFile: File) {
        input = inputFile.readLines()
    }

    private sealed interface Node {

        class File(val size: Int) : Node

        class Dir : Node {

            val children = mutableListOf<Node>()
        }
    }

    // n = number of input lines, m = number of nodes
    // time: O(n), space: (m)
    override fun solveFirstPuzzle(): Int {
        val root = parseFileSystem()
        var result = 0

        fun dfs(node: Node): Int = when (node) {
            is Node.File -> node.size
            is Node.Dir -> node.children.sumOf(::dfs).also { if (it <= 100000) result += it }
        }

        dfs(root)
        return result
    }

    // time: O(n), space: (m)
    override fun solveSecondPuzzle(): Any {
        val root = parseFileSystem()

        fun calculateTotalUsedSpace(node: Node): Int = when (node) {
            is Node.File -> node.size
            is Node.Dir -> node.children.sumOf(::calculateTotalUsedSpace)
        }

        val spaceNeeded = calculateTotalUsedSpace(root) - 40000000
        var result = Int.MAX_VALUE

        fun findBestDirToDelete(node: Node): Int = when (node) {
            is Node.File -> {
                node.size
            }
            is Node.Dir -> {
                val sum = node.children.sumOf(::findBestDirToDelete)
                if (sum in spaceNeeded until result) result = sum
                sum
            }
        }

        findBestDirToDelete(root)
        return result
    }

    private fun parseFileSystem(): Node {
        val root = Node.Dir()
        val stack = LinkedList<Node.Dir>()
        for (line in input) {
            when {
                line == "$ cd /" -> {
                    stack.clear()
                    stack.push(root)
                }
                line == "$ cd .." -> {
                    stack.pop()
                }
                line.startsWith("$ cd ") -> {
                    val dir = Node.Dir()
                    stack.peek().children.add(dir)
                    stack.push(dir)
                }
                line != "$ ls" && !line.startsWith("dir ") -> {
                    val size = line.substringBefore(' ').toInt()
                    stack.peek().children.add(Node.File(size))
                }
            }
        }
        return root
    }
}