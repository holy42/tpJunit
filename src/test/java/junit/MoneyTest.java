package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	
	private Money f12CHF, f14CHF, f7USD, f21USD;
    private MoneyBag fMB1, fMB2;

    @Before
    public void setUp() {
    	f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD  = new Money(7,  "USD");
        f21USD = new Money(21, "USD");
        fMB1   = new MoneyBag(new Money[]{ f12CHF, f7USD });
        fMB2   = new MoneyBag(new Money[]{ f14CHF, f21USD });
    }
	
    @Test
    public void testMixedSimpleAdd() {
    	// [12 CHF] + [7 USD] == {[12 CHF][7 USD]} 
    	Money bag[] = { f12CHF, f7USD }; 
    	MoneyBag expected = new MoneyBag(bag); 
    	assertEquals(expected, f12CHF.add(f7USD));
    }

    @Test
    public void testBagSimpleAdd() { // MoneyBag + Money
        IMoney sum = fMB1.add(f14CHF);
        MoneyBag expected = new MoneyBag(new Money[]{ new Money(26,"CHF"), f7USD });
        assertEquals(expected, sum);
    }

    @Test
    public void testSimpleBagAdd() { // Money + MoneyBag
        IMoney sum = f14CHF.add(fMB1);
        MoneyBag expected = new MoneyBag(new Money[]{ new Money(26,"CHF"), f7USD });
        assertEquals(expected, sum);
    }

    @Test
    public void testBagBagAdd() { // MoneyBag + MoneyBag
        IMoney sum = fMB1.add(fMB2);
        MoneyBag expected = new MoneyBag(new Money[]{ new Money(26,"CHF"), new Money(28,"USD") });
        assertEquals(expected, sum);
    }
    @Test
    public void testEquals() {
        assertTrue(!f12CHF.equals(null));
        assertEquals(f12CHF, f12CHF);
        assertEquals(f12CHF, new Money(12, "CHF"));
        assertTrue(!f12CHF.equals(f14CHF));
    }

}
