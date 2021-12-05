fun main() {
    fun part1(input: List<String>): Int {
        val gammaRate = StringBuilder()
        val epsilonRate = StringBuilder()
        for(i in input[0].indices){
            val zeros = input.count{ it[i] == '0'}
            val ones = input.size - zeros
            gammaRate.append(if (zeros > ones) '0' else '1')
            epsilonRate.append(if (zeros <= ones) '0' else '1')
        }
        return gammaRate.toString().toInt(2) * epsilonRate.toString().toInt(2)
    }

    fun part2(input: List<String>): Int {
        fun findOnly(mostFrequent:Boolean): Int{
            val copy = input.toMutableList()
            for(i in input[0].indices){
                val zeros = copy.count{ it[i] == '0'}
                val ones = copy.size - zeros
                val toKeep =
                    if (zeros <= ones) if (mostFrequent) '1' else '0'
                    else if (mostFrequent) '0' else '1'
                copy.removeIf { it[i] != toKeep }
                if(copy.size == 1) return copy.first().toInt(2)
            }
            error("didn't find the only element")
        }

        return findOnly(true) * findOnly(false)
    }


    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))

    check(part2(testInput) == 230)
    println(part2(input))
}
