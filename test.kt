package com.example.hello

open class Human (name:String="[No name]"){

    //부생성자의 의미 : 선택적 paramater
    //constuctor : 선택적 paramater에 대한 statment
    //this : 주생성자로부터 위임 받도록 하는 기능
    constructor(name: String, age: Int): this(name){
        println("name : ${name}, age : ${age}")
    }

    //init : 무조건 최초로 적용되는 statement
    init{
        println("${name} is born")
    }

    var name:String=name
    var energy=0
    var sleep_time=8/*hour*/

    open fun eat(food:String):String?{
        if(energy>=100)
            return "full"
        else{
            when(food) {
                "noodle" -> energy += 10
                "pizza" -> energy += 40
                "chicken" -> energy += 50
            }
            energy = if(energy>=100) 100 else energy
        }

        println("I'm eating ${food} energy ${energy}")

        sleep_time-=2
        return null
    }

    fun run(time:Int):String?{
        /*hour*/
        energy-=time*50
        if(energy<=0){
            energy=0
        }

        sleep_time-=time

        if(energy<=0)
            return "exhusted"
        else if(sleep_time<=0)
            return "sleepy"
        else
            return null
    }

    fun sleep(time:Int):String?{
        if(sleep_time<=8) {
            sleep_time += time
            return null
        }
        else
            return "not sleepy"
    }
}

class Korean(name:String):Human(name){
    override fun eat(food:String):String?{
        if(energy>=100)
            return "full"
        else{
            when(food) {
                "noodle" -> energy += 10
                "pizza" -> energy += 40
                "chicken" -> energy += 50
                "rice" -> energy += 30 //추가
                "kimchi" -> energy += 10 //추가
            }
            energy = if(energy>=100) 100 else energy
        }

        sleep_time-=2
        return null
        //?
    }
}

fun main(){
    val koreans = arrayListOf<Korean>(Korean("철수"),
        Korean("영희"),Korean("민수"))
    var day=0
    var step=0

    for(i in 0 until koreans.size) {
        while (day < 7) {
            println("day : ${day}")
            when (step) {
                0 -> koreans[i].eat("noodle")?.let {
                    println("${koreans[i].name} is ${it}")
                    step++
                }
                1 -> koreans[i].run(1)?.let {
                    println("${koreans[i].name} is ${it}")
                    step++
                }
                2 -> koreans[i].sleep(2)?.let {
                    println("${koreans[i].name} is ${it}")
                    day++
                    step = 0
                }
            }
        }
    }
}
