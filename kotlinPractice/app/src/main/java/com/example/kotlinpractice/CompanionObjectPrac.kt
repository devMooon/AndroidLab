package com.example.kotlinpractice

//private constructor: 다른 곳에서는 객체를 생성할 수 없도록 설정
class Book private constructor (val id : Int, val name : String) {
    //private constructor 클래스의 메소드를 읽어올 수 있게 함함
   companion object BookFactory : IdProvider {
        override fun getId(): Int {
            return 444
        }
        val myBook = "new Book"
        fun create() = Book(0, myBook)
    }
}
interface IdProvider {
    fun getId() : Int
}
fun main(){
    val book : Book = Book.create()
    val bookId = Book.BookFactory.getId()
    println("${book.id} ${book.name}")
}