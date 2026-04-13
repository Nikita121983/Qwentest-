package org.apache.logging.log4j;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.spi.CleanableThreadContextMap;
import org.apache.logging.log4j.spi.DefaultThreadContextMap;
import org.apache.logging.log4j.spi.DefaultThreadContextStack;
import org.apache.logging.log4j.spi.MutableThreadContextStack;
import org.apache.logging.log4j.spi.ReadOnlyThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextMap2;
import org.apache.logging.log4j.spi.ThreadContextMapFactory;
import org.apache.logging.log4j.spi.ThreadContextStack;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.ProviderUtil;

/* loaded from: classes10.dex */
public final class ThreadContext {
    private static final String DISABLE_ALL = "disableThreadContext";
    private static final String DISABLE_STACK = "disableThreadContextStack";
    public static final Map<String, String> EMPTY_MAP = Collections.emptyMap();
    public static final ThreadContextStack EMPTY_STACK = new EmptyThreadContextStack();
    private static ThreadContextMap contextMap;
    private static ThreadContextStack contextStack;
    private static ReadOnlyThreadContextMap readOnlyContextMap;

    /* loaded from: classes10.dex */
    public interface ContextStack extends Serializable, Collection<String> {
        List<String> asList();

        ContextStack copy();

        int getDepth();

        ContextStack getImmutableStackOrNull();

        String peek();

        String pop();

        void push(String message);

        void trim(int depth);
    }

    /* loaded from: classes10.dex */
    private static class EmptyThreadContextStack extends AbstractCollection<String> implements ThreadContextStack {
        private static final long serialVersionUID = 1;

        private EmptyThreadContextStack() {
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public String pop() {
            return null;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public String peek() {
            return null;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public void push(final String message) {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public int getDepth() {
            return 0;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public List<String> asList() {
            return Collections.emptyList();
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public void trim(final int depth) {
        }

        @Override // java.util.Collection
        public boolean equals(final Object o) {
            return (o instanceof Collection) && ((Collection) o).isEmpty();
        }

        @Override // java.util.Collection
        public int hashCode() {
            return 1;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public ContextStack copy() {
            return new MutableThreadContextStack();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(final T[] ignored) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(final String ignored) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(final Collection<?> ignored) {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(final Collection<? extends String> ignored) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(final Collection<?> ignored) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(final Collection<?> ignored) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<String> iterator() {
            return Collections.emptyIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return 0;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public ContextStack getImmutableStackOrNull() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static final class NoOpThreadContextStack extends EmptyThreadContextStack {
        private NoOpThreadContextStack() {
            super();
        }

        @Override // org.apache.logging.log4j.ThreadContext.EmptyThreadContextStack, java.util.AbstractCollection, java.util.Collection
        public boolean add(final String ignored) {
            return false;
        }

        @Override // org.apache.logging.log4j.ThreadContext.EmptyThreadContextStack, java.util.AbstractCollection, java.util.Collection
        public boolean addAll(final Collection<? extends String> ignored) {
            return false;
        }

        @Override // org.apache.logging.log4j.ThreadContext.EmptyThreadContextStack, org.apache.logging.log4j.ThreadContext.ContextStack
        public void push(final String ignored) {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(final Object ignored) {
            return false;
        }

        @Override // org.apache.logging.log4j.ThreadContext.EmptyThreadContextStack, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(final Collection<?> ignored) {
            return false;
        }

        @Override // org.apache.logging.log4j.ThreadContext.EmptyThreadContextStack, java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(final Collection<?> ignored) {
            return false;
        }
    }

    static {
        init();
    }

    private ThreadContext() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void init() {
        ThreadContextStack noOpThreadContextStack;
        PropertiesUtil properties = PropertiesUtil.getProperties();
        Object[] objArr = 0;
        if (properties.getBooleanProperty(DISABLE_STACK) || properties.getBooleanProperty(DISABLE_ALL)) {
            noOpThreadContextStack = new NoOpThreadContextStack();
        } else {
            noOpThreadContextStack = new DefaultThreadContextStack();
        }
        contextStack = noOpThreadContextStack;
        ThreadContextMapFactory.init();
        contextMap = ProviderUtil.getProvider().getThreadContextMapInstance();
        readOnlyContextMap = contextMap instanceof ReadOnlyThreadContextMap ? (ReadOnlyThreadContextMap) contextMap : null;
    }

    public static void put(final String key, final String value) {
        contextMap.put(key, value);
    }

    public static void putIfNull(final String key, final String value) {
        if (!contextMap.containsKey(key)) {
            contextMap.put(key, value);
        }
    }

    public static void putAll(final Map<String, String> m) {
        if (contextMap instanceof ThreadContextMap2) {
            ((ThreadContextMap2) contextMap).putAll(m);
            return;
        }
        if (contextMap instanceof DefaultThreadContextMap) {
            ((DefaultThreadContextMap) contextMap).putAll(m);
            return;
        }
        for (Map.Entry<String, String> entry : m.entrySet()) {
            contextMap.put(entry.getKey(), entry.getValue());
        }
    }

    public static String get(final String key) {
        return contextMap.get(key);
    }

    public static void remove(final String key) {
        contextMap.remove(key);
    }

    public static void removeAll(final Iterable<String> keys) {
        if (contextMap instanceof CleanableThreadContextMap) {
            ((CleanableThreadContextMap) contextMap).removeAll(keys);
            return;
        }
        if (contextMap instanceof DefaultThreadContextMap) {
            ((DefaultThreadContextMap) contextMap).removeAll(keys);
            return;
        }
        for (String key : keys) {
            contextMap.remove(key);
        }
    }

    public static void clearMap() {
        contextMap.clear();
    }

    public static void clearAll() {
        clearMap();
        clearStack();
    }

    public static boolean containsKey(final String key) {
        return contextMap.containsKey(key);
    }

    public static Map<String, String> getContext() {
        return contextMap.getCopy();
    }

    public static Map<String, String> getImmutableContext() {
        Map<String, String> map = contextMap.getImmutableMapOrNull();
        return map == null ? EMPTY_MAP : map;
    }

    public static ReadOnlyThreadContextMap getThreadContextMap() {
        return readOnlyContextMap;
    }

    public static boolean isEmpty() {
        return contextMap.isEmpty();
    }

    public static void clearStack() {
        contextStack.clear();
    }

    public static ContextStack cloneStack() {
        return contextStack.copy();
    }

    public static ContextStack getImmutableStack() {
        ContextStack result = contextStack.getImmutableStackOrNull();
        return result == null ? EMPTY_STACK : result;
    }

    public static void setStack(final Collection<String> stack) {
        if (stack.isEmpty()) {
            return;
        }
        contextStack.clear();
        contextStack.addAll(stack);
    }

    public static int getDepth() {
        return contextStack.getDepth();
    }

    public static String pop() {
        return contextStack.pop();
    }

    public static String peek() {
        return contextStack.peek();
    }

    public static void push(final String message) {
        contextStack.push(message);
    }

    public static void push(final String message, final Object... args) {
        contextStack.push(ParameterizedMessage.format(message, args));
    }

    public static void removeStack() {
        contextStack.clear();
    }

    public static void trim(final int depth) {
        contextStack.trim(depth);
    }
}
