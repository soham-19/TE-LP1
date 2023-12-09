package FINAL.OS.CPU;

import java.util.Scanner;

public class SJF {

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

    public static void scheduleSJF(Process[] p) {
        int time = 0;
        int completed = 0;
        while(completed < p.length) {
            int sj_Idx = -1, sj_Time = Integer.MAX_VALUE;
            for(int i=0; i<p.length; i++) {
                Process curr = p[i];
                if(curr.arrival <= time && curr.remain > 0 && curr.remain < sj_Time) {
                    sj_Idx = i;
                    sj_Time = curr.remain;
                }
            }
            if(sj_Idx == -1) {
                time++;
            } else {
                Process sj = p[sj_Idx];
                sj.remain--;
                time++;
                if(sj.remain == 0) {
                    completed++;
                    sj.turnAround = time - sj.arrival;
                    sj.waiting = sj.turnAround - sj.burst;
                }
            }
        }
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

    public static void main(String[] args) {

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        Process[] p = new Process[n];

        setProcesses(p);
        scheduleSJF(p);
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

/*
 * Enter number of processes : 4
 * Enter AT for P1 : 4
 * Enter BT for P1 : 7
 * Enter AT for P2 : 0
 * Enter BT for P2 : 4
 * Enter AT for P3 : 2
 * Enter BT for P3 : 1
 * Enter AT for P4 : 3
 * Enter BT for P4 : 3
 * Data stored successfully !!
 * PId AT BT
 * ------------------------
 * P1 4 7
 * ------------------------
 * P2 0 4
 * ------------------------
 * P3 2 1
 * ------------------------
 * P4 3 3
 * ------------------------
 * 
 * PId AT BT WT TAT CT
 * ------------------------------------------
 * P1 4 7 4 11 15
 * ------------------------------------------
 * P2 0 4 1 5 5
 * ------------------------------------------
 * P3 2 1 0 1 3
 * ------------------------------------------
 * P4 3 3 2 5 8
 * ------------------------------------------
 * 
 * Avg WT : 1.75
 * Avg TAT : 5.5
 * Avg CT : 7.75
 */