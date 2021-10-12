//
//  Monsters.swift
//  Milestone3IOS
//
//  Created by Kai on 10/5/21.
//

import Foundation

class Mons{
    var level:Int?;
    var type1:Int?;
    var type2:Int?;
    var stat1:Int?;
    var stat2:Int?;
    var isReceiving: Bool = false
    
    
    init() {
        
    }
    
    init(_ lvl:Int,_ t1:Int,_ t2:Int,_ s1:Int,_ s2:Int, _ receiving:Bool){
        level = lvl;
        type1 = t1;
        type2 = t2;

        isReceiving = receiving;
        stat1 = s1
        stat2 = s2
    }
    func setData(_ lvl:Int,_ t1:Int,_ t2:Int,_ s1:Int,_ s2:Int){
        level = (lvl != -1 ? lvl : level);
        if(t1 == 0 && t2 != 0){
            type1 = t2
            type2 = 0
        }else{
            type1 = t1;
            type2 = t2;
        }
        
        stat1 = (s1 != -1 ? calcStats(s1, lvl, isReceiving) : stat1);
        stat2 = (s2 != -1 ? calcStats(s2, lvl, false) : stat2);
        
    }
    
    func calcStats(_ baseStat:Int, _ levelS: Int, _ isHP:Bool) -> Int{
        if(isHP){
            return (((2 * baseStat) + 31) * levelS)/100 + levelS + 10
        }
        let temp:Float = Float(((2 * baseStat + 31) * levelS)/100) + 5
        return Int(floor(temp))
    }
    
}
