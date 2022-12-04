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
		inputStrings.forEach { s ->
			val chunked = s.chunked(s.length / 2)
			val shared = findAllCommon(chunked[0].toList(), chunked[1].toList()).first()
			sum += a.indexOf(shared) + 1
		}
		return sum
	}
}

/* ------------ */

println(Solution(getStringPerLine(args)).solution())
