import java.io.File
import java.util.Scanner

/* 
 * move the 3 cups after curr cup to destination (which is cup with label of curr - 1)
 * except now there are 1 million cups, with it being sequential after the first inputted cups
 * linked lists
 */

data class Node(
	val value: Long,
	var child: Node? = null
)

class Solution(val inputStrings: List<String>) {

	fun solution(): Long {
		val input = inputStrings[0]

		val map = mutableMapOf<Long, Node>()

		var curr: Node? = null
		input.forEachIndexed { i, v ->
			val n = Node(v.toString().toLong())
			map.put(v.toString().toLong(), n)
			if (i == 0) {
				curr = n
			}
		}

		val temp = curr
		input.forEach {
			val n = map[it.toString().toLong()]
			curr!!.child = n
			curr = n
		}

		for (i in (input.length + 1L)..1000000L) {
			val n = Node(i)
			map.put(i, n)
			curr!!.child = n
			curr = n
			if (i == 1000000L) {
				n.child = temp
			}
		}
		curr = temp

		for (i in 1..10000000) {
			val first = curr!!.child!!
			val second = first.child!!
			val third = second.child!!

			var newDest = curr!!.value - 1
			if (newDest == 0L) {
				newDest = map.keys.size.toLong()
			}
			var dest = map[newDest]!!
			while (dest == first || dest == second || dest == third) {
				newDest = dest.value - 1
				if (newDest == 0L) {
					newDest = map.keys.size.toLong()
				}
				dest = map[newDest]!!
			}

			curr!!.child = third.child

			third.child = dest.child
			dest.child = first

			curr = curr!!.child
		}

		val head = map[1]!!
		val firstRight = head.child!!
		val secondRight = firstRight.child!!
		return firstRight.value * secondRight.value
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