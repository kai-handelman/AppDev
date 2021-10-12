//
//  ViewController.swift
//  Milestone3IOS
//
//  Created by Kai on 10/5/21.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource,UITextFieldDelegate{
   
    @IBOutlet weak var moveTypePicker: UIPickerView!
    var receiveMon = Mons(100, 10, 12, 321, 186,true)
    var attackMon = Mons(100, 10, 12, 220, -1,false)
    var pickerData: [String] = ["None","Bug", "Dark", "Dragon", "Electric", "Fairy","Fighting","Fire","Flying","Ghost","Grass","Ground","Ice","Normal","Poison","Psychic","Rock","Steel","Water"]

    @IBOutlet weak var ReceivingType1: UIImageView!
    @IBOutlet weak var ReceivingType2: UIImageView!
    @IBOutlet weak var ReceivingLevel: UILabel!
    @IBOutlet weak var ReceivingHP: UILabel!
    @IBOutlet weak var ReceivingDefStat: UILabel!
    
    
    @IBOutlet weak var AttackingType1: UIImageView!
    @IBOutlet weak var AttackingType2: UIImageView!
    @IBOutlet weak var AttackingLevel: UILabel!
    @IBOutlet weak var AttackingAttStat: UILabel!
    
    @IBOutlet weak var attackBP: UITextField!

    
    var isPhysical = true
    
    override func viewDidLoad() {
        super.viewDidLoad()
        attackBP.delegate = self
        self.moveTypePicker.delegate = self
        self.moveTypePicker.dataSource = self
        setTypeImage(ReceivingType1, receiveMon.type1!)
        setTypeImage(ReceivingType2, receiveMon.type2!)
        setTypeImage(AttackingType1, attackMon.type1!)
        setTypeImage(AttackingType2, attackMon.type2!)
        setStats()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func specialPhysicalSwitch(_ sender: UISegmentedControl){
    if(sender.selectedSegmentIndex == 0){
        isPhysical = true
    }else{
        isPhysical = false
    }
    setStats()
}
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // Number of columns of data
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    // The number of rows of data
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return pickerData.count
    }
    
    // The data to return fopr the row and component (column) that's being passed in
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return pickerData[row]
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if (segue.identifier == "showDamageResult"){
            let resultVC = segue.destination as! ResultPageController
            resultVC.receiveMon = receiveMon
            resultVC.attackMon = attackMon
            if let text = attackBP.text, text.isEmpty{
                resultVC.attackBasePower = 100
            }else{
                resultVC.attackBasePower = Int(attackBP.text!)!
            }
            resultVC.attackType = moveTypePicker.selectedRow(inComponent: 0)
            

        }
    }
    
    
    
    
//     Helper Functions for loading back in
    
    func setTypeImage(_ image:UIImageView,_ typeInt:Int){
//        if(typeInt == -1){
//            image.image = nil
//        }else{
//            image.image = UIImage(named:pickerData[typeInt])
//        }
        image.image = UIImage(named:pickerData[typeInt])
    }
    
    func setStats(){
        AttackingLevel.text = "Level: " + String(attackMon.level!)
        ReceivingLevel.text = "Level: " + String(receiveMon.level!)
        ReceivingHP.text = "Approx. HP: " + String(receiveMon.stat1!)
        if (isPhysical){
            ReceivingDefStat.text = "Approx. Def: " + String(receiveMon.stat2!)
            AttackingAttStat.text = "Approx Att: " + String(attackMon.stat1!)
        }else{
            ReceivingDefStat.text = "Approx. SpD: " + String(receiveMon.stat2!)
            AttackingAttStat.text = "Approx SpA: " + String(attackMon.stat1!)
        }
    }
    
//    Catches the Unwind
    @IBAction func unwindSeq( _segue:UIStoryboardSegue){
        setStats()
        setTypeImage(ReceivingType1, receiveMon.type1!)
        setTypeImage(ReceivingType2, receiveMon.type2!)
        setTypeImage(AttackingType1, attackMon.type1!)
        setTypeImage(AttackingType2, attackMon.type2!)
    }

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
}

