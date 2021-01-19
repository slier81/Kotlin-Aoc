package day03

import java.io.File

fun main() {
    //part1()
    part2()
}

fun part1() {
    val foundTree = traverseTree(Pair(3, 1))
    println("Tree found: $foundTree")
}

fun part2() {
    val slopes =
        arrayOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))

    val totalTree = traverseTree(*slopes)
    println(totalTree)

}

fun traverseTree(vararg slopes: Pair<Int, Int>): Long {
    val forest = File("input/day03/input.txt").readLines()
    val colMax = forest[0].length
    var totalTree = 1L

    slopes.forEach {
        var col = 0
        val colWidth = it.first
        val step = it.second
        var foundTree = 0;

        for (i in forest.indices step step) {
            if (i == 0) continue

            col = (col + colWidth) % colMax

            if (forest[i][col] == '#') {
                foundTree++
            }
        }

        println("Tree found: $foundTree")
        totalTree *= foundTree
    }

    return totalTree
}