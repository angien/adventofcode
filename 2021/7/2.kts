import java.io.File
import java.util.Scanner

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		val crabs = inputStrings.map { it.toInt() }

		var leastSum = Integer.MAX_VALUE
		val min = crabs.minOrNull() ?: 0
		val max = crabs.maxOrNull() ?: 0

		for (p in min..max) {
			var sum = 0
			crabs.forEach {
				val n = Math.abs(it - p)
				sum += n * (n + 1) / 2 //sum from 1 to n
			}
			leastSum = Math.min(sum, leastSum)
		}

		return leastSum
	}

	private infix fun Int.toward(to: Int): IntProgression {
	    val step = if (this > to) -1 else 1
	    return IntProgression.fromClosedRange(this, to, step)
	}
}

/* ------------ */

println(Solution(getStringPerSplit()).solution())

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