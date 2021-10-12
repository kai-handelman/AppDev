//
//  AttackEditViewController.swift
//  Milestone3IOS
//
//  Created by Kai on 10/7/21.
//

import UIKit

class AttackEditViewController: UIViewController , UITextFieldDelegate,UIPickerViewDelegate,UIPickerViewDataSource{
    @IBOutlet weak var lvlText: UITextField!
    @IBOutlet weak var attackingStatText: UITextField!
    
    @IBOutlet weak var typeOne: UIPickerView!
    @IBOutlet weak var typeTwo: UIPickerView!
    
    var pickerData: [String] = ["None","Bug", "Dark", "Dragon", "Electric", "Fairy","Fighting","Fire","Flying","Ghost","Grass","Ground","Ice","Normal","Poison","Psychic","Rock","Steel","Water"]

    
    override func viewDidLoad() {
        super.viewDidLoad()
        lvlText.delegate = self
        attackingStatText.delegate = self
        self.typeOne.delegate = self
        self.typeOne.dataSource = self
        self.typeTwo.delegate = self
        self.typeTwo.dataSource = self

        // Do any additional setup after loading the view.
    }
    var slevel: Int = -1
    var sOne: Int = -1
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if (segue.identifier == "doneAttackEdit"){
            let scene1VC = segue.destination as! ViewController
            if lvlText.text?.isEmpty == false{
                slevel = Int(lvlText.text!)!
            }
            if attackingStatText.text?.isEmpty == false{
                sOne = Int(attackingStatText.text!)!
            }
            scene1VC.attackMon.setData(slevel, typeOne.selectedRow(inComponent: 0), typeTwo.selectedRow(inComponent: 0), sOne, -1)

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

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
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
}
