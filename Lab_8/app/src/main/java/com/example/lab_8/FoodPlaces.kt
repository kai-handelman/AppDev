package com.example.lab_8

data class FoodPlaces (var name:String = "", var main:String = "",var url:String =""){
    fun getFood(place:Int){
        placeName(place)
        placeFood(place)
        placeUrl(place)
    }

    private fun placeName(p:Int){
        when(p){
            0 -> name = "McDonald's"
            1 -> name = "Wendy's"
            2 -> name = "Taco Bell"
            else -> name = "Home"
        }
    }

    private fun placeFood(p:Int){
        when(p){
            0 -> main = "Big Mac"
            1 -> main = "Baconator"
            2 -> main = "Crunchwrap Supreme"
            else -> main = "Leftovers"
        }
    }

    private fun placeUrl(p:Int){
        when(p){
            0 -> url = "https://www.mcdonalds.com/us/en-us.html"
            1 -> url = "https://www.wendys.com/"
            2 -> url = "https://www.tacobell.com/"
            else -> url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        }
    }
}

