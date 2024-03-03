package com.example.eartheden

import android.media.Image

class NotiModel(var title: String?, var img: String?) {
    // Default constructor
    constructor() : this(null, null)

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("img", img!!) // เปลี่ยนชื่อ attribute เป็น "img" ตามคลาส PlantModel
        return result
    }
}