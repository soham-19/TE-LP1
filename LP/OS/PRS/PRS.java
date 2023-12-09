package PRS;
import java.util.*;
public class PRS {
    static Scanner sc = new Scanner(System.in);
    public static void fifo(int[] arr) {
        
        int hit = 0, miss = 0;
        int fSize;
        int idx = 0;
        ArrayList<Integer> frame = new ArrayList<>();

        System.out.print("Enter the number frames : ");
        fSize = sc.nextInt();
        for(int i : arr) {
            if(frame.size() < fSize) {
                if(frame.contains(i)) {
                    hit++;
                } else {
                    miss++;
                    frame.add(i);
                }
            } else {
                if(frame.contains(i)) {
                    hit++;
                } else {
                    miss++;
                    frame.set(idx, i);
                    idx = (idx+1)%fSize;
                }
            }
            System.out.println("Frame : " + frame);
        }
        System.out.println("Total Miss : " + miss);
        System.out.println("Total Hit : " + hit);
    }
    public static void lru(int[] arr) {
        int hit = 0, miss = 0;
        int fSize;
        
        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();

        System.out.print("Enter number of frames : ");
        fSize = sc.nextInt();

        for(int i : arr) {

            if(frame.size() < fSize) {

                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    frame.add(i);
                    track.add(i);
                }
            } else {

                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    int leastUsed = track.get(0);
                    int idx = frame.indexOf(leastUsed);
                    frame.set(idx, i);
                    track.remove(0);
                    track.add(i);
                }
            }

            System.out.println("Frame : " + frame + "\t\tTrack : " + track);
        }
        System.out.println("Total Miss : " + miss);
        System.out.println("Total Hit : " + hit);        
    }
    public static void mru(int[] arr) {

        int hit = 0, miss = 0, fSize;

        System.out.print("Enter number of frames : ");
        fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();
        for(int i : arr) {

            if(frame.size() < fSize) {
                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    frame.add(i);
                    track.add(i);
                }
            } else {
                if(frame.contains(i)) {
                    hit++;
                    int idx = track.indexOf(i);
                    track.remove(idx);
                    track.add(i);
                } else {
                    miss++;
                    int MRU = track.get(track.size()-1);
                    int idx = frame.indexOf(MRU);
                    frame.set(idx, i);
                    track.remove(track.size()-1);
                    track.add(i);
                }
            }
            System.out.println("Frame : " + frame + "\t\tTrack : " + track);
        }
        System.out.println("Total Miss : " + miss);
        System.out.println("Total Hit : " + hit);               
    }    
    public static void optimal(int[] arr) {
        int hit = 0, miss = 0, fSize;
        System.out.print("Enter number of frames : ");
        fSize = sc.nextInt();
        
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
                    int idx = optimalIndex(arr, i, frame);
                    frame.set(idx, arr[i]);
                }
            }

            System.out.println("Frame : " + frame);
        }
        System.out.println("Total Miss : " + miss); 
        System.out.println("Total Hit : " + hit);               
    }
    private static int optimalIndex(int[] arr, int curr, ArrayList<Integer> frame) {
        int opt = -1;
        int far = curr;

        for(int i=0; i<frame.size(); i++) {
            int page = frame.get(i);
            int nxt = next(arr, curr, page);
            if(nxt ==-1){
                return i;
            }
            if(nxt > far) {
                far = nxt;
                opt = i;
            }
        }

        return opt == -1 ? 0 : opt;
    }
    private static int next(int[] arr, int curr, int page) {
        for(int i=curr+1; i<arr.length; i++) {
            if(arr[i] == page) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) { 
        int arr[] = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1 };
        optimal(arr);
    }
}