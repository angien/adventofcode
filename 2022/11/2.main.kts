#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

data class Monkey(
	val items: MutableList<Long> = mutableListOf(),
	val operation: String,
	val divisibleBy: Long,
	val ifTrue: Int,
	val ifFalse: Int,
)

class Solution(val inputStrings: List<List<String>>) {
	val monkeys = mutableListOf<Monkey>()
	fun solution(): Long {
		inputStrings.forEach {
			val items = mutableListOf<Long>()
			it[1].split(": ")[1].split(", ").forEach { n ->
				items.add(n.toLong())
			}
			monkeys.add(Monkey(
				items, 
				it[2].split(" = ")[1], 
				it[3].split(" by ")[1].toLong(), 
				it[4].split(" monkey ")[1].toInt(),
				it[5].split(" monkey ")[1].toInt()))
		}

		val monkeyWork = MutableList<Long>(monkeys.size) { 0L }
		var highestValue = 1L
		monkeys.forEach {
			highestValue *= it.divisibleBy
		}

		repeat (10000) {
			monkeys.forEachIndexed { i, m ->
				m.items.forEach {
					val operation = m.operation.split(" ")
					val operationNum = if (operation[2] == "old") {
						it
					} else {
						operation[2].toLong()
					}
					val w = if (operation[1] == "+") it + operationNum else it * operationNum
					val worry = w.toLong() % highestValue
					if (worry.rem(m.divisibleBy) == 0L) {
						monkeys[m.ifTrue].items.add(worry)
					} else {
						monkeys[m.ifFalse].items.add(worry)
					}

					monkeyWork[i] += 1L
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
