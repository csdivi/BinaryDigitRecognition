package demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class GenerateData extends JFrame {
    public static void main(String[] args) {
        GenerateData d = new GenerateData();
        d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //d.setSize(400, 400);
        final DrawPanel panel = new DrawPanel();
        panel.setPreferredSize(new Dimension(80,80));
        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                int currentX = evt.getX();
                int currentY = evt.getY();
                panel.fillPixel(currentX, currentY);
                panel.repaint();
            }
        });
        JButton clear = new JButton("Generate");
        //JButton generate = new JButton("Generate");
        clear.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                System.out.println(panel.toString());
                panel.clear();
                panel.repaint();
            }
        });

        Container cc = d.getContentPane();
        GroupLayout gl = new GroupLayout(cc);
        cc.setLayout(gl);
        
        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(clear));

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(clear));

        d.pack();
        d.setVisible(true);
    }
}

