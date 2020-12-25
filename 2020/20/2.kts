import java.io.File
import java.util.Scanner

/* 
 * 0, 0 is top left, put the picture together to find sea monsters
 */

data class Tile(
	val value: List<String>
) {
	fun t() = value[0]
	fun b() = value[value.size - 1]
	fun l(): String {
		var toReturn = ""
		value.forEach {
			toReturn = toReturn + it[0]
		}
		return toReturn
	}
	fun r(): String {
		var toReturn = ""
		value.forEach {
			toReturn = toReturn + it[value.size - 1]
		}
		return toReturn
	}
}

class Solution(val inputStrings: List<List<String>>) {

	val tiles = mutableMapOf<String, List<Tile>>()
	val picture = mutableMapOf<Pair<Int, Int>, Tile>()

	fun solution(): Int {
		inputStrings.forEach {
			val tileValue = mutableListOf<String>()
			it.forEach {
				if (it.contains("Tile").not()) {
					tileValue.add(it)
				}
			}
			val tileKey = it[0].replace("Tile ", "").replace(":","")

			val tileConfigs = mutableListOf<Tile>()
			allConfigurations(tileValue).forEach {
				tileConfigs.add(Tile(it))
			}
			tiles.put(tileKey, tileConfigs)
		}

		val corner = "1283" // use this for real answer
		//val corner = "1951" // use this for example

		picture.put(Pair(0, 0), tiles[corner]!![0])
		tiles.remove(corner)
		while (tiles.isNotEmpty()) {
			val copy = picture.toMutableMap()
			copy.forEach {
				findNeighbors(it.key, it.value)
			}
		}

		// calculate offset
		var xmin = 0
		var ymin = 0
		var xmax = 0
		var ymax = 0
		picture.keys.forEach {
			xmin = Math.min(xmin, it.first)
			xmax = Math.max(xmax, it.first)

			ymin = Math.min(ymin, it.second)
			ymax = Math.max(ymax, it.second)
		}

		// flatten picture
		var flattened = listOf<String>()
		for (i in ymin..ymax) {
			val rowOfStrippedBlocks = mutableListOf<List<String>>()
			for (j in xmin..xmax) {
				val tile = picture[Pair(j, i)]!!
				rowOfStrippedBlocks.add(stripBorder(tile.value))
			}
			flattened = flattened + flattenToStrings(rowOfStrippedBlocks)
		}

		// check all configs for sea monsters
		allConfigurations(flattened).forEach { config ->
			val allPounds = config.fold(0) {
				sum, element -> sum + element.filter { it == '#' }.count()
			}
			val seaMonsters = countSeaMonsters(config)
			if (seaMonsters != 0) {
				return allPounds - (seaMonsters * 15)
			}
		}
		
		return 0
	}

	fun stripBorder(o: List<String>): List<String> {
		val toReturn = mutableListOf<String>()

		for (i in 1 until o.size - 1) {
			toReturn.add(o[i].substring(1, o[i].length - 1))
		}

		return toReturn
	}

	fun countSeaMonsters(flattened: List<String>): Int {
		var count = 0

		for (y in 0 until flattened.size) {
			for (x in 0 until flattened[0].length) {
				val a = flattened.getOrNull(y)?.getOrNull(x) ?: '.' == '#'
				val b = flattened.getOrNull(y+1)?.getOrNull(x+1) ?: '.' == '#'

				val c = flattened.getOrNull(y+1)?.getOrNull(x+4) ?: '.' == '#'
				val d = flattened.getOrNull(y)?.getOrNull(x+5) ?: '.' == '#'
				val e = flattened.getOrNull(y)?.getOrNull(x+6) ?: '.' == '#'
				val f = flattened.getOrNull(y+1)?.getOrNull(x+7) ?: '.' == '#'

				val g = flattened.getOrNull(y+1)?.getOrNull(x+10) ?: '.' == '#'
				val h = flattened.getOrNull(y)?.getOrNull(x+11) ?: '.' == '#'
				val i = flattened.getOrNull(y)?.getOrNull(x+12) ?: '.' == '#'
				val j = flattened.getOrNull(y+1)?.getOrNull(x+13) ?: '.' == '#'

				val k = flattened.getOrNull(y+1)?.getOrNull(x+16) ?: '.' == '#'
				val l = flattened.getOrNull(y)?.getOrNull(x+17) ?: '.' == '#'
				val m = flattened.getOrNull(y)?.getOrNull(x+18) ?: '.' == '#'
				val n = flattened.getOrNull(y)?.getOrNull(x+19) ?: '.' == '#'
				val o = flattened.getOrNull(y-1)?.getOrNull(x+18) ?: '.' == '#'

				if (a && b && c && d && e && f && g && h && i && j && k && l && m && n && o) {
					count = count + 1
				}
			}
		}

		return count
	}

