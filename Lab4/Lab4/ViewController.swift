//
//  ViewController.swift
//  Lab4
//
//  Created by Kai on 9/28/21.
//

import UIKit

class ViewController: UIViewController,UITextFieldDelegate {

    @IBOutlet weak var pCounter: UIStepper!
    @IBOutlet weak var testLabel: UILabel!
    
    @IBOutlet weak var inputNum: UITextField!
    
    @IBOutlet weak var operationLabel: UILabel!
    @IBOutlet weak var operationSegControl: UISegmentedControl!
    @IBOutlet weak var resutl: UILabel!
    @IBOutlet weak var resultValue: UILabel!
    
    
    
    override func viewDidLoad() {
        inputNum.delegate=self
        let tap = UITapGestureRecognizer(target: self, action: #selector(UIInputViewController.dismissKeyboard))
        view.addGestureRecognizer(tap)
        super.viewDidLoad()

    }
    
    @IBAction func updateStepper(_ sender: UIStepper){
        testLabel.text = String(sender.value)
        Output()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func changeOperatorLabel(_ element:UISegmentedControl){
        if (element.selectedSegmentIndex == 0){
            operationLabel.text = "+"
        }else if (element.selectedSegmentIndex == 1){
            operationLabel.text = "-"
        }
        else if (element.selectedSegmentIndex == 2){
            operationLabel.text = "*"
        }
        else if (element.selectedSegmentIndex == 3){
            operationLabel.text = "/"
        }
        Output()
        
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
            Output()
    }
    
    @objc func dismissKeyboard() {
        //Causes the view (or one of its embedded text fields) to resign the first responder status.
        view.endEditing(true)
    }
    
    @objc func dismissKeyBoard(){
        view.endEditing(true)
    }
    
    func Output(){
        var rightValue:Float //check amount
        var leftValue:Float
        rightValue = Float(testLabel.text!)!

        if(inputNum.text!.isEmpty){
            leftValue = 0.0
        }else{
            leftValue = Float(inputNum.text!)!
        }
        
        if(operationSegControl.selectedSegmentIndex == 3 && leftValue == 0){
            let alert=UIAlertController(title: "Warning", message: "Cannot Divide by Zero", preferredStyle: UIAlertController.Style.alert)
            let okAction=UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: {action in
                self.operationSegControl.selectedSegmentIndex = 0
                self.changeOperatorLabel(self.operationSegControl)
            })
            alert.addAction(okAction)
            present(alert, animated: true, completion: nil)
        }
        
        if(operationSegControl.selectedSegmentIndex == 0){
            resultValue.text = String(rightValue + leftValue)
        }else if(operationSegControl.selectedSegmentIndex == 1){
            resultValue.text = String(rightValue - leftValue)
        }else if(operationSegControl.selectedSegmentIndex == 2){
            resultValue.text = String(rightValue * leftValue)
        }else{
            resultValue.text = String(rightValue / leftValue)
        }
    }

}

