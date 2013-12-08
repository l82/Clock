/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


/**
 * The Line class handles the drawing of the clock
 * @author lotta
 */
    class Line extends JComponent {
 
        private final int xCentre;
        private final int yCentre;
        private int xMinuteEnd;
        private int yMinuteEnd;
        private int xHourEnd;
        private int yHourEnd;
        private int xSecondEnd;
        private int ySecondEnd;
        private final int lengthHour;
        private final int lengthMinute;
        private final int lengthSecond;
        
        /**
         * The constructor for the clock
         * @param XCENTRE The X-position of the centre
         * @param YCENTRE The Y-position of the centre
         * @param lengthH The length of the hour pointer
         * @param lengthM The length of the minute pointer
         * @param lengthS The length of the second pointer
         */
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
    
       /**
        * Changes the end points of the pointers
        * @param xPoint End x-point
        * @param yPoint End y-point
        * @param lineType Type of line, hour, minute or second
        */
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
       
       /**
        * Prints the current time
        */
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
       
       private int calculateQuadrant(double angle) {
           int quadrant;
           
           if ((int)angle < 90) {
               quadrant = 1;
           }
           else if ((int)angle >= 90 && (int)angle < 180) {
               quadrant = 2;
           }
           else if ((int)angle >= 180 && (int)angle < 270) {
               quadrant = 3;
           }
           else if ((int)angle >= 270 && (int)angle < 360) {
               quadrant = 4;
           }
           else if ((int)angle == 360) {
               quadrant = 1;
           }
           else {
               System.out.println("ERROR: Invalid quadrant");
               quadrant = 1;
           }
           return quadrant;
       }
       
       private void setXYLine(double angle, int length, LINE_TYPE lineType) {
           
           double radian;
           double angelQuadrant;
           
           int quadrant;
           double xP;
           double yP;
           
           quadrant = calculateQuadrant(angle);
           switch (quadrant) {
               case 1:
                   radian = Math.toRadians(angle);
                   xP = length * Math.sin(radian);
                   yP = length * Math.cos(radian);
                   changeSizeLine((xCentre + (int)(xP)), (yCentre - (int)(yP)), lineType);
                   break;
               case 2:
                   angelQuadrant = angle - 90;
                   radian = Math.toRadians(angelQuadrant);
                   yP = length * Math.sin(radian);
                   xP = length * Math.cos(radian);
                   changeSizeLine((xCentre + (int)(xP)), (yCentre + (int)(yP)), lineType);
                   break;
               case 3:
                   angelQuadrant = angle - 180;
                   radian = Math.toRadians(angelQuadrant);
                   xP = length * Math.sin(radian);
                   yP = length * Math.cos(radian);
                   changeSizeLine((xCentre - (int)(xP)), (yCentre + (int)(yP)), lineType);
                   break;
               case 4:
                   angelQuadrant = angle - 270;
                   radian = Math.toRadians(angelQuadrant);
                   yP = length * Math.sin(radian);
                   xP = length * Math.cos(radian);
                   changeSizeLine((xCentre - (int)(xP)), (yCentre - (int)(yP)), lineType);
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
