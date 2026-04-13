package org.apache.logging.log4j.util;

import org.apache.logging.log4j.message.Message;

@FunctionalInterface
/* loaded from: classes10.dex */
public interface MessageSupplier extends Supplier<Message> {
    @Override // org.apache.logging.log4j.util.Supplier, java.util.function.Supplier
    Message get();
}
