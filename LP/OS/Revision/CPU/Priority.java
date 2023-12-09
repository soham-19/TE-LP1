import java.util.*;
public class Priority {

    public static void setProcesses(Process[] P) {

        for(int i=0; i<P.length; i++) {
            P[i] = new Process(i+1);
        }

        System.out.println("Data stored successfully.");
    }

    public static void sortPriority(Process[] P) {

        for(int i=0; i<P.length; i++) {
            Process swap;
            for(int j=i+1; j<P.length; j++) {
                if(P[i].priority > P[j].priority) {
                    swap = P[i];
                    P[i] = P[j];
                    P[j] = swap;
                } else if(P[i].priority == P[j].priority) {
                    if(P[i].burst > P[j].burst) {
                        swap = P[i];
                        P[i] = P[j];
                        P[j] = swap;
                    }
                }
            }
        }
    }

    public static void calculate(Process[] P) {

        P[0].waiting = 0;

        for(int i=1; i<P.length; i++) {
            P[i].waiting = P[i-1].waiting + P[i-1].burst;
        }

        for(int i=0; i<P.length; i++) {
            P[i].turnAround = P[i].waiting + P[i].burst;
        }
    }

    public static void sortId(Process[] P) {
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

    public static void displayDetails(Process[] P) {

        float avgWaiting = 0, avgTurnAround = 0;

        for(Process p : P) {
            avgWaiting += p.waiting;
            avgTurnAround += p.turnAround;
        }

        avgWaiting /= P.length;
        avgTurnAround /= P.length;

        System.out.println("Avg Waiting Time :\t" + avgWaiting);
        System.out.println("AVg Turn-Around Time :\t" + avgTurnAround);
    }

    public static void displayProcesses(Process[] P) {
        System.out.println("PId\tP\tAT\tBT\tWT\tTAT");
        System.out.println("-----------------------------------------");
        for(Process p : P) {
            System.out.println(p.id + "\t" + p.priority + "\t" + p.arrival + "\t" + p.burst + "\t" + p.waiting + "\t" + p.turnAround);
            System.out.println("-----------------------------------------");
        }
    }
    
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes :\t");
        int n = sc.nextInt();

        Process[] P = new Process[n];

        setProcesses(P);

        sortPriority(P);
        displayProcesses(P);
        calculate(P);

        sortId(P);
        displayProcesses(P);
        displayDetails(P);
    }
}

class Process {

    int id;
    int arrival;
    int burst;
    int priority;
    int waiting;
    int turnAround;

    Scanner sc = new Scanner(System.in);

    public Process(){}

    public Process(int id) {
        this.id = id;

        arrival = 0;

        System.out.print("Enter burst time for P" + id + " :\t");
        burst = sc.nextInt();

        System.out.print("Enter priority time for P" + id + " :\t");
        priority = sc.nextInt();

        waiting = turnAround = 0;
    }
}