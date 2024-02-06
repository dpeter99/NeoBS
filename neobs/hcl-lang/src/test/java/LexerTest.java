import com.aperlab.neobs.hcl.parser.Lexer;
import com.aperlab.neobs.hcl.parser.Token;
import com.aperlab.neobs.hcl.parser.TokenType;
import com.aperlab.neobs.hcl.parser.HclParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {

    @Test
    public void testScanTokens() {
        HclParser parser = new HclParser();
        Lexer lexer = new Lexer(parser, "123 abc // comment\n\"string\" [ { test } ]");

        List<Token> tokens = lexer.scanTokens();

        assertEquals(10, tokens.size());

        assertEquals(TokenType.NUMBER, tokens.get(0).getType());
        assertEquals(123.0, tokens.get(0).getLiteral());

        assertEquals(TokenType.IDENTIFIER, tokens.get(1).getType());
        assertEquals("abc", tokens.get(1).getLexeme());

        assertEquals(TokenType.COMMENT, tokens.get(2).getType());
        assertEquals("// comment", tokens.get(2).getLiteral());

        assertEquals(TokenType.STRING, tokens.get(3).getType());
        assertEquals("string", tokens.get(3).getLiteral());

        assertEquals(TokenType.LEFT_BRACKET, tokens.get(4).getType());
        assertEquals(TokenType.LEFT_BRACE, tokens.get(5).getType());

        assertEquals(TokenType.IDENTIFIER, tokens.get(6).getType());
        assertEquals("test", tokens.get(6).getLexeme());

        assertEquals(TokenType.RIGHT_BRACE, tokens.get(7).getType());
        assertEquals(TokenType.RIGHT_BRACKET, tokens.get(8).getType());

        assertEquals(TokenType.EOF, tokens.get(9).getType());
    }
}