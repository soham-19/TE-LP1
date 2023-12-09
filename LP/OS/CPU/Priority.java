package CPU;
import java.util.*;
public class Priority {
    
    public static void setTasks(Task[] T) {

        for(int i=0; i<T.length; i++) {
            T[i] = new Task(i+1);
        }
    }

    public static void sortTasks(Task[] T) {

        for(int i=0; i<T.length; i++) {

            Task temp = new Task();

            for(int j=i+1; j<T.length; j++) {

                if(T[i].priority > T[j].priority) {
                    temp = T[i];
                    T[i] = T[j];
                    T[j] = temp;
                } else if (T[i].priority == T[j].priority) {
                    if(T[i].burst > T[j].burst) {
                        temp = T[i];
                        T[i] = T[j];
                        T[j] = temp;
                    }
                }
            }
        }
    }

    public static void calculate(Task[] T) {

        T[0].waiting = 0;

        for(int i=1; i<T.length; i++) {
            T[i].waiting = T[i-1].waiting + T[i-1].burst;
        }

        for(int i=0; i<T.length; i++) {
            T[i].turnAround = T[i].waiting + T[i].burst;
        }
    }

    public static void sortByIds(Task[] T) {

        for(int i=0; i<T.length; i++) {
            Task temp;
            for(int j=i+1; j<T.length; j++) {
                if(T[i].id > T[j].id) {
                    temp = T[i];
                    T[i] = T[j];
                    T[j] = temp;
                }
            }
        }
    }

    public static void display(Task[] T) {
        System.out.println("Task\tBurst\tPriority\tWaiting\tTurn-Around");
        for(Task t : T) {
            System.out.println("T" + t.id + "\t" + t.burst + "\t" + t.priority + "\t" + t.waiting + "\t" + t.turnAround);
        }
    }

    public static void average(Task[] T) {

        float avgWaiting = 0, avgTurnAround = 0;

        for(Task t : T) {
            avgWaiting += t.waiting;
            avgTurnAround += t.turnAround;
        }

        avgTurnAround /= T.length;
        avgWaiting /= T.length;

        System.out.println("Avg Waiting Time : " + avgWaiting);
        System.out.println("Avg TurnAround Time : " + avgTurnAround);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of tasks : ");
        int n = sc.nextInt();

        Task[] T = new Task[n];

        setTasks(T);
        sortTasks(T);
        calculate(T);
        sortByIds(T);
        display(T);
        average(T);
    }
}

class Task {
    int id;
    int arrival;
    int burst;
    int priority;
    int waiting;
    int turnAround;

    Scanner sc = new Scanner(System.in);

    public Task(){}
    public Task(int id) {

        this.id = id;

        arrival = 0;

        System.out.println("Enter burst time for T" + (id));
        burst = sc.nextInt();

        System.out.println("Enter priority for T" + (id));
        priority = sc.nextInt();

    }
}