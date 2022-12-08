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
					visible += 1
				} else {
					val h = c.toString().toInt()
					val leftMax = row.substring(0, i2).map { it.toString().toInt() }.max()
					val rightMax = row.substring(i2 + 1, row.length).map { it.toString().toInt() }.max()
					val above = mutableListOf<Int>()
					for (i in 0 until i - 1) {
						above.add(inputStrings[i][i2].toString().toInt())
					}
					val below = mutableListOf<Int>()
					for (i in i + 1 until inputStrings.size) {
						below.add(inputStrings[i][i2].toString().toInt())
					}
					val aboveMax = above.max()
					val belowMax = below.max()

					if (h > aboveMax || h > belowMax || h > leftMax || h > rightMax) {
						visible += 1
					}

				}
			}
		}
		return visible
	}
}

/* ------------ */

println(Solution(getStringPerLine(args)).solution())
