/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clock;

/**
 * The timer object. Draws the clock at certain intervals
 * @author lotta
 */
public class Timer extends Thread {
    
    private final int timerDelay;
    private final Line lines;
    
    /**
     * Sleeps and draws the clock at certain times
     * @param delay How long to sleep between the drawings.
     * @param inLines The graphical Line object to draw
     */
    public Timer(int delay, Line inLines) {
	super();
        timerDelay = delay;
        lines = inLines;
    }
    
    @Override
    public void run() {
        int i = 0;
	while (true) {
	    System.out.println("Elapsed time in s: " + (timerDelay * i / timerDelay) );
            try {
		sleep(timerDelay);
                lines.printTime();
                i++;
	    } catch (InterruptedException | IllegalThreadStateException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
    }
}
