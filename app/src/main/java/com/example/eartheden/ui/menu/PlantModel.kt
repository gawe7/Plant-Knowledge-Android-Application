package com.example.eartheden.ui.menu

class PlantModel (var title: String?, var img: String?) {
    // Default constructor
    constructor() : this(null, null)

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("Image", img!!)

        return result
    }
}