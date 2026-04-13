package org.apache.commons.lang3.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.Strings;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes9.dex */
public class DefaultExceptionContext implements ExceptionContext, Serializable {
    private static final long serialVersionUID = 20110706;
    private final List<Pair<String, Object>> contextValues = new ArrayList();

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public DefaultExceptionContext addContextValue(String label, Object value) {
        this.contextValues.add(new ImmutablePair(label, value));
        return this;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public List<Pair<String, Object>> getContextEntries() {
        return this.contextValues;
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public Set<String> getContextLabels() {
        return (Set) stream().map(new Function() { // from class: org.apache.commons.lang3.exception.DefaultExceptionContext$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (String) ((Pair) obj).getKey();
            }
        }).collect(Collectors.toSet());
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public List<Object> getContextValues(final String label) {
        return (List) stream().filter(new Predicate() { // from class: org.apache.commons.lang3.exception.DefaultExceptionContext$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = Strings.CS.equals(label, (String) ((Pair) obj).getKey());
                return equals;
            }
        }).map(new DefaultExceptionContext$$ExternalSyntheticLambda1()).collect(Collectors.toList());
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public Object getFirstContextValue(final String label) {
        return stream().filter(new Predicate() { // from class: org.apache.commons.lang3.exception.DefaultExceptionContext$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = Strings.CS.equals(label, (String) ((Pair) obj).getKey());
                return equals;
            }
        }).findFirst().map(new DefaultExceptionContext$$ExternalSyntheticLambda1()).orElse(null);
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public String getFormattedExceptionMessage(String baseMessage) {
        StringBuilder buffer = new StringBuilder(256);
        if (baseMessage != null) {
            buffer.append(baseMessage);
        }
        if (!this.contextValues.isEmpty()) {
            if (buffer.length() > 0) {
                buffer.append('\n');
            }
            buffer.append("Exception Context:\n");
            int i = 0;
            for (Pair<String, Object> pair : this.contextValues) {
                buffer.append("\t[");
                i++;
                buffer.append(i);
                buffer.append(NameUtil.COLON);
                buffer.append(pair.getKey());
                buffer.append("=");
                Object value = pair.getValue();
                try {
                    buffer.append(Objects.toString(value));
                } catch (Exception e) {
                    buffer.append("Exception thrown on toString(): ");
                    buffer.append(ExceptionUtils.getStackTrace(e));
                }
                buffer.append("]\n");
            }
            buffer.append("---------------------------------");
        }
        return buffer.toString();
    }

    @Override // org.apache.commons.lang3.exception.ExceptionContext
    public DefaultExceptionContext setContextValue(final String label, Object value) {
        this.contextValues.removeIf(new Predicate() { // from class: org.apache.commons.lang3.exception.DefaultExceptionContext$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = Strings.CS.equals(label, (String) ((Pair) obj).getKey());
                return equals;
            }
        });
        addContextValue(label, value);
        return this;
    }

    private Stream<Pair<String, Object>> stream() {
        return this.contextValues.stream();
    }
}
