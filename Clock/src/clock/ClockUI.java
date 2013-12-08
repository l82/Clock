/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ClockUI class draws a very simple clock with a button to start- and stop 
 * the clock
 * @author Lotta
 */
public class ClockUI {
    
    private JButton          button;
    private Line             lines;
    private final int        lengthSecond;
    private final int        lengthMinute;
    private final int        lengthHour;
    private final int        XCENTRE;
    private final int        YCENTRE;
    private Boolean          started;
    private Timer            timer;
    
    /**
     * Constructor that constructs a new ClockLogic object.
     */
    public ClockUI() {
        XCENTRE = 300;
        YCENTRE = 300;
        lengthHour = 150;
        lengthMinute = 170;
        lengthSecond = 180;
        started = false;
    }
    
    /**
     * Runs the clock
     */
    public void runUi() {
        generateClockGUI();
    }
    
    private void generateClockGUI() {
        
        JPanel myPanel = new JPanel();
        JFrame myFrame = new JFrame();
        button = new JButton();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize (600, 600);
        lines = new Line(XCENTRE, YCENTRE, lengthHour, lengthMinute, lengthSecond);
        lines.printTime();
        myFrame.add(lines);
        button.setText("Start");
        myPanel.add(button);
        button.setPreferredSize(new Dimension(150, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (started == false) {
                    timer = new Timer(1000, lines);
                    button.setText("Stop");
                    started = true;
                    timer.start();
                }
                else {
                    button.setText("Start");
                    started = false;
                    try {
                        timer.interrupt();
                        timer.join();
                    } 
                    catch (IllegalThreadStateException | InterruptedException itse) {
                        System.out.println("Error: " + itse.getMessage());
                    }
                }
            }
        });     
        myFrame.getContentPane().add(BorderLayout.SOUTH, myPanel);
        myFrame.setVisible(true);
    }   
}

