import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;


public class UniqLauncher {
    @Option(name = "-i", metaVar = "ignoreCase", usage = "Ignore register")
    private boolean ignoreRegister = false;

    @Option(name = "-u", metaVar = "onlyUniq", usage = "Print only unique strings")
    private boolean onlyUniqStrings = false;

    @Option(name = "-s", metaVar = "skipNSymbols", usage = "Ignore the first N symbols of each string")
    private int skipNSymbols = 0;

    @Option(name = "-c", metaVar = "countedStrings", usage = "Text with amount of changed strings")
    private boolean amountOfStrings = false;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private String inputFileName = null;

    @Option(name="-o", metaVar = "outputFile", usage = "Output file name")
    private String outputFileName = null;

    public static void main(String[] args) throws IOException {
        new UniqLauncher().launch(args);
    }

    private void launch(String[] args){
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar uniq.jar [-i] [-u] [-c] [-s num] [-o ofile] [file]");
            parser.printUsage(System.err);
            return;
        }

        Uniq uniq = new Uniq(skipNSymbols, ignoreRegister,  onlyUniqStrings,
                amountOfStrings,  inputFileName, outputFileName);

        try {
            uniq.begin(inputFileName, outputFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}