#!/usr/bin/env kotlin

@file:Import("../../util.kts")
@file:Import("../../parseutil.kts")

/* 
 * 
 */

data class Node (
	val name: String,
	val parent: Node? = null,
	val children: MutableSet<String> = mutableSetOf()
)

class Solution(val inputStrings: List<String>) {
	val simpleSizeMap = HashMap<String, Long>()
	val nodeMap = HashMap<String, Node>()

	fun solution(): Long {
		val head = Node("/")
		nodeMap[head.name] = head
		var curr = head

		inputStrings.forEach {
			if (it == "$ cd /") {
			} else if (it.startsWith("$ ls")) {
				
			} else if (it == "$ cd ..") {
				curr = curr.parent!!
			} else if (it.startsWith("$ cd")) {
				curr = nodeMap[curr.name + "/" + it.split(" ")[2]]!!
			} else if (it.startsWith("dir")) {
				val name = curr.name + "/" + it.split(" ")[1]
				var n = nodeMap[name]
				if (n == null) {
					n = Node(name, parent = curr)
					nodeMap[name] = n
				}
				curr.children.add(name)
			} else {
				val size = simpleSizeMap[curr.name] ?: 0L
				simpleSizeMap[curr.name] = size + it.split(" ")[0].toLong()
			}
		}
		calculateTrueSize(head)

		return simpleSizeMap.values.filter {
			it <= 100000
		}.sum()
	}

	fun calculateTrueSize(n: Node): Long {
		var size = simpleSizeMap[n.name] ?: 0L
		n.children.forEach {
			size += calculateTrueSize(nodeMap[it]!!)
		}
		simpleSizeMap[n.name] = size
		return size
	}
}

/* ------------ */

println(Solution(getStringPerLine(args)).solution())
