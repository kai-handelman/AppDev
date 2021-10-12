//
//  TypeMatchUpChart.swift
//  Milestone3IOS
//
//  Created by Kai on 10/8/21.
//

import Foundation

class TypeChart{
    var matchUp: [[Int]] = [[0,4480,3076], [8,66048,143360],[0,18689,40960],
                            [0,17410,128],[32768,200704,18],[0,196612,9224],
                            [0,141634,133],[128,135424,16452],[4128,131088,66048],
                            [0,16769,134224],[16384,20,321],[0,64,6150],
                            [512,0,4096],[0,143632,136],[0,4104,197120],
                            [0,3120,4355],[16,173422,6272],[0,2115,16640]]
    
    func getTypeChart(_ monType:Int,_ moveType:Int) -> Float{
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
        if matchUp[monType-1][0] & (1 << (17 - moveType)) == 0{
            return false
        }
        return true
    }
    
    private func isResisted(_ monType:Int,_ moveType:Int) -> Bool{
        if matchUp[monType-1][1] & (1 << (17 - moveType)) == 0{
            return false
        }
        return true
    }
    private func isSuperEffective(_ monType:Int,_ moveType:Int) -> Bool{
        if matchUp[monType-1][2] & (1 << (17 - moveType)) == 0{
            return false
        }
        return true
    }
    
}
