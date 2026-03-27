import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import components.simplewriter.SimpleWriter1L;

public class ChampionDBSecondaryTest {

    private ChampionDB testDB;
    private ChampionDB otherDB;

    @Before
    public void setUp() {
        testDB = new ChampionDBKernel1L();
        otherDB = new ChampionDBKernel1L();
    }

    // contains tests

    @Test
    public void testContainsEmptyFalse() {
        assertFalse(testDB.contains("Ahri"));
    }

    @Test
    public void testContainsTrueAfterAdd() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        assertTrue(testDB.contains("Ahri"));
    }

    @Test
    public void testContainsFalseAfterRemoval() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        testDB.removeChampion("Ahri");
        assertFalse(testDB.contains("Ahri"));
    }

    @Test
    public void testContainsNullArgument() {
        assertFalse(testDB.contains(null));
    }

    // toString tests

    @Test
    public void testToStringEmpty() {
        assertEquals("ChampionDB with 0 champions", testDB.toString());
    }

    @Test
    public void testToStringAfterOneAdd() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        assertEquals("ChampionDB with 1 champions", testDB.toString());
    }

    @Test
    public void testToStringAfterMultipleAdds() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        testDB.addChampion("Darius", "Fighter", "Top", 2);

        assertEquals("ChampionDB with 2 champions", testDB.toString());
    }

    // equals tests

    @Test
    public void testEqualsTwoEmptyDBs() {
        assertTrue(testDB.equals(otherDB));
    }

    @Test
    public void testEqualsWithDifferentSizes() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        assertFalse(testDB.equals(otherDB));
    }

    @Test
    public void testEqualsSameSizeDifferentNames() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        otherDB.addChampion("Darius", "Fighter", "Top", 2);

        assertTrue(testDB.equals(otherDB));
    }

    @Test
    public void testEqualsWithNullObject() {
        assertFalse(testDB.equals(null));
    }

    @Test
    public void testEqualsDifferentTypes() {
        assertFalse(testDB.equals("Not a DB"));
    }

    // hashCode tests

    @Test
    public void testHashCodeEmpty() {
        assertEquals(0, testDB.hashCode());
    }

    @Test
    public void testHashCodeMatchesSize() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        assertEquals(1, testDB.hashCode());
    }

    @Test
    public void testHashCodeMatchesSizeMultiple() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        testDB.addChampion("Ashe", "ADC", "Bot", 1);
        testDB.addChampion("Fizz", "Assassin", "Mid", 3);
        assertEquals(3, testDB.hashCode());
    }

    // printAll tests

    @Test
    public void testPrintAllEmpty() {
        SimpleWriter1L out = new SimpleWriter1L();
        testDB.printAll(out);
        out.close();
    }

    @Test
    public void testPrintAllAfterAdd() {
        testDB.addChampion("Ahri", "Mage", "Mid", 4);
        SimpleWriter1L out = new SimpleWriter1L();
        testDB.printAll(out);
        out.close();
    }

    @Test(expected = AssertionError.class)
    public void testPrintAllClosedStream() {
        SimpleWriter1L out = new SimpleWriter1L();
        out.close();
        testDB.printAll(out);
    }
}