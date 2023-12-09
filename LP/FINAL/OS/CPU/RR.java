package FINAL.OS.CPU;

import java.util.*;

public class RR {
    static Scanner sc = new Scanner(System.in);

    public static void setProcesses(Process[] p) {
        for (int i = 0; i < p.length; i++) {
            p[i] = new Process(i + 1);
        }
        System.out.println("Data stored successfully !!");
        System.out.println("PId\tAT\tBT");
        System.out.println("------------------------");
        for (Process pi : p) {
            System.out.println("P" + pi.id + "\t" + pi.arrival + "\t" + pi.burst);
            System.out.println("------------------------");
        }
        System.out.println();
    }

    public static void displayResults(Process[] p) {
        System.out.println("PId\tAT\tBT\tWT\tTAT\tCT");
        System.out.println("------------------------------------------");
        for (Process pi : p) {
            System.out.println("P" + pi.id + "\t" + pi.arrival + "\t" + pi.burst + "\t" + pi.waiting + "\t"
                    + pi.turnAround + "\t" + pi.completion);
            System.out.println("------------------------------------------");
        }
        System.out.println();
    }

    public static void calculateAvg(Process[] p) {
        float waiting = 0, turnAround = 0, completion = 0;
        for (Process pi : p) {
            waiting += pi.waiting;
            turnAround += pi.turnAround;
            completion += pi.completion;
        }

        waiting /= p.length;
        turnAround /= p.length;
        completion /= p.length;

        System.out.println("Avg WT : " + waiting);
        System.out.println("Avg TAT : " + turnAround);
        System.out.println("Avg CT : " + completion);
    }

    public static void rr(Process[] p) {
        int time = 0, remaining = p.length;
        System.out.print("Quantum : ");
        int Quantum = sc.nextInt();

        while(remaining > 0) {
            for(int i=0; i<p.length; i++) {
                Process curr = p[i];
                if(curr.remain > 0 && curr.arrival <= time) {
                    if(curr.remain <= Quantum) {
                        time += curr.remain;
                        curr.remain = 0;
                        curr.completion = time;  
                        remaining--;              
                    } else {
                        time += Quantum;
                        curr.remain -= Quantum;
                    }
                }
            }
        }

        for(int i=0; i<p.length; i++) {
            p[i].turnAround = p[i].completion - p[i].arrival;
            p[i].waiting = p[i].completion - p[i].burst - p[i].arrival;
        }
    }
    public static void main(String[] args) {

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        setProcesses(p);
        rr(p);
        displayResults(p);
        calculateAvg(p);

    }
}

class Process {
    int id;
    int arrival;
    int burst;
    int waiting;
    int turnAround;
    int completion;
    int remain;

    Scanner sc = new Scanner(System.in);

    public Process(int id) {
        this.id = id;

        System.out.print("Enter AT for P" + id + " : ");
        arrival = sc.nextInt();

        System.out.print("Enter BT for P" + id + " : ");
        burst = sc.nextInt();
        remain = burst;
        turnAround = waiting = completion = 0;
    }

}