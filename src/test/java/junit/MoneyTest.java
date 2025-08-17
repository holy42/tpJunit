package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	
	private Money f12CHF, f14CHF, f7USD;

    @Before
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD = new Money(7, "USD");
    }
	
    @Test
    public void testSimpleAdd() {
    	// [12 CHF] + [7 USD] == {[12 CHF][7 USD]} 
    	Money bag[] = { f12CHF, f7USD }; 
    	MoneyBag expected = new MoneyBag(bag); 
    	assertEquals(expected, f12CHF.add(f7USD));
    }

    @Test
    public void testEquals() {
        assertTrue(!f12CHF.equals(null));
        assertEquals(f12CHF, f12CHF);
        assertEquals(f12CHF, new Money(12, "CHF"));
        assertTrue(!f12CHF.equals(f14CHF));
    }

}
