import java.util.*;

class fcfs {
    static Scanner sc = new Scanner(System.in);

    public static class Task{
        public int id;
        public int at;
        public int bt;
        public int wt;
        public int tat;
        public int ct;
    }
    
    public static void input(Task T[])
    {
        for(int i=0;i<T.length;i++)
        {
            T[i].id=(i+1);
            System.out.println("Enter the Arrival Time for T"+(i+1));
            T[i].at=sc.nextInt();
            System.out.println("Enter the Burst Time for T"+(i+1));
            T[i].bt=sc.nextInt();
        }
    }

    public static void sort(Task T[])
    {
        for(int i=0;i<T.length;i++)
        {
            Task temp;
            for(int j=i+1;j<T.length;j++)
            {
                if(T[i].at>T[j].at)
                {
                    temp=T[i];
                    T[i]=T[j];
                    T[j]=temp;
                }
            }
        }
    }

    public static void calc(Task T[])
    {
        T[0].wt=0;
        T[0].ct=T[0].bt;
        
        for(int i=1; i<T.length;i++)
        {
            T[i].ct = T[i-1].ct + T[i].bt; 
        }

        for(int i=1; i<T.length; i++)
        {
            T[i].wt=T[i].ct-T[i].at-T[i].bt;
        }
        for(int i=0;i<T.length;i++)
        {
            T[i].tat= T[i].wt + T[i].bt;
        }
    }

    public static void display(Task T[])
    {
        System.out.println("Process\tAT\tBT\tWT\tCt\tTAT");
        for(int i=0;i<T.length;i++)
        {
            System.out.println("T"+T[i].id+"\t"+T[i].at+"\t"+T[i].bt+"\t"+T[i].wt+"\t"+T[i].ct+"\t"+T[i].tat);
        }
    }

    public static void avg(Task T[])
    {
        int wtsum=0;
        int tatsum=0;
        int avgwt=0;
        int avgtat=0;
        for(int i=0;i<T.length;i++)
        {
            wtsum+=T[i].wt;
            tatsum+=T[i].tat;
        }
        avgwt=wtsum/T.length;
        avgtat=tatsum/T.length;

        System.out.println("Average WT is "+avgwt+" & Average TAT is "+avgtat);
    }
    public static void main(String args[])
    {
        System.out.println("Enter the Number of processes  : ");
        int n= sc.nextInt();

        Task T[] = new Task[n];

        for(int i=0; i<n; i++)
        {
            T[i]= new Task();
        }

        input(T);
        sort(T);
        calc(T);
        display(T);
        avg(T);
    }
}

//at ghe non-preem