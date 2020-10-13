package com.ift.concurrency.cap03;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author liufei
 * @date 2020/10/12
 */
public class FindDirFiles {


    private static class FindDirFileTask extends RecursiveAction {
        File file;
        public FindDirFileTask(File file) {
            this.file = file;
        }
        @Override
        protected void compute() {
            if (file == null) {
                return;
            }
            List<FindDirFileTask> findDirFileTasks = new ArrayList<>();
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (File tempFile : files) {
                if (tempFile.isDirectory()) {
                    findDirFileTasks.add(new FindDirFileTask(tempFile));
                } else {
                    if (tempFile.getAbsolutePath().endsWith(".mp4")) {
                        System.out.println("The File Path is: " + tempFile.getAbsolutePath());
                    }
                }
            }
            for (FindDirFileTask findDirFileTask : invokeAll(findDirFileTasks)) {
                findDirFileTask.join();
            }
        }
    }
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FindDirFileTask task = new FindDirFileTask(new File("e:/"));
        pool.execute(task);
        System.out.println("The Main Thread is keep running......");
        task.join();
        System.out.println("The Fork Join Task is finished......");
    }
}
