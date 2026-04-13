package org.apache.logging.log4j.simple.internal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.simple.SimpleLoggerContextFactory;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.apache.logging.log4j.spi.NoOpThreadContextMap;
import org.apache.logging.log4j.spi.Provider;
import org.apache.logging.log4j.spi.ThreadContextMap;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.jspecify.annotations.NullMarked;

@NullMarked
/* loaded from: classes10.dex */
public final class SimpleProvider extends Provider {
    private final ThreadContextMap threadContextMap;

    public SimpleProvider() {
        super(null, "2.6.0");
        this.threadContextMap = Config.INSTANCE.showContextMap ? super.getThreadContextMapInstance() : NoOpThreadContextMap.INSTANCE;
    }

    @Override // org.apache.logging.log4j.spi.Provider
    public LoggerContextFactory getLoggerContextFactory() {
        return SimpleLoggerContextFactory.INSTANCE;
    }

    @Override // org.apache.logging.log4j.spi.Provider
    public ThreadContextMap getThreadContextMapInstance() {
        return this.threadContextMap;
    }

    /* loaded from: classes10.dex */
    public static final class Config {
        private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
        public static final Config INSTANCE = new Config();
        private static final String SYSTEM_ERR = "system.err";
        private static final String SYSTEM_OUT = "system.out";
        private static final String SYSTEM_PREFIX = "org.apache.logging.log4j.simplelog.";
        public final String dateTimeFormat;
        public final Level defaultLevel;
        public final PrintStream stream;
        public final PropertiesUtil props = new PropertiesUtil("log4j2.simplelog.properties");
        public final boolean showContextMap = this.props.getBooleanProperty("org.apache.logging.log4j.simplelog.showContextMap", false);
        public final boolean showLogName = this.props.getBooleanProperty("org.apache.logging.log4j.simplelog.showlogname", false);
        public final boolean showShortName = this.props.getBooleanProperty("org.apache.logging.log4j.simplelog.showShortLogname", true);
        public final boolean showDateTime = this.props.getBooleanProperty("org.apache.logging.log4j.simplelog.showdatetime", false);

        private Config() {
            String str;
            PrintStream ps;
            String lvl = this.props.getStringProperty("org.apache.logging.log4j.simplelog.level");
            this.defaultLevel = Level.toLevel(lvl, Level.ERROR);
            if (this.showDateTime) {
                str = this.props.getStringProperty("org.apache.logging.log4j.simplelog.dateTimeFormat", DEFAULT_DATE_TIME_FORMAT);
            } else {
                str = null;
            }
            this.dateTimeFormat = str;
            String fileName = this.props.getStringProperty("org.apache.logging.log4j.simplelog.logFile", SYSTEM_ERR);
            if (SYSTEM_ERR.equalsIgnoreCase(fileName)) {
                ps = System.err;
            } else if (SYSTEM_OUT.equalsIgnoreCase(fileName)) {
                ps = System.out;
            } else {
                try {
                    ps = new PrintStream(new FileOutputStream(fileName));
                } catch (FileNotFoundException e) {
                    ps = System.err;
                }
            }
            this.stream = ps;
        }
    }
}
