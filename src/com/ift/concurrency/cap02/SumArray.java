package com.ift.concurrency.cap02;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author liufei
 * @date 2020/10/11
 */
public class SumArray {

    private static class WorkTask extends RecursiveTask<Integer> {
        private static final int THREAD_NUM = MakeArray.SIZE / 10;
        private int[] data;
        private int fromIndex;
        private int toIndex;

        public WorkTask(int[] data, int fromIndex, int toIndex) {
            this.data = data;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            if (toIndex - fromIndex < THREAD_NUM) {
                int count = 0;
                for (int i = fromIndex; i <= toIndex; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count = count + data[i];
                }
                return count;
            } else {
                int mid = (toIndex + fromIndex) / 2;
                WorkTask left = new WorkTask(data, fromIndex, mid);
                WorkTask right = new WorkTask(data, mid + 1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] data = MakeArray.makeArray();
        long startTime = System.currentTimeMillis();
        WorkTask workTask = new WorkTask(data, 0, data.length - 1);
        forkJoinPool.invoke(workTask);
        Integer count = workTask.join();
        long endTime = System.currentTimeMillis();
        System.out.println("result is " + count + " used " + (endTime - startTime) + " ms.");
    }
}
