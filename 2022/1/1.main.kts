#!/usr/bin/env kotlin

@file:Import("../util.kts")

import java.io.File
import java.util.Scanner
import util.*

/* 
 * find largest sum
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		val sorted = inputStrings.map { sumStrings(it) }.sorted().reversed()

		return sorted[0]
	}
}

/* ------------ */

println(Solution(getMultipleStringsPerBreak(args)).solution())
