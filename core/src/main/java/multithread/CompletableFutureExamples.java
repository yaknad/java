package multithread;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CompletableFutureExamples {

    public static void main(String[] args){

        new CompletableFutureExamples().thenRunVsThenRunAsyncWhenFreeThreadIsAvailable();
//        new CompletableFutureExamples().orderOfRun();
//        new CompletableFutureExamples().thenRunWillRunOnWhichThread();
    }

    public void thenRunWillRunOnWhichThread(){

        Runnable runnable = () -> System.out.println("running on thread: " + Thread.currentThread().getId());
        runnable.run();

        System.out.println("---------- EXAMPLE 1--------------");

        long start1 = System.currentTimeMillis();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(runnable);
        for (int i = 0; i < 10; i++) {
            voidCompletableFuture.thenRun(() -> runnable.run());
        }
        voidCompletableFuture.join();
        long end1 = System.currentTimeMillis();
        System.out.println("ending example 1. took: " + (end1 - start1));

        /// Results are inconsistent. Sometimes te "thenRun"s will run on the main thread, and on other times on the default
        /// thread pool that ran the first "runAsync".
        /// The reason:
        /// If the first asyncRun still runs while registering the "thenRun", it will run on the async pool,
        /// but if it already finished, then it will run on the registering thread (i.e. the main thread)
        /// on example 2, it will almost sure run on the async thread, since the registering of "thenRun" is done before the "runAsync" finishes.
        /// on example 3, it will almost sure run on the main (sync) thread, since the registering of "thenRun" is done lomg after
        //  the "runAsync" finishes (the stream operations are long).
        /// SEE:
        // https://www.nurkiewicz.com/2015/11/which-thread-executes.html
        // https://stackoverflow.com/questions/36462774/does-thenrunasync-as-opposed-to-thenrun-make-any-difference-if-chained-after-a

        System.out.println("---------- EXAMPLE 2--------------");
        long start2 = System.currentTimeMillis();
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable);
        voidCompletableFuture2.join();
        long end2 = System.currentTimeMillis();
        System.out.println("ending example 2. took: " + (end2 - start2));

        System.out.println("---------- EXAMPLE 3--------------");
        long start3 = System.currentTimeMillis();
        CompletableFuture<Void> voidCompletableFuture3 = CompletableFuture.runAsync(runnable);
        IntStream.range(0, 10).mapToObj(i -> runnable).forEach(n -> voidCompletableFuture.thenRun(n::run));
        voidCompletableFuture3.join();
        long end3 = System.currentTimeMillis();
        System.out.println("ending example 3. took: " + (end3 - start3));

    }

    public void thenRunVsThenRunAsyncWhenFreeThreadIsAvailable(){

        Runnable runnable = () -> System.out.println("running on thread: " + Thread.currentThread().getId());
        runnable.run();

        ExecutorService executorService = Executors.newFixedThreadPool(20);

        long start = System.currentTimeMillis();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(runnable, executorService)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable)
                .thenRun(runnable);
        voidCompletableFuture.join();
        long end = System.currentTimeMillis();
        System.out.println("ending runWith. took: " + (end - start));

        long start2 = System.currentTimeMillis();
        CompletableFuture<Void> voidCompletableFuture2 = CompletableFuture.runAsync(runnable, executorService)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable)
                .thenRunAsync(runnable);
        voidCompletableFuture2.join();
        long end2 = System.currentTimeMillis();
        System.out.println("ending runWith. took: " + (end2 - start2));
    }

    public void orderOfRun(){

        Runnable runnable1 = () -> System.out.println("runnable1 running on thread: " + Thread.currentThread().getId());
        Runnable runnable2 = () -> System.out.println("runnable2 running on thread: " + Thread.currentThread().getId());
        Runnable runnable3 = () -> System.out.println("runnable3 running on thread: " + Thread.currentThread().getId());
        Runnable runnable4 = () -> System.out.println("runnable4 running on thread: " + Thread.currentThread().getId());
        Runnable runnable5 = () -> System.out.println("runnable5 running on thread: " + Thread.currentThread().getId());
        Runnable runnable6 = () -> System.out.println("runnable6 running on thread: " + Thread.currentThread().getId());
        Runnable runnable7 = () -> System.out.println("runnable7 running on thread: " + Thread.currentThread().getId());
        Runnable runnable8 = () -> System.out.println("runnable8 running on thread: " + Thread.currentThread().getId());
        List<Runnable> runnables = List.of(runnable1, runnable2, runnable3, runnable4, runnable5, runnable6, runnable7, runnable8);

        System.out.println("*****run all runnables in a sync loop*****");
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> runnables.forEach(Runnable::run));
        voidCompletableFuture.join();

        System.out.println("*****run all runnables as callbacks with thenRun*****");
        CompletableFuture<Void> calc1 = CompletableFuture.completedFuture(null);
        runnables.forEach(run -> calc1.thenRun(() -> run.run()));
        calc1.join();


        System.out.println("*****run each runnable as a callbacks of the former CompletableStage with thenRunAsync. Wait on the initial CompletableFuture*****");
        CompletableFuture<Void> calc3 = CompletableFuture.completedFuture(null)
                .thenRunAsync(runnable1)
                .thenRunAsync(runnable2)
                .thenRunAsync(runnable3)
                .thenRunAsync(runnable4)
                .thenRunAsync(runnable5)
                .thenRunAsync(runnable6)
                .thenRunAsync(runnable7)
                .thenRunAsync(runnable8);
        calc3.join();


        System.out.println("***** another version of the last example ********");

        CompletableFuture<Void> calc4 = CompletableFuture.completedFuture(null);
        CompletableFuture<Void> pointer = calc4;
        for (Runnable run: runnables) {
            pointer = pointer.thenRunAsync(run);
        }
        pointer.join(); // ? why calc4.join() is not waiting - maybe because it's already completed!


        System.out.println("***** another version of the last example (2) not working well ********");

        CompletableFuture<Void> calc5 = CompletableFuture.completedFuture(null);
        CompletableFuture<Void> reduced = runnables.stream().reduce(calc5,
                (calc, run) -> calc5.thenRunAsync(run::run), (c, c2) -> c);
        reduced.join(); // try a longer action in the runnable and see that it's inconsistent!

        System.out.println("***** another version of the last example (3) ********");

        CompletableFuture<Void> calc6 = CompletableFuture.completedFuture(null);
        runnables.forEach(run -> calc6.thenRunAsync(run::run).join());


        System.out.println("*****run all runnables as callbacks on the first CompletableStage with thenRunAsync*****");
        CompletableFuture<Void> calc2 = CompletableFuture.completedFuture(null);
        runnables.forEach(run -> calc2.thenRunAsync(() -> run.run()));
        calc2.join();
        // Since the loop (for each) registers all the callbacks on the initial CompletableFuture, they will run parallel to each other
    }
}
