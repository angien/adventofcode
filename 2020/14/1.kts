import java.io.File
import java.util.Scanner

/* 
 * binary addition
 */

class Solution(val inputStrings: List<String>) {


	fun solution(): Long {
		var mask = ""

		val map = mutableMapOf<Int, String>()
		inputStrings.forEach {
			if (it.contains("mask")) {
				mask = it.split(" = ")[1]
			} else {
				val split = it.split(" = ")
				val mem = split[0].replace("mem[", "").replace("]", "").toInt()
				val value = Integer.toBinaryString(split[1].toInt())

				map.put(mem, applyMask(mask, value.padStart(mask.length, '0')))
			}
		}
	
		var sum = 0L
		map.forEach {
			sum = sum + it.value.toLong(2)
		}

		return sum
	}

	private fun applyMask(mask: String, value: String): String {
		var r = ""
		for (i in 0 until mask.length) {
			when (mask.get(i)) {
				'X' -> r = r + value.get(i)
				'0' -> r = r + "0"
				'1' -> r = r + "1"
			}
			
		}
		return r

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