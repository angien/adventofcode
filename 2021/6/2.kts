import java.io.File
import java.util.Scanner
import java.util.Collections

class Solution(val inputStrings: List<String>) {
	fun solution(): Long {
		var fish = arrayListOf<Long>(0L,0L,0L,0L,0L,0L,0L,0L,0L)
		inputStrings[0].split(',').forEach {
			fish[it.toInt()] += 1L
		}

		for (i in 0 until 256) {
			var newFish = fish[0]
			Collections.rotate(fish, -1)
			fish[6] += newFish
		}

		return fish.sum()
	}

	private infix fun Int.toward(to: Int): IntProgression {
	    val step = if (this > to) -1 else 1
	    return IntProgression.fromClosedRange(this, to, step)
	}
}

/* ------------ */

println(Solution(populateInputStrings()).solution())

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