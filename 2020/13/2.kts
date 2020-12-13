import java.io.File
import java.util.Scanner

/* 
 * chinese remainder theorem
 */

class Solution(val inputStrings: List<String>) {

	val time = inputStrings[0].toInt()
	val busses = busSchedule()

	fun busSchedule(): Map<Long,Long> {
		val b = mutableMapOf<Long,Long>()
		inputStrings[1].split(',').forEachIndexed { i, bus ->
			if (bus != "x") {
				b.put(bus.toLong(), (-i).toLong()) // bus to time from timestamp
			}

		}
		return b
	}

	fun solution(): Long {
		println(busses.keys)
		println(busses.values)

		return chineseRemainder(busses.keys.toList(), busses.values.toList())
	}
 
	fun chineseRemainder(mod: List<Long>, remainders: List<Long>): Long {
	    val prod = mod.fold(1L) { acc, i -> acc * i }
	    var sum = 0L
	    for (i in 0 until mod.size) {
	        val p = prod / mod[i]
	        sum += remainders[i] * multInv(p, mod[i]) * p
	    }
	    return Math.floorMod(sum, prod)
	}

	fun multInv(a: Long, b: Long): Long {
	    if (b == 1L) return 1L
	    var aa = a
	    var bb = b
	    var x0 = 0L
	    var x1 = 1L
	    while (aa > 1L) {
	        val q = aa / bb
	        var t = bb
	        bb = Math.floorMod(aa, bb)
	        aa = t
	        t = x0
	        x0 = x1 - q * x0
	        x1 = t
	    }
	    if (x1 < 0L) x1 += b
	    return x1
	} 
	 
	// fun main(args: Array<String>) {
	//     val mod = intArrayOf(3, 5, 7)
	//     val remainders = intArrayOf(2, 3, 2)
	//     println(chineseRemainder(mod, remainders))
	// }
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