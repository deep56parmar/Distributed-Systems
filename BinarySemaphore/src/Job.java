public class Job implements Runnable {

    private Queues queues;

    public Job(Queues queues) {
        this.queues = queues;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " is going to run");
        queues.printJob(new Object());
    }
}
