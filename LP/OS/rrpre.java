import java.util.*;

class rrpre {
    static int tt = 0;
    static Scanner sc = new Scanner(System.in);

    static class Task {
        public int buTime;
        public int trav;
        public int id;
        public int wt;
        public int ct;
        public int tat;
        public int at; // Arrival Time

    }

    public static void input(Task T[]) {
        int n = T.length;
        for (int i = 0; i < n; i++) {
            T[i].id = i + 1;
            System.out.println("Enter the Arrival Time for T" + T[i].id);
            T[i].at = sc.nextInt();
            System.out.println("Enter the Burst Time for T" + T[i].id);
            T[i].buTime = sc.nextInt();
            T[i].trav = T[i].buTime;
        }
    }

    public static void display(Task T[]) {
        int n = T.length;
        for (int i = 0; i < n; i++) {
            System.out.println("T" + T[i].id + " " + T[i].ct + "  " + T[i].buTime + "  " + T[i].wt + " " + T[i].tat);
        }
    }

    public static void CWT(Task T[]) {
        int n = T.length;
        Queue<Task> q = new LinkedList<>();
        int remainingTasks = n;

        int quantum = 3; // Preemptive time quantum

        while (remainingTasks > 0) {
            for (int i = 0; i < n; i++) {
                if (T[i].at <= tt && T[i].trav > 0) {
                    if (T[i].trav <= quantum) {
                        tt += T[i].trav;
                        T[i].ct = tt;
                        T[i].trav = 0;
                        remainingTasks--;
                    } else {
                        tt += quantum;
                        T[i].trav -= quantum;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            T[i].wt = T[i].ct - T[i].buTime - T[i].at;
            T[i].tat = T[i].ct - T[i].at;
        }
    }

    public static void calAvg(Task T[]) {
        float avgwt = 0;
        float avgtat = 0;
        float sumwt = 0;
        float sumtat = 0;
        for (int i = 0; i < T.length; i++) {
            sumwt += T[i].wt;
            sumtat += T[i].tat;
        }
        avgwt = sumwt / T.length;
        avgtat = sumtat / T.length;
        System.out.println("Average Waiting Time: " + avgwt + " & Average Turnaround Time: " + avgtat);
    }

    public static void sort(Task T[]) {
        for (int i = 0; i < T.length; i++) {
            Task temp;
            for (int j = i + 1; j < T.length; j++) {
                if (T[i].at > T[j].at) {
                    temp = T[i];
                    T[i] = T[j];
                    T[j] = temp;
                }
            }
        }
    }


    public static void idsort(Task T[])
    {
        for(int i=0;i<T.length;i++)
        {
            Task temp;
            for(int j=i+1;j<T.length;j++)
            {
                if(T[i].id>T[j].id)
                {
                    temp=T[i];
                    T[i]=T[j];
                    T[j]=temp;
                }
            }
        }
    }
    public static void main(String args[]) {
        System.out.println("Enter the number of tasks");
        int n = sc.nextInt();
        Task T[] = new Task[n];
        for (int i = 0; i < n; i++) {
            T[i] = new Task();
        }
        input(T);
        sort(T);
        CWT(T);
        idsort(T);
        display(T);
        calAvg(T);
        sc.close();
    }
}