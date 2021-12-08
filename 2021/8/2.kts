import java.io.File
import java.util.Scanner

class Solution(val inputStrings: List<String>) {
	fun solution(): Int {
		var sum = 0
		inputStrings.forEach {
			val line = it.split(" | ")
			val signals = deduce(line[0].split(' '))
			val encoded = line[1].split(' ')

			var output = ""
			encoded.forEach { p ->
				output += signals.filterValues { it == sort(p) }.keys.first()
			}
			sum += output.toInt()
		}

		return sum
	}

	private fun deduce(signals: List<String>):  Map<String, String> {
		val done = mutableMapOf<String, String>()

		signals.forEach {
			if (it.length == 2) {
				done.put("1", sort(it))
			} else if (it.length == 4) {
				done.put("4", sort(it))
			} else if (it.length == 3) {
				done.put("7", sort(it))
			} else if (it.length == 7) {
				done.put("8", sort(it))
			}
		}

		signals.forEach {
			if (it.length == 5) {
				if (numDiff(it, done["1"]!!) == 0) {
					done.put("3", sort(it))
				} else if (numDiff(it, done["4"]!!) == 1) {
					done.put("5", sort(it))
				} else {
					done.put("2", sort(it))
				}
			}
		}

		signals.forEach {
			if (it.length == 6) {
				if (numDiff(it, done["7"]!!) == 1) {
					done.put("6", sort(it))
				} else if (numDiff(it, done["5"]!!) == 0) {
					done.put("9", sort(it))
				} else {
					done.put("0", sort(it))
				}
			}
		}

		return done
	}

	private fun numDiff(a: String, b: String) = b.filter { !a.contains(it) }.length

	private fun sort(s: String) = s.toCharArray().sorted().joinToString("")
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