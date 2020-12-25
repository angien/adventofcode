import java.io.File
import java.util.Scanner

/* 
 * calculate with precendences
 */

class Solution(val inputStrings: List<String>) {

	fun solution(): Long {
		return inputStrings.fold(0L) {
			acc, s -> acc + calculate(rpn(s.replace(" ", "")))
		}
	}

	private fun rpn(v: String): List<String> {
		val stack = mutableListOf<String>()

		val result = mutableListOf<String>()

		v.forEach { c ->
			val it = c.toString()
			when (it) {
				"(" -> stack.add(0, it)
				")" -> {
					while (stack.isNotEmpty() && stack.first() != "(") {
						result.add(stack.removeFirst())
					}
					if (stack.isNotEmpty() && stack.first() == "(") {
						stack.removeFirst()
					}
				}
				"+", "*" -> {
					while (stack.isNotEmpty() && it.isLess(stack.first())) {
						result.add(stack.removeFirst())
					}
					stack.add(0, it)
				}
				else -> {
					result.add(it)
				}
			}
		}

		while (stack.isNotEmpty()) {
			result.add(stack.removeFirst())
		}

		return result
	}

	private fun String.isLess(op: String): Boolean {
		val precedence = mapOf("(" to 0, "*" to 1, "+" to 2)
		return precedence[this]!! <= precedence[op]!!
	}

	private fun calculate(v: List<String>): Long {
		val stack = mutableListOf<String>()
		v.forEach {
			when (it) {
				"+", "*" -> {
					val a = stack.removeFirst().toLong()
					val b = stack.removeFirst().toLong()
					stack.add(0, calculateSimple(a, b, it).toString())
				}
				else -> {
					stack.add(0, it)
				}
			}
		}
		return stack.removeFirst().toLong()
	}

	private fun calculateSimple(left: Long, right: Long, op: String): Long {
		return if (op == "*") {
			left * right
		} else {
			left + right
		}
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