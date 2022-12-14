#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		val visited = mutableSetOf<Pair<Int, Int>>()
		var hx = 0
		var hy = 0
		var tx = 0
		var ty = 0
		visited.add(Pair(tx, ty))
		inputStrings.forEach {
			if (it[0] == "R") {
				repeat (it[1].toInt()) {
					val newx = hx + 1
					if (Math.abs(newx - tx) > 1 || Math.abs(hy - ty) > 1) {
						tx = hx
						ty = hy
						visited.add(Pair(tx, ty))
					}
					hx = newx
				}
			} else if (it[0] == "L") {
				repeat (it[1].toInt()) {
					val newx = hx - 1
					if (Math.abs(newx - tx) > 1 || Math.abs(hy - ty) > 1) {
						tx = hx
						ty = hy
						visited.add(Pair(tx, ty))
					}
					hx = newx
				}
			} else if (it[0] == "U") {
				repeat (it[1].toInt()) {
					val newy = hy + 1
					if (Math.abs(hx - tx) > 1 || Math.abs(newy - ty) > 1) {
						tx = hx
						ty = hy
						visited.add(Pair(tx, ty))
					}
					hy = newy
				}
			} else if (it[0] == "D") {
				repeat (it[1].toInt()) {
					val newy = hy - 1
					if (Math.abs(hx - tx) > 1 || Math.abs(newy - ty) > 1) {
						tx = hx
						ty = hy
						visited.add(Pair(tx, ty))
					}
					hy = newy
				}
			}
		}
		return visited.size
	}
}

/* ------------ */

println(Solution(getMultipleStringsWithSplits(args, " ")).solution())
