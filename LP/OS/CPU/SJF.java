import java.util.Scanner;

public class SJF {

    static Scanner sc = new Scanner(System.in);

    public static void setProcesses(Process[] p) {
        for (int i = 0; i < p.length; i++) {
            p[i] = new Process(i + 1);
        }

        System.out.println("Data stored successfully!!");
    }

    public static void display(Process[] p) {
        System.out.println("P\tWT\tTAT");
        System.out.println("-------------------");
        for (Process pi : p) {
            System.out.println("P" + pi.id + "\t" + pi.waiting + "\t" + pi.turnAround);
            System.out.println("-------------------");
        }
    }

    public static void sjf(Process[] p) {
        int time = 0;
        int completed = 0;

        while(completed < p.length) {
            int sj_Idx = -1;
            int sj_Time = Integer.MAX_VALUE;
            for(int i=0; i<p.length; i++) {
                Process curr = p[i];
                if(curr.remain > 0 && curr.arrival <= time && curr.remain < sj_Time) {
                    sj_Idx = i;
                    sj_Time = curr.remain;
                }
            }
            if(sj_Idx == -1){
                time++;
            } else {
                Process sj = p[sj_Idx];
                //execute
                sj.remain--;
                time++;
                if(sj.remain == 0) {
                    completed++;
                    System.out.println("P"+ sj.id + " completed");

                    sj.turnAround = time - sj.arrival;
                    sj.waiting = sj.turnAround - sj.burst;
                }
            }
        }
    }

    public static void avg(Process[] p) {
        float wt = 0, tat = 0;
        for (Process pi : p) {
            tat += pi.turnAround;
            wt += pi.waiting;
        }

        wt /= p.length;
        tat /= p.length;

        System.out.println("Avg WT : " + wt);
        System.out.println("Avg TAT : " + tat);
    }

    public static void main(String[] args) {

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        setProcesses(p);
        sjf(p);
        display(p);
        avg(p);
    }
}

class Process {
    int id;
    int arrival;
    int burst;
    int waiting;
    int turnAround;
    int remain;

    Scanner sc = new Scanner(System.in);

    public Process(int id) {
        this.id = id;

        System.out.print("Enter Arrival Time for P" + id + " : ");
        arrival = sc.nextInt();
        System.out.print("Enter Burst Time for P" + id + " : ");
        burst = sc.nextInt();

        waiting = turnAround = 0;
        remain = burst;
    }
}