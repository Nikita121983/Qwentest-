package org.apache.logging.log4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes10.dex */
public class PropertyFilePropertySource extends PropertiesPropertySource {
    private static final Logger LOGGER = StatusLogger.getLogger();

    public PropertyFilePropertySource(final String fileName) {
        this(fileName, true);
    }

    public PropertyFilePropertySource(final String fileName, final boolean useTccl) {
        super(loadPropertiesFile(fileName, useTccl));
    }

    private static Properties loadPropertiesFile(final String fileName, final boolean useTccl) {
        Properties props = new Properties();
        for (URL url : LoaderUtil.findResources(fileName, useTccl)) {
            try {
                InputStream in = url.openStream();
                try {
                    props.load(in);
                    if (in != null) {
                        in.close();
                    }
                } catch (Throwable th) {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                    break;
                }
            } catch (IOException error) {
                LOGGER.error("Unable to read URL `{}`", url, error);
            }
        }
        return props;
    }
}
