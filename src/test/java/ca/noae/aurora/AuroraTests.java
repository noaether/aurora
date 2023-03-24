package ca.noae.aurora;

import ca.noae.aurora.lexer.Lexer;
import ca.noae.aurora.lexer.Token;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class AuroraTests {

    @Test
    public void testLexer() throws Lexer.LexicalException {
        String source = "var a: Int = 1 + 2;";
        String expected = "[VAR , IDENTIFIER , INT , EQUAL =, INTEGER_LITERAL 1, PLUS +, INTEGER_LITERAL 2, EOF ]";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.tokenize();
        Assert.assertEquals(expected, tokens.toString());
    }
}