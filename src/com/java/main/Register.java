package com.java.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Beatrice Bock TP038381
 * GUI for New Member registration
 */
 class Register extends JFrame {

    int months = 1; //Default month number
    int totalfees = 0; //Default fees
    String member = "Deluxe";

    //Elements requiring global access
    JTextField txtName = new JTextField(20);

    public Register () {
        //Frame setup
        setSize(500,300);
        setLocation(400, 200);
        setLayout(new BorderLayout());
        setTitle("Register New Member");

        //Panel with input fields
        Panel inputPanel = new Panel();
        inputPanel.setLayout(new GridLayout(0,2));
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout());

        //Initialize Combo Box with membertypes
        String [] memberTypes = {"Deluxe", "Non-Deluxe", "Week-Day"};
        JComboBox cbMemberType = new JComboBox(memberTypes);


        //buttons
        Button btnConfirm = new Button("Confirm");
        Button btnMenu = new Button("<< Main Menu");
        Button btnClear = new Button("Clear");

        //Add elements to input panel
        inputPanel.add(new Label("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new Label("Membership Types:"));
        inputPanel.add(cbMemberType);

        //Add buttons to button panel
        buttonPanel.add(btnMenu);
        buttonPanel.add(btnConfirm);
        buttonPanel.add(btnClear);

        add(inputPanel, "Center");
        add(buttonPanel, "South");

        //Action Listener for the combobox
        //Reads the selected item and puts it in a string variable
        cbMemberType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cbMemberType = (JComboBox) e.getSource();
                String memberTypes = (String) cbMemberType.getSelectedItem();
                member = memberTypes;
            }
        });

        //actionListener for Confirm button
        btnConfirm.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputName = txtName.getText();

                try {
                    if (!txtName.getText().trim().isEmpty()) {
                        Random rand = new Random();
                        int randomNum = rand.nextInt((99999 - 10000) + 1) + 10000;

                        int option = JOptionPane.showConfirmDialog(null, "Confirm entered information and enter information into database?", "Confirm enter", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
                        if (option == JOptionPane.OK_OPTION) {
                            JOptionPane.showMessageDialog(null, "New member added.\nName: " + inputName + "\nMember ID: " + randomNum + "\nMembership Type: " + member);

                            try {
                                PrintWriter outputFile = new PrintWriter(new FileWriter("members.txt", true));
                                outputFile.append(randomNum + ":" + inputName + ":" + member + "\n");
                                outputFile.close();

                            } catch (Exception fileExcp) {
                                JOptionPane.showMessageDialog(null, "Error: " + fileExcp.getMessage());
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"Name cannot be empty");
                    }
                }catch(Exception nullExcp){
                    JOptionPane.showMessageDialog(null, "Please select a membership type");
                }
            }
        });

        //actionListener for Menu button
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu();
                dispose();
            }
        });

        //actionListener for Clear button
        btnClear.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText(" ");
                cbMemberType.setSelectedItem("Deluxe");
            }
        }));

        //Window Listener for "Close Window"
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            int input = JOptionPane.showConfirmDialog(null, "Confirm exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
            if (input == JOptionPane.OK_OPTION) {
                dispose();
            }
            }
        });


        setVisible(true);
    }
}


