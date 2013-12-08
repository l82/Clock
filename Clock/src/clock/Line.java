/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.math.*;
/**
 *
 * @author lotta
 */
   
    class Line extends JComponent {
 
        private int xCentre;
        private int yCentre;
        private int xMinuteEnd;
        private int yMinuteEnd;
        private int xHourEnd;
        private int yHourEnd;
        private int xSecondEnd;
        private int ySecondEnd;
        private int lengthHour;
        private int lengthMinute;
        private int lengthSecond;
        
        public Line(int XCENTRE, int YCENTRE, int lengthH, int lengthM, 
                int lengthS) {
            
            xCentre = XCENTRE;
            yCentre = YCENTRE;
            xMinuteEnd = 0;
            yMinuteEnd = 0;
            xHourEnd = 0;
            yHourEnd = 0;
            xHourEnd = 0;
            yHourEnd = 0;
            lengthHour = lengthH;
            lengthMinute = lengthM;
            lengthSecond = lengthS;
        }
        
       @Override
       public void paintComponent(Graphics g) {
            
            g.drawLine(xCentre, yCentre, xMinuteEnd, yMinuteEnd);
            g.drawLine(xCentre, yCentre, xHourEnd, yHourEnd);
            g.drawLine(xCentre, yCentre, xSecondEnd, ySecondEnd);
            super.repaint();
            setVisible(true);
       }
       
       private enum LINE_TYPE {hour, minute, second}; 
    
       public void changeSizeLine (int xPoint, int yPoint, LINE_TYPE lineType) {
           
           switch(lineType) {
               case hour:
                   xHourEnd = xPoint;
                   yHourEnd = yPoint;
                   break;
               case minute:
                   xMinuteEnd = xPoint;
                   yMinuteEnd = yPoint;
                   break;
               case second:
                   xSecondEnd = xPoint;
                   ySecondEnd = yPoint;
                   break;
           }         
       }
       
       public void printTime () {
            Calendar timeNow = Calendar.getInstance();
            // You cannot use Date class to extract individual Date fields
            int amPm = timeNow.get(Calendar.AM_PM);
            int hour = timeNow.get(Calendar.HOUR);
            int minute = timeNow.get(Calendar.MINUTE);
            int second = timeNow.get(Calendar.SECOND);
            System.out.println("Time is: " + hour + ":" + minute + ":" + second);
            calculateCoordinates(hour, minute, second);
       }
       
       public int calculateQuadrant(double angle) {
           int quadrant;
           
           if (angle < 90) {
               quadrant = 1;
           }
           else if (angle >= 90 && angle < 180) {
               quadrant = 2;
           }
           else if (angle >= 180 && angle < 270) {
               quadrant = 3;
           }
           else if (angle >= 270 && angle <= 360) {
               quadrant = 4;
           }
           else {
               System.out.println("ERROR: Invalid quadrant");
               quadrant = 1;
           }
           return quadrant;
       }
       
       private void setXYLine(double angle, int length, LINE_TYPE lineType) {
           
           double radianHours;
           double angelQuadrant;
           
           int quadrant;
           double xHour;
           double yHour;
           
           quadrant = calculateQuadrant(angle);
           switch (quadrant) {
               case 1:
                   radianHours = Math.toRadians(angle);
                   xHour = length * Math.sin(radianHours);
                   yHour = length * Math.cos(radianHours);
                   changeSizeLine((xCentre + (int)(xHour)), (yCentre + (int)(yHour)), lineType);
                   break;
               case 2:
                   angelQuadrant = angle - 90;
                   radianHours = Math.toRadians(angelQuadrant);
                   yHour = length * Math.sin(radianHours);
                   xHour = length * Math.cos(radianHours);
                   changeSizeLine((xCentre + (int)(xHour)), (yCentre - (int)(yHour)), lineType);
                   break;
               case 3:
                   angelQuadrant = angle - 180;
                   radianHours = Math.toRadians(angelQuadrant);
                   xHour = length * Math.sin(radianHours);
                   yHour = length * Math.cos(radianHours);
                   changeSizeLine((xCentre + (int)(xHour)), (yCentre + (int)(yHour)), lineType);
                   break;
               case 4:
                   angelQuadrant = angle - 270;
                   radianHours = Math.toRadians(angelQuadrant);
                   yHour = length * Math.sin(radianHours);
                   xHour = length * Math.cos(radianHours);
                   changeSizeLine((xCentre - (int)(xHour)), (yCentre - (int)(yHour)), lineType);
                   break;
           }
       }    
       
       private void calculateCoordinates(int hour, int minute, int second) {
           
           double angleMinute = 360 / 60 * (double)minute;
           double angleSecond = 360 / 60 * (double)second;
           double angleHour = 360 / 12 * (double)hour;
           LINE_TYPE lineType;
           
           lineType = LINE_TYPE.hour;
           setXYLine(angleHour, lengthHour, lineType);
           lineType = LINE_TYPE.minute;
           setXYLine(angleMinute, lengthMinute, lineType);
           lineType = LINE_TYPE.second;
           setXYLine(angleSecond, lengthSecond, lineType);
       }
}
