package framework.threads;

import static framework.threads.ThreadColors.*;

public class MainThread {

    //HEAP- all threads access the heap
    //Thread Stack - each thread has its own stack memory (local variables are stored here)
    //Each thread has its own copy of local variable
    //memory required to store instance values is allocated on the heap
    //When multiple thread work with the same object (instance var), they share the same object
    //Race condition = when threads share the same resource; Thread Interference; Concurrency

    public static void main(String[] args) {
        System.out.println(ANSI_GREEN + "Hello from the main thread");

        AnotherThread anotherThread = new AnotherThread();
        anotherThread.start();

        Thread runnableThread = new Thread(new RunnableThread() {
            public void run(){
                System.out.println(ANSI_RED + "Hello from anonymous class");
                try {
                    anotherThread.join();  //we want to join another thread from current thread.
                    // This thread can interrupt prematurely if this thread is interrupted by anotherThread
                    //it will not continue execution until another thread is done
                    System.out.println(ANSI_RED  + "Another thread terminated, so I'm running again");
                }catch(InterruptedException e){
                    System.out.println(ANSI_RED + "I was interrupted");
                }
            }
        });


        new Thread(() -> System.out.println(ANSI_BLUE + "Hello from anonymous class instant run"))
                .start();

        //anotherThread.interrupt();

        runnableThread.start();
    }
}
