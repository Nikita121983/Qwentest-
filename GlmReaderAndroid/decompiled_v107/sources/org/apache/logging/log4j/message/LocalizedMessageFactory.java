package org.apache.logging.log4j.message;

import java.util.Objects;
import java.util.ResourceBundle;

/* loaded from: classes10.dex */
public class LocalizedMessageFactory extends AbstractMessageFactory {
    private static final long serialVersionUID = -1996295808703146741L;
    private final String baseName;
    private final transient ResourceBundle resourceBundle;

    public LocalizedMessageFactory(final ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        this.baseName = null;
    }

    public LocalizedMessageFactory(final String baseName) {
        this.resourceBundle = null;
        this.baseName = baseName;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(final String key) {
        if (this.resourceBundle == null) {
            return new LocalizedMessage(this.baseName, key);
        }
        return new LocalizedMessage(this.resourceBundle, key);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(final String key, final Object... params) {
        if (this.resourceBundle == null) {
            return new LocalizedMessage(this.baseName, key, params);
        }
        return new LocalizedMessage(this.resourceBundle, key, params);
    }

    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LocalizedMessageFactory that = (LocalizedMessageFactory) object;
        if (Objects.equals(this.resourceBundle, that.resourceBundle) && Objects.equals(this.baseName, that.baseName)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.resourceBundle, this.baseName);
    }
}
