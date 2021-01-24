package com.example.lessons.algos;

import com.example.lessons.consoleUI.ConsoleMethods;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;
import java.time.Duration;

/**
 * Evaluates String as palindrome
 * a word, phrase, or sequence that reads the same backward as forward, e.g., madam or nurses run.
 *
 * Contains 3 methods of evaluation: Loop, Recursion, Built in replace and compare
 *
 * @version 1.0
 * @author John Mortensen
 */

public class Palindrome
{
    // Instance variables
    private String Candidate;
    private String Log;
    private List<String> Log2 = new  ArrayList<String>();;


    public static void main(String[] args) 						// Console driver
    {
        // Input using console
        String candidate = ConsoleMethods.inputString("Enter a word or phrase: ");

        // Evaluate by all methods
        Palindrome test = new Palindrome();
        test.setPaliCandidate(candidate);
        test.isPali(1);
        test.isPali(2);
        test.isPali(3);
    }

    public static String isPaliLog(String candidate, int method)	// Business logic driver
    {
        // Tests candidate and returns result message
        Palindrome test = new Palindrome();
        test.setPaliCandidate(candidate);
        test.isPali(method);
        return test.getPaliLog();
    }

    public static List<String> isPaliLog2(String candidate, int method)	// Business logic driver
    {
        // Tests candidate and returns result message
        Palindrome test = new Palindrome();
        test.setPaliCandidate(candidate);
        test.isPali(method);
        return test.getPaliLog2();
    }

    public String getPaliCandidate() {
        // Getter
        return Candidate;
    }

    public String getPaliLog() {
        // Getter
        return Log;
    }

    public List<String> getPaliLog2() {
        // Getter
        return Log2;
    }

    private void setPaliCandidate(String candidate) {
        // Setter
        Candidate = candidate;
    }

    private void setPaliLog(String log) {
        // Setter
        Log = "\n" + log;
        Log2.add("");
        Log2.add(log);
    }

    private void concatPaliLog(String log) {
        // Setter
        Log += "\n" + log;
        Log2.add(log);
    }

    private void setPaliLog(boolean isPali)
    {
        // Log to persist in Object
        this.concatPaliLog( String.format ("%s",
                isPali
                        ? "Is pali :)"
                        : "Not pali :("
        ) );
        ConsoleMethods.println( this.getPaliLog() );
    }

    private boolean isPali(int method)
    {
        // Base condition, no need to test 0 or 1 condition
        if (Candidate.length() < 2) {
            this.setPaliLog(Candidate +" is to small to test");
            return true;
        }


        // Select testing method and calculate time
        boolean result;
        Instant start = Instant.now();
        switch (method) {
            case 1:
                result =  palindromeTestIJ();
                break;
            case 2:
                result =  palindromeRecurseDriver();
                break;
            case 3:
                result = palindromeReplace();
                break;
            default:
                this.setPaliLog(Candidate +" not run " + method + " unknown");
                result = false;
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        this.concatPaliLog(timeElapsed.getNano() + " nano seconds");
        return result;
    }

    private boolean palindromeReplace()
    {
        // Entering Replace (Built-in) method
        setPaliLog ("Replace method");
        concatPaliLog( String.format("Candidate: \"%s\",  Length = %d" ,Candidate, Candidate.length() ) );
        int step = 0;

        // Built in methods to remove special characters by regular expression (regex)
        String forwardStr = Candidate.replaceAll("[^a-zA-Z0-9]", "");
        concatPaliLog( String.format( "Step %d: Prepare string one \"%s\" to  \"%s\"", step++, Candidate, forwardStr ) );

        // Built in method in StringBuilder to reverse string and convert back to string
        String reverseStr = new StringBuilder(forwardStr).reverse().toString();
        concatPaliLog( String.format( "Step %d: Prepare string two \"%s\" to  \"%s\"", step++, forwardStr, reverseStr ) );

        // Compare strings by ignoring case
        boolean result = (forwardStr.equalsIgnoreCase(reverseStr));
        concatPaliLog( String.format( "Step %d: Compare ignoring case \"%s\" to reverse \"%s\"", step, forwardStr, reverseStr ) );

        setPaliLog(result);
        return result;
    }

    private boolean palindromeTestIJ()
    {
        // Entering IJ method
        setPaliLog("IJ method");
        concatPaliLog( String.format("Candidate: \"%s\",  Length = %d" , Candidate, Candidate.length()) );

        int length = Candidate.length()-1;
        int step = 0;
        for (int i=0, j=length; i < j; step++ )
        {
            char lchar = Character.toLowerCase(Candidate.charAt(i));
            char rchar = Character.toLowerCase(Candidate.charAt(j));

            // Compare is Character by Character
            concatPaliLog( String.format( "Step %d: lchar(%d):\"%c\"  ==  rchar(%d):\"%c\"" , step, i, lchar, j, rchar ));
            if ( !(Character.isLetterOrDigit(lchar)) ) {
                concatPaliLog( String.format( " skip left %c" ,lchar ));
                i++;
            } else if ( !(Character.isLetterOrDigit(rchar)) ) {
                concatPaliLog( String.format( " skip right %c", rchar ));
                j--;
            } else if ( lchar == rchar) {
                concatPaliLog( String.format(" eq left %c = right %c" , lchar, rchar));
                i++; j--;
            } else {
                concatPaliLog( String.format(" not eq left %c = right %c" , lchar, rchar));
                setPaliLog(false);
                return false;
            }
        }
        setPaliLog(true);
        return true;
    }

    private boolean palindromeRecurseDriver()
    {
        // Recursion driver,  used to setup recursion method
        setPaliLog("Recursion method");
        concatPaliLog( String.format("Candidate: \"%s\",  Length = %d" , Candidate, Candidate.length()) );

        String testStr = Candidate;
        boolean result = palindromeTestRecurse(testStr, 0);
        setPaliLog(result);

        return result;
    }

    private boolean palindromeTestRecurse(String shrinker, int step)
    {
        // Entering recursion method
        int lindex = 0;
        int rindex = shrinker.length();

        // String reduces on each recursion
        concatPaliLog( String.format("Step %d: Candidate \"%s\"" , step, shrinker ) );
        char lchar = Character.toLowerCase(shrinker.charAt(0));
        char rchar = Character.toLowerCase(shrinker.charAt(rindex-1));

        if (! (Character.isLetterOrDigit(lchar) )) {
            concatPaliLog( String.format(" skip left %c" , lchar));
            lindex++;
        } else if (! (Character.isLetterOrDigit(rchar) )) {
            concatPaliLog( String.format(" skip right %c" , rchar));
            rindex--;
        } else if ( lchar == rchar ) {
            concatPaliLog( String.format(" eq left %c = right %c" , lchar, rchar));
            lindex++; rindex--;
        } else {
            concatPaliLog( String.format(" not eq left %c = right %c" , lchar, rchar));
            return false;
        }

        // Java recursion acts funny if True and Recursion are not at the end, thus placement and use of indexes
        return (((rindex - lindex) <= 2) || palindromeTestRecurse(shrinker.substring(lindex, rindex), ++step));
    }

}