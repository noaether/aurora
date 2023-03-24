package ca.noae.aurora;

import ca.noae.aurora.lexer.Lexer;
import ca.noae.aurora.lexer.Token;
import ca.noae.aurora.lexer.TokenType;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

public class AuroraTests {

    @Test
    public void testLexer() throws Lexer.LexicalException {
        String source = """
                var a: Int = 1 + 2;
                if(a == 3 || a == 4) {
                    print("Hello, world!");
                }
                """;
        String expected = "[VAR , IDENTIFIER , INT , EQUAL =, INTEGER_LITERAL 1, PLUS +, INTEGER_LITERAL 2, IF , IDENTIFIER , DOUBLE_EQUAL ==, INTEGER_LITERAL 3, DOUBLE_PIPE ||, IDENTIFIER , DOUBLE_EQUAL ==, INTEGER_LITERAL 4, LBRACE {, IDENTIFIER , STRING_LITERAL Hello, world!, SEMICOLON ;, RBRACE }, EOF ]";
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.tokenize();
        Assert.assertEquals(expected, tokens.toString());
    }

    @Test
    public void testTokenTypes() {
        Assert.assertEquals(TokenType.VAR, TokenType.getKeywordTokenType("var"));
        Assert.assertEquals(TokenType.INT, TokenType.getKeywordTokenType("Int"));
        Assert.assertEquals(TokenType.DOUBLE, TokenType.getKeywordTokenType("Double"));
        Assert.assertEquals(TokenType.STRING, TokenType.getKeywordTokenType("String"));
        Assert.assertEquals(TokenType.BOOLEAN, TokenType.getKeywordTokenType("Boolean"));
        Assert.assertEquals(TokenType.TRUE, TokenType.getKeywordTokenType("true"));
        Assert.assertEquals(TokenType.FALSE, TokenType.getKeywordTokenType("false"));
        Assert.assertEquals(TokenType.IF, TokenType.getKeywordTokenType("if"));
        Assert.assertEquals(TokenType.ELSE, TokenType.getKeywordTokenType("else"));
        Assert.assertEquals(TokenType.WHILE, TokenType.getKeywordTokenType("while"));
        Assert.assertEquals(TokenType.FOR, TokenType.getKeywordTokenType("for"));
        Assert.assertEquals(TokenType.FUNCTION, TokenType.getKeywordTokenType("func"));
        Assert.assertEquals(TokenType.RETURN, TokenType.getKeywordTokenType("return"));
        Assert.assertEquals(TokenType.PLUS, TokenType.getSymbolTokenType('+'));
        Assert.assertEquals(TokenType.MINUS, TokenType.getSymbolTokenType('-'));
        Assert.assertEquals(TokenType.ASTERISK, TokenType.getSymbolTokenType('*'));
        Assert.assertEquals(TokenType.SLASH, TokenType.getSymbolTokenType('/'));
        Assert.assertEquals(TokenType.PERCENT, TokenType.getSymbolTokenType('%'));
        Assert.assertEquals(TokenType.EQUAL, TokenType.getSymbolTokenType('='));
        Assert.assertEquals(TokenType.LESS, TokenType.getSymbolTokenType('<'));
        Assert.assertEquals(TokenType.GREATER, TokenType.getSymbolTokenType('>'));
        Assert.assertEquals(TokenType.AMPERSAND, TokenType.getSymbolTokenType('&'));
        Assert.assertEquals(TokenType.PIPE, TokenType.getSymbolTokenType('|'));
        Assert.assertEquals(TokenType.BANG, TokenType.getSymbolTokenType('!'));
        Assert.assertEquals(TokenType.LPAREN, TokenType.getSymbolTokenType('('));
        Assert.assertEquals(TokenType.RPAREN, TokenType.getSymbolTokenType(')'));
        Assert.assertEquals(TokenType.LBRACE, TokenType.getSymbolTokenType('{'));
        Assert.assertEquals(TokenType.RBRACE, TokenType.getSymbolTokenType('}'));
        Assert.assertEquals(TokenType.LBRACKET, TokenType.getSymbolTokenType('['));
        Assert.assertEquals(TokenType.RBRACKET, TokenType.getSymbolTokenType(']'));
        Assert.assertEquals(TokenType.COMMA, TokenType.getSymbolTokenType(','));
        Assert.assertEquals(TokenType.COLON, TokenType.getSymbolTokenType(':'));
        Assert.assertEquals(TokenType.SEMICOLON, TokenType.getSymbolTokenType(';'));
        Assert.assertEquals(TokenType.DOT, TokenType.getSymbolTokenType('.'));
        // Assert.assertEquals(TokenType.EOF, TokenType.getSymbolTokenType((char) -1)); This test will not work. EOF is added automatically at the end of a tokenization
    }

    @Test
    public void testLexerWithInvalidInput() {
        String source = "var a: Int = 1 + 2;";
        Lexer lexer = new Lexer(source);
        try {
            lexer.tokenize();
        } catch (Lexer.LexicalException e) {
            Assert.fail("LexicalException should not have been thrown");
        }
        source = "var a: Int = ¹ + ²";
        lexer = new Lexer(source);
        try {
            lexer.tokenize();
            Assert.fail("LexicalException should have been thrown");
        } catch (Lexer.LexicalException e) {
            // This is expected
        }
    }
}