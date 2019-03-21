import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Terminal
{
    // instance variables - replace the example below with your own
    private Scanner sc;

    /**
     * Constructor for objects of class Terminal
     */
    public Terminal()
    {
        // initialise instance variables
                sc = new Scanner(System.in);
    }

    public String[] prompt() {
        print("\nYou:  ", 0, false);
        String s = sc.nextLine();
        if (s.indexOf(' ') != -1)
            return new String[]{s.substring(0, s.indexOf(' ')), s.substring(s.indexOf(' ') + 1)};
        return new String[]{s, ""};
    }
    
        //printing method, slows down printing
    //Bluej doesnt print err messages inside the same window as out,
    // so it cant be used to achieve multiple colors inside terminal
    public void print(String text, int speed, boolean isRed) {
        if (text != null)
            for (int i = 0; i < text.length(); i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(speed);
                } catch (InterruptedException ignored) {
                }
                if (isRed)
                    System.out.print(text.charAt(i));//System.err.print(text.charAt(i));
                else
                    System.out.print(text.charAt(i));
            }
        try {
            TimeUnit.MILLISECONDS.sleep(speed);
        } catch (InterruptedException ignored) {
        }
    }
}
