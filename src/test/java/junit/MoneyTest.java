package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	
	private Money f12CHF, f14CHF;

    @Before
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
    }
	
    @Test
    public void testSimpleAdd() {
        Money expected = new Money(26, "CHF");
        Money result = f12CHF.add(f14CHF);
        assertEquals(expected, result);
    }

    @Test
    public void testEquals() {
        assertTrue(!f12CHF.equals(null));
        assertEquals(f12CHF, f12CHF);
        assertEquals(f12CHF, new Money(12, "CHF"));
        assertTrue(!f12CHF.equals(f14CHF));
    }

}
