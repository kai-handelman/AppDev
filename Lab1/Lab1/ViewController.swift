//
//  ViewController.swift
//  Lab1
//
//  Created by Kai on 9/3/21.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var dis: UILabel!
    @IBOutlet weak var plantImage: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

 
    @IBAction func buttonPress(_ sender: UIButton) {
        var temp = sender.tag;
        if temp == 0{
            temp = Int.random(in: 0..<4)
        }
        if temp == 1{
            dis.text = "Its a Japanese Maple!"
            plantImage.image = UIImage(named: "JpnsMaple");
        }else if temp == 2{
            dis.text = "Its a Giant Sequoia!"
            plantImage.image = UIImage(named: "Sequoia");
        }else if temp == 3{
            dis.text = "Its a some Dandelions!"
            plantImage.image = UIImage(named: "dandelion");
        }
        

    }
    
}

