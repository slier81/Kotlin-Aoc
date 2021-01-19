package day02

import java.io.File

fun main() {
//    part1()
    part2()
}

data class PasswordRule(val min: Int, val max: Int, val ch: Char, val password: String)

fun part1() {
    var validPassword = 0
    val passwordRuleList = passwordRuleList()

    passwordRuleList.forEach {
        val min = it.min
        val max = it.max
        val needle = it.ch
        val password = it.password
        var needleFound = 0

        password.forEach { itt ->
            if (itt == needle) {
                needleFound++
            }
        }

        if (needleFound >= min && needleFound <= max) {
            validPassword++
        }
    }

    println("Found valid password: $validPassword")
}

fun part2() {
    var validPassword = 0
    val passwordRuleList = passwordRuleList()

    passwordRuleList.forEach {
        val needle = it.ch
        val firstPos = it.min
        val secondPos = it.max
        var found = 0

        it.password.forEachIndexed { index, c ->
            val _index = index + 1
            if (c == needle && (_index == firstPos || _index == secondPos)) {
                found++
            }
        }

        if (found == 1) {
            validPassword++
        }
    }

    println("Valid password is :$validPassword")
}

private fun passwordRuleList(): MutableList<PasswordRule> {
    val passwordRuleList = mutableListOf<PasswordRule>()

    val passwords = File("input/day02/input.txt").readLines()

    passwords.forEach { it ->
        val res = it.split(":").map { it.trim() }

        val password = res[1]
        val (minMax, ch) = res[0].split(" ")
        val (min, max) = minMax.split("-").map { it.toInt() }

        passwordRuleList.add(PasswordRule(min, max, ch.single(), password))
    }
    return passwordRuleList
}