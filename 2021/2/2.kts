import java.io.File
import java.util.Scanner

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		var depth = 0
		var horizontal = 0
		var aim = 0

		inputStrings.forEach {
			val direction = it[0]
			val value = it[1].toInt()
			when (direction) {
				"forward" -> {
					horizontal += value
					depth += (aim * value)
				}
				"down" -> aim += value
				"up" -> aim -= value
			}
		}
		return depth * horizontal
	}
}

/* ------------ */

println(Solution(populateInputStringsAndSplit()).solution())

fun populateInputStringsAndSplit(split: String = " "): List<List<String>> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<List<String>>()

	while (inputScanner.hasNextLine()) {
		val next = inputScanner.nextLine()
		inputStrings.add(next.split(split))
	}
	return inputStrings
}

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