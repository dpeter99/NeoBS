import com.aperlab.neobs.hcl.parser.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.aperlab.neobs.hcl.parser.HclParser;
import com.aperlab.neobs.hcl.parser.Token;

import java.util.ArrayList;
import java.util.List;

public class HclParserTest {

    @Test
    public void testConfigFile() {
        // Add tokens to the list as per your test case
        var parser = new HclParser();
        Node actual = parser.parse(
                """
                test = "test"
                """);

        assertNotNull(actual);
        assertEquals(Node.ConfigFile.class, actual.getClass());

    }

    @Test
    public void testDeclarationBlockEmpty() {
        // Add tokens to the list as per your test case
        var parser = new HclParser();
        Node actual = parser.parse(
                """
                test {}
                """);

        assertNotNull(actual);
        assertEquals(Node.ConfigFile.class, actual.getClass());

    }

    @Test
    public void testDeclarationBlock() {
        // Add tokens to the list as per your test case
        var parser = new HclParser();
        Node actual = parser.parse(
                """
                test {
                    testatr = "testval"
                }
                """);

        assertNotNull(actual);
        assertEquals(Node.ConfigFile.class, actual.getClass());

    }

    @Test
    public void testDeclarationBlocks() {
        // Add tokens to the list as per your test case
        var parser = new HclParser();
        Node actual = parser.parse(
                """
                test {
                    testatr = "testval"
                }
                test {
                    testatr = "testval"
                }
                test {
                    testatr = "testval"
                }
                """);

        assertNotNull(actual);
        assertEquals(Node.ConfigFile.class, actual.getClass());

    }

    @Test
    public void testDeclarationBlockLabels() {
        // Add tokens to the list as per your test case
        var parser = new HclParser();
        Node actual = parser.parse(
                """
                test "label" lab2 {
                    testatr = "testval"
                }
                """);

        assertNotNull(actual);
        assertEquals(Node.ConfigFile.class, actual.getClass());

    }

    // Add more test methods for other public methods in HclParser
}