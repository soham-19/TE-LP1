package FINAL.OS.PRS;

import java.util.ArrayList;
import java.util.Scanner;

public class PRS {

    static Scanner sc = new Scanner(System.in);

    public static void fifo(int[] arr) {
        int hit = 0, miss = 0;
        System.out.print("fSize : ");
        int fSize = sc.nextInt();
        ArrayList<Integer> frame = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (frame.size() < fSize) {
                if (frame.contains(arr[i])) {
                    hit++;
                } else {
                    miss++;
                    frame.add(arr[i]);
                }
            } else {
                if (frame.contains(arr[i])) {
                    hit++;
                } else {
                    miss++;
                    frame.set(idx, arr[i]);
                    idx = (idx + 1) % fSize;
                }
            }
            System.out.println("Frame : " + frame);
        }
        System.out.println("Total Hit : " + hit);
        System.out.println("Total Miss : " + miss);
    }

    public static void lru(int[] arr) {
        int hit = 0, miss = 0;

        System.out.print("fSize : ");
        int fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();

        for(int i=0; i<arr.length; i++) {

            if(frame.size() < fSize) {
                if(frame.contains(arr[i])) {
                    hit++;
                    int idx = track.indexOf(arr[i]);
                    track.remove(idx);
                    track.add(arr[i]);
                } else {
                    miss++;
                    frame.add(arr[i]);
                    track.add(arr[i]);
                }
            } else {
                if(frame.contains(arr[i])) {
                    hit++;
                    int idx = track.indexOf(arr[i]);
                    track.remove(idx);
                    track.add(arr[i]);
                } else {
                    miss++;
                    int leastUsed = track.get(0);
                    int idx = frame.indexOf(leastUsed);
                    frame.set(idx, arr[i]);
                    track.remove(0);
                    track.add(arr[i]);
                }
            }
            System.out.println("Frame : " + frame + "\tTrack : " + track);
        }
        System.out.println("Total Hit : " + hit);
        System.out.println("Total Miss : " + miss);
    }

    public static void mru(int[] arr) {

        int hit = 0, miss = 0;
        System.out.print("fSize : ");
        int fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();

        for(int i=0; i<arr.length; i++) {
            if(frame.size() < fSize) {
                if(frame.contains(arr[i])) {
                    hit++;
                    int idx = track.indexOf(arr[i]);
                    track.remove(idx);
                    track.add(arr[i]);
                } else {
                    miss++;
                    frame.add(arr[i]);
                    track.add(arr[i]);
                }
            } else {
                if(frame.contains(arr[i])) {
                    hit++;
                    int idx = track.indexOf(arr[i]);
                    track.remove(idx);
                    track.add(arr[i]);
                } else {
                    miss++;
                    int mostUsed = track.get(track.size()-1);
                    int idx = frame.indexOf(mostUsed);
                    frame.set(idx, arr[i]);
                    track.remove(track.size()-1);
                    track.add(arr[i]);

                }
            }
            System.out.println("Frame  : " + frame + "\tTrack : " + track);
        }
        System.out.println("Total Hit : " + hit);
        System.out.println("Total Miss : " + miss);
    }

    public static void optimal(int[] arr) {
        int hit = 0, miss = 0;
        System.out.print("fSize : ");
        int fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();
        for(int i=0; i<arr.length; i++) {
            if(frame.size() < fSize) {
                if(frame.contains(arr[i])) {
                    hit++;
                } else {
                    miss++;
                    frame.add(arr[i]);
                }
            } else {
                if(frame.contains(arr[i])) {
                    hit++;
                } else {
                    miss++;
                    int optimal = getOptimalIndex(arr, i, frame);
                    frame.set(optimal, arr[i]);
                }
            }
            System.out.println("Frame : " + frame);
        }
        System.out.println("Total Hit : " + hit);
        System.out.println("Total Miss : " + miss);
    }

    public static int getOptimalIndex(int[] arr, int curr, ArrayList<Integer> frame) {
        int optimal = -1;
        int far = curr;

        for(int i=0; i<frame.size(); i++) {

            int page = frame.get(i);
            int nxt = getNextOcc(arr, curr, page);
            if(nxt == -1)
                return i;
            if(nxt > far) {
                far = nxt;
                optimal  = i;
            }
        }
        return optimal==-1?0:optimal;
    }

    public static int getNextOcc(int[] arr, int curr, int page) {
        for(int i=curr+1; i<arr.length; i++) {
            if(arr[i]==page)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {

        int arr[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1 };
        fifo(arr);
        lru(arr);
        mru(arr);
        optimal(arr);
    }
}
