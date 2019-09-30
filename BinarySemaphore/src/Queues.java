import java.util.Date;
import java.util.concurrent.*;

public class Queues {
    private final Semaphore semaphore;

    public Queues() {
        semaphore = new Semaphore(1);
    }

    public void printJob(Object document) {
        try {
            semaphore.acquire();

            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + ": JobQueue: Running a Job during " + (duration / 1000) + " seconds :: Time - " + new Date());
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " The job has been completed");

            semaphore.release();
        }
    }
}
