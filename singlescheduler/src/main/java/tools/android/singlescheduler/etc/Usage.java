package tools.android.singlescheduler.etc;

import android.util.Log;

import tools.android.singlescheduler.CallableImpl;
import tools.android.singlescheduler.SingleScheduler;

public class Usage {

    public void run() {
        new SingleScheduler(new Result())
                .runInBackground(new CallableImpl<Result>() {
                    @Override
                    public Result call(Result result) {
                        return getFirstStep(result);
                    }
                }).runInBackground(new CallableImpl<Result>() {
            @Override
            public Result call(Result result) {
                return getSecondStep(result);
            }
        }).runOnUiThread(new CallableImpl<Result>() {
            @Override
            public Result call(Result result) {
                Log.d("PPP", "result.count=" + result.count);
                return result;
            }
        });
    }

    private Result getFirstStep(Result result) {
        result.count++;
        Log.d("PPP", "getFirstStep|result.count=" + result.count);
        return result;
    }

    private Result getSecondStep(Result result) {
        result.count++;
        Log.d("PPP", "getSecondStep|result.count=" + result.count);
        return result;
    }

    class Result {
        String id;
        int count = 0;
        boolean success;
    }
}


