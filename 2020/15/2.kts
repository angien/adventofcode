import java.io.File
import java.util.Scanner

/* 
 * turns - optimized
 */

class Solution(val inputStrings: List<String>) {

	val occ = mutableMapOf<Int, MutableList<Int>>()

	fun solution(): Int {
		val start = inputStrings[0].split(",")
	
		var turn = 0
		var curr = 0
		while (turn < 30000000) {
			if (turn < start.size) {
				occ.put(start[turn].toInt(), mutableListOf(turn))
				curr = start[turn].toInt()
			} else {
				curr = recent(curr, turn)
			} 

			turn = turn + 1
		}

		return curr
	}

	private fun recent(v: Int, turn: Int): Int {
		val vocc = occ[v] ?: mutableListOf()

		val r = if ((vocc.size ?: 0) < 2) {
			0
		} else {
			vocc[1] - vocc[0]
		}

		val prev = occ[r] ?: mutableListOf()

		if (prev.size == 2) {
			prev.removeFirst()
		}
		prev.add(turn)
		occ.put(r, prev)
		return r
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