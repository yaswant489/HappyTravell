/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.CodeVerificationView;

/**
 *
 * @author Acer
 */
public class CodeVerificationController {

    private CodeVerificationView codeView;
    public CodeVerificationController(CodeVerificationView codeView, String currentEmail) {
        this.codeView = codeView;
    }
    public void open(){
    this.codeView.setVisible(true);
    } 
    public void close(){
    this.codeView.dispose();
    }
}
