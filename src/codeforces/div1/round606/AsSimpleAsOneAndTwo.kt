package codeforces.exercises

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * http://codeforces.com/problemset/problem/1276/A
 */
val twone = listOf('t', 'w', 'o', 'n', 'e')
val one = listOf('o', 'n', 'e')
val two = listOf('t', 'w', 'o')

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()

    for (i in 0 until t){
        val s = br.readLine().toList()
        val indices = getIndices(s)
        println(indices.size)
        var ans = ""
        for (i in indices){
            ans += "$i "
        }
        ans.trim()
        println(ans)
    }
}

fun getIndices (s: List<Char>): Set<Int>{
    tailrec fun go (i: Int, s: List<Char>, indices: Set<Int>): Set<Int> =
        if (s.isEmpty()) indices
        else {
            val first3 = take(3, s)
            val first5 = take(5, s)
            if (indices.contains(i)){
                go(i + 1, s.drop(1), indices)
            }else{
                if (first5 == twone){
                    go(
                        i + 1,
                        s.drop(1),
                        indices + (i + 2)
                    )
                }else{
                    go (
                        i + 1,
                        s.drop(1),
                        if (first3 == one || first3 == two) indices + (i + 1) else indices
                    )
                }
            }
        }
    return go(1, s, emptySet())
}

fun take (n: Int, s: List<Char>): List<Char>{
    tailrec fun go (i: Int, sub: List<Char>, s: List<Char>): List<Char> =
        if (i == 0 || s.isEmpty()) sub
        else go (i - 1, sub + s.first(), s.drop(1))
    return if (n > s.size) s else go(n, emptyList(), s)
}

