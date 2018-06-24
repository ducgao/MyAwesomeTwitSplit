package zalora.assignment.duckie.twitsplit.core.thread_excutor;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorSupplier {
    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private final ThreadPoolExecutor mForBackgroundTasks;
    private final ThreadPoolExecutor mForLightWeightBackgroundTasks;
    private final Executor mMainThreadExecutor;

    private static ExecutorSupplier sInstance;

    public static ExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (ExecutorSupplier.class) {
                sInstance = new ExecutorSupplier();
            }
        }

        return sInstance;
    }

    private ExecutorSupplier() {
        ThreadFactory backgroundPriorityThreadFactory = new
                PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        mForBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        mForLightWeightBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        mMainThreadExecutor = new MainThreadExecutor();
    }

    public ThreadPoolExecutor forBackgroundTasks() {
        return mForBackgroundTasks;
    }

    public ThreadPoolExecutor forLightWeightBackgroundTasks() {
        return mForLightWeightBackgroundTasks;
    }

    public Executor forMainThreadTasks() {
        return mMainThreadExecutor;
    }
}
