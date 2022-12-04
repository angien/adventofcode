#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		var sum = 0
		inputStrings.forEach {
			val first = it[0].split("-").toIntList()
			val second = it[1].split("-").toIntList()

			if (first[0] >= second[0] && first[1] <= second[1]) {
				sum += 1
			} else if (second[0] >= first[0] && second[1] <= first[1]) {
				sum += 1
			} else if (first[1] < second[0]) {
				sum += 0
			} else if (second[1] < first[0]) {
				sum += 0
			} else {
				sum += 1
			}
		}
		return sum
	}
}

/* ------------ */

println(Solution(getMultipleStringsWithSplits(args)).solution())
