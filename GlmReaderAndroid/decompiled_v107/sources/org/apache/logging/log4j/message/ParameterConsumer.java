package org.apache.logging.log4j.message;

/* loaded from: classes10.dex */
public interface ParameterConsumer<S> {
    void accept(Object parameter, int parameterIndex, S state);
}
