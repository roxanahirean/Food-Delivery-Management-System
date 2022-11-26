package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MainGUI extends JFrame{

    private JLabel userLabel = new JLabel("User:");
    private JTextField userText = new JTextField(20);

    private JLabel passwordLabel = new JLabel("Password");
    private JPasswordField passwordText = new JPasswordField();

    private JButton submitButton = new JButton("Login");
    private JPanel finalPanel = new JPanel();

    private JButton registerButton = new JButton("Register");
    private JPanel buttonPanel = new JPanel();

    private JRadioButton selectUser = new JRadioButton("Administrator");
    private JRadioButton selectUser1 = new JRadioButton("Client");
    private JRadioButton selectUser2 = new JRadioButton("Employee");

    private ButtonGroup group = new ButtonGroup();

    public MainGUI(){
        super("LOGIN");
        super.setSize(350,240);
        super.setDefaultCloseOperation(super.EXIT_ON_CLOSE);

        super.add(finalPanel);

        finalPanel.setLayout(null);
        userLabel.setBounds(10,20,80,25);
        userText.setBounds(100,20,165,25);

        passwordLabel.setBounds(10,50,80,25);
        passwordText.setBounds(100,50,162,25);

        submitButton.setBounds(110,80,80,25);
        registerButton.setBounds(10,80,100,25);
        selectUser.setBounds(10,110,100,25);
        selectUser1.setBounds(10,140,100,25);
        selectUser2.setBounds(10,170,100,25);

        finalPanel.add(userLabel);
        finalPanel.add(userText);
        finalPanel.add(passwordLabel);
        finalPanel.add(passwordText);
        finalPanel.add(registerButton);
        finalPanel.add(submitButton);
        group.add(selectUser);
        group.add(selectUser1);
        group.add(selectUser2);
        finalPanel.add(selectUser);
        finalPanel.add(selectUser1);
        finalPanel.add(selectUser2);

        super.setContentPane(finalPanel);
        super.setVisible(true);


    }

    public void addSubmitButton(ActionListener a){
        submitButton.addActionListener(a);
    }



    public void addRegisterButton(ActionListener a){
        registerButton.addActionListener(a);
    }

    public JLabel getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(JLabel userLabel) {
        this.userLabel = userLabel;
    }

    public JTextField getUserText() {
        return userText;
    }

    public void setUserText(JTextField userText) {
        this.userText = userText;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JPasswordField getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(JPasswordField passwordText) {
        this.passwordText = passwordText;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public JPanel getFinalPanel() {
        return finalPanel;
    }

    public void setFinalPanel(JPanel finalPanel) {
        this.finalPanel = finalPanel;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JRadioButton getSelectUser() {
        return selectUser;
    }

    public void setSelectUser(JRadioButton selectUser) {
        this.selectUser = selectUser;
    }

    public JRadioButton getSelectUser1() {
        return selectUser1;
    }

    public void setSelectUser1(JRadioButton selectUser1) {
        this.selectUser1 = selectUser1;
    }

    public JRadioButton getSelectUser2() {
        return selectUser2;
    }

    public void setSelectUser2(JRadioButton selectUser2) {
        this.selectUser2 = selectUser2;
    }

    public ButtonGroup getGroup() {
        return group;
    }

    public void setGroup(ButtonGroup group) {
        this.group = group;
    }

}