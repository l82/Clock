/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
/**
 *
 * @author lotta
 */
   
    class Line extends JComponent {
 
        private int xMinuteStart;
        private int yMinuteStart;
        private int xMinuteEnd;
        private int yMinuteEnd;
        private int xHourStart;
        private int yHourStart;
        private int xHourEnd;
        private int yHourEnd;
        private int xSecondStart;
        private int ySecondStart;
        private int xSecondEnd;
        private int ySecondEnd;
        
        public void Line() {
            xMinuteStart = 0;
            yMinuteStart = 0;
            xMinuteEnd = 0;
            yMinuteEnd = 0;
            xHourStart = 0;
            yHourStart = 0;
            xHourEnd = 0;
            yHourEnd = 0;
            xSecondStart = 0;
            yHourStart = 0;
            xHourEnd = 0;
            yHourEnd = 0;
        }
        
       @Override
       public void paintComponent(Graphics g) {
            
            g.drawLine(xMinuteStart, yMinuteStart, xMinuteEnd, yMinuteEnd);
            g.drawLine(xHourStart, yHourStart, xHourEnd, yHourEnd);
            g.drawLine(xSecondStart, ySecondStart, xSecondEnd, ySecondEnd);
            super.repaint();
            setVisible(true);
       }
    
       public void changeSecondSizeLine (int firstX, int firstY, int secondX, 
                int secondY) {
            xSecondStart = firstX;
            ySecondStart = firstY;
            xSecondEnd = secondX;
            ySecondEnd = secondY;
       }
       
       public void changeMinuteSizeLine (int firstX, int firstY, int secondX, 
                int secondY) {
            xMinuteStart = firstX;
            yMinuteStart = firstY;
            xMinuteEnd = secondX;
            yMinuteEnd = secondY;
       }
       public void changeHourSizeLine (int firstX, int firstY, int secondX, 
                int secondY) {
            xHourStart = firstX;
            yHourStart = firstY;
            xHourEnd = secondX;
            yHourEnd = secondY;
       }
       
}
