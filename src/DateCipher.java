package date.cipher;

import java.util.Scanner;

/**
 * @author Robert Hart
 * 4/17/2019
 */
public class DateCipher {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int input = 0;
        String KeyInputNum = "";
        System.out.println("Welcome to DateCipher!\nThe Key is a date like 8/31/19.\nPlease enter it as 83119");
        System.out.println("Enter the Key: ");
        KeyInputNum = in.nextLine();

        System.out.println("To Encode a msg enter (1)\nTo Decode a msg enter (2)\n");
        input = in.nextInt();
        in.nextLine();// need this after a next int or it will skip over the nextLine below
        if (input == 1) {
            System.out.println("Enter a msg to encode \nPlease enter only letters");
            String msgToEncode = in.nextLine();
            System.out.println("original msg: " + msgToEncode);
            encode(msgToEncode, KeyInputNum);
        }
        if (input == 2) {
            System.out.println("Enter a msg to Decode \nPlease enter only letters");
            String msgToDecode = in.nextLine();
            System.out.println("original msg Encoded: " + msgToDecode);
            decode(msgToDecode, KeyInputNum);
        }


    }

    public static void encode(String msgToEncode, String Key) {
        String outputEncoded = "";
        msgToEncode = msgToEncode.toLowerCase();
        int curKeyValue = 0;
        int keyPos = 0;
        for (int letterPos = 0; letterPos < msgToEncode.length(); letterPos++) {
            if (keyPos == Key.length()) {
                keyPos = 0;
            }
            curKeyValue = (int) Key.charAt(keyPos) - 48;

            int curCharValue = (int) msgToEncode.charAt(letterPos);
            if (curCharValue == 32) {
                outputEncoded = outputEncoded + "$";
                continue;
            }
            //adjusting ascii value with key
            int curCharValueAdjusted = curCharValue + curKeyValue;
            if (curCharValueAdjusted > 122) {
                curCharValueAdjusted = curCharValueAdjusted - 58;
            }
            char encodedChar = (char) curCharValueAdjusted;
            outputEncoded = outputEncoded + encodedChar;

            keyPos++;
        }

        System.out.println("Encoded msg:" + outputEncoded);
    }

    public static void decode(String msgToDecode, String Key) {
        String outputDecoded = "";

        int curKeyValue = 0;
        int keyPos = 0;
        for (int letterPos = 0; letterPos < msgToDecode.length(); letterPos++) {
            if (keyPos == Key.length()) {
                keyPos = 0;
            }
            curKeyValue = (int) Key.charAt(keyPos) - 48;

            int curCharValue = (int) msgToDecode.charAt(letterPos);
            if (curCharValue == 36) {
                outputDecoded = outputDecoded + " ";
                continue;
            }
            //adjusting ascii value with key
            int curCharValueAdjusted = 0;
            int Correction = 0;// if over 122 it cycles back down to 97 for lowercase a this adjusts it
            if (curCharValue > 64 && curCharValue < 91) {
                Correction = 58;
            }
            curCharValueAdjusted = curCharValue - curKeyValue + Correction;

            char DecodedChar = (char) curCharValueAdjusted;
            outputDecoded = outputDecoded + DecodedChar;

            keyPos++;


        }
        System.out.println("Decoded msg: " + outputDecoded);

    }
}
