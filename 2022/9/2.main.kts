#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

data class Knot(
	var x: Int = 0,
	var y: Int = 0
)

class Solution(val inputStrings: List<List<String>>) {
	val visited = mutableSetOf<Pair<Int, Int>>()
	val rope = List<Knot>(9) { Knot() }

	fun solution(): Int {
		var hx = 0
		var hy = 0
		visited.add(Pair(rope[0].x, rope[0].y))
		inputStrings.forEach {
			if (it[0] == "R") {
				repeat (it[1].toInt()) {
					updateRope(hx, hy, hx + 1, hy)
					visited.add(Pair(rope.last().x, rope.last().y))
					hx = hx + 1
				}
			} else if (it[0] == "L") {
				repeat (it[1].toInt()) {
					updateRope(hx, hy, hx - 1, hy)
					visited.add(Pair(rope.last().x, rope.last().y))
					hx = hx - 1
				}
			} else if (it[0] == "U") {
				repeat (it[1].toInt()) {
					updateRope(hx, hy, hx, hy + 1)
					visited.add(Pair(rope.last().x, rope.last().y))
					hy = hy + 1
				}
			} else if (it[0] == "D") {
				repeat (it[1].toInt()) {
					updateRope(hx, hy, hx, hy - 1)
					visited.add(Pair(rope.last().x, rope.last().y))
					hy = hy - 1
				}
			}
		}
		return visited.size
	}

	fun updateRope(a: Int, b: Int, c: Int, d: Int) {
		var tempx = a
		var tempy = b
		var ux = c
		var uy = d
		rope.forEachIndexed { i, k ->		
			val x = k.x
			val y = k.y			
			if (Math.abs(ux - x) > 1 && uy - y == 0) {
				k.x += if (ux - x > 0) 1 else - 1
			}						
			else if (Math.abs(uy - y) > 1 && ux - x == 0) {
				k.y += if (uy - y > 0) 1 else - 1
			}
			else if (Math.abs(uy - y) > 1 || Math.abs(ux - x) > 1) {
				k.x += if (ux - x > 0) 1 else - 1
				k.y += if (uy - y > 0) 1 else - 1
			}
			tempx = x
			tempy = y
			ux = k.x
			uy = k.y
		}
	}
}

/* ------------ */

println(Solution(getMultipleStringsWithSplits(args, " ")).solution())
