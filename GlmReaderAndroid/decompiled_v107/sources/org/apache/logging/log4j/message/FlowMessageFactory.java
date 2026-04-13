package org.apache.logging.log4j.message;

/* loaded from: classes10.dex */
public interface FlowMessageFactory {
    EntryMessage newEntryMessage(String message, Object... params);

    EntryMessage newEntryMessage(Message message);

    ExitMessage newExitMessage(Object result, EntryMessage message);

    ExitMessage newExitMessage(Object result, Message message);

    ExitMessage newExitMessage(String format, Object result);

    ExitMessage newExitMessage(EntryMessage message);

    ExitMessage newExitMessage(Message message);
}
