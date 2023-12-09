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