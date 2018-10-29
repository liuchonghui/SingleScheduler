package tools.android.singlescheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class SingleScheduler<T> {

    private T mT;

    private static ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory() {
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "single-scheduler-processor");
            thread.setPriority(Thread.MAX_PRIORITY - 1);
            return thread;
        }
    });

    public SingleScheduler(T t) {
        mT = t;
    }

    public SingleScheduler<T> runInBackground(CallableImpl<T> impl) {
        T t;
        try {
            impl.set(mT);
            Future<T> future = executor.submit(impl);
            t = future.get();
        } catch (Exception e) {
            e.printStackTrace();
            t = null;
        }
        mT = t;
        return this;
    }

    public void runOnUiThread(final CallableImpl<T> impl) {
        new android.os.Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    impl.set(mT);
                    impl.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
