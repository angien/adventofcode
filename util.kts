package util

val lowercase = "abcdefghijklmnopqrstuvwxyz"
val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

//println(sumStrings(listOf("2", "3"))) // 5
fun sumStrings(s: List<String>): Int {
	return s.map { it.toInt() }.reduce { acc, it -> acc + it.toInt() }
}

// println(listOf("0", "1").toIntList()) // [0, 1]
fun List<String>.toIntList(): List<Int> {
	return map { it.toInt() }
}

//println(findAllCommon(listOf(1,2), listOf(2,3))) // 2
fun <T> findAllCommon(vararg x: List<T>): Set<T> {
	var result = x[0].toSet()
	x.forEach {
		result = result.intersect(it)
	}
	return result
}

// val graph = listOf(
// 	listOf("0,0", "0,1", "0,2"), 
// 	listOf("1,0", "1,1", "1,2"), 
// 	listOf("2,0", "2,1", "2,2"))
// println(getSurroundingPoints(0, 0, graph))
// println(getSurroundingPoints(1, 1, graph))
fun <T> getSurroundingPoints(x: Int, y: Int, graph: List<List<T>>): List<T?> {
	val result = mutableListOf<T?>()
	for (x1 in x-1 .. x+1) {
		for (y1 in y-1 .. y+1) {
			result += if (x1 < 0 || y1 < 0) { null } else { graph[x1][y1] }
		}
	}
	return result
}

// println(wrapIncrement(-4, -8, -4, -1)) // -4
// println(wrapIncrement(-4, startInclusive = -4, endInclusive = -1)) // -3
// println(wrapIncrement(0, 2, -2, 1)) // -2
// println(wrapIncrement(0, 3, -2, 1)) // -1
// println(wrapIncrement(0, 2, endInclusive = 1)) // 0
// println(wrapIncrement(1, 3, endInclusive = 2)) // 1
// println(wrapIncrement(1, 4, endInclusive = 2)) // 2
// println(wrapIncrement(1, 6, 1, 4)) // 3
fun wrapIncrement(curr: Int, delta: Int = 1, startInclusive: Int = 0, endInclusive: Int): Int {
	// Adjust the start so it starts at 0, and end/current respectively
	val adjustment = Math.abs(startInclusive)
	val (adjustedEnd, adjustedCurr) = if (startInclusive < 0) {
		listOf(endInclusive + adjustment, curr + adjustment)
	} else {
		listOf(endInclusive - adjustment, curr - adjustment)
	}

	// Adjust the delta to only be less than the range
	val adjustedDelta = if (Math.abs(delta) > adjustedEnd + 1) {
		if (delta < 0) {
			(Math.abs(delta) % (adjustedEnd + 1)) * -1
		} else {
			Math.abs(delta) % (adjustedEnd + 1)
		}
	} else {
		delta
	}

	val adjustedResult = wrapIncrement(adjustedCurr, adjustedDelta, adjustedEnd)

	// Adjust result back
	val result = if (startInclusive < 0) {
		adjustedResult - adjustment
	} else {
		adjustedResult + adjustment
	}

	return result
}

// println(wrapIncrement(3, endInclusive = 3)) // 0
fun wrapIncrement(curr: Int, delta: Int = 1, endInclusive: Int): Int {
	return ((curr + delta) + (endInclusive + 1)) % (endInclusive + 1)
} 

