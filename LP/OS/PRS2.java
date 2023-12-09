import java.util.*;

public class PRS2 {
    static Scanner sc = new Scanner(System.in);

    public static void optimal(int[] arr) {
        int miss = 0;
        int hit = 0;
        System.out.print("Enter the frame size ");
        int fSize = sc.nextInt();

        ArrayList<Integer> frame = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (frame.size() < fSize) {
                if (!frame.contains(arr[i])) {
                    miss++;
                    frame.add(arr[i]);
                } else {
                    hit++;
                }
            } else {
                int temp = arr[i];
                if (frame.contains(temp)) {
                    hit++;
                } else {
                    miss++;
                    int index = optimalIndex(arr, i, frame);
                    frame.set(index, temp);
                }
            }

        }

        System.out.println("Page Miss : " + miss);
        System.out.println("Hit : " + hit);
    }

    private static int optimalIndex(int[] arr, int curr, ArrayList<Integer> frame) {
        int far = curr;
        int optimal = -1;

        for (int i = 0; i < frame.size(); i++) {
            int page = frame.get(i);
            int nextOccurrence = next(arr, curr, page);
            if (nextOccurrence == -1) {
                return i;
            }
            if (nextOccurrence > far) {
                far = nextOccurrence;
                optimal = i;
            }
        }

        return (optimal == -1) ? 0 : optimal;
    }
 
    private static int next(int[] arr, int curr, int page) {
        for (int i = curr + 1; i < arr.length; i++) {
            if (arr[i] == page) {
                return i;
            }
        }
        return -1;
    }

    
    public static void main(String[] args) {
        int arr[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        optimal(arr);
    }
}