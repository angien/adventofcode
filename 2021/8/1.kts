import java.io.File
import java.util.Scanner

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		var sum = 0
		inputStrings.forEach {
			val line = it.split(" | ")
			val output = line[1].split(' ')

			sum += output.filter { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }.size
		}

		return sum
	}
}

/* ------------ */

println(Solution(getStringPerLine()).solution())

fun getMultipleStringsWithSplits(split: String = " "): List<List<String>> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<List<String>>()

	while (inputScanner.hasNextLine()) {
		val next = inputScanner.nextLine()
		inputStrings.add(next.split(split))
	}
	return inputStrings
}

fun getMultipleStringsPerBreak(): List<List<String>> {
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

fun getStringPerSplit(split: String = ","): List<String> {
	return getStringPerLine()[0].split(split)
}

fun getStringPerLine(): List<String> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine())
	}
	return inputStrings
}

fun getInputFile(): File {
	val input = if (args.isNotEmpty() && args[0] != "") args[0] else "input"
	return File(input)
}