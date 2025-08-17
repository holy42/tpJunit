package junit;

import java.util.*;

class MoneyBag implements IMoney {
    private final Vector<Money> fMonies = new Vector<>();

    MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    MoneyBag(Money[] monies) {
        for (Money m : monies) {
            appendMoney(m);
        }
    }

    private void appendMoney(Money m) {
        if (m.amount() == 0) return;

        for (int i = 0; i < fMonies.size(); i++) {
            Money existing = fMonies.get(i);
            if (existing.currency().equals(m.currency())) {
                int sum = existing.amount() + m.amount();
                if (sum == 0) {
                    fMonies.remove(i);
                } else {
                    fMonies.set(i, new Money(sum, m.currency()));
                }
                return;
            }
        }
        fMonies.add(m);
    }

    public Vector<Money> monies() {
        return new Vector<>(fMonies);
    }

    // ===== Implémentation de IMoney =====
    @Override
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    @Override
    public IMoney addMoney(Money m) {
        MoneyBag result = new MoneyBag(this.fMonies.toArray(new Money[0]));
        result.appendMoney(m);
        return result;
    }

    @Override
    public IMoney addMoneyBag(MoneyBag bag) {
        MoneyBag result = new MoneyBag(this.fMonies.toArray(new Money[0]));
        for (Money m : bag.fMonies) {
            result.appendMoney(m);
        }
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MoneyBag)) return false;
        MoneyBag other = (MoneyBag) obj;
        if (this.fMonies.size() != other.fMonies.size()) return false;
        // Compare par contenu (devise->montant)
        Map<String,Integer> map1 = toMap(this.fMonies);
        Map<String,Integer> map2 = toMap(other.fMonies);
        return map1.equals(map2);
    }

    @Override
    public int hashCode() {
        return toMap(fMonies).hashCode();
    }

    private static Map<String,Integer> toMap(Vector<Money> monies) {
        Map<String,Integer> m = new HashMap<>();
        for (Money x : monies) m.put(x.currency(), x.amount());
        return m;
    }
}
