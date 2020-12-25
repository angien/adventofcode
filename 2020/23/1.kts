import java.io.File
import java.util.Scanner

/* 
 * move the 3 cups after curr cup to destination (which is cup with label of curr - 1)
 */

data class Node(
	val value: Int,
	var child: Node? = null
)

class Solution(val inputStrings: List<String>) {

	fun solution() {
		val input = inputStrings[0]

		val map = mutableMapOf<Int, Node>()

		var curr: Node? = null
		input.forEachIndexed { i, v ->
			val n = Node(v.toString().toInt())
			map.put(v.toString().toInt(), n)
			if (i == 0) {
				curr = n
			}
		}

		val temp = curr
		input.forEach { 
			val n = map[it.toString().toInt()]
			curr!!.child = n
			curr = n
		}
		curr!!.child = temp
		curr = temp

		for (i in 1..100) {
			val first = curr!!.child!!
			val second = first.child!!
			val third = second.child!!

			// println("curr ${curr?.value}")
			// println("first ${first?.value}")
			// println("second ${second?.value}")
			// println("third ${third?.value}")

			var newDest = curr!!.value - 1
			if (newDest == 0) {
				newDest = input.length
			}
			var dest = map[newDest]!!
			while (dest == first || dest == second || dest == third) {
				newDest = dest.value - 1
				if (newDest == 0) {
					newDest = input.length
				}
				dest = map[newDest]!!
			}

			// println("dest ${dest?.value}")
			// println()

			curr!!.child = third.child

			third.child = dest.child
			dest.child = first

			curr = curr!!.child
		}

		var head = map[1]!!
		for (i in 1..input.length - 1) {
			head = head.child!!
			print(head.value)
		}
		println()
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