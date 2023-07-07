package framework.threads;

import org.apache.logging.log4j.core.pattern.AnsiEscape;

import static framework.threads.ThreadColors.ANSI_PURPLE;


public class RunnableThread implements Runnable{
    @Override public void run() {
        System.out.println(ANSI_PURPLE + "Hello from runnable thread");
    }
}
