package greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array of jobs where every job has a deadline and associated profit if the job
 * is finished before the deadline. It is also given that every job takes a single unit of time,
 * so the minimum possible deadline for any job is 1. How to maximize total profit if only one
 * job can be scheduled at a time.
 * <p>
 * Time Complexity - O(n^2)
 */
public class JobSequencingWithDeadline {

    public static void main(String[] args) {
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job("a", 2, 100));
        jobs.add(new Job("b", 1, 19));
        jobs.add(new Job("c", 2, 27));
        jobs.add(new Job("d", 1, 25));
        jobs.add(new Job("e", 3, 15));
        scheduleJob(jobs, 3);
    }

    static class Job {
        private String jobId;
        private int deadline;
        private int profit;

        Job(String jobId, int deadline, int profit) {
            this.jobId = jobId;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    private static void scheduleJob(List<Job> jobs, int maxDeadline) {
        Job[] scheduler = new Job[maxDeadline];
        Queue<Job> maxJobHeap = new PriorityQueue<>((j1, j2) -> Integer.valueOf(j2.profit).compareTo(j1.profit)); // O(n)
        maxJobHeap.addAll(jobs);
        while (maxJobHeap.size() > 0) { // O(n) Iterating over all the Jobs
            Job job = maxJobHeap.poll();
            // Find a free slot for this job (Note that we start from the last possible slot)
            for (int i = job.deadline - 1; i >= 0; i--) { // O(n) worst case we have to always start from the end.
                if (scheduler[i] == null) {
                    scheduler[i] = job;
                    break;
                }
            }
        }

        for (Job scheduledJob : scheduler) {
            System.out.println(scheduledJob.jobId + ">>" + scheduledJob.deadline + ">>" + scheduledJob.profit);
        }

    }
}


