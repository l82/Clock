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
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Lotta
 */
public class ClockUI {
    
    private ClockLogic logic;
    private Line       lines;
    private int        lengthSecond;
    private int        lengthMinute;
    private int        lengthHour;
    private int        XCENTRE;
    private int        YCENTRE;
    private Boolean    started;
    private JButton    button;
    private Timer      timer;
    private JFrame     myFrame; 
    
    /**
     * Constructor that constructs a new ClockLogic object.
     */
    public ClockUI() {
        logic = new ClockLogic();
        XCENTRE = 300;
        YCENTRE = 300;
        lengthHour = 150;
        lengthMinute = 170;
        lengthSecond = 180;
        button = new JButton();
        myFrame = new JFrame(); 
        started = false;
        button.setText("Start");
    }
    
    /**
     * Runs the clock
     */
    public void runUi() {
        generateClockGUI();
    }
    
    private void generateClockGUI() {
        
        JPanel myPanel = new JPanel();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize (600, 600);
        lines = new Line(XCENTRE, YCENTRE, lengthHour, lengthMinute, lengthSecond);
        
        lines.printTime();
        myFrame.add(lines);
        myPanel.add(button);
        button.setPreferredSize(new Dimension(150, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (started == false) {
                    timer = new Timer(1000, lines, XCENTRE, YCENTRE, lengthHour, 
                    lengthMinute, lengthSecond);
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

