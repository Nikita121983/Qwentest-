package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

/* loaded from: classes9.dex */
public class MutableLong extends Number implements Comparable<MutableLong>, Mutable<Number> {
    private static final long serialVersionUID = 62986528375L;
    private long value;

    public MutableLong() {
    }

    public MutableLong(long value) {
        this.value = value;
    }

    public MutableLong(Number value) {
        this.value = value.longValue();
    }

    public MutableLong(String value) {
        this.value = Long.parseLong(value);
    }

    public void add(long operand) {
        this.value += operand;
    }

    public void add(Number operand) {
        this.value += operand.longValue();
    }

    public long addAndGet(long operand) {
        this.value += operand;
        return this.value;
    }

    public long addAndGet(Number operand) {
        this.value += operand.longValue();
        return this.value;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableLong other) {
        return NumberUtils.compare(this.value, other.value);
    }

    public void decrement() {
        this.value--;
    }

    public long decrementAndGet() {
        this.value--;
        return this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableLong) && this.value == ((MutableLong) obj).longValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) this.value;
    }

    public long getAndAdd(long operand) {
        long last = this.value;
        this.value += operand;
        return last;
    }

    public long getAndAdd(Number operand) {
        long last = this.value;
        this.value += operand.longValue();
        return last;
    }

    public long getAndDecrement() {
        long last = this.value;
        this.value--;
        return last;
    }

    public long getAndIncrement() {
        long last = this.value;
        this.value++;
        return last;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    @Deprecated
    /* renamed from: getValue */
    public Number getValue2() {
        return Long.valueOf(this.value);
    }

    public int hashCode() {
        return (int) (this.value ^ (this.value >>> 32));
    }

    public void increment() {
        this.value++;
    }

    public long incrementAndGet() {
        this.value++;
        return this.value;
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number value) {
        this.value = value.longValue();
    }

    public void subtract(long operand) {
        this.value -= operand;
    }

    public void subtract(Number operand) {
        this.value -= operand.longValue();
    }

    public Long toLong() {
        return Long.valueOf(longValue());
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
