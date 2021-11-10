package com.example.kotlin

//
// 默认状态类的声明是 public final class ObjectDemo，也就是不能被继承，
// 在class前面加关键字open，即可以被继承
open class ObjectDemo(id : Int) { // 主构造方法

    // 次构造方法，但是必须调用主构造方法
    constructor() : this(787)

    constructor(id : Int , name : String) : this (id)

}