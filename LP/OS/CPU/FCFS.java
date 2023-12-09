

import java.util.*;

public class FCFS {

    public static void fcfs(Process[] P) {

        P[0].waiting = 0;
        P[0].completion = P[0].burst;

        for(int i=1; i<P.length; i++) {
            P[i].completion = P[i-1].completion + P[i].burst;
            P[i].waiting = P[i].completion - P[i].burst - P[i].arrival;
            P[i].turnAround = P[i].waiting + P[i].burst;
        }
    }

    public static void printResults(Process[] P) {

        float avgWT = 0, avgTAT = 0, avgCT = 0;
        for(Process p : P) {
            avgCT += p.completion;
            avgTAT += p.turnAround;
            avgWT += p.waiting;
        }

        avgCT /= P.length;
        avgTAT /= P.length;
        avgWT /= P.length;

        System.out.println("Avg WT : \t"+ avgWT);
        System.out.println("Avg TAT : \t"+ avgTAT);
        System.out.println("Avg CT : \t"+ avgCT);
    }

    public static void setProcesses(Process[] P) {
        for (int i = 0; i < P.length; i++) {
            P[i] = new Process(i + 1);
        }
        System.out.println("Data stored successfully !");
    }

    public static void sortArrival(Process[] P) {
        for (int i = 0; i < P.length; i++) {
            Process temp;
            for (int j = i + 1; j < P.length; j++) {
                if (P[i].arrival > P[j].arrival) {
                    temp = P[i];
                    P[i] = P[j];
                    P[j] = temp;
                } else if (P[i].arrival == P[j].arrival) {
                    if (P[i].burst > P[j].burst) {
                        temp = P[i];
                        P[i] = P[j];
                        P[j] = temp;
                    }
                }
            }
        }
    }

    public static void sortId(Process[] P) {
        for (int i = 0; i < P.length - 1; i++) {
            Process temp;
            for (int j = i + 1; j < P.length; j++) {
                if (P[i].id > P[j].id) {
                    temp = P[i];
                    P[i] = P[j];
                    P[j] = temp;
                }
            }
        }
    }

    public static void displayProcesses(Process[] P) {
        System.out.println("P\tAT\tBT\tWT\tCT\tTAT");
        System.out.println("------------------------------");
        for (Process p : P) {
            System.out.println("P" + p.id + "\t" + p.arrival + "\t" + p.burst + "\t" + p.waiting + "\t" + p.completion + "\t"+ p.turnAround);
            System.out.println("------------------------------");
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes : ");
        int n = sc.nextInt();

        Process[] P = new Process[n];

        setProcesses(P);
        sortArrival(P);
        displayProcesses(P);
        fcfs(P);
        sortId(P);
        displayProcesses(P);
    }
}

class Process {

    int id;
    int arrival;
    int burst;
    int waiting;
    int turnAround;
    int completion;

    Scanner sc = new Scanner(System.in);

    public Process(int id) {
        this.id = id;
        System.out.print("Enter arrival time for P" + id + " ");
        arrival = sc.nextInt();
        System.out.print("Enter burst time for P" + id + " ");
        burst = sc.nextInt();
        waiting = turnAround = completion = 0;
    }
}
