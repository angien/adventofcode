#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		var cycle = 1
		var x = 1
		var result = 0
		inputStrings.forEach {					
			if (checkCycle(cycle)) {
				result += cycle * x
			}
			cycle += 1	
			if (it[0] == "addx") {	
				if (checkCycle(cycle)) {
					result += cycle * x
				}
				cycle += 1
				x += it[1].toInt()
			}

		}
		return result
	}

	fun checkCycle(cycle: Int): Boolean {
		return cycle == 20 || cycle == 60 || cycle == 100 || cycle == 140 || cycle == 180 || cycle == 220
	}
}

/* ------------ */

println(Solution(getMultipleStringsWithSplits(args, " ")).solution())
