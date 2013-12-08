/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clock;

/**
 *
 * @author lotta
 */
public class Timer extends Thread {
    
    private final int timerDelay;
    private Line lines;
    private int TXCENTER, TYCENTER, minuteLength, hourLength, secondLength;
    
    public Timer(int delay, Line inLines, int XCENTER, int YCENTER, 
            int hourArrow, int minuteArrow, int secondArrow) {
	super();
        timerDelay = delay;
        lines = inLines;
        TXCENTER = XCENTER;
        TYCENTER = YCENTER;
        secondLength = secondArrow;
        minuteLength = minuteArrow;
        hourLength = hourArrow;
    }
    
    @Override
    public void run() {
        int i = 0;
	while (true) {
	    System.out.println(i + "Just some text");
            try {
		sleep(timerDelay);
                lines.changeSecondSizeLine(TXCENTER, TYCENTER, TXCENTER + i, TYCENTER - minuteLength);
                //lines.changeMinuteSizeLine(TXCENTER, TYCENTER, TXCENTER + i, TYCENTER - minuteLength);
                //lines.changeHourSizeLine(TXCENTER, TYCENTER, TXCENTER + hourLength, TYCENTER + i);
                i++;
	    } catch (InterruptedException | IllegalThreadStateException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
    }
}
