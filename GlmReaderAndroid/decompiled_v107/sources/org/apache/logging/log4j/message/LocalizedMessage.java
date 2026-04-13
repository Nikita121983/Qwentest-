package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes10.dex */
public class LocalizedMessage implements Message, LoggerNameAwareMessage {
    private static final long serialVersionUID = 3893703791567290742L;
    private transient Object[] argArray;
    private String baseName;
    private String formattedMessage;
    private String key;
    private final Locale locale;
    private transient StatusLogger logger;
    private String loggerName;
    private transient ResourceBundle resourceBundle;
    private String[] stringArgs;
    private transient Throwable throwable;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final String messagePattern, final Object[] arguments) {
        this((ResourceBundle) null, (Locale) null, messagePattern, arguments);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final String baseName, final String key, final Object[] arguments) {
        this(baseName, (Locale) null, key, arguments);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final ResourceBundle bundle, final String key, final Object[] arguments) {
        this(bundle, (Locale) null, key, arguments);
    }

    public LocalizedMessage(final String baseName, final Locale locale, final String key, final Object[] arguments) {
        this.logger = StatusLogger.getLogger();
        this.key = key;
        this.argArray = arguments;
        this.throwable = null;
        this.baseName = baseName;
        this.resourceBundle = null;
        this.locale = locale;
    }

    public LocalizedMessage(final ResourceBundle bundle, final Locale locale, final String key, final Object[] arguments) {
        this.logger = StatusLogger.getLogger();
        this.key = key;
        this.argArray = arguments;
        this.throwable = null;
        this.baseName = null;
        this.resourceBundle = bundle;
        this.locale = locale;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final Locale locale, final String key, final Object[] arguments) {
        this((ResourceBundle) null, locale, key, arguments);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final String messagePattern, final Object arg) {
        this((ResourceBundle) null, (Locale) null, messagePattern, new Object[]{arg});
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final String baseName, final String key, final Object arg) {
        this(baseName, (Locale) null, key, new Object[]{arg});
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final ResourceBundle bundle, final String key) {
        this(bundle, (Locale) null, key, new Object[0]);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final ResourceBundle bundle, final String key, final Object arg) {
        this(bundle, (Locale) null, key, new Object[]{arg});
    }

    public LocalizedMessage(final String baseName, final Locale locale, final String key, final Object arg) {
        this(baseName, locale, key, new Object[]{arg});
    }

    public LocalizedMessage(final ResourceBundle bundle, final Locale locale, final String key, final Object arg) {
        this(bundle, locale, key, new Object[]{arg});
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final Locale locale, final String key, final Object arg) {
        this((ResourceBundle) null, locale, key, new Object[]{arg});
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final String messagePattern, final Object arg1, final Object arg2) {
        this((ResourceBundle) null, (Locale) null, messagePattern, new Object[]{arg1, arg2});
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final String baseName, final String key, final Object arg1, final Object arg2) {
        this(baseName, (Locale) null, key, new Object[]{arg1, arg2});
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final ResourceBundle bundle, final String key, final Object arg1, final Object arg2) {
        this(bundle, (Locale) null, key, new Object[]{arg1, arg2});
    }

    public LocalizedMessage(final String baseName, final Locale locale, final String key, final Object arg1, final Object arg2) {
        this(baseName, locale, key, new Object[]{arg1, arg2});
    }

    public LocalizedMessage(final ResourceBundle bundle, final Locale locale, final String key, final Object arg1, final Object arg2) {
        this(bundle, locale, key, new Object[]{arg1, arg2});
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalizedMessage(final Locale locale, final String key, final Object arg1, final Object arg2) {
        this((ResourceBundle) null, locale, key, new Object[]{arg1, arg2});
    }

    @Override // org.apache.logging.log4j.message.LoggerNameAwareMessage
    public void setLoggerName(final String name) {
        this.loggerName = name;
    }

    @Override // org.apache.logging.log4j.message.LoggerNameAwareMessage
    public String getLoggerName() {
        return this.loggerName;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.formattedMessage != null) {
            return this.formattedMessage;
        }
        ResourceBundle bundle = this.resourceBundle;
        if (bundle == null) {
            if (this.baseName != null) {
                bundle = getResourceBundle(this.baseName, this.locale, false);
            } else {
                bundle = getResourceBundle(this.loggerName, this.locale, true);
            }
        }
        String myKey = getFormat();
        String msgPattern = (bundle == null || !bundle.containsKey(myKey)) ? myKey : bundle.getString(myKey);
        Object[] array = this.argArray == null ? this.stringArgs : this.argArray;
        FormattedMessage msg = new FormattedMessage(msgPattern, array);
        this.formattedMessage = msg.getFormattedMessage();
        this.throwable = msg.getThrowable();
        return this.formattedMessage;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.key;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        if (this.argArray != null) {
            return this.argArray;
        }
        return this.stringArgs;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return this.throwable;
    }

    protected ResourceBundle getResourceBundle(final String rbBaseName, final Locale resourceBundleLocale, final boolean loop) {
        ResourceBundle rb = null;
        ResourceBundle rb2 = null;
        if (rbBaseName == null) {
            return null;
        }
        try {
            if (resourceBundleLocale != null) {
                rb2 = ResourceBundle.getBundle(rbBaseName, resourceBundleLocale);
                rb = rb2;
            } else {
                rb2 = ResourceBundle.getBundle(rbBaseName);
                rb = rb2;
            }
        } catch (MissingResourceException e) {
            if (!loop) {
                this.logger.debug("Unable to locate ResourceBundle {}", rbBaseName);
                return rb2;
            }
        }
        String substr = rbBaseName;
        while (rb == null) {
            int i = substr.lastIndexOf(46);
            if (i <= 0) {
                break;
            }
            substr = substr.substring(0, i);
            if (resourceBundleLocale != null) {
                try {
                    rb = ResourceBundle.getBundle(substr, resourceBundleLocale);
                } catch (MissingResourceException e2) {
                    this.logger.debug("Unable to locate ResourceBundle {}", substr);
                }
            } else {
                ResourceBundle rb3 = ResourceBundle.getBundle(substr);
                rb = rb3;
            }
        }
        return rb;
    }

    public String toString() {
        return getFormattedMessage();
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        getFormattedMessage();
        out.writeUTF(this.formattedMessage);
        out.writeUTF(this.key);
        out.writeUTF(this.baseName);
        out.writeInt(this.argArray.length);
        this.stringArgs = new String[this.argArray.length];
        int i = 0;
        for (Object obj : this.argArray) {
            this.stringArgs[i] = obj.toString();
            i++;
        }
        out.writeObject(this.stringArgs);
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.formattedMessage = in.readUTF();
        this.key = in.readUTF();
        this.baseName = in.readUTF();
        in.readInt();
        this.stringArgs = (String[]) in.readObject();
        this.logger = StatusLogger.getLogger();
        this.resourceBundle = null;
        this.argArray = null;
    }
}
