package day06

import java.io.File

fun main() {
    //part1()
    part2()
}

fun part1() {
    var sumOfYes = 0
    var answer = mutableMapOf<Char, Int>()
    val groupListAnswer = mutableListOf<Map<Char, Int>>()
    val forms = File("input/day06/input.txt").readLines()

    forms.forEach { line ->
        if (line.isEmpty()) {
            groupListAnswer.add(answer)
            answer = mutableMapOf()
            return@forEach
        }

        line.forEach { ch ->
            answer[ch] = 1
        }

        if (line === forms.last()) {
            groupListAnswer.add(answer)
        }
    }

    groupListAnswer.forEach {
        sumOfYes += it.size
    }

    println("Sum of answer: $sumOfYes")
}

fun part2() {
    val forms = File("input/day06/input.txt").readLines()
    var answer = mutableListOf<String>()
    var yesCount = 0
    var totalYesCount = 0

    forms.forEach outer@{ line ->
        if (line.isEmpty() || line === forms.last()) {

            if (line == forms.last()) {
                answer.add(line)
            }

            answer[0].forEach l1@{ ch ->
                answer.slice(1 until answer.size).forEach l2@{ elem ->
                    elem.forEach { ch2 ->
                        if (ch == ch2) {
                            return@l2
                        }
                    }
                    return@l1
                }
                yesCount++
            }

            totalYesCount += yesCount
            yesCount = 0
            answer = mutableListOf()
            return@outer
        }

        answer.add(line)
    }

    println("Total yes: $totalYesCount")

}