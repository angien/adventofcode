import java.io.File
import java.util.Scanner

/* 
 * Sum of the smallest and largest numbers in the range that adds up to target
 */

fun solution(inputStrings: List<String>): Long {
	val target = 105950735

	for (i in 0 until inputStrings.size) {
		var currSum = 0.toLong()
		for (j in i until inputStrings.size) {
			val num = inputStrings[j].toLong()
			currSum = currSum + num
			if (currSum == target.toLong()) {
				val range = mutableListOf<Long>()
				inputStrings.subList(i, j).forEach {
					range.add(it.toLong())
				}
				range.sort()
				return range[0] + range[range.size - 1]
			}
		}
	}
	
	return 0
}

fun hasSum(target: Int, window: List<String>): Boolean {

        val map = mutableMapOf<Int, Int>()
        window.forEachIndexed { index, value ->
        	val num = value.toInt()
            val diff = target - num
            map.get(diff)?.let {
                return true
            }
            map.put(num,index)
        }
        return false
    
}

fun populateInputStrings(): List<String> {
	val inputScanner = Scanner(getInputFile())
	val inputStrings = mutableListOf<String>()

	while (inputScanner.hasNextLine()) {
		inputStrings.add(inputScanner.nextLine())
	}
	if (inputStrings.isEmpty()) {
		throw RuntimeException("No input data")
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

println(solution(populateInputStrings()))