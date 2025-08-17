package junit;

import java.util.Objects;

class Money implements IMoney{ 
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
	
	@Override
    public IMoney add(IMoney m) {
        return m.addMoney(this); 
    }

    @Override
    public IMoney addMoney(Money other) {
        if (currency().equals(other.currency())) {
            return new Money(amount() + other.amount(), currency());
        }
        return new MoneyBag(this, other);
    }

    @Override
    public IMoney addMoneyBag(MoneyBag bag) {
        return bag.addMoney(this);
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
