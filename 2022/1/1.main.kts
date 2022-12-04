#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

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
