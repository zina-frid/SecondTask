import java.io.*;

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

    public void begin(String inputFileName, String outputFileName) throws IOException {
        BufferedReader br;
        BufferedWriter bw;
        if (inputFileName == null) {
            System.out.println("Enter text:");
            br = new BufferedReader(new InputStreamReader(System.in));
        } else {
            br = new BufferedReader(new FileReader(inputFileName));
        }
        if (outputFileName == null){
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
        } else {
            bw = new BufferedWriter(new FileWriter(outputFileName));
        }
        processing(br, bw);
    }

    private void processing(BufferedReader br, BufferedWriter bw) throws IOException {
        String prevStr = null;
        try (br) {
            try (bw) {
                String str;
                int number = 1;
                while ((str = br.readLine()) != null) {
                    if (equalStrings(str, prevStr)) number += 1;
                    else {
                        textWithCountedStrings(number, prevStr, bw);
                        prevStr = str;
                        number = 1;
                    }
                }
                textWithCountedStrings(number, prevStr, bw);
            }
        }
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

    private void textWithCountedStrings(int number, String prevStr,BufferedWriter bw) throws IOException {
        if (prevStr == null) return;
        if (onlyUniqStrings && number == 1) {
            bw.write(prevStr);
            bw.newLine();
        }
        if (amountOfStrings) {
            if (number > 1) {
                bw.write(number + " " + prevStr);
            } else {
                bw.write(prevStr);
            }
            bw.newLine();
        }
        if (!amountOfStrings && !onlyUniqStrings) {
            bw.write(prevStr);
        bw.newLine();
        }
    }
}