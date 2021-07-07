package fcam;

public class array {
    public static void main(String[] args) {

        String[] str = {"hello world1", "hello world2", "hello world3", "hello world4", "hello world5"};
        int count = 0;
        for(String s : str) {
            for(int i=0; i<s.length(); i++) {
                if(s.charAt(i) == 'w') {
                    count++;
                }
            }
        }
        System.out.println("count : " + count);
    }
}
