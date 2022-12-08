#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		for (i in 4 .. inputStrings[0].length - 1) {
			if (noRepeats(inputStrings[0].substring(i - 4, i + 1))) {
				return i + 1
			}
		}
		return 0
	}

	fun noRepeats(s: String): Boolean {
		s.forEach { c ->
			if (s.count { it == c } > 1) {
				return false
			}
		}
		return true
	}
}

/* ------------ */

println(Solution(getStringPerLine(args)).solution())
