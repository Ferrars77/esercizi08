package it.unibo.deathnote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.DeathNoteImpl;

import static it.unibo.deathnote.api.DeathNote.RULES;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TestDeathNote {

    private DeathNote book;
    private String ciccioPas = "Ciccio Pasticcio";
    private String marameo = "marameo ciossi";


    @BeforeEach
    void init() {
        book = new DeathNoteImpl();
    }
    @Test
    void testRuleFirst() {
        //rule number 0 and negative
        try {
            book.getRule(0);
            fail();
        } catch(IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        try {
            book.getRule(-1);
            fail();
        } catch(IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        
         
    }

    @Test
    void testRuleSecond() {
        for(int i = 1; i <= DeathNote.RULES.size(); i++) {
            String rule = book.getRule(i);
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
        
        
    }

    @Test
    void testRuleThird() {
        assertFalse(book.isNameWritten(ciccioPas));
        book.writeName(ciccioPas);
        assertTrue(book.isNameWritten(ciccioPas));
        assertFalse(book.isNameWritten(marameo));
        assertFalse(book.isNameWritten(""));
    }

    @Test
    void testRuleFour() throws InterruptedException {
        try {
            book.writeDeathCause("redzyficazione");
            fail();
        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        book.writeName(marameo);
        assertEquals("heart attack", book.getDeathCause(marameo));
        book.writeName(ciccioPas);
        assertTrue(book.writeDeathCause("karting accident"));
        assertEquals("karting accident", book.getDeathCause(ciccioPas));
        Thread.sleep(100);
        assertFalse(book.writeDeathCause("ciccio gaming too much"));
        assertEquals("karting accident", book.getDeathCause(ciccioPas));
    }

    @Test
    void testRuleFifth() throws InterruptedException {
        try {
            book.writeDetails("redzyficazione");
            fail();
        } catch(IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        book.writeName(marameo);
        assertEquals("", book.getDeathDetails(marameo));
        assertTrue(book.writeDetails("ran for too long"));
        assertEquals("ran for too long", book.getDeathDetails(marameo));
        book.writeName(ciccioPas);
        Thread.sleep(6100);
        assertFalse(book.writeDetails("ciccio gaming too much"));
        assertEquals("", book.getDeathDetails(ciccioPas));
    }

   

    
}