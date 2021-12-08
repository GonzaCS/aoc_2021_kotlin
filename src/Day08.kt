fun main() {
    fun part1(input: List<String>): Int {
        val last = input.map { it.split(" | ").last() }
        val words = last.flatMap { it.split(" ") }
        return words.count { it.length in listOf(2, 3, 4, 7) }
    }

    //   0000
    //  1    2
    //  1    2
    //   3333
    //  4    5
    //  4    5
    //   6666

    val segmentsToDigits = mapOf(
        setOf(0, 1, 2, 4, 5, 6) to 0,
        setOf(2, 5) to 1,
        setOf(0, 2, 3, 4, 6) to 2,
        setOf(0, 2, 3, 5, 6) to 3,
        setOf(1, 2, 3, 5) to 4,
        setOf(0, 1, 3, 5, 6) to 5,
        setOf(0, 1, 3, 4, 5, 6) to 6,
        setOf(0, 2, 5) to 7,
        setOf(0, 1, 2, 3, 4, 5, 6) to 8,
        setOf(0, 1, 2, 3, 5, 6) to 9
    )

    fun randomConf(words: List<String>, expectedNumbers: List<String>): Int {
        val inputCables = 0..6
        val inputChars = 'a'..'g'
        fun getMapping(): Map<Char,Int>{
            permute@ while(true){
                val perm = inputChars.zip(inputCables.shuffled()).toMap()
                for (word in words) {
                    val mapped = word.map { perm[it]!! }.toSet()
                    val isValidDigit = segmentsToDigits.containsKey(mapped)
                    if (!isValidDigit) continue@permute
                }
                return perm
            }
        }
        val mapping = getMapping()
        val num = expectedNumbers.joinToString("") { digit ->
            val segments = digit.map { mapping[it]!! }.toSet()
            val dig = segmentsToDigits[segments]!!
            "$dig"
        }
        return num.toInt()
    }

    fun part2(inputFromFile: List<String>): Int {
        val lists = inputFromFile.map { val (input, output) = it.split(" | "); input.split(" ") to output.split(" ") }
        val result = lists.sumOf { (input, output) ->
            randomConf(input, output)
        }
        return result
    }






    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    val input = readInput("Day08")
    println(part1(input))

    check(part2(testInput) == 61229)
    println(part2(input))


}