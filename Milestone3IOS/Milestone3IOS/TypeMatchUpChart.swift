//
//  TypeMatchUpChart.swift
//  Milestone3IOS
//
//  Created by Kai on 10/8/21.
//

import Foundation

class TypeChart{
//    Incorrect Need to Fix
    var matchUp: [[Int]] = [[0,1568,32960],
                               [16384,258,49],
                               [0,131656,2068],
                               
                               [0,65576,1024],
                               [4,35,73728],
                               [0,32770,16528],
                               
                               [0,68176,164864],
                               [1024,545,34824],
                               
                               [4128,8193,258],
                               [0,132616,10433],
                               [16,40960,133632],
                               
                               [0,2048,98400],
                               [256,0,32],
                               [0,8753,17408],
                               
                               [0,16416,259],
                               [0,12480,198176],
                               [8192,121492,1120],
                               [0,198720,520]]
    
    func getTypeChart(_ monType:Int,_ moveType:Int) -> Float{
        if(moveType == 0){
            return 1
        }
        if(isNotEffective(monType,moveType)){
            return 0
        }
        if(isResisted(monType,moveType)){
            return 0.5
        }
        if(isSuperEffective(monType,moveType)){
            return 2
        }
        return 1
    }
    init() {
        
    }
    
    private func isNotEffective(_ monType:Int,_ moveType:Int) -> Bool{
        if matchUp[monType-1][0] & (1 << (moveType-1)) == 0{
            return false
        }
        return true
    }
    
    private func isResisted(_ monType:Int,_ moveType:Int) -> Bool{
        if matchUp[monType-1][1] & (1 << (moveType-1)) == 0{
            return false
        }
        return true
    }
    private func isSuperEffective(_ monType:Int,_ moveType:Int) -> Bool{
        if matchUp[monType-1][2] & (1 << (moveType-1)) == 0{
            return false
        }
        return true
    }
    
}
