import java.io.File
import java.util.Scanner

/* 
 * imagine in 4d
 */

 data class Quadruple(
 	val w: Int,
 	val x: Int,
 	val y: Int,
 	val z: Int
 )

class Solution(val inputStrings: List<String>) {

	val map = mutableMapOf<Quadruple, String>()
	var omap = mutableMapOf<Quadruple, String>()

	fun solution(): Int {

		inputStrings.forEachIndexed { y, s ->
			s.forEachIndexed { x, c ->
				map.put(Quadruple(0, x, y, 0), c.toString())
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

	private fun flip(coords: Quadruple) {
		val w = coords.w
		val x = coords.x
		val y = coords.y
		val z = coords.z

		for (currW in w-1 until w+2) {
			for (currZ in z-1 until z+2) {
				for (currY in y-1 until y+2) {
					for (currX in x-1 until x+2) {
						val toCheck = Quadruple(currW, currX, currY, currZ)
						when (omap[toCheck] ?: ".") {
							"#" -> flipForActive(toCheck)
							"." -> flipForInactive(toCheck)
						}
					}
				}
			}
		}
	}

	private fun flipForActive(coords: Quadruple) {
		val actives = countActives(coords)
		if (actives == 2 || actives == 3 ) {
			map.put(coords, "#")
		} else {
			map.put(coords, ".")
		}
	}

	private fun flipForInactive(coords: Quadruple) {
		val actives = countActives(coords)
		if (actives == 3 ) {
			map.put(coords, "#")
		} else {
			map.put(coords, ".")
		}
	}

	private fun countActives(coords: Quadruple): Int {
		val w = coords.w
		val x = coords.x
		val y = coords.y
		val z = coords.z

		var count = 0

		for (currW in w-1 until w+2) {
			for (currZ in z-1 until z+2) {
				for (currY in y-1 until y+2) {
					for (currX in x-1 until x+2) {
						val curr = Quadruple(currW, currX, currY, currZ)
						if (curr != coords) {
							if (omap[curr] == "#") {
								count = count + 1
							}
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