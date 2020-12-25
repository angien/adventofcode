import java.io.File
import java.util.Scanner

/* 
 * binary addition with floating
 */

class Solution(val inputStrings: List<String>) {

	fun solution(): Long {
		var mask = ""

		val map = mutableMapOf<Long, Long>()
		inputStrings.forEach {
			if (it.contains("mask")) {
				mask = it.split(" = ")[1]
			} else {
				val split = it.split(" = ")
				val mem = Integer.toBinaryString(split[0].replace("mem[", "").replace("]", "").toInt())

				applyMask(mask, mem.padStart(mask.length, '0')).forEach {
					map.put(it.toLong(2), split[1].toLong())
				}
			}
		}
	
		var sum = 0L
		map.forEach {
			sum = sum + it.value
		}

		return sum
	}

	private fun applyMask(mask: String, value: String): List<String> {
		var r = ""
		for (i in 0 until mask.length) {
			when (mask.get(i)) {
				'X' -> r = r + "X"
				'0' -> r = r + value.get(i)
				'1' -> r = r + "1"
			}
			
		}
		val l = mutableListOf<String>()
		l.add(r)
		var indexWithX = getIndexWithX(l)
		while (indexWithX != null) {
			val str = l.removeAt(indexWithX)

			val index = str.indexOf('X')

			l.add(str.replaceRange(index, index + 1, "0"))
			l.add(str.replaceRange(index, index + 1, "1"))

			indexWithX = getIndexWithX(l)
		}
		return l

	}

	private fun getIndexWithX(l :List<String>): Int? {
		l.forEachIndexed { i, v ->
			if (v.contains("X")) {
				return i
			}
		}
		return null
	}
}

/* ------------ */

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