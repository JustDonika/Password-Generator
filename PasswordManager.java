
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * @Author: Donovan Tull
 * When run, this program asks the user for a general purpose password, and the name of the service. It will then ask the user if default password settings need to be altered.
 * With this information, it should then generate a password.
 * This password must be:
 *  - Compliant with specifications
 *  - Consistent between attempts with the same inputs across any device
 *  - Difficult to crack without reverse engineering this algorithm (which no malicious actor would bother to do for a small pet project)
 *
 */
public class PasswordManager{
    private static int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};
    private static char[] charsOrder = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '!', '#', '$', '%'};
    private static String sqrt;
    private static String generalPassword;
    private static String passPurpose;
    private static int passwordLength=10;
    private static int numDigits = 2;
    private static int numLowercase = 2;
    private static int numCapital = 2;
    private static int numSpecial = 2;
    /**
     * If executed via command terminal, the arguments should be in the form:
     * [general password] [name of what it's used for] followed by either ["t"] (use defaults) OR
     * ["f"] + [password length] + [min number digits] + [min number capital] + [min number lowercase] + [min number special characters]
     * EG: 12dworssap NotMyMethDealer t or 12dworssap NotMyMethDealer f 12 4 2 0 2
     */
    public static void main(String[] args) throws IOException {
        if(args.length==3){
            generalPassword=args[0];
            passPurpose=args[1];
        }
        else if(args.length==8){
            generalPassword=args[0];
            passPurpose=args[1];
            if(Character.isDigit(args[3].charAt(0))) {
                passwordLength = Integer.parseInt(args[3]);
            }
            if(Character.isDigit(args[4].charAt(0))) {
                numDigits = Integer.parseInt(args[4]);
            }
            if(Character.isDigit(args[5].charAt(0))) {
                numLowercase = Integer.parseInt(args[5]);
            }
            if(Character.isDigit(args[6].charAt(0))) {
                numCapital = Integer.parseInt(args[6]);
            }
            if(Character.isDigit(args[7].charAt(0))) {
                numSpecial = Integer.parseInt(args[7]);
            }
        }
        else{
            Scanner userInput = new Scanner(System.in);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "What is your general password?");
            generalPassword = userInput.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "What is the password for?");
            passPurpose = userInput.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "Do you need any parameters beyond the defaults? y/n");
            String continuing = userInput.nextLine();
            if(continuing.equals("y")){
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                        "Password length (input integer)");
                passwordLength = Integer.parseInt(userInput.nextLine());
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                        "Number of digits (input integer)");
                numDigits = Integer.parseInt(userInput.nextLine());
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                        "Number lowercase (input integer)");
                numLowercase = Integer.parseInt(userInput.nextLine());
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                        "Number capital (input integer)");
                numCapital = Integer.parseInt(userInput.nextLine());
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                        "Number special (!#$%) (input integer)");
                numSpecial = Integer.parseInt(userInput.nextLine());
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            }
        }
        //catch nonviable requirements
        if(numSpecial+numCapital+numLowercase+numDigits>passwordLength){
            System.out.println("Impossible to comply with prerequisites; try increasing password length, or reducing other requirements.");
            return;
        }
        //All the inputs have been received, so we can now run the algorithm and find the desired password.
        int startPoint = findStart();
        if(startPoint==0){
            System.out.println("Invalid start point produced");
            return;
        }
        System.out.println(output(startPoint));
    }

    private static Integer findStart() throws IOException {
        sqrt=Files.readString(Path.of("TenMillionSqrt(2).txt"));
        //Finds the start point for algorithm.
        double startPointFinder = 0;
        for(int i=0; i<generalPassword.length(); i++){
            int index = -1;
            int o=0;
            while(o<charsOrder.length){
                if(charsOrder[o]==generalPassword.charAt(i)){
                    index=o;
                    o=100000;
                }
                o++;
            }
            if(index==-1){
                System.out.println("Invalid character in password.");
                return 0;
            }
            startPointFinder+=Math.sqrt(primes[i])*primes[index];
        }
        for(int i=generalPassword.length(); i<generalPassword.length()+passPurpose.length(); i++){
            int index = -1;
            int o=0;
            while(o<charsOrder.length){
                if(charsOrder[o]==passPurpose.charAt(i-generalPassword.length())){
                    index=o;
                    o=100000;
                }
                o++;
            }
            if(index==-1){
                System.out.println("Invalid character in password.");
                return 0;
            }
            startPointFinder+=Math.sqrt(primes[i])*primes[index];
        }
        String starter = ""+startPointFinder;
        int index = 0;
        int startPoint = 0;
        while(startPoint<1000000){
            startPoint=startPoint*10;
            if(Character.isDigit(starter.charAt(index))){
                startPoint+=Integer.parseInt(String.valueOf(starter.charAt(index)));
            }
            index++;
        }
        //We now finally have a startpoint for the algorithm.
        return startPoint;
    }

    private static String output(int spotInSqrt) {
        //Algorithm
        ArrayList<Character> outputChars = new ArrayList<>();
        int index = 0;
        int passwordSpot=0;

        //Lowercase in charOrder occupy indices 0-25
        for(int i = 0; i<numLowercase; i++){
            if(spotInSqrt>=10000000){
                spotInSqrt=spotInSqrt%10000000;
            }
            int numDivide = Integer.parseInt(sqrt.substring(spotInSqrt, spotInSqrt+3));
            Character c = charsOrder[numDivide%26];
            outputChars.add(c);
            int o=0;
            //Now to set a new point for spotInSqrt
            while(o<charsOrder.length){
                if(charsOrder[o]==generalPassword.charAt(passwordSpot)){
                    index=o;
                    o=100000;
                    if(passwordSpot<generalPassword.length()){
                        passwordSpot++;
                    }
                    else{
                        passwordSpot=0;
                    }
                }
                o++;
            }
            spotInSqrt+=index+5;
        }

        //Capitals in charOrder occupy indices 26-51
        for(int i = 0; i<numCapital; i++){
            if(spotInSqrt>=10000000){
                spotInSqrt=spotInSqrt%10000000;
            }
            int numDivide = Integer.parseInt(sqrt.substring(spotInSqrt, spotInSqrt+3));
            Character c = charsOrder[numDivide%26+26];
            outputChars.add(c);
            int o=0;
            //Now to set a new point for spotInSqrt
            while(o<charsOrder.length){
                if(charsOrder[o]==generalPassword.charAt(passwordSpot)){
                    index=o;
                    o=100000;
                }
                o++;
            }
            spotInSqrt+=index+7;
        }

        //Digits in charOrder occupy indices 52-61
        for(int i = 0; i<numDigits; i++){
            if(spotInSqrt>=10000000){
                spotInSqrt=spotInSqrt%10000000;
            }
            int numDivide = Integer.parseInt(sqrt.substring(spotInSqrt, spotInSqrt+3));
            Character c = charsOrder[numDivide%10+52];
            outputChars.add(c);
            int o=0;
            //Now to set a new point for spotInSqrt
            while(o<charsOrder.length){
                if(charsOrder[o]==generalPassword.charAt(passwordSpot)){
                    index=o;
                    o=100000;
                    if(passwordSpot<generalPassword.length()){
                        passwordSpot++;
                    }
                    else{
                        passwordSpot=0;
                    }
                }
                o++;
            }
            spotInSqrt+=index+11;
        }
        //Special characters in charOrder occupy indices 62-65
        for(int i = 0; i<numDigits; i++){
            if(spotInSqrt>=10000000){
                spotInSqrt=spotInSqrt%10000000;
            }
            int numDivide = Integer.parseInt(sqrt.substring(spotInSqrt, spotInSqrt+3));
            Character c = charsOrder[numDivide%4+62];
            outputChars.add(c);
            int o=0;
            //Now to set a new point for spotInSqrt
            while(o<charsOrder.length){
                if(charsOrder[o]==generalPassword.charAt(passwordSpot)){
                    index=o;
                    o=100000;
                    if(passwordSpot<generalPassword.length()){
                        passwordSpot++;
                    }
                    else{
                        passwordSpot=0;
                    }
                }
                o++;
            }
            spotInSqrt+=index+11;
        }
        //Remaining characters, occupy indices 0-65.
        for(int i = 0; i<passwordLength-(numDigits+numCapital+numLowercase+numSpecial); i++){
            if(spotInSqrt>=10000000){
                spotInSqrt=spotInSqrt%10000000;
            }
            int numDivide = Integer.parseInt(sqrt.substring(spotInSqrt, spotInSqrt+3));
            Character c = charsOrder[numDivide%66];
            outputChars.add(c);
            int o=0;
            //Now to set a new point for spotInSqrt
            while(o<charsOrder.length){
                if(charsOrder[o]==generalPassword.charAt(passwordSpot)){
                    index=o;
                    o=100000;
                    if(passwordSpot<generalPassword.length()){
                        passwordSpot++;
                    }
                    else{
                        passwordSpot=0;
                    }
                }
                o++;
            }
            spotInSqrt+=index+13;
        }
        //Now to determine order of these characters
        StringBuilder output = new StringBuilder();
        while(output.length()<passwordLength){
            if(spotInSqrt>=10000000){
                spotInSqrt=spotInSqrt%10000000;
            }
            spotInSqrt+=100;
            int numDivide = Integer.parseInt(sqrt.substring(spotInSqrt, spotInSqrt+3));
            output.append(outputChars.get(numDivide % outputChars.size()));
            outputChars.remove(numDivide % outputChars.size());
        }
        //Finally, return password.
        return String.valueOf(output);
    }
}