package day04

import java.io.File

data class Passport(
    var byr: String? = null,
    var iyr: String? = null,
    var eyr: String? = null,
    var hgt: String? = null,
    var hcl: String? = null,
    var ecl: String? = null,
    var pid: String? = null,
    var cid: String? = null
)

fun main() {
    //part1()
    part2()
}

fun part1() {
    val passports = File("input/day04/input.txt").readLines()
    val passportList = mutableListOf<Passport>()
    var validPassport = 0;
    var p = Passport()

    passports.forEach {
        if (it.isEmpty()) {
            passportList.add(p)
            p = Passport()
            return@forEach
        }

        val passportData = it.split(" ")

        passportData.forEach { itt ->
            val (k, v) = itt.split(":")

            when (k) {
                "byr" -> p.byr = v
                "iyr" -> p.iyr = v
                "eyr" -> p.eyr = v
                "hgt" -> p.hgt = v
                "hcl" -> p.hcl = v
                "ecl" -> p.ecl = v
                "pid" -> p.pid = v
                "cid" -> p.cid = v
            }
        }

        if (it === passports.last()) {
            passportList.add(p)
        }
    }

    passportList.forEach {
        if (isValidPassport(it)) {
            validPassport++
        }
    }

    println("Valid passport: $validPassport")
}

fun part2() {
    val passports = File("input/day04/input.txt").readLines()
    var passport = Passport()
    val passportList = mutableListOf<Passport>()
    var validPassport = 0

    passports.forEach {
        if (it.isEmpty()) {
            passportList.add(passport)
            passport = Passport()
            return@forEach
        }

        it.split(" ").forEach { itt ->
            val (k, v) = itt.split(":")

            when (k) {
                "byr" -> passport.byr = v
                "iyr" -> passport.iyr = v
                "eyr" -> passport.eyr = v
                "hgt" -> passport.hgt = v
                "hcl" -> passport.hcl = v
                "ecl" -> passport.ecl = v
                "pid" -> passport.pid = v
                "cid" -> passport.cid = v
            }
        }

        if (it == passports.last()) {
            passportList.add(passport)
        }
    }

    passportList.forEach {
        if (
            isValidPassport(it) && isValidByr(it.byr ?: "") &&
            isValidIyr(it.iyr ?: "") && isValidEyr(it.eyr ?: "") &&
            isValidHgt(it.hgt ?: "") && isValidHcl(it.hcl ?: "") &&
            isValidEcl(it.ecl ?: "") && isValidPid(it.pid ?: "")
        ) {
            validPassport++
        }
    }

    println("Valid passport is :$validPassport")
}

fun isValidPassport(p: Passport): Boolean {
    if (
        p.byr != null && p.iyr != null && p.eyr != null && p.hgt != null &&
        p.hcl != null && p.ecl != null && p.pid != null
    ) {
        return true
    }

    return false
}


fun isValidByr(byr: String): Boolean {
    //byr (Birth Year) - four digits; at least 1920 and at most 2002.
    val birthYear = byr.toIntOrNull()
    return birthYear?.let { it in 1920..2002 } == true && byr.length == 4
}

fun isValidIyr(iyr: String): Boolean {
    //kiyr (Issue Year) - four digits; at least 2010 and at most 2020.
    val issuedYear = iyr.toIntOrNull()
    return issuedYear?.let { it in 2010..2020 } == true && iyr.length == 4
}

fun isValidEyr(eyr: String): Boolean {
    //eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    val expirationyear = eyr.toIntOrNull()
    return expirationyear?.let { it in 2020..2030 } == true && eyr.length == 4
}

fun isValidHgt(hgt: String): Boolean {
    //hgt (Height) - a number followed by either cm or in :
    //If cm, the number must be at least 150 and at most 193.
    //If in , the number must be at least 59 and at most 76.

    val matches = Regex("^([0-9]+)(cm|in)$").find(hgt)
    val height = matches?.groupValues?.get(1)?.toIntOrNull()
    val measurement = matches?.groupValues?.get(2)

    if (measurement == "cm" && (height in 150..196)) {
        return true
    }

    if (measurement == "in" && height in 59..76) {
        return true
    }

    return false
}

fun isValidHcl(hcl: String): Boolean {
    //hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    val re = Regex("^#([0-9]|[a-f]){6}$")
    if (re.matches(hcl)) {
        return true
    }
    return false
}

fun isValidEcl(ecl: String): Boolean {
    //ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    val validColors = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    validColors.forEach {
        if (it == ecl) {
            return true
        }
    }

    return false
}


fun isValidPid(pid: String): Boolean {
    // A nine-digit number, including leading zeroes
    val re = Regex("^[0-9]{9}$")
    return re.matches(pid)
}
