#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

data class Monkey(
	val items: MutableList<Int> = mutableListOf(),
	val operation: String,
	val divisibleBy: Int,
	val ifTrue: Int,
	val ifFalse: Int,
)

class Solution(val inputStrings: List<List<String>>) {
	val monkeys = mutableListOf<Monkey>()
	fun solution(): Int {
		inputStrings.forEach {
			val items = mutableListOf<Int>()
			it[1].split(": ")[1].split(", ").forEach { n ->
				items.add(n.toInt())
			}
			monkeys.add(Monkey(
				items, 
				it[2].split(" = ")[1], 
				it[3].split(" by ")[1].toInt(), 
				it[4].split(" monkey ")[1].toInt(),
				it[5].split(" monkey ")[1].toInt()))
		}

		val monkeyWork = MutableList<Int>(monkeys.size) { 0 }

		repeat (20) {
			monkeys.forEachIndexed { i, m ->
				m.items.forEach {
					val operation = m.operation.split(" ")
					val operationNum = if (operation[2] == "old") {
						it
					} else {
						operation[2].toInt()
					}
					val w = if (operation[1] == "+") it + operationNum else it * operationNum
					val worry = w / 3
					if (worry % m.divisibleBy == 0) {
						monkeys[m.ifTrue].items.add(worry)
					} else {
						monkeys[m.ifFalse].items.add(worry)
					}

					monkeyWork[i] += 1
				}
				m.items.clear()
			}
		}

		val sorted = monkeyWork.sorted().reversed()

		return sorted[0] * sorted[1]
	}
}

/* ------------ */

println(Solution(getMultipleStringsPerBreak(args)).solution())
