public class RecursiveCountdown {
    public static void main(String[] args) {
        System.out.println(countdown(31));
    }

    public static String countdown(int target) {
        if (target == 0) {
            return "0";
        }

        return target + countdown(target - 1);
    }
}
