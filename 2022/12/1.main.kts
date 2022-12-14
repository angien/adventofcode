#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		var result = 0
		var sx = 0
		var sy = 0
		inputStrings.forEachIndexed { y, s ->
			s.forEachIndexed { x, i ->
				if (i == 'S') {
					sx = x
					sy = y
				}
			}
		}

		val start = Pair(sx, sy)

		val visited = mutableSetOf<Pair<Int, Int>>()
		val deque = ArrayDeque<Pair<Int, Int>>()
		val dist = mutableMapOf<Pair<Int, Int>, Int>()

		deque.add(start)
		visited.add(start)
		dist[start] = 1

		while (deque.isNotEmpty()) {
			val pair = deque.removeFirst()
			val v = inputStrings[pair.second][pair.first]

			val toAdd = listOf(
				Pair(pair.first + 1, pair.second),
				Pair(pair.first - 1, pair.second),
				Pair(pair.first, pair.second + 1),
				Pair(pair.first, pair.second - 1)
			)
			toAdd.forEach { p ->
				if (isValid(p.first, p.second) && p !in visited) {
					val v2 = inputStrings[p.second][p.first]
					if (v == 'z' && v2 == 'E') {
						return dist[pair]!!
					}
					if ((v2 != 'E' && v + 1 >= v2) || (v == 'S' && v2 == 'a')) {
						deque.add(p)
						visited.add(p)
						dist[p] = Math.min(dist[pair]!! + 1, dist[p] ?: Integer.MAX_VALUE)
					}
				}
			}
		}

		return 0
	}

	fun isValid(x: Int, y: Int): Boolean {
		return x >= 0 && x < inputStrings[0].length && y >= 0 && y < inputStrings.size
	}
}

/* ------------ */

println(Solution(getStringPerLine(args)).solution())
