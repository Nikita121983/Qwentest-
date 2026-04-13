package org.apache.commons.io.channels;

import java.lang.reflect.Proxy;
import java.nio.channels.Channel;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes9.dex */
public final class CloseShieldChannel {
    private static final Class<?>[] EMPTY = new Class[0];

    private static Set<Class<?>> collectChannelInterfaces(Class<?> type, Set<Class<?>> out) {
        for (Class<?> currentType = type; currentType != null; currentType = currentType.getSuperclass()) {
            for (Class<?> iface : currentType.getInterfaces()) {
                if (CloseShieldChannelHandler.isSupported(iface) && out.add(iface)) {
                    collectChannelInterfaces(iface, out);
                }
            }
        }
        return out;
    }

    public static <T extends Channel> T wrap(T channel) {
        Objects.requireNonNull(channel, "channel");
        if (Proxy.isProxyClass(channel.getClass()) && (Proxy.getInvocationHandler(channel) instanceof CloseShieldChannelHandler)) {
            return channel;
        }
        Set<Class<?>> set = collectChannelInterfaces(channel.getClass(), new LinkedHashSet());
        return (T) Proxy.newProxyInstance(channel.getClass().getClassLoader(), set.isEmpty() ? new Class[]{Channel.class} : (Class[]) set.toArray(EMPTY), new CloseShieldChannelHandler(channel));
    }

    private CloseShieldChannel() {
    }
}
