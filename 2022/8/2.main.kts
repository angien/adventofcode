#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		var visible = 0
		inputStrings.forEachIndexed { i, row ->
			row.forEachIndexed { i2, c ->
				if (i == 0 || i2 == 0 || i == inputStrings.size - 1 || i2 == row.length - 1) {
					// Do nothing
				} else {
					val h = c.toString().toInt()
					var left = 0
					for (i in i2 - 1 downTo 0) {
						val v = row[i].toString().toInt()
						left += 1
						if (v >= h) {
							break
						}
					}
					var right = 0
					for (i in i2 + 1 until row.length) {
						val v = row[i].toString().toInt()
						right += 1
						if (v >= h) {
							break
						}
					}
					var above = 0
					for (i in i - 1 downTo 0) {
						val v = inputStrings[i][i2].toString().toInt()
						above += 1
						if (v >= h) {
							break
						}
					}
					var below = 0
					for (i in i + 1 until inputStrings.size) {
						val v = inputStrings[i][i2].toString().toInt()
						below += 1
						if (v >= h) {
							break
						}
					}

					visible = maxOf(above * below * left * right, visible)

				}
			}
		}
		return visible
	}
}

/* ------------ */

println(Solution(getStringPerLine(args)).solution())
