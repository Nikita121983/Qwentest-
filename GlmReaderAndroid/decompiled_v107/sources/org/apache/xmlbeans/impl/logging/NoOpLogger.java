package org.apache.xmlbeans.impl.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.FlowMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.Supplier;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class NoOpLogger implements Logger {
    static final NoOpLogger INSTANCE = new NoOpLogger();

    NoOpLogger() {
    }

    @Override // org.apache.logging.log4j.Logger
    public void catching(Level level, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void catching(Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void debug(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void entry() {
    }

    @Override // org.apache.logging.log4j.Logger
    public void entry(Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void error(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void exit() {
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R exit(R result) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void fatal(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public Level getLevel() {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public <MF extends MessageFactory> MF getMessageFactory() {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public FlowMessageFactory getFlowMessageFactory() {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public String getName() {
        return "";
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void info(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isDebugEnabled() {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isEnabled(Level level) {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isEnabled(Level level, Marker marker) {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isErrorEnabled() {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isFatalEnabled() {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isFatalEnabled(Marker marker) {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isInfoEnabled() {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isTraceEnabled() {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isWarnEnabled() {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void log(Level level, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void printf(Level level, Marker marker, String format, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void printf(Level level, String format, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public <T extends Throwable> T throwing(Level level, T throwable) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public <T extends Throwable> T throwing(T throwable) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void trace(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry() {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(String format, Object... params) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(Supplier<?>... paramSuppliers) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(String format, Supplier<?>... paramSuppliers) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public EntryMessage traceEntry(Message message) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public void traceExit() {
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(R result) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(String format, R result) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public void traceExit(EntryMessage message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(EntryMessage message, R result) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public <R> R traceExit(Message message, R result) {
        return null;
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Message message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Message message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(MessageSupplier messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(MessageSupplier messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(CharSequence message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(CharSequence message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Object message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Object message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object... params) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Supplier<?>... paramSuppliers) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Supplier<?> messageSupplier) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Supplier<?> messageSupplier, Throwable throwable) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(Marker marker, String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1, Object p2) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1, Object p2, Object p3) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
    }

    @Override // org.apache.logging.log4j.Logger
    public void warn(String message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    }
}
