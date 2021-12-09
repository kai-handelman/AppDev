package com.example.project2milestone3

class TypeMatchUp {
    val matchUp = arrayOf(
        arrayOf(0,1568,32960),
        arrayOf(16384,258,49),
        arrayOf(0,131656,2068),

        arrayOf(0,65576,1024),
        arrayOf(4,35,73728),
        arrayOf(0,32770,16528),

        arrayOf(0,68176,164864),
        arrayOf(1024,545,34824),
        arrayOf(4128,8193,258),

        arrayOf(0,132616,10433),
        arrayOf(16,40960,133632),
        arrayOf(0,2048,98400),

        arrayOf(256,0,32),
        arrayOf(0,8753,17408),
        arrayOf(0,16416,259),

        arrayOf(0,12480,198176),
        arrayOf(8192,121492,1120),
        arrayOf(0,198720,520)
    )


    fun getTypeChart(monType:Int, moveType:Int):Float{
        if(moveType == 0){
            return 1f
        }else if(isNotEffective(monType, moveType)){
            return 0f
        }else if(isResisted(monType, moveType)) {
            return 0.5f
        }else if(isSuperEffective(monType, moveType)) {
            return 2f
        }
        return 1f
    }

    fun isNotEffective(monType:Int, moveType:Int):Boolean{
        val result: Int = (matchUp[monType-1][0] and (1 shl (moveType-1)))
        if (result == 0){
            return false
        }

        return true
    }

    fun isResisted(monType:Int, moveType:Int):Boolean{
        val result: Int = (matchUp[monType-1][1] and (1 shl (moveType-1)))
        if (result == 0){
            return false
        }
        return true
    }
    fun isSuperEffective(monType:Int, moveType:Int):Boolean{
        val result: Int = (matchUp[monType-1][2] and (1 shl (moveType-1)))
        if (result == 0){
            return false
        }
        return true
    }
}