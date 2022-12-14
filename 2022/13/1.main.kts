#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		val result = mutableListOf<Int>()
		inputStrings.forEachIndexed { i, pair -> 
			if (compare(pair[0], pair[1]) >= 0 ) {
				result.add(i + 1)
			}
		}
		return result.sum()
	}

	private fun getElements(s: String): List<String> {
		var result = mutableListOf<String>()
		var element = ""
		var open = 0
		s.removePrefix("[").removeSuffix("]").forEach {
			if (it == '[') {
				open += 1
			}
			if (it == ']') {
				open -= 1
			}
			if (it == ',' && open == 0) {
				result.add(element)
				element = ""
			} else {
				element += it
			}
		}
		if (element != "") {
			result.add(element)
		}
		return result
	}

	private fun compare(left: String, right: String): Int {
		val l = left.toIntOrNull()
		val r = right.toIntOrNull()

		if (l != null && r != null) {
			return if (l < r) 1 else if (l > r) -1 else 0
		} else if (r == null && l == null) {
			val le = getElements(left)
			val re = getElements(right)
			for (i in 0 until Math.min(le.size, re.size)) {
				val c = compare(le[i], re[i])
				if (c != 0) {
					return c
				}
			}
			return compare(le.size.toString(), re.size.toString())
		} else {
			val r1 = if (r != null) "[" + r.toString() + "]" else right
	  		val l1 = if (l != null) "[" + l.toString() + "]" else left
	  		return compare(l1, r1)
		}		
	}

}

/* ------------ */

println(Solution(getMultipleStringsPerBreak(args)).solution())
