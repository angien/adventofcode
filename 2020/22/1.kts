import java.io.File
import java.util.Scanner

/* 
 * get corners of the tiles when they are put together after rotations and flips
 */

class Solution(val inputStrings: List<List<String>>) {

	fun solution(): Long {

		val player1 = inputStrings[0].filter { it.contains("Player").not() }.toMutableList()
		val player2 = inputStrings[1].filter { it.contains("Player").not() }.toMutableList()


		while (player1.isNotEmpty() && player2.isNotEmpty()) {
			val p1 = player1.removeFirst()
			val p2 = player2.removeFirst()
			if (p1.toInt() > p2.toInt()) {
				player1.add(p1)
				player1.add(p2)
			} else {
				player2.add(p2)
				player2.add(p1)
			}
		}

		val r = if (player1.isEmpty()) player2 else player1

		var sum = 0L
		var count = r.size
		r.forEach {
			sum = sum + (it.toLong() * count)
			count = count - 1
		}

		return sum
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