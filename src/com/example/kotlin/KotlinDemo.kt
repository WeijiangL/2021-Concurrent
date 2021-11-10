package com.example.kotlin

class KotlinDemo{

}

fun main() {
    val num1 = 999
    val num2 = 888
    val num3 = "abc"

    val max1  = if (num1 > num2) num1 else num2

    println(max1)

    val max2 : Int = if (num1 > num2) {
        println("num1最大")
        num1
    } else {
        println("num2最大")
        num2
    }

    switchMethod()
    markAndCircleMethod()
}

// switch语句
fun switchMethod() {


    // 情况1
    val num = 2
    when (num) {
        1 -> println("一")
        2 -> println("二")
        3 -> println("三")
    }

    // 情况二
    val num2 = 456
    when (num2) {
        in 1..100 -> println("一")
        in 100..300 -> println("二")
        in 300..500 -> println("三")
    }

    // 情况三
    val num3 = 3
    val result : Int = when (num3) {
        1 -> {
            println("一")
            11
        }
        2 -> {
            println("二")
            22
        }
        3 -> {
            println("三")
            33
        }
        else -> 11
    }
    println(result)
}

// 循环和标签
fun markAndCircleMethod () {
    // 标签
    A@for (i in 1..20) {
        for (j in 1..20) {
           println("i: $i , j: $j")
            if (i == 5) {
                break@A
            }
        }
    }

    // 循环
    // 1.
    val lists = listOf("一" , "二" , "三")
    for (list in lists) {
        println(list)
    }
    println()

    // 2.
    lists.forEach {
        println(it)
    }
    println()

    //3.
    val indices = lists.indices
    for (index in indices) {
        println("索引：$index , 对应的值：${lists[index]}")
    }

}