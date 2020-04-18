import java.io.*;
import java.util.ArrayList;


public class Uniq {

    private int skipNSymbols;
    private boolean ignoreRegister;
    private boolean onlyUniqStrings;
    private boolean amountOfStrings;
    private String inputFileName;
    private String outputFileName;

    public Uniq(int skipNSymbols, boolean ignoreRegister, boolean onlyUniqStrings,
                boolean amountOfStrings, String inputFileName, String outputFileName) {
        this.skipNSymbols = skipNSymbols;
        this.ignoreRegister = ignoreRegister;
        this.onlyUniqStrings = onlyUniqStrings;
        this.amountOfStrings = amountOfStrings;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void begin(String inputFileName) throws IOException {
        BufferedReader br;
        ArrayList<String> result = new ArrayList<>();
        if (inputFileName == null) {
            System.out.println("Enter text:");
            br = new BufferedReader(new InputStreamReader(System.in));
        } else {
            br = new BufferedReader(new FileReader(inputFileName));
        }
        String prevStr = null;
        try (br) {
            String str;
            int number = 1;
            while ((str = br.readLine()) != null) {
                if (equalStrings(str, prevStr)) number += 1;
                else {
                    textWithCountedStrings(number, prevStr, result);
                    prevStr = str;
                    number = 1;
                }
            }
            textWithCountedStrings(number, prevStr, result);
        }
        write(result);
    }

    private boolean equalStrings(String str, String prevStr) {
        boolean res = false;
        if (prevStr == null) return false;
        if (str.equals(prevStr)) res = true;
        if (ignoreRegister) {
            if (str.equalsIgnoreCase(prevStr)) res = true;
        }
        if (skipNSymbols > 0) {
            if (str.substring(skipNSymbols).equals(prevStr.substring(skipNSymbols))) res = true;
        }
        return res;
    }

    private void textWithCountedStrings(int number, String prevStr, ArrayList<String> result) {
        if (prevStr == null) return;
        if (onlyUniqStrings && number == 1) result.add(prevStr);
        if (amountOfStrings) {
            if (number > 1) {
                result.add(number + " " + prevStr);
            } else {
                result.add(prevStr);
            }
        }
        if (!amountOfStrings && !onlyUniqStrings)
            result.add(prevStr);

    }

    private void write(ArrayList<String> result) throws IOException {
        if (outputFileName == null) {
            System.out.println("Output text:");
            for (String str : result) {
                System.out.println(str);
            }
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {
                for (String str : result) {
                    bw.write(str);
                    bw.newLine();
                }
            }
        }
    }
}