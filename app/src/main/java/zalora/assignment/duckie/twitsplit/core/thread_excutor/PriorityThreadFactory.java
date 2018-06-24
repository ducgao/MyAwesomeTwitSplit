package zalora.assignment.duckie.twitsplit.core.thread_excutor;

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {

    private final int priority;

    public PriorityThreadFactory(int priority) {
        this.priority = priority;
    }
    @Override
    public Thread newThread(@NonNull final Runnable r) {
        Runnable wrapperRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(priority);
                } catch (Throwable t) {

                }
                r.run();
            }
        };
        return new Thread(wrapperRunnable);
    }
}
