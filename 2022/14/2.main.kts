#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		var maxY = 0
		var maxX = 0
		val blocked = mutableSetOf<Pair<Int, Int>>()
		inputStrings.forEach {
			var currX = 0
			var currY = 0
			it.forEach { s ->
				val c = s.split(",")
				val c0 = c[0].toInt()
				val c1 = c[1].toInt()
				if (currX != 0) {
					for (x in currX toward c0) {
						blocked.add(Pair(x, c1))
						maxX = Math.max(x, maxX)
					}
					for (y in currY toward c1) {
						blocked.add(Pair(c0, y))
						maxY = Math.max(y, maxY)
					}
				}
				currX = c0
				currY = c1
				blocked.add(Pair(c0, c1))
				maxX = Math.max(c0, maxX)
				maxY = Math.max(c1, maxY)
			}
		}
		maxY = maxY + 2

		var sand = true
		var result = 0

		while (sand) {
			var x = 500
			var y = 0

			while (true) {
				val nextY = y + 1
				val leftX = x - 1
				val rightX = x + 1

				if (nextY == maxY) {
					blocked.add(Pair(x, nextY))
					blocked.add(Pair(leftX, nextY))
					blocked.add(Pair(rightX, nextY))
				}

				if (blocked.contains(Pair(x, nextY)).not()) {
					x = x
					y = nextY
				} else if (blocked.contains(Pair(leftX, nextY)).not()) {
					x = leftX
					y = nextY
				} else if (blocked.contains(Pair(rightX, nextY)).not()) {
					x = rightX
					y = nextY
				} else { // comes to rest
					blocked.add(Pair(x, y))
					result += 1
					if (x == 500 && y == 0) sand = false
					break
				}
			}
			
		}
		return result
	}
}

/* ------------ */

println(Solution(getMultipleStringsWithSplits(args, " -> ")).solution())
