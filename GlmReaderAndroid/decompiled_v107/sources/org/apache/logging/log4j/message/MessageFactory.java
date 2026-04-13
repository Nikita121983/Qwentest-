package org.apache.logging.log4j.message;

/* loaded from: classes10.dex */
public interface MessageFactory {
    Message newMessage(Object message);

    Message newMessage(String message);

    Message newMessage(String message, Object... params);
}
