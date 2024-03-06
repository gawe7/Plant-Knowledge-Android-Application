package com.example.eartheden

import android.media.Image

class NotiModel { var title: String? = null
var img: String? = null
var key:String?= null
var detail:String?= null
    var desc:String?=null
constructor()
constructor(title:String?,img:String?,key:String?,detail:String?){
    this.title = title
    this.img = img
    this.key = key
    this.detail = detail
    this.desc = desc
}

fun toMap(): Map<String, Any> {
    val result = HashMap<String, Any>()
    result.put("title", title!!)
    result.put("img", img!!) // เปลี่ยนชื่อ attribute เป็น "img" ตามคลาส PlantModel
    result.put("key", key!!)
    result.put("detail", detail!!)
    result.put("desc", desc!!)
    return result
}
}