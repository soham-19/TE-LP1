import java.util.*;
public class p {
    static Scanner sc= new Scanner(System.in);
    public static class Task{
        public int id;
        public int bt;
        public int priority_no;
        public int wt;
        public int tat;
    }

    public static void input(Task T[])
    {
        for(int i=0;i<T.length; i++)
        {
            T[i].id=i+1;
            System.out.println("Enter the burst time for T"+(i+1));
            T[i].bt=sc.nextInt();
            System.out.println("Enter the Priority_no for T"+(i+1));
            T[i].priority_no=sc.nextInt();
        }
    }

    public static void sort(Task T[])
    {
        for(int i=0;i<T.length; i++ )
        {
            Task temp =  new Task();
            for(int j=i+1; j<T.length;j++)
            {
                if(T[i].priority_no>T[j].priority_no)
                {
                    temp=T[i];
                    T[i]=T[j];
                    T[j]=temp;
                }
                else if(T[i].priority_no == T[j].priority_no)
                {
                    if(T[i].bt>T[j].bt)
                    {
                        temp=T[i];
                        T[i]=T[j];
                        T[j]=temp;
                    }
                }
            }
        }
    }
    public static void calc(Task T[])
    {
        T[0].wt=0;
        for(int i=1;i<T.length;i++)
        {
            T[i].wt=T[i-1].wt+T[i-1].bt;
        }
        for(int i=0;i<T.length;i++)
        {
            T[i].tat=T[i].wt+T[i].bt;
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
    public static void display(Task T[])
    {
        System.out.println("Task BT Priority WT TAT");
        for(int i=0; i<T.length;i++)
        {
            System.out.println("T"+T[i].id+"\t"+T[i].bt+"\t"+T[i].priority_no+"\t"+T[i].wt+"\t"+T[i].tat);
        }
    }

    public static void avg(Task T[])
    {
        float avgwt = 0;
        float avgtat = 0;
        float wtsum = 0;
        float tatsum = 0;

        for (int i = 0; i < T.length; i++) {
            wtsum += T[i].wt;
            tatsum += T[i].tat;
        }

        avgwt = wtsum / T.length;
        avgtat = tatsum / T.length;

        System.out.println("Average WT is " + avgwt + " & Averag  TAT is " + avgtat);
    }
    public static void main(String args[])
    {
        System.out.println("Enter the number of processe : ");
        int n=sc.nextInt();

        Task T[]= new Task[n];
        for(int i=0;i<T.length;i++)
        {
            T[i]= new Task();
        }

        input(T);
        sort(T);
        calc(T);
        idsort(T);
        display(T);
        avg(T);
    }
}