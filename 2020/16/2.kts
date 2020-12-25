import java.io.File
import java.util.Scanner

/* 
 * associate index to the valid ticket
 */

data class Ticket(
	val label: String,
	var possibleIndex: MutableList<Int>,
	val ranges: List<Pair<Int, Int>>
)

class Solution(val inputStrings: List<List<String>>) {

	val tickets = mutableListOf<Ticket>()

	fun solution(): Long {
		val my = inputStrings[1][1].split(",")

		inputStrings[0].forEach { 
			val labelled = it.split(": ")
			if (labelled.size > 1) {
				val ranges = labelled[1].split(" or ")
				val listRanges = mutableListOf<Pair<Int, Int>>()
				ranges.forEach { r ->
					val m = r.split("-")
					listRanges.add(Pair(m[0].toInt(), m[1].toInt()))
				}
				tickets.add(Ticket(labelled[0], MutableList(my.size){it}, listRanges))
			}
		}
		
		inputStrings[2].forEach {
			if (it.contains(":").not()) {
				val valids = mutableListOf<String>()
				it.split(",").forEach { ticket ->
					if (valid(ticket.toInt())) {
						valids.add(ticket)
					}
				}
					
				valids.forEachIndexed { index, ticket ->
					inRange(index, ticket.toInt())
				}
			}
		}
		
		consolidate(my.size)
	
		var result = 1L
		tickets.filter { it.label.contains("departure") }.forEach {
			result = result * my[it.possibleIndex[0]].toLong()
		}
		
		return result
	}

	private fun inRange(i: Int, v: Int) {
		tickets.forEach {
			var remove = true
			it.ranges.forEach { range ->
				if (v >= range.first && v <= range.second) {
					remove = false
				}
			}
			if (remove) {
				it.possibleIndex.remove(i)
			}
		}
	}

	private fun valid(v: Int): Boolean {
		var valid = false

		tickets.forEach {
			it.ranges.forEach { range ->
				if (v >= range.first && v <= range.second) {
					valid = true
				}
			}
		}
		return valid
	}

	private fun consolidate(size: Int) {
		var i = 0
		while (i < size) {
			tickets.forEach {
				if (it.possibleIndex.size == 1) {
					val toRemove = it.possibleIndex[0]
					tickets.filter { item -> item.possibleIndex.size > 1 }.forEach { update ->
						update.possibleIndex.remove(toRemove)
						
					}
				}
			}
			i = i + 1
		}
	}
}

/* ------------ */

fun populateInputStringsAndBreaks(): List<List<String>> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<List<String>>()
	var innerString = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		val next = inputScanner.nextLine()
		if (next == "") {
			inputStrings.add(innerString)
			innerString = mutableListOf<String>()
		} else {
			innerString.add(next)
		}
	}
	inputStrings.add(innerString)
	return inputStrings
}

fun populateInputStrings(): List<String> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine())
	}
	return inputStrings
}

fun getInputFile(): File {
	val input = if (args.isNotEmpty() && args[0] != "") { 
		args[0] 
	} else { 
		"input" 
	}
	return File(input)
}

println(Solution(populateInputStringsAndBreaks()).solution())