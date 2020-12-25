import java.io.File
import java.util.Scanner

/* 
 * 3d active switcher
 */

class Solution(val inputStrings: List<String>) {

	val map = mutableMapOf<Triple<Int, Int, Int>, String>()
	var omap = mutableMapOf<Triple<Int, Int, Int>, String>()

	fun solution(): Int {

		inputStrings.forEachIndexed { y, s ->
			s.forEachIndexed { x, c ->
				map.put(Triple(x, y, 0), c.toString())
			}
		}

		var cycle = 0
		while (cycle < 6) {
			omap.clear()
			map.forEach {
				omap.put(it.key, it.value)
			}

			omap.filter { it.value == "#" }.forEach {
				flip(it.key)
			}

			cycle = cycle + 1
		}
		
		return map.values.filter { it == "#" }.count()
	}

	private fun flip(triple: Triple<Int, Int, Int>) {
		val x = triple.first
		val y = triple.second
		val z = triple.third

		for (currZ in z-1 until z+2) {
			for (currY in y-1 until y+2) {
				for (currX in x-1 until x+2) {
					val toCheck = Triple(currX, currY, currZ)
					when (omap[toCheck] ?: ".") {
						"#" -> flipForActive(toCheck)
						"." -> flipForInactive(toCheck)
					}
				}
			}
		}
	}

	private fun flipForActive(triple: Triple<Int, Int, Int>) {
		val actives = countActives(triple)
		if (actives == 2 || actives == 3 ) {
			map.put(triple, "#")
		} else {
			map.put(triple, ".")
		}
	}

	private fun flipForInactive(triple: Triple<Int, Int, Int>) {
		val actives = countActives(triple)
		if (actives == 3 ) {
			map.put(triple, "#")
		} else {
			map.put(triple, ".")
		}
	}

	private fun countActives(triple: Triple<Int, Int, Int>): Int {
		val x = triple.first
		val y = triple.second
		val z = triple.third

		var count = 0
		for (currZ in z-1 until z+2) {
			for (currY in y-1 until y+2) {
				for (currX in x-1 until x+2) {
					val currTriple = Triple(currX, currY, currZ)
					if (currTriple != triple) {
						if (omap[currTriple] == "#") {
							count = count + 1
						}
					}
				}
			}
		}
		return count
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