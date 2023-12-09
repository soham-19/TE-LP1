import java.util.*;

public class sjfpre {
    static Scanner sc = new Scanner(System.in);

    public static class Task {
        public int id;
        public int at;
        public int bt;
        public int wt;
        public int tat;
        public int remainingTime;

    }

    public static void input(Task T[]) {
        for (int i = 0; i < T.length; i++) {
            T[i].id=i+1;
            System.out.println("Enter the Arrival Time for T" + (i + 1));
            T[i].at = sc.nextInt();
            System.out.println("Enter the Burst Time for T" + (i + 1));
            T[i].bt = sc.nextInt();
            T[i].remainingTime = T[i].bt; // Initialize remainingTime
        }
    }

    public static void display(Task T[]) {
        System.out.println("Task \tWaiting Time \tTurnaround Time");
        for (int i = 0; i < T.length; i++) {
            System.out.println("T" + T[i].id + "\t" + T[i].wt + "\t\t" + T[i].tat);
        }
    }

    public static void schedule(Task T[]) {
        int currentTime = 0;
        int completedTasks = 0;

        while (completedTasks < T.length) {
            int shortestJobIndex = -1;
            int shortestJobTime = Integer.MAX_VALUE;

            for (int i = 0; i < T.length; i++) {
                if (T[i].remainingTime > 0 && T[i].at <= currentTime) {
                    if (T[i].remainingTime < shortestJobTime) {
                        shortestJobIndex = i;
                        shortestJobTime = T[i].remainingTime;
                    }
                }
            }

            if (shortestJobIndex == -1) {
                currentTime++;
            } else {
                T[shortestJobIndex].remainingTime--;
                currentTime++;
                if (T[shortestJobIndex].remainingTime == 0) {
                    T[shortestJobIndex].tat = currentTime - T[shortestJobIndex].at;
                    T[shortestJobIndex].wt = T[shortestJobIndex].tat - T[shortestJobIndex].bt;
                    completedTasks++;
                }
            }
        }
    }

    public static void avg(Task T[]) {
        int totalwt = 0;
        int totaltat = 0;

        for (int i = 0; i < T.length; i++) {
            totalwt += T[i].wt;
            totaltat += T[i].tat;
        }

        double avgwt = (double) totalwt / T.length;
        double avgtat = (double) totaltat / T.length;

        System.out.println("Average Waiting Time: " + avgwt);
        System.out.println("Average Turnaround Time: " + avgtat);
    }

    public static void main(String args[]) {
        System.out.println("Enter the number of Tasks:");
        int n = sc.nextInt();

        Task T[] = new Task[n];
        for(int i=0;i<n;i++)
        {
            T[i]= new Task();
        }

        input(T);
        schedule(T);
        display(T);
        avg(T);
    }
}