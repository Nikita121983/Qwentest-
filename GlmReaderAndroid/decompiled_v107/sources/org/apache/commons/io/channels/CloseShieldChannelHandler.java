package org.apache.commons.io.channels;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.InterruptibleChannel;
import java.nio.channels.NetworkChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes9.dex */
final class CloseShieldChannelHandler implements InvocationHandler {
    private static final Set<Class<? extends Channel>> SUPPORTED_INTERFACES;
    private volatile boolean closed;
    private final Channel delegate;

    static {
        Set<Class<? extends Channel>> interfaces = new HashSet<>();
        interfaces.add(AsynchronousChannel.class);
        interfaces.add(ByteChannel.class);
        interfaces.add(Channel.class);
        interfaces.add(GatheringByteChannel.class);
        interfaces.add(InterruptibleChannel.class);
        interfaces.add(NetworkChannel.class);
        interfaces.add(ReadableByteChannel.class);
        interfaces.add(ScatteringByteChannel.class);
        interfaces.add(SeekableByteChannel.class);
        interfaces.add(WritableByteChannel.class);
        SUPPORTED_INTERFACES = Collections.unmodifiableSet(interfaces);
    }

    private static boolean isAllowedAfterClose(Class<?> declaringClass, String name, int parameterCount) {
        return parameterCount == 0 && name.equals("supportedOptions") && NetworkChannel.class.equals(declaringClass);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSupported(Class<?> interfaceClass) {
        return SUPPORTED_INTERFACES.contains(interfaceClass);
    }

    private static boolean returnsThis(Class<?> declaringClass, String name, int parameterCount) {
        if (SeekableByteChannel.class.equals(declaringClass)) {
            if (parameterCount == 1) {
                return name.equals("position") || name.equals("truncate");
            }
            return false;
        }
        if (NetworkChannel.class.equals(declaringClass)) {
            return (parameterCount == 1 && name.equals("bind")) || (parameterCount == 2 && name.equals("setOption"));
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CloseShieldChannelHandler(Channel delegate) {
        this.delegate = (Channel) Objects.requireNonNull(delegate, "delegate");
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> declaringClass = method.getDeclaringClass();
        String name = method.getName();
        int parameterCount = method.getParameterCount();
        if (declaringClass == Object.class) {
            return invokeObjectMethod(proxy, method, args);
        }
        if (parameterCount == 0 && name.equals("close")) {
            this.closed = true;
            return null;
        }
        if (parameterCount == 0 && name.equals("isOpen")) {
            return Boolean.valueOf(!this.closed && this.delegate.isOpen());
        }
        if (this.closed && !isAllowedAfterClose(declaringClass, name, parameterCount)) {
            throw new ClosedChannelException();
        }
        try {
            Object result = method.invoke(this.delegate, args);
            return returnsThis(declaringClass, name, parameterCount) ? proxy : result;
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private Object invokeObjectMethod(Object proxy, Method method, Object[] args) {
        char c;
        String name = method.getName();
        switch (name.hashCode()) {
            case -1776922004:
                if (name.equals("toString")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1295482945:
                if (name.equals("equals")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 147696667:
                if (name.equals("hashCode")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return "CloseShieldChannel(" + this.delegate + ")";
            case 1:
                return Integer.valueOf(Objects.hashCode(this.delegate));
            case 2:
                Object other = args[0];
                if (other == null) {
                    return false;
                }
                if (proxy == other) {
                    return true;
                }
                if (Proxy.isProxyClass(other.getClass())) {
                    InvocationHandler h = Proxy.getInvocationHandler(other);
                    if (h instanceof CloseShieldChannelHandler) {
                        return Boolean.valueOf(Objects.equals(((CloseShieldChannelHandler) h).delegate, this.delegate));
                    }
                }
                return false;
            default:
                return null;
        }
    }
}
