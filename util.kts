package util

val lowercase = "abcdefghijklmnopqrstuvwxyz"
val uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

//println(sumStrings(listOf("2", "3"))) // 5
fun sumStrings(s: List<String>): Int {
	return s.map { it.toInt() }.reduce { acc, it -> acc + it.toInt() }
}

// println(listOf("0", "1").toIntList()) // [0, 1]
// println(listOf<String>().toIntList()) // []
//println(listOf("").toIntList()) // []
fun List<String>.toIntList(): List<Int> {
	return if (this == listOf("")) listOf() else map { it.toInt() }
}

//println(findAllCommon(listOf(1,2), listOf(2,3))) // 2
fun <T> findAllCommon(vararg x: List<T>): Set<T> {
	var result = x[0].toSet()
	x.forEach {
		result = result.intersect(it)
	}
	return result
}

// for (i in 3 toward 0) { println(i) }
// for (i in 0 toward 3) { println(i) }
infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}

fun gcd(x: Long, y: Long): Long {
	if (x == 0L) {
		return y
	}
	return gcd(y.mod(x), x)
}

// val list = mutableListOf(1,2,3,4,5,6,7,8)
// list.removeSlice(2, 4)
// println(list) // [1,2,6,7,8]
fun <T> MutableList<T>.removeSlice(startInclusive: Int, endInclusive: Int) {
    for (i in endInclusive downTo startInclusive) {
        removeAt(i)
    }
}

// val list = mutableListOf(1,2,3,4,5,6,7,8)
// list.removeLast(4)
// println(list) // [1,2,3,4]
// list.removeLast(3)
// println(list) // [1]
fun <T> MutableList<T>.removeLast(count: Int) {
    for (i in size - 1 downTo size - count) {
        removeAt(i)
    }
}

// val graph = listOf(
// 	listOf("0,0", "0,1", "0,2"), 
// 	listOf("1,0", "1,1", "1,2"), 
// 	listOf("2,0", "2,1", "2,2"))
// println(graph.getSurroundingPoints(0, 0)) // [null, null, null, null, 0,0, 0,1, null, 1,0, 1,1]
// println(graph.getSurroundingPoints(1, 1)) // [0,0, 0,1, 0,2, 1,0, 1,1, 1,2, 2,0, 2,1, 2,2]
fun <T> List<List<T>>.getSurroundingPoints(x: Int, y: Int): List<T?> {
	val result = mutableListOf<T?>()
	for (x1 in x-1 .. x+1) {
		for (y1 in y-1 .. y+1) {
			result += if (x1 < 0 || y1 < 0) { null } else { this[x1][y1] }
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
		(Math.abs(delta) % (adjustedEnd + 1)) * if (delta < 0) { -1 } else { 1 }
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

