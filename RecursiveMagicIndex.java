import java.util.Arrays;
import java.util.List;

public class RecursiveMagicIndex {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-1, 0, 20, 13, 6, 10, 7, 8);

        System.out.println(numbers);
        System.out.println(magicIndex(numbers));
    }

    public static int magicIndex(List<Integer> numbers) {
        return recursiveMagicIndex(numbers, 0, numbers.size() - 1);
    }

    private static int recursiveMagicIndex(List<Integer> numbers, int start, int end) {
        if (end < start) {
            return -1;
        }

        int mid = (start + end) / 2;

        if (numbers.get(mid) == mid) {
            return mid;
        } else if(numbers.get(mid) > mid) {
            return recursiveMagicIndex(numbers, 0, mid - 1);
        } else {
            return recursiveMagicIndex(numbers, mid + 1, end);
        }
    }
}
