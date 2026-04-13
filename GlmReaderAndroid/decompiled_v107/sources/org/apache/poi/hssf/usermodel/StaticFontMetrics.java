package org.apache.poi.hssf.usermodel;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;

/* loaded from: classes10.dex */
final class StaticFontMetrics {
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) StaticFontMetrics.class);
    private static final Map<String, FontDetails> fontDetailsMap = new HashMap();
    private static Properties fontMetricsProps;

    private StaticFontMetrics() {
    }

    public static synchronized FontDetails getFontDetails(Font font) {
        FontDetails fontDetails;
        synchronized (StaticFontMetrics.class) {
            if (fontMetricsProps == null) {
                try {
                    fontMetricsProps = loadMetrics();
                } catch (IOException e) {
                    throw new IllegalStateException("Could not load font metrics", e);
                }
            }
            String fontName = font.getName();
            String fontStyle = font.isPlain() ? "plain" : "";
            if (font.isBold()) {
                fontStyle = fontStyle + "bold";
            }
            if (font.isItalic()) {
                fontStyle = fontStyle + "italic";
            }
            String fontHeight = FontDetails.buildFontHeightProperty(fontName);
            String styleHeight = FontDetails.buildFontHeightProperty(fontName + "." + fontStyle);
            if (fontMetricsProps.get(fontHeight) == null && fontMetricsProps.get(styleHeight) != null) {
                fontName = fontName + "." + fontStyle;
            }
            fontDetails = fontDetailsMap.get(fontName);
            if (fontDetails == null) {
                fontDetails = FontDetails.create(fontName, fontMetricsProps);
                fontDetailsMap.put(fontName, fontDetails);
            }
        }
        return fontDetails;
    }

    private static Properties loadMetrics() throws IOException {
        InputStream metricsIn;
        File propFile = null;
        try {
            String propFileName = System.getProperty("font.metrics.filename");
            if (propFileName != null) {
                propFile = new File(propFileName);
                if (!propFile.exists()) {
                    LOGGER.atWarn().log("font_metrics.properties not found at path {}", propFile.getAbsolutePath());
                    propFile = null;
                }
            }
        } catch (SecurityException e) {
            LOGGER.atWarn().withThrowable(e).log("Can't access font.metrics.filename system property");
        }
        if (propFile != null) {
            metricsIn = Files.newInputStream(propFile.toPath(), new OpenOption[0]);
        } else {
            metricsIn = FontDetails.class.getResourceAsStream("/font_metrics.properties");
        }
        try {
            if (metricsIn == null) {
                throw new IOException("font_metrics.properties not found in classpath");
            }
            Properties props = new Properties();
            props.load(metricsIn);
            if (metricsIn != null) {
                metricsIn.close();
            }
            return props;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (metricsIn != null) {
                    try {
                        metricsIn.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
