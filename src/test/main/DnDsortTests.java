import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.*;

public class DnDsortTests {

    @Test
    public void testStrengthUpDownCase() {
        ByteArrayInputStream bais = new ByteArrayInputStream("sTrEngTH".getBytes());
        System.setIn(bais);

        String expected = String.join("The characters are in ascending order of their strength:\n",
                                      "Thief - Anna Prentice (7)\n",
                                      "Cleric - Barry Cade (9)\n",
                                      "Monk - Cara Van (11)\n",
                                      "Sorcerer - Anne Teak (13)\n",
                                      "Bard - Chip Munk (16)\n",
                                      "Ranger - Claire Annette Reed (16)\n",
                                      "Druid - Brock Lee (17)\n",
                                      "Warlock - Billy Rubin (18)\n",
                                      "Wizard - Cam Payne (19)\n",
                                      "Fighter - Anna Sasin (23)\n",
                                      "Barbarian - Alf A. Romeo (25)\n",
                                      "Paladin - Casey Macy (26)\n");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        DnDsort.main();

        String actual = baos.toString();

        assertEquals(expected, actual);
    }


}