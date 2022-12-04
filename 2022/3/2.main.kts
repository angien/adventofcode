#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		val a = lowercase + uppercase
		var sum = 0
		inputStrings.forEachIndexed { index, s ->
			if (index % 3 == 0) {
				val shared = findAllCommon(inputStrings[index].toList(), 
					inputStrings[index + 1].toList(), 
					inputStrings[index + 2].toList()).first()
				sum += a.indexOf(shared) + 1
			}
			
		}
		return sum
	}
}

/* ------------ */

println(Solution(getStringPerLine(args)).solution())
