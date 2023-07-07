package framework.threads;

import org.apache.logging.log4j.core.pattern.AnsiEscape;

import static framework.threads.ThreadColors.ANSI_YELLOW;

public class AnotherThread extends Thread{

    @Override
    public void run() {
        System.out.println(ANSI_YELLOW + "Hello from another thread!");

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            System.out.println(ANSI_YELLOW + "Other thread woke me up");
            return;
        }

        System.out.println(ANSI_YELLOW + "3 seconds have passed and I am awake");
    }
}
