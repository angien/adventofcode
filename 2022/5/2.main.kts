#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): String {
		val list = MutableList( 9 ) { mutableListOf<Char>() }
		inputStrings[0].forEach {
			var i = 1
			var x = 0
			while(i < it.length) {
				if (it[i] != ' ') {
					list[x].add(0, it[i]) 
				}
				i += 4
				x += 1
			}
		}

		inputStrings[1].forEach {
			val s = it.split(" ")
			val count = s[1].toInt()
			val from = s[3].toInt()
			val to = s[5].toInt()

			list[to - 1].addAll(list[from - 1].takeLast(count))
			list[from - 1].removeLast(count)
		}

		var result = ""
		list.filter { it.isNotEmpty() }.forEach {
			result += it.last()
		}
		return result
	}
}

/* ------------ */

println(Solution(getMultipleStringsPerBreak(args)).solution())
