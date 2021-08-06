import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.*;

public class PasswordTests {

    private static char[] charsOrder = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '!', '#', '$', '%'};

    @Test // single character password
    public void test1(){
        runManager("0", "spaghetti", 12, 2, 2, 2, 2);
    }

    @Test // single character purpose
    public void test2(){
        runManager("password", "s", 12, 2, 2, 2, 2);
    }

    @Test // single character for both
    public void test3(){
        runManager("0", "s", 12, 2, 2, 2, 2);
    }

    @Test // zero characters for both
    public void test4(){
        try {
            PasswordManager.main(new String[]{"", "","f"});
        }
        catch(IOException ie){
            return;
        }
        fail();
    }


    public void runManager(String password, String purpose, int length, int capitals, int lowercase, int digits, int special) {
        try {
            PasswordManager.main(new String[]{password, purpose,"t", "" + length, "" + capitals, "" + lowercase, "" + digits, "" + special});
        }
        catch(IOException ie){
            fail(ie);
        }
    }

    public void runManager(String password, String purpose) {
        try {
            PasswordManager.main(new String[]{password, purpose,"f"});
        }
        catch(IOException ie){
            fail(ie);
        }
    }

}
