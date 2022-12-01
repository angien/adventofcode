package util

import java.io.File
import java.util.Scanner

fun sumStrings(s: List<String>): Int {
	return s.map { it.toInt() }.reduce { acc, it -> acc + it.toInt() }
}

/**
 * hello
 * world
 * 
 * hi
 * world
 */
fun getMultipleStringsPerBreak(args: Array<String>): List<List<String>> {
	val inputScanner = Scanner(getInputFile(args))
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
 * hello,world
 * hi,world
 */
fun getMultipleStringsWithSplits(args: Array<String>, split: String = ","): List<List<String>> {
	val inputScanner = Scanner(getInputFile(args))
	val inputStrings = mutableListOf<List<String>>()

	while (inputScanner.hasNextLine()) {
		val next = inputScanner.nextLine()
		inputStrings.add(next.split(split))
	}
	return inputStrings
}


/**
 * hello world hi world
 */
fun getStringPerSplit(args: Array<String>, split: String = " "): List<String> {
	return getStringPerLine(args)[0].split(split)
}

/**
 * hello
 * world
 * hi
 * world
 */
fun getStringPerLine(args: Array<String>): List<String> {
	val inputScanner = Scanner(getInputFile(args))
	val inputStrings = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine())
	}
	return inputStrings
}

// If no input specified, will use "input". If no "input" exists, will use "easy"
fun getInputFile(args: Array<String>): File {
	var input = if (args.isNotEmpty() && args[0] != "") args[0] else "input"
	if (File(input).exists().not()) {
		input = "easy"
	}
	return File(input)
}