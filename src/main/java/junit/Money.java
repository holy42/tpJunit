package junit;

import java.util.Objects;

class Money { 
	private int fAmount; 
	private String fCurrency; 
	
	public Money(int amount, String currency) { 
		fAmount = amount; 
		fCurrency = currency; 
	} 

	public int amount() { 
		return fAmount; 
	} 
	public String currency() { 
		return fCurrency; 
	}
	
	public Money add(Money m) { 
		return new Money(amount() + m.amount(), currency()); 
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return fAmount == other.fAmount &&
               Objects.equals(fCurrency, other.fCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fAmount, fCurrency);
    }
} 
