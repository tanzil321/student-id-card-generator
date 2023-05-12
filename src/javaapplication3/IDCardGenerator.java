/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class IDCardGenerator {
    private JFrame frame;
    private JTextField nameField;
    private JTextField idField;
    private JTextField positionField;
    private JTextField phoneField;
    private JFileChooser fileChooser;
    private File photoFile;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IDCardGenerator window = new IDCardGenerator();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public IDCardGenerator() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("ID Card Generator");
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        nameField = new JTextField();
        nameField.setBounds(165, 25, 230, 25);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        idField = new JTextField();
        idField.setBounds(165, 65, 230, 25);
        frame.getContentPane().add(idField);
        idField.setColumns(10);

        positionField = new JTextField();
        positionField.setBounds(165, 105, 230, 25);
        frame.getContentPane().add(positionField);
        positionField.setColumns(10);
        
        phoneField = new JTextField();
        phoneField.setBounds(165, 145, 230, 25);
        frame.getContentPane().add(phoneField);
        phoneField.setColumns(10);

        JButton btnUploadPhoto = new JButton("Upload Photo");
        btnUploadPhoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    photoFile = fileChooser.getSelectedFile();
                }
            }
        });
        btnUploadPhoto.setBounds(165, 185, 230, 25);
        frame.getContentPane().add(btnUploadPhoto);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(40, 25, 90, 25);
        frame.getContentPane().add(lblName);

        JLabel lblID = new JLabel("ID");
        lblID.setBounds(40, 65, 90, 25);
        frame.getContentPane().add(lblID);

        JLabel lblPosition = new JLabel("Program");
        lblPosition.setBounds(40, 105, 90, 25);
        frame.getContentPane().add(lblPosition);
        
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(40, 145, 90, 25);
        frame.getContentPane().add(lblPhone);

        JLabel lblPhoto = new JLabel("Photo");
        lblPhoto.setBounds(40, 185, 90, 25);
        frame.getContentPane().add(lblPhoto);
        
        
        JButton btnGenerateIDCard = new JButton("Generate ID Card");
        btnGenerateIDCard.setBackground(Color.ORANGE);
        btnGenerateIDCard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateIDCard();
            }
        });
        btnGenerateIDCard.setBounds(200, 225, 150, 40);
        frame.getContentPane().add(btnGenerateIDCard);
    }

    private void generateIDCard() {
        int width = 640;
        int height = 400;
        BufferedImage idCard = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = idCard.createGraphics();

        // Draw background
        g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, width, height);

        // Draw ID card border
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, width - 20, height - 20);

        // Draw student photo
        try {
            BufferedImage photo = ImageIO.read(photoFile);
            BufferedImage resizedPhoto = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
            Graphics2D photoGraphics = resizedPhoto.createGraphics();
            photoGraphics.drawImage(photo, 0, 0, 150, 150, null);
            photoGraphics.dispose();
            g.drawImage(resizedPhoto, 30, 100, null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading photo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Draw Logo
        try {
            BufferedImage photo = ImageIO.read(new File("images/logo.png"));
            Graphics2D photoGraphics = photo.createGraphics();
            photoGraphics.drawImage(photo, 0, 0, 50, 50, null);
            photoGraphics.dispose();
            g.drawImage(photo, 230, 50, null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading photo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Draw Signature
            try {
            BufferedImage photo = ImageIO.read(new File("images/Signature240.png"));
            BufferedImage resizedPhoto = new BufferedImage(120, 60, BufferedImage.TYPE_INT_ARGB);
            Graphics2D photoGraphics = resizedPhoto.createGraphics();
            photoGraphics.drawImage(photo, 0, 0, 120, 60, null);
            photoGraphics.dispose();
            g.drawImage(resizedPhoto, 410, 300, null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading photo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
            
            
        // Draw text
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("North Western University", 285, 75);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Name: " + nameField.getText(), 230, 130);
        g.drawString("ID: " + idField.getText(), 230, 170);
        g.drawString("Program: " + positionField.getText(), 230, 210);
        g.drawString("Phone: " + phoneField.getText(), 230, 250);
        g.drawString("Signature of Vice-Chancellor", 380,380);
        

        // Save ID card as an image
        try {
            JFileChooser fc = new JFileChooser();
            fc.showSaveDialog(fc);
            File file = fc.getSelectedFile();
            
            if (!file.getAbsolutePath().toLowerCase().endsWith(".png")) {
                // If not, add the ".png" extension to the file name
                file = new File(file.getAbsolutePath() + ".png");
            }
        
            ImageIO.write(idCard, "png", file);
            JOptionPane.showMessageDialog(frame, "ID card saved as 'idCard.png'", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving ID card", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
