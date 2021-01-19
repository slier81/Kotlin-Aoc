package day01

import java.io.File

fun main() {
//    part1()
    part2()
}

fun part1() {
    val numbers = File("input/day01/input.txt").readLines().map {
        it.toInt()
    }

    outter@ for (i in 0 until numbers.size) {
        for (j in i + 1 until numbers.size) {

            val res = numbers[i] + numbers[j]

            if (res == 2020) {
                println("The answer is: ${numbers[i] * numbers[j]}")
                break@outter
            }
        }
    }
}

fun part2() {
    val numbers = File("input/day01/input.txt").readLines().map { it.toInt() }

    outter@ for (i in 0 until numbers.size) {
        for (j in i + 1 until numbers.size) {
            for (k in j + 1 until numbers.size) {
                var res = numbers[i] + numbers[j] + numbers[k]

                if (res == 2020) {
                    println("The answer is ${numbers[i] * numbers[j] * numbers[k]}")
                    break@outter
                }
            }
        }
    }
}
