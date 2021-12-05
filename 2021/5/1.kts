import java.io.File
import java.util.Scanner

class Solution(val inputStrings: List<List<String>>) {
	fun solution(): Int {
		val draws = mutableMapOf<Pair<Int, Int>, Int>()

		inputStrings.forEach {
			val first = it[0].split(",")
			val firstX = first[0].toInt()
			val firstY = first[1].toInt()

			val second = it[1].split(",")
			val secondX = second[0].toInt()
			val secondY = second[1].toInt()

			if (firstX == secondX) {
				for (y in firstY toward secondY) {
					val key = Pair(firstX, y)
					draws.put(key, (draws.get(key) ?: 0) + 1)
				}
			} else if (firstY == secondY) {
				for (x in firstX toward secondX) {
					val key = Pair(x, firstY)
					draws.put(key, (draws.get(key) ?: 0) + 1)
				}
			}
		}

		return draws.filter { it.value > 1 }.size
	}

	private infix fun Int.toward(to: Int): IntProgression {
	    val step = if (this > to) -1 else 1
	    return IntProgression.fromClosedRange(this, to, step)
	}
}

/* ------------ */

println(Solution(populateInputStringsAndSplit(" -> ")).solution())

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