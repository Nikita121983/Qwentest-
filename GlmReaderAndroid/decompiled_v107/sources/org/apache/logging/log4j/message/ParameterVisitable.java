package org.apache.logging.log4j.message;

/* loaded from: classes10.dex */
public interface ParameterVisitable {
    <S> void forEachParameter(ParameterConsumer<S> action, S state);
}
