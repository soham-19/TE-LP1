package Final.MPS;

import java.util.*;

public class MPS {
    static Scanner sc = new Scanner(System.in);

    public static void storeData(Process[] p, Block[] b) {
        for (int i = 0; i < p.length; i++) {
            p[i] = new Process(i + 1);
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = new Block(i + 1);
        }
        System.out.println("Processes : \t");
        System.out.println("PId\tSize");
        System.out.println("------------");
        for (Process pi : p) {
            System.out.println("P" + pi.pId + "\t" + pi.size);
            System.out.println("------------");
        }
        System.out.println();
        System.out.println("Blocks : \t");
        System.out.println("BId\tSize");
        System.out.println("------------");
        for (Process pi : p) {
            System.out.println("B" + pi.pId + "\t" + pi.size);
            System.out.println("------------");
        }
        System.out.println("\nData stored Successfully!\n");
    }

    public static void bestFit(Process[] p, Block[] b) {
        for(int i=0; i<p.length; i++) {
            sortAsc(b);
            for(int j=0; j<b.length; j++) {
                if(p[i].size <= b[j].freeSpace) {
                    p[i].block = b[j].bId;
                    b[j].freeSpace -= p[i].size;
                    p[i].fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void worstFit(Process[] p, Block[] b) {
        for(int i=0; i<p.length; i++) {
            sortDesc(b);
            for(int j=0; j<b.length; j++) {
                if(p[i].size <= b[j].freeSpace) {
                    p[i].block = b[j].bId;
                    b[j].freeSpace -= p[i].size;
                    p[i].fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void firstFit(Process[] p, Block[] b) {

        for(int i=0; i<p.length; i++) {
            for(int j=0; j<b.length; j++) {
                if(p[i].size <= b[j].freeSpace) {
                    p[i].block = b[j].bId;
                    b[j].freeSpace -= p[i].size;
                    p[i].fragmentation = b[j].freeSpace;
                    break;
                }
            }
        }
    }

    public static void nextFit(Process[] p, Block[] b) {

        int block = -1;
        int m = b.length;
        for(int i=0; i<p.length; i++) {
            for(int j=(block+1)%m; j != block; j=(j+1)%m) {
                if(p[i].size <= b[j].freeSpace) {
                    p[i].block = b[j].bId;
                    b[j].freeSpace -= p[i].size;
                    p[i].fragmentation = b[j].freeSpace;
                    block = j;
                    break;
                }
            }
        }
    }

    public static void sortAsc(Block[] b) {
        for(int i=0; i<b.length; i++) {
            for(int j=i+1; j<b.length; j++) {
                if(b[i].freeSpace > b[j].freeSpace) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }

    public static void sortDesc(Block[] b) {
        for(int i=0; i<b.length; i++) {
            for(int j=i+1; j<b.length; j++) {
                if(b[i].freeSpace < b[j].freeSpace) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }

    public static void reset(Process[] p, Block[] b) {
        for(Process pi : p) {
            pi.block = 0;
            pi.fragmentation = 0;
        }
        for(Block bi : b) {
            bi.freeSpace = bi.size;
        }
        for(int i=0; i<p.length; i++) {
            for (int j=i+1; j<b.length; j++){
                if(b[i].bId > b[j].bId) {
                    Block temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;
                }
            }
        }
    }

    public static void displayResults(Process[] p) {
        System.out.println("PId\tSize\tBlock\tFrag");
        System.out.println("------------");
        for (Process pi : p) {
            System.out.println("P" + pi.pId + "\t" + pi.size + "\t" + pi.block + "\t" + pi.fragmentation);
            System.out.println("------------");
        }
    }

    public static void main(String[] args) {

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        System.out.print("Enter number of blocks : ");
        int m = sc.nextInt();

        Process[] p = new Process[n];
        Block[] b = new Block[m];

        storeData(p, b);

        while (true) {
            System.out.print("Enter your choice : \n\t1-best fit\n\t2-worst fit\n\t3-first fit\n\tnext fit");
            int choice = sc.nextInt();
            reset(p, b);
            switch (choice) {
                case 1:
                    bestFit(p, b);
                    break;
                case 2:
                    worstFit(p, b);
                    break;
                case 3:
                    firstFit(p, b);
                    break;
                case 4:
                    nextFit(p, b);
                    break;
                
                case 0:
                    break;

                default:
                    System.out.println("Invalid input!!");
            }

            if (choice > 0 && choice < 5) {
                displayResults(p);
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
        System.out.print("\tP" + id + " : Size : ");
        size = sc.nextInt();

        fragmentation = block = 0;
    }
}

class Block {

    int bId;
    int size;

    int freeSpace;

    Scanner sc = new Scanner(System.in);

    public Block(int id) {
        bId = id;
        System.out.print("\tB" + id + " : Size : ");
        size = sc.nextInt();
        freeSpace = size;
    }
}