package CPU;
import java.util.*;
public class RoundRobin {
    
    public static void setProcesses(Process[] P) {
        for(int i=0; i<P.length; i++) {
            P[i] = new Process(i+1);
        }
        System.out.println("Data stored succesfully !");
    }

    public static void displayProcesses(Process[] P) {
        System.out.println("P\tAT\tBT\tWT\tCT\tTAT");
        for(Process p : P) {
            System.out.println("P"+p.id+"\t"+p.arrival+"\t"+p.burst+"\t"+p.waiting+"\t"+p.completion+"\t"+p.turnAround);
        }
        System.out.println();
    }

    public static void sortAT(Process[] P){
        for(int i=0; i<P.length-1; i++) {
            Process temp;
            for(int j=i+1; j<P.length; j++) {
                if(P[i].arrival > P[j].arrival) {
                    temp = P[i];
                    P[i] = P[j];
                    P[j] = temp;
                }
            }
        }
    }

    public static void sortID(Process[] P){
        for(int i=0; i<P.length-1; i++) {
            Process temp;
            for(int j=i+1; j<P.length; j++) {
                if(P[i].id > P[j].id) {
                    temp = P[i];
                    P[i] = P[j];
                    P[j] = temp;
                }
            }
        }
    }

    public static void displayResults(Process[] P) {
        float avgWT = 0, avgTAT = 0, avgCT = 0;
        for(Process p : P) {
            avgCT += p.completion;
            avgTAT += p.turnAround;
            avgWT += p.waiting;
        }

        avgCT /= P.length;
        avgTAT /= P.length;
        avgWT /= P.length;

        System.out.println("AVG WT : " + avgWT);
        System.out.println("AVG CT : " + avgCT);
        System.out.println("AVG TAT : " + avgTAT);
    }

    public static void roundRobin(Process[] P) {

        int n = P.length;
        int tt = 0;
        Queue<Process> q = new LinkedList<>();

        int rem = n;

        while(rem > 0) {

            for(int i=0; i<n; i++) {

                if(P[i].arrival < tt && P[i].trav > 0) {
                    
                }
            }
        }
    }
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        Process[] P = new Process[n];

        setProcesses(P);
        displayProcesses(P);
        sortAT(P);

    }
}

class Process {

    int id;
    int arrival;
    int burst;
    int trav;
    int waiting;
    int completion;
    int turnAround;

    Scanner sc = new Scanner(System.in);

    public Process(int id) {
        this.id = id;

        System.out.print("P"+id + " : ");
        System.out.print("\tAT :");
        arrival = sc.nextInt();
        System.out.print("\tBT :");
        burst = sc.nextInt();
        trav = burst;
    }
}