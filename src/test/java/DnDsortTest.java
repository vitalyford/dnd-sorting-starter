import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DnDsortTest {

    private List<Character> characters;

    @BeforeEach
    public void setUp() throws IOException {
        characters = new ArrayList<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("characters.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue; // skip empty lines
            }
            String name = line.trim();
            Map<String, Integer> abilities = new HashMap<>();
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    // existing code
                }
            }
        }
    }

    @Test
    public void testStrengthSorting() throws Exception {
        String input = "streNgTH\nrecursion!\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DnDsort.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("The characters are in ascending order of their strength:"));

        List<String> expectedOrder = Arrays.asList(
            "Thief - Anna Prentice (7)",
            "Cleric - Barry Cade (9)",
            "Monk - Cara Van (11)",
            "Sorcerer - Anne Teak (13)",
            "Bard - Chip Munk (16)",
            "Ranger - Claire Annette Reed (16)",
            "Druid - Brock Lee (17)",
            "Warlock - Billy Rubin (18)",
            "Wizard - Cam Payne (19)",
            "Fighter - Anna Sasin (23)",
            "Barbarian - Alf A. Romeo (25)",
            "Paladin - Casey Macy (26)"
        );

        verifyOrder(output, expectedOrder);
    }

    @Test
    public void testWisdomSorting() throws Exception {
        String input = "wisDOM\nrecursion!\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DnDsort.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("The characters are in ascending order of their wisdom:"));

        List<String> expectedOrder = Arrays.asList(
            "Barbarian - Alf A. Romeo (2)",
            "Fighter - Anna Sasin (6)",
            "Paladin - Casey Macy (9)",
            "Warlock - Billy Rubin (9)",
            "Thief - Anna Prentice (10)",
            "Bard - Chip Munk (11)",
            "Ranger - Claire Annette Reed (14)",
            "Sorcerer - Anne Teak (15)",
            "Monk - Cara Van (19)",
            "Wizard - Cam Payne (20)",
            "Druid - Brock Lee (21)",
            "Cleric - Barry Cade (26)"
        );

        verifyOrder(output, expectedOrder);
    }

    @Test
    public void testDexteritySorting() throws Exception {
        String input = "dexterity\nrecursion!\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DnDsort.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("The characters are in ascending order of their dexterity:"));

        List<String> expectedOrder = Arrays.asList(
            "Cleric - Barry Cade (4)",
            "Warlock - Billy Rubin (4)",
            "Wizard - Cam Payne (8)",
            "Monk - Cara Van (9)",
            "Paladin - Casey Macy (14)",
            "Barbarian - Alf A. Romeo (15)",
            "Druid - Brock Lee (16)",
            "Fighter - Anna Sasin (17)",
            "Sorcerer - Anne Teak (17)",
            "Bard - Chip Munk (22)",
            "Ranger - Claire Annette Reed (23)",
            "Thief - Anna Prentice (28)"
        );

        verifyOrder(output, expectedOrder);
    }

    private void verifyOrder(String output, List<String> expectedOrder) {
        int lastIndex = -1;
        for (String expectedLine : expectedOrder) {
            int currentIndex = output.indexOf(expectedLine);
            assertTrue(currentIndex > lastIndex, "Expected line '" + expectedLine + "' to appear after previous lines.");
            lastIndex = currentIndex;
        }
    }

    @Test
    public void testInvalidSkill() throws Exception {
        String input = "unknownSkill\nrecursion!\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DnDsort.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("Invalid skill entered. Please try again."));
    }

    @Test
    public void testExitCondition() throws Exception {
        String input = "recursion!\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DnDsort.main(new String[]{});

        String output = outContent.toString();

        assertTrue(output.contains("Bye. May the D&D use recursion on you!"));
    }
}