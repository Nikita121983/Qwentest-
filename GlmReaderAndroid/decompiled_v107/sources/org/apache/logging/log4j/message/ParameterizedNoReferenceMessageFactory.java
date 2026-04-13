package org.apache.logging.log4j.message;

/* loaded from: classes10.dex */
public final class ParameterizedNoReferenceMessageFactory extends AbstractMessageFactory {
    public static final ParameterizedNoReferenceMessageFactory INSTANCE = new ParameterizedNoReferenceMessageFactory();
    private static final long serialVersionUID = 5027639245636870500L;

    /* loaded from: classes10.dex */
    static class StatusMessage implements Message {
        private static final long serialVersionUID = 4199272162767841280L;
        private final String formattedMessage;
        private final Throwable throwable;

        public StatusMessage(final String formattedMessage, final Throwable throwable) {
            this.formattedMessage = formattedMessage;
            this.throwable = throwable;
        }

        @Override // org.apache.logging.log4j.message.Message
        public String getFormattedMessage() {
            return this.formattedMessage;
        }

        @Override // org.apache.logging.log4j.message.Message
        public String getFormat() {
            return this.formattedMessage;
        }

        @Override // org.apache.logging.log4j.message.Message
        public Object[] getParameters() {
            return null;
        }

        @Override // org.apache.logging.log4j.message.Message
        public Throwable getThrowable() {
            return this.throwable;
        }
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(final String message, final Object... params) {
        if (params == null) {
            return new SimpleMessage(message);
        }
        ParameterizedMessage msg = new ParameterizedMessage(message, params);
        return new StatusMessage(msg.getFormattedMessage(), msg.getThrowable());
    }
}
