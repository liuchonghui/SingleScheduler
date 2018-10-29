package tools.android.singlescheduler;

import java.util.concurrent.Callable;

public abstract class CallableImpl<V> implements Callable<V> {
    V v;

    public CallableImpl() {
    }

    public void set(V v) {
        this.v = v;
    }

    public V get() {
        return v;
    }

    @Override
    public V call() throws Exception {
        return call(this.v);
    }

    abstract public V call(V result);
}
