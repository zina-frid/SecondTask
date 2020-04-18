import org.junit.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UniqTest {


    @Test
    public void testWithoutFlags() throws IOException {
        String[] args1 = new String[]{"-o", "src/test/resources/test", "src/test/resources/text"};
        UniqLauncher.main(args1);
        List<String> output1 = Files.readAllLines(Paths.get("src/test/resources/output1"));
        List<String> test = Files.readAllLines(Paths.get("src/test/resources/test"));

        assertEquals(output1, test);
    }


    @Test
    public void testForIgnoreCase() throws IOException {
        String[] args2 = new String[]{"-i", "-o", "src/test/resources/test", "src/test/resources/text"};
        UniqLauncher.main(args2);
        List<String> output2 = Files.readAllLines(Paths.get("src/test/resources/output2"));
        List<String> test = Files.readAllLines(Paths.get("src/test/resources/test"));
        assertEquals(output2, test);
    }


    @Test
    public void testForSkipNSymbols() throws IOException {
        String[] args3 = new String[]{"-s", "8", "-o", "src/test/resources/test", "src/test/resources/text"};
        UniqLauncher.main(args3);
        List<String> output3 = Files.readAllLines(Paths.get("src/test/resources/output3"));
        List<String> test = Files.readAllLines(Paths.get("src/test/resources/test"));
        assertEquals(output3, test);
    }


    @Test
    public void testForCountedString() throws IOException {
        String[] args5 = new String[]{"-c", "-o", "src/test/resources/test", "src/test/resources/text"};
        UniqLauncher.main(args5);
        List<String> output5 = Files.readAllLines(Paths.get("src/test/resources/output5"));
        List<String> test = Files.readAllLines(Paths.get("src/test/resources/test"));
        assertEquals(output5, test);
    }


    @Test
    public void testForOnlyUniqStrings() throws IOException {
        String[] args4 = new String[]{"-u", "-o", "src/test/resources/test", "src/test/resources/text"};
        UniqLauncher.main(args4);
        List<String> output4 = Files.readAllLines(Paths.get("src/test/resources/output4"));
        List<String> test = Files.readAllLines(Paths.get("src/test/resources/test"));
        assertEquals(output4, test);
    }


    @Test
    public void testForCombination() throws IOException {
        String[] args6 = new String[]{"-i","-s", "8", "-o", "src/test/resources/test", "src/test/resources/text"};
        UniqLauncher.main(args6);
        List<String> output6 = Files.readAllLines(Paths.get("src/test/resources/output6"));
        List<String> test = Files.readAllLines(Paths.get("src/test/resources/test"));
        assertEquals(output6, test);
    }

    @Test
    public void testForCombination2() throws IOException {
        String[] args7 = new String[]{"-s", "8", "-c", "-o", "src/test/resources/test", "src/test/resources/text"};
        UniqLauncher.main(args7);
        List<String> output7 = Files.readAllLines(Paths.get("src/test/resources/output7"));
        List<String> test = Files.readAllLines(Paths.get("src/test/resources/test"));
        assertEquals(output7, test);
    }

    @Test
    public void testForCombination3() throws IOException {
        String[] args8 = new String[]{"-i", "-u", "-o", "src/test/resources/test", "src/test/resources/text"};
        UniqLauncher.main(args8);
        List<String> output8 = Files.readAllLines(Paths.get("src/test/resources/output8"));
        List<String> test = Files.readAllLines(Paths.get("src/test/resources/test"));
        assertEquals(output8, test);
    }
}