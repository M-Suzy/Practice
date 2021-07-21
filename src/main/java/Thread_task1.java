
public class Thread_task1 {
    public static volatile int count = 0;
    public static volatile boolean signal = false;

    private static void increment() {
        count++;
    }

    private static void decrement(){
        count--;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    for (int i = 0; i < 10; i++) {
                        signal  = true;
                        increment();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    signal = false;
                }
            }

        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    while(signal){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < 10; i++) {
                        decrement();
                    }
                    //signal = false;
                    System.out.println(Thread.currentThread().getName() + " " + count);
                    notify();
                }
            }

        });
        t1.start();
        t2.start();
    }
}
