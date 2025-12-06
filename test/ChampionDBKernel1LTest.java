import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ChampionDBKernel1LTest {

    private ChampionDBKernel testDB;

    @Before
    public void setUp() {
        this.testDB = new ChampionDBKernel1L();
    }

    // Constructor and initial state

    @Test
    public void testConstructorEmpty() {
        assertEquals(0, testDB.size());
    }

    @Test
    public void testConstructorNoChampions() {
        assertTrue(testDB.size() == 0);
        assertFalse(testDB.contains("Ahri"));
    }

    // addChampion Tests

    @Test
    public void testAddSingleChampion() {
        testDB.addChampion("Ahri", "Mid Mage");
        assertEquals(1, testDB.size());
        assertTrue(testDB.contains("Ahri"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullName() {
        testDB.addChampion(null, "Mid Mage");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullRole() {
        testDB.addChampion("Ahri", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateChampion() {
        testDB.addChampion("Ahri", "Mid Mage");
        testDB.addChampion("Ahri", "Mid Mage");
    }

    @Test
    public void testAddMultipleChampions() {
        testDB.addChampion("Ahri", "Mage");
        testDB.addChampion("Sett", "Fighter");
        testDB.addChampion("Ezreal", "Marksman");

        assertEquals(3, testDB.size());
    }

    @Test
    public void testAddSimilarNameDifferentCase() {
        testDB.addChampion("Ahri", "Mage");
        testDB.addChampion("ahri", "Mage");

        assertEquals(2, testDB.size());
        assertTrue(testDB.contains("Ahri"));
        assertTrue(testDB.contains("ahri"));
    }

    // removeChampion Tests

    @Test
    public void testRemoveOneChampion() {
        testDB.addChampion("Ahri", "Mage");

        String removed = testDB.removeChampion("Ahri");

        assertEquals("Mage", removed);
        assertEquals(0, testDB.size());
        assertFalse(testDB.contains("Ahri"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullName() {
        testDB.removeChampion(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotPresent() {
        testDB.removeChampion("InvalidName");
    }

    @Test
    public void testRemoveOneOfMany() {
        testDB.addChampion("Ahri", "Mage");
        testDB.addChampion("Sett", "Fighter");
        testDB.addChampion("Ezreal", "Marksman");

        testDB.removeChampion("Sett");

        assertEquals(2, testDB.size());
        assertFalse(testDB.contains("Sett"));
    }

    @Test
    public void testRemoveRepeatedlyUntilEmpty() {
        testDB.addChampion("Ahri", "Mage");
        testDB.addChampion("Sett", "Fighter");

        testDB.removeChampion("Sett");
        testDB.removeChampion("Ahri");

        assertEquals(0, testDB.size());
    }

    // clear() Tests

    @Test
    public void testClearEmpty() {
        testDB.clear();
        assertEquals(0, testDB.size());
    }

    @Test
    public void testClearAfterEntries() {
        testDB.addChampion("Ahri", "Mage");
        testDB.addChampion("Sett", "Fighter");
        testDB.addChampion("Ezreal", "Marksman");

        testDB.clear();
        assertEquals(0, testDB.size());
    }

    @Test
    public void testClearTwice() {
        testDB.addChampion("Ahri", "Mage");
        testDB.clear();
        testDB.clear();

        assertEquals(0, testDB.size());
    }

    // size() Tests

    @Test
    public void testSizeUpdatesCorrectly() {
        assertEquals(0, testDB.size());
        testDB.addChampion("Ahri", "Mage");
        assertEquals(1, testDB.size());
        testDB.addChampion("Sett", "Fighter");
        assertEquals(2, testDB.size());
        testDB.removeChampion("Ahri");
        assertEquals(1, testDB.size());
    }

    // transferFrom Tests

    @Test
    public void testTransferFromTypical() {
        ChampionDBKernel source = new ChampionDBKernel1L();
        source.addChampion("Ahri", "Mage");
        source.addChampion("Sett", "Fighter");

        this.testDB.transferFrom(source);

        assertEquals(2, testDB.size());
        assertTrue(testDB.contains("Ahri"));
        assertTrue(testDB.contains("Sett"));

        assertEquals(0, source.size());
    }

    @Test
    public void testTransferFromEmptyIntoFilled() {
        this.testDB.addChampion("Ahri", "Mage");

        ChampionDBKernel empty = new ChampionDBKernel1L();

        this.testDB.transferFrom(empty);

        assertEquals(1, this.testDB.size());
        assertTrue(this.testDB.contains("Ahri"));
    }

    @Test
    public void testTransferFromFilledIntoEmpty() {
        ChampionDBKernel src = new ChampionDBKernel1L();
        src.addChampion("Sett", "Fighter");

        this.testDB.transferFrom(src);

        assertEquals(1, this.testDB.size());
        assertTrue(testDB.contains("Sett"));

        assertEquals(0, src.size());
    }

    // contains Tests

    @Test
    public void testContainsFalseInitially() {
        assertFalse(testDB.contains("Ahri"));
    }

    @Test
    public void testContainsTrueAfterInsert() {
        testDB.addChampion("Ahri", "Mage");
        assertTrue(testDB.contains("Ahri"));
    }

    @Test
    public void testContainsAfterRemoval() {
        testDB.addChampion("Ahri", "Mage");
        testDB.removeChampion("Ahri");
        assertFalse(testDB.contains("Ahri"));
    }

    @Test
    public void testContainsCaseSensitivity() {
        testDB.addChampion("Ahri", "Mage");
        assertFalse(testDB.contains("ahri"));
    }
}