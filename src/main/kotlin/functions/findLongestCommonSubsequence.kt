package top.xuansu.mirai.exchangeRateHelper.functions

fun longestCommonSubsequenceInOrder(a: String, b: String): Int {
    var indexA = 0
    var indexB = 0
    var commonLength = 0

    while (indexA < a.length && indexB < b.length) {
        if (a[indexA] == b[indexB]) {
            commonLength++
        }
        indexA++
        indexB++
    }

    return commonLength
}

fun findKeyWithLongestCommonSubsequencePercentage(a: String, map: Map<String, List<String>>): Pair<String?, Double> {
    var maxCommonLength = 0.0
    var resultKey: String? = null

    for ((key, values) in map) {
        for (value in values) {
            val commonLength = longestCommonSubsequenceInOrder(a, value)
            val b = if (a.length > value.length) {
                a.length
            } else {
                value.length
            }
            val percentage = commonLength.toDouble() / b * 100

            if (percentage > maxCommonLength) {
                maxCommonLength = percentage
                resultKey = key
            }
        }
    }

    return Pair(resultKey, maxCommonLength)
}