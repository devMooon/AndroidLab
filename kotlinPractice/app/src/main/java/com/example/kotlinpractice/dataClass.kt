package com.example.kotlinpractice

data class Ticket(val companyName : String, val name : String, var date : String, var setNum : Int)
//컴파일 하면 자동으로 이 메소드가 만들어짐
//toString(), hashCode(), equals(), copy()

class TicketNomal(val companyName : String, val name : String, var date : String, var setNum : Int)

fun main(){
    val ticketA = Ticket("koreanAir", "seoyeon", "2022-02-15", 14)
    val ticketB = TicketNomal("koreanAir", "seoyeon", "2022-02-15", 14)

    println(ticketA)
    println(ticketB)
}