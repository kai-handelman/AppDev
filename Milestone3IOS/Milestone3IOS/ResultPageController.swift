//
//  ResultPageController.swift
//  Milestone3IOS
//
//  Created by Kai on 10/8/21.
//

import UIKit

class ResultPageController: UIViewController {

    var receiveMon: Mons = Mons();
    var attackMon: Mons = Mons();
    var attackType: Int = 0;
    var attackBasePower: Int = 0;
    var typeCalc: TypeChart = TypeChart();
    
    @IBOutlet weak var damageRange: UILabel!
    @IBOutlet weak var critDamageRange: UILabel!
    @IBOutlet weak var chanceOfKo: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        getDamage(attackMon, receiveMon, attackType,attackBasePower)
        // Do any additional setup after loading the view.
    }
    
    
    func getDamage(_ attackMon:Mons,_ receiveMon:Mons,_ aType:Int, _ aBP:Int){
        let aPart:Float = floor(Float(((2 * (attackMon.level!))/5 + 2))) // floor
        let Power:Float = Float(aBP)
        let ad:Float = Float(attackMon.stat1!)/Float(receiveMon.stat2!)
        var initialPower:Float = Float(((aPart * Power * ad)/50) + 2);
        if(aType == attackMon.type1 || aType == attackMon.type2){
            initialPower = Float(1.5) * initialPower;
        }
        var matchUpMultiplyer = typeCalc.getTypeChart(receiveMon.type1!,aType)
        
        if(receiveMon.type2! != 0){
            matchUpMultiplyer = matchUpMultiplyer * typeCalc.getTypeChart(receiveMon.type2!,aType)
        }
        initialPower = floor(initialPower * matchUpMultiplyer)
        
//        Caluculate Super Effectiveness
        let upperBound:Int = Int(initialPower)
        let lowerBound:Int = Int(initialPower * Float(0.85))
        let KOChance:Float = Float(receiveMon.stat1!)/initialPower
        
        
//        damageRange.text = String(lowerBound) + " ~ " + String(upperBound) + ""
        damageRange.text = String(100 * Float(lowerBound)/Float(receiveMon.stat1!)) + "% ~ " + String(100 * Float(upperBound)/Float(receiveMon.stat1!)) + "%"
        critDamageRange.text = String(100 * (Float(lowerBound) * 1.5)/Float(receiveMon.stat1!)) + "% ~ " + String(100 * (Float(upperBound) * 1.5)/Float(receiveMon.stat1!)) + " %"
        
//        Need to Fix
        if (receiveMon.stat1! - lowerBound < 1) {
            chanceOfKo.text = "100%"
        }else if (receiveMon.stat1! - upperBound > 0){
            chanceOfKo.text = "0%"
        }else{
            chanceOfKo.text = String(100 - 100*((KOChance - 0.85) * 100)/16) + "%"
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
