package com.example.kotlinpractice

fun invokeLamda(lamda : (Double) -> Boolean) : Boolean {
    return lamda(5.2343)
}
fun main() {
    val lamda = {number : Double ->
        number == 4.1234
    }
    println(invokeLamda(lamda)) //1번 방식
    println(invokeLamda({it > 3.23})) //2번 방식 (람다 리터럴)
    println(invokeLamda{it > 3.23}) //3번 방식 function의 마지막 파라미터가 람다식일 때는 생략 가능
}