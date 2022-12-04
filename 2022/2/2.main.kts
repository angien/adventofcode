#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		var total = 0
		inputStrings.forEach {
			total += getChoice(it[1]) + getScore(it[0], it[1])
		}
		return total
	}

	private fun getScore(s: String, s2: String): Int {
		val first = getChoice(s)
		return when (s2) {
			"X" -> wrapIncrement(first, -1, 1, 3)
			"Y" -> first
			"Z" -> wrapIncrement(first, 1, 1, 3)
			else -> 0
		}
	}

	private fun getChoice(s: String): Int {
		return when (s) {
			"X" -> 0
			"Y" -> 3
			"Z" -> 6
			"A" -> 1
			"B" -> 2
			"C" -> 3
			else -> 0
		}
	}
}

/* ------------ */

println(Solution(getMultipleStringsWithSplits(args, " ")).solution())
