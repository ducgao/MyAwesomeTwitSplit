package zalora.assignment.duckie.twitsplit.core.thread_excutor;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorSupplier {
    private final ThreadPoolExecutor forBackgroundTasks;

    private static ExecutorSupplier instance;

    public static ExecutorSupplier getInstance() {
        if (instance == null) {
            synchronized (ExecutorSupplier.class) {
                instance = new ExecutorSupplier();
            }
        }

        return instance;
    }

    private ExecutorSupplier() {
        ThreadFactory backgroundPriorityThreadFactory = new
                PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        forBackgroundTasks = new ThreadPoolExecutor(
                1,
                1,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

    }

    public ThreadPoolExecutor forBackgroundTasks() {
        return forBackgroundTasks;
    }
}
