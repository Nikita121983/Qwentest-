package org.apache.commons.math3.ode;

import java.util.Collection;

/* loaded from: classes10.dex */
public interface Parameterizable {
    Collection<String> getParametersNames();

    boolean isSupported(String str);
}