	fun flattenToStrings(blocks: List<List<String>>): List<String> {
		val toReturn = mutableListOf<String>()
		for (i in 0 until blocks[0].size) {
			var combinedString = ""
			for (j in 0 until blocks.size) {
				combinedString = combinedString + blocks[j][i]
			}
			toReturn.add(combinedString)	
		}
		return toReturn
	}

	fun allConfigurations(value: List<String>): List<List<String>> {
		val tile = value
		val flipDown = value.reversed()
		val flipSide = mutableListOf<String>()
		value.forEach {
			flipSide.add(it.reversed())
		}
		val flipSideDown = flipSide.reversed()

		val simple = listOf(tile, flipDown, flipSide, flipSideDown)

		val singleLeft = simple.fold(listOf<List<String>>()) {
			acc, it -> acc + listOf(rotateLeft(it))
		}
		val doubleLeft = singleLeft.fold(listOf<List<String>>()) {
			acc, it -> acc + listOf(rotateLeft(it))
		}
		val tripleLeft = doubleLeft.fold(listOf<List<String>>()) {
			acc, it -> acc + listOf(rotateLeft(it))
		}
		val singleRight = simple.fold(listOf<List<String>>()) {
			acc, it -> acc + listOf(rotateRight(it))
		}
		val doubleRight = singleRight.fold(listOf<List<String>>()) {
			acc, it -> acc + listOf(rotateRight(it))
		}
		val tripleRight = doubleRight.fold(listOf<List<String>>()) {
			acc, it -> acc + listOf(rotateRight(it))
		}

		return simple + singleLeft + doubleLeft + tripleLeft + singleRight + doubleRight + tripleRight
	}

	fun rotateLeft(o: List<String>): List<String> {
		val left = MutableList<String>(o.size) { "" }

		for (i in 0 until o.size) {
			for (j in 0 until o[i].length) {
				left[j] = left[j] + o[i].reversed().get(j).toString()
			}
		}

		return left
	}

	fun rotateRight(o: List<String>): List<String> {
		val right = MutableList<String>(o.size) { "" }
		val reversed = o.reversed()

		for (i in 0 until o.size) {
			for (j in 0 until o[i].length) {
				right[j] = right[j] + reversed[i].get(j).toString()
			}
		}

		return right
	}

	fun findNeighbors(coords: Pair<Int,Int>, t: Tile) {
		val x = coords.first
		val y = coords.second
		if (picture[Pair(x, y-1)] == null) {
			var toRemove = ""
			tiles.forEach { key, set ->
				set.forEach {
					if (t.t() == it.b()) {
						picture[Pair(x, y-1)] = it
						toRemove = key
					}
				}
			}
			tiles.remove(toRemove)
		}
		if (picture[Pair(x, y+1)] == null) {
			var toRemove = ""
			tiles.forEach { key, set ->
				set.forEach {
					if (t.b() == it.t()) {
						picture[Pair(x, y+1)] = it
						toRemove = key
					}
				}
			}
			tiles.remove(toRemove)
		}
		if (picture[Pair(x-1, y)] == null) {
			var toRemove = ""
			tiles.forEach { key, set ->
				set.forEach {
					if (t.l() == it.r()) {
						picture[Pair(x-1, y)] = it
						toRemove = key
					}
				}
			}
			tiles.remove(toRemove)
		}
		if (picture[Pair(x+1, y)] == null) {
			var toRemove = ""
			tiles.forEach { key, set ->
				set.forEach {
					if (t.r() == it.l()) {
						picture[Pair(x+1, y)] = it
						toRemove = key
					}
				}
			}
			tiles.remove(toRemove)
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

println(Solution(populateInputStringsAndBreaks()).solution())