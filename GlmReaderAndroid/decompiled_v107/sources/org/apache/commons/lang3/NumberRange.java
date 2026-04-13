package org.apache.commons.lang3;

import java.lang.Number;
import java.util.Comparator;

/* loaded from: classes9.dex */
public class NumberRange<N extends Number> extends Range<N> {
    private static final long serialVersionUID = 1;

    public NumberRange(N number1, N number2, Comparator<N> comp) {
        super(number1, number2, comp);
    }
}
