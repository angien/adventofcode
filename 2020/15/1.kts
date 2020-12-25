import java.io.File
import java.util.Scanner

/* 
 * turns 
 */

class Solution(val inputStrings: List<String>) {

	fun solution(): Int {
		val turns = mutableListOf<Int>()
		val start = inputStrings[0].split(",")
		var turn = 0
		while (turns.size < 2020) {
			if (turn < start.size) {
				turns.add(start[turn].toInt())
			} else {
				val prev = turns[turn - 1]

				turns.add(recent(prev, turns))
			} 
			turn = turn + 1
		}

		return turns[2019]
	}

	private fun recent(v: Int, turns: List<Int>): Int {
		if (turns.filter { it == v }.count() == 1) {
			return 0
		} else {
			val a = turns.lastIndexOf(v)
			val b = turns.subList(0, a).lastIndexOf(v)

			return a - b
		}
	}
}

/* ------------ */

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

println(Solution(populateInputStrings()).solution())