package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes9.dex */
public class FixedOrderComparator<T> implements Comparator<T>, Serializable {
    private static final long serialVersionUID = 82794675842863201L;
    private int counter;
    private boolean isLocked;
    private final Map<T, Integer> map = new HashMap();
    private UnknownObjectBehavior unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;

    /* loaded from: classes9.dex */
    public enum UnknownObjectBehavior {
        BEFORE,
        AFTER,
        EXCEPTION
    }

    public FixedOrderComparator() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FixedOrderComparator(List<T> items) {
        Iterator it = ((List) Objects.requireNonNull(items, "items")).iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FixedOrderComparator(T... items) {
        for (Object obj : (Object[]) Objects.requireNonNull(items, "items")) {
            add(obj);
        }
    }

    public boolean add(T obj) {
        checkLocked();
        Map<T, Integer> map = this.map;
        int i = this.counter;
        this.counter = i + 1;
        Integer position = map.put(obj, Integer.valueOf(i));
        return position == null;
    }

    public boolean addAsEqual(T existingObj, T newObj) {
        checkLocked();
        Integer position = this.map.get(existingObj);
        if (position == null) {
            throw new IllegalArgumentException(existingObj + " not known to " + this);
        }
        Integer result = this.map.put(newObj, position);
        return result == null;
    }

    protected void checkLocked() {
        if (isLocked()) {
            throw new UnsupportedOperationException("Cannot modify a FixedOrderComparator after a comparison");
        }
    }

    @Override // java.util.Comparator
    public int compare(T obj1, T obj2) {
        this.isLocked = true;
        Integer position1 = this.map.get(obj1);
        Integer position2 = this.map.get(obj2);
        if (position1 == null || position2 == null) {
            switch (this.unknownObjectBehavior) {
                case BEFORE:
                    if (position1 == null) {
                        return position2 == null ? 0 : -1;
                    }
                    return 1;
                case AFTER:
                    if (position1 == null) {
                        return position2 == null ? 0 : 1;
                    }
                    return -1;
                case EXCEPTION:
                    Object unknownObj = position1 == null ? obj1 : obj2;
                    throw new IllegalArgumentException("Attempting to compare unknown object " + unknownObj);
                default:
                    throw new UnsupportedOperationException("Unknown unknownObjectBehavior: " + this.unknownObjectBehavior);
            }
        }
        return position1.compareTo(position2);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FixedOrderComparator<?> other = (FixedOrderComparator) obj;
        if (this.counter == other.counter && this.isLocked == other.isLocked && Objects.equals(this.map, other.map) && this.unknownObjectBehavior == other.unknownObjectBehavior) {
            return true;
        }
        return false;
    }

    public UnknownObjectBehavior getUnknownObjectBehavior() {
        return this.unknownObjectBehavior;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.counter), Boolean.valueOf(this.isLocked), this.map, this.unknownObjectBehavior);
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public void setUnknownObjectBehavior(UnknownObjectBehavior unknownObjectBehavior) {
        checkLocked();
        this.unknownObjectBehavior = (UnknownObjectBehavior) Objects.requireNonNull(unknownObjectBehavior, "unknownObjectBehavior");
    }
}
