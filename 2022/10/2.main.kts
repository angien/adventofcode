#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): String {
		var cycle = 1
		var x = 1
		var result = ""
		inputStrings.forEach {
			result += if (checkRange(cycle, x)) { "#" } else { "." }
			if (checkCycle(cycle)) {
				result += "\n"
			}
			cycle += 1
			if (it[0] == "addx") {	
				result += if (checkRange(cycle, x)) { "#" } else { "." }
				x += it[1].toInt()
				if (checkCycle(cycle)) {
					result += "\n"
				}
				cycle += 1
			} 
		}
		return result
	}

	fun checkCycle(cycle: Int): Boolean {
		return cycle == 40 || cycle == 80 || cycle == 120 || cycle == 160 || cycle == 200 || cycle == 240
	}

	fun checkRange(cycle: Int, x: Int): Boolean {
		val c = cycle % 40
		return c % 40 == x || c == x + 1 || c == x + 2
	}
}

/* ------------ */

println(Solution(getMultipleStringsWithSplits(args, " ")).solution())
