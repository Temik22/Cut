import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CutTest {

    private void assertFileContent(String name, String expectedContent) throws Exception {
        File file = new File("src/files/" + name);
        List<String> lines = Files.readAllLines(file.toPath());
        StringBuilder content = new StringBuilder();
        int counter = 1;
        for (String line : lines) {
            if (counter < lines.size()) {
                content.append(line + "\n");
                counter++;
            } else {
                content.append(line);
            }
        }
        assertEquals(expectedContent, content.toString());
    }

    @Test
    public void cutTest() throws Exception {
        String[] args = "cut -c -o output test 7-14".split(" ");

        File in = new File("src/files/test.txt");
        FileWriter inw = new FileWriter(in);
        inw.append("анердпосроя-гейнрир\n" + "нурвготксая-тупнаор");
        inw.close();

        Cut.main(args);

        assertFileContent("output.txt", "осроя-ге\n" + "тксая-ту");

        Files.delete(Paths.get("src/files/test.txt"));
    }

    @Test
    public void anotherCutTest() throws Exception {
        String[] args = "cut -w -o output test 2-".split(" ");

        File in = new File("src/files/test.txt");
        FileWriter inw = new FileWriter(in);
        inw.append("qwerty asdfgh zxcvbn\n" + "zxcvbn asdfgh qwerty");
        inw.close();

        Cut.main(args);

        assertFileContent("output.txt", "asdfgh zxcvbn\n" + "asdfgh qwerty");

        Files.delete(Paths.get("src/files/test.txt"));
    }
}