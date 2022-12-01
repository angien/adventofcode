#!/usr/bin/env kotlin

@file:Import("../util.kts")

import util.*

/* 
 * find top 3 sums
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		val sorted = inputStrings.map { sumStrings(it) }.sorted().reversed()

		return sorted[0] + sorted[1] + sorted[2]
	}
}

/* ------------ */

println(Solution(getMultipleStringsPerBreak(args)).solution())