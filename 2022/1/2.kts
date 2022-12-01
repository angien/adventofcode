import java.io.File
import java.util.Scanner

/* 
 * find top 3 sums
 */

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		val sorted = inputStrings.map { sumStrings(it) }.sorted().reversed()

		return sorted[0] + sorted[1] + sorted[2]
	}
}

/* ------------ */

println(Solution(getMultipleStringsPerBreak()).solution())

fun sumStrings(s: List<String>): Int {
	return s.map { it.toInt() }.reduce { acc, it -> acc + it.toInt() }
}

/**
 * hello,world
 * hi,world
 */
fun getMultipleStringsWithSplits(split: String = ","): List<List<String>> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<List<String>>()

	while (inputScanner.hasNextLine()) {
		val next = inputScanner.nextLine()
		inputStrings.add(next.split(split))
	}
	return inputStrings
}

/**
 * hello
 * world
 * 
 * hi
 * world
 */
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

/**
 * hello world hi world
 */
fun getStringPerSplit(split: String = " "): List<String> {
	return getStringPerLine()[0].split(split)
}

/**
 * hello
 * world
 * hi
 * world
 */
fun getStringPerLine(): List<String> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine())
	}
	return inputStrings
}

// If no input specified, will use "input". If no "input" exists, will use "easy"
fun getInputFile(): File {
	var input = if (args.isNotEmpty() && args[0] != "") args[0] else "input"
	if (File(input).exists().not()) {
		input = "easy"
	}
	return File(input)
}