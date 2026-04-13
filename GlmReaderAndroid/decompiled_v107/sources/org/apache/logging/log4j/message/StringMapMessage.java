package org.apache.logging.log4j.message;

import java.util.Map;

@AsynchronouslyFormattable
/* loaded from: classes10.dex */
public class StringMapMessage extends MapMessage<StringMapMessage, String> {
    private static final long serialVersionUID = 1;

    public StringMapMessage() {
    }

    public StringMapMessage(final int initialCapacity) {
        super(initialCapacity);
    }

    public StringMapMessage(final Map<String, String> map) {
        super(map);
    }

    @Override // org.apache.logging.log4j.message.MapMessage
    public StringMapMessage newInstance(final Map<String, String> map) {
        return new StringMapMessage(map);
    }
}
