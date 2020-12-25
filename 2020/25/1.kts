import java.io.File
import java.util.Scanner

/* 
 * find the loop, then use that loop to transform input for encryption
 */

class Solution(val inputStrings: List<String>) {
	val map = mutableMapOf<Long, Long>(0L to 1L)

	fun solution(): Long {
		val door = inputStrings[0].toInt()
		val card = inputStrings[1].toInt()

		var doorLoop = 0
		var cardLoop = 0
		var loop = 1
		while (doorLoop == 0 && cardLoop == 0) {
			val transform = transform(loop.toLong())
			if (transform == card.toLong()) {
				cardLoop = loop
				println("card loop: $loop")
			}
			if (transform == door.toLong()) {
				doorLoop = loop
				println("door loop: $loop")
			}
			loop = loop + 1
		}
		
		return if (cardLoop != 0) {
			transform(cardLoop, door)
		} else {
			transform(doorLoop, card)
		}
	}

	private fun transform(loop: Int, subject: Int): Long {
		var value = 1L
		for (i in 0 until loop) {
		 	value = value * subject
			value = value % 20201227
		}
		return value
	}

	private fun transform(loop: Long): Long {
		var value = map[loop - 1]!!
		value = value * 7
		value = value % 20201227
		map[loop] = value
		return value
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

println(Solution(populateInputStrings()).solution())