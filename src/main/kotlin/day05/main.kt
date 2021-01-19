package day05

import java.io.File

fun main() {
    //part1()
    part2()
}

fun part1() {
    var highestSeatNumber = 0
    val seatList = getSeatList()

    seatList.forEach {
        if (it > highestSeatNumber) {
            highestSeatNumber = it
        }
    }

    println("Highest seat number: $highestSeatNumber")
}

fun part2() {
    val seatList = getSeatList()
    var prevSeat: Int? = null

    seatList.forEach {

        if (prevSeat != null && it - prevSeat!! > 1) {
            println("My seat number is : ${it - 1}")
        }

        prevSeat = it
    }
}

fun getSeatList(): List<Int> {
    val seats = File("input/day05/input.txt").readLines()
    val seatList = mutableListOf<Int>()

    seats.forEach {
        var min = 0
        var max = 128

        val rows = it.slice(0..6)
        val cols = it.slice(7 until it.length)

        rows.forEach { rit ->
            val middle = (max - min) / 2

            if (rit == 'F') {
                max -= middle

            } else if (rit == 'B') {
                min += middle
            }
        }

        val rowNo = min

        min = 0
        max = 8
        cols.forEach { cit ->
            val middle = (max - min) / 2
            if (cit == 'L') {
                max -= middle
            } else if (cit == 'R') {
                min += middle
            }
        }

        val colNo = min
        val seatNo = (rowNo * 8) + colNo

        seatList.add(seatNo)
    }

    seatList.sort()
    return seatList
}