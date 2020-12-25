import java.io.File
import java.util.Scanner

/* 
 * 
 */

class Solution(val inputStrings: List<List<String>>) {

	val rules = mutableMapOf<String, List<String>>()

	fun solution(): Long {
		val ruleStrings = inputStrings[0]
		val messageStrings = inputStrings[1]

		ruleStrings.forEach {
			val keyValue = it.replace("\"", "").split(": ")
			rules.put(keyValue[0], keyValue[1].split(" "))
		}

		var m = 0L
		messageStrings.forEach {
			if (Regex(resolve("0")).matches(it)) {
				m = m + 1
			}
		}
		return m
	}

	fun consolidate(key: String): String {
		return when (key) {
			"a", "b", "|" -> key
			else -> "(" + resolve(key) + ")"
		}
	}

	fun resolve(key: String): String {
		var r = ""
		rules[key]?.forEach {
			r = r + consolidate(it)
		}
		return r
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