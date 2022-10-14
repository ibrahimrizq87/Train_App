package com.bemo.train.Classes

class Message {
    var text:String? = null
    var time :String? = null
    var number :String? = null
    var currentStation :String? = null

    constructor()
    constructor(text:String?,time :String?,number :String?,currentStation :String?){
        this.text=text
        this.time=time
        this.number=number
        this.currentStation=currentStation


    }
}