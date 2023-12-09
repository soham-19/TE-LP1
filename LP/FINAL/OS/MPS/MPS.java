package FINAL.OS.MPS;

import java.util.Scanner;

public class MPS {

    static Scanner sc = new Scanner(System.in);

    public static void setProcesses(Process[] p, Block[] b) {
        for (int i = 0; i < p.length; i++)
            p[i] = new Process(i + 1);

        for (int j = 0; j < b.length; j++) {
            b[j] = new Block(j + 1);
        }
    }

    public static void reset(Process[] p, Block[] b) {
        for (int i = 0; i < p.length; i++) {
            p[i].block = 0;
            p[i].fragmentation = 0;
        }
        for (int i = 0; i < b.length; i++) {
            b[i].freeSpace = b[i].size;
        }

        for (int i = 0; i < b.length; i++) {
            for (int j = i + 1; j < b.length; j++) {
                if (b[i].bId > b[j].bId) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }

    public static void firstFit(Process[] p, Block[] b) {
        for (int i = 0; i < p.length; i++) {
            Process curr = p[i];
            for (int j = 0; j < b.length; j++) {
                if (curr.size <= b[j].freeSpace) {
                    curr.block = b[j].bId;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void nextFit(Process[] p, Block[] b) {
        int m = b.length;
        int idx = -1;
        for (int i = 0; i < p.length; i++) {
            Process curr = p[i];
            for(int j=(idx+1)%m; j!=idx; j=(j+1)%m) {
                if(curr.size <= b[j].freeSpace) {
                    curr.block = b[j].bId;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    idx = j;
                    break;
                }
            }
        }
    }

    public static void bestFit(Process[] p, Block[] b) {
        for(int i=0; i<p.length; i++) {
            Process curr = p[i];
            sortAsc(b);
            for(int j=0; j<b.length; j++) {
                if(curr.size <= b[j].freeSpace) {
                    curr.block = b[j].bId;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void worstFit(Process[] p, Block[] b) {
        for(int i=0; i<p.length; i++) {
            Process curr = p[i];
            sortDesc(b);
            for(int j=0; j<b.length; j++) {
                if(curr.size <= b[j].freeSpace) {
                    curr.block = b[j].bId;
                    b[j].freeSpace -= curr.size;
                    curr.fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void sortAsc(Block[] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = i + 1; j < b.length; j++) {
                if (b[i].freeSpace > b[j].freeSpace) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }

    public static void sortDesc(Block[] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = i + 1; j < b.length; j++) {
                if (b[i].freeSpace < b[j].freeSpace) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }

    public static void printResults(Process[] p) {

        System.out.println("PId\tSize\tBlock\tFrag");
        System.out.println("---------------------------------------------");
        for (Process pi : p) {
            System.out.print("P" + pi.pId + "\t" + pi.size + "\t");
            if (pi.block == 0) {
                System.out.println("-\tNA");
            } else {
                System.out.println("B" + pi.block + "\t" + pi.fragmentation);
            }
            System.out.println("---------------------------------------------");
        }
    }

    public static void main(String[] args) {

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();
        System.out.println();

        System.out.print("Enter number of processes : ");
        int m = sc.nextInt();
        System.out.println();

        Process[] p = new Process[n];
        Block[] b = new Block[m];

        setProcesses(p, b);

        while (true) {
            System.out.println("1-first fit\n2-next fit\n3-best fit\n4-worst fit");

            int choice = sc.nextInt();
            reset(p, b);

            switch (choice) {
                case 1:
                    firstFit(p, b);
                    break;
                case 2:
                    nextFit(p, b);
                    break;
                case 3:
                    bestFit(p, b);
                    break;
                case 4:
                    worstFit(p, b);
                    break;
            }

            if (choice < 5) {
                printResults(p);
            }
        }

    }

}

class Process {
    int pId;
    int size;

    int block;
    int fragmentation;

    Scanner sc = new Scanner(System.in);

    public Process(int id) {
        pId = id;

        System.out.print("\t-P" + pId + " Size : ");
        size = sc.nextInt();

        block = fragmentation = 0;
    }
}

class Block {
    int bId;
    int size;

    int freeSpace;

    Scanner sc = new Scanner(System.in);

    public Block(int id) {
        bId = id;

        System.out.print("\t-B" + bId + " Size : ");
        size = sc.nextInt();

        freeSpace = size;
    }
}
