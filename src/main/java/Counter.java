public class Counter {
    public synchronized void count(String name){
        for(int i =1; i<=10; i++){
            System.out.println(name+" count= "+ i);
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
class CounterThread extends Thread{
    private String name;
    private Counter counter;
    CounterThread(String name, Counter counter){
        this.name = name;
        this.counter = counter;
    }
    @Override
    public void run(){

            counter.count(name);
    }

}
class Test{
    public static void main(String[] args) {
        Counter counter = new Counter();
        CounterThread t1 = new CounterThread("T1", counter);
        t1.start();
        CounterThread t2 = new CounterThread("T2", counter);
        t2.start();
    }
}
