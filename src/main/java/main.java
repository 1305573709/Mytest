public class main {
    public static boolean isPalindrome(int x) {
        if(x < 0) return false;
        if(x < 10) return true;
        int sum = 0;
        int i = 0;
        while(x > 0){
            sum = sum * 10 + x % 10;
            x = x / 10;
        }
        System.out.println(sum);
        return sum == x;
    }

    public static void main(String[] args) {
        int x = 121;
        int y = 121;
        System.out.println(x == y);
        boolean b = isPalindrome(121);
        System.out.println(b);
    }
}
