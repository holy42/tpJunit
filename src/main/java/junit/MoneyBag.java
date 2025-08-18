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
        if (m.amount() == 0) return; // on n'ajoute pas de zéro
        for (int i = 0; i < fMonies.size(); i++) {
            Money ex = fMonies.get(i);
            if (ex.currency().equals(m.currency())) {
                int sum = ex.amount() + m.amount();
                if (sum == 0) fMonies.remove(i);
                else fMonies.set(i, new Money(sum, m.currency()));
                return;
            }
        }
        fMonies.add(m);
    }
    
    @Override
    public IMoney addMoney(Money m) {
        MoneyBag res = new MoneyBag(this.fMonies.toArray(new Money[0]));
        res.appendMoney(m);
        return res.simplified();             
    }

    @Override
    public IMoney addMoneyBag(MoneyBag bag) {
        MoneyBag res = new MoneyBag(this.fMonies.toArray(new Money[0]));
        for (Money x : bag.fMonies) res.appendMoney(x);
        return res.simplified();              
    }

    private IMoney simplified() {
        if (fMonies.isEmpty()) return new Money(0, "");
        if (fMonies.size() == 1) return fMonies.firstElement();
        return this;
    }

    public Vector<Money> monies() {
        return new Vector<>(fMonies);
    }

    @Override
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
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
