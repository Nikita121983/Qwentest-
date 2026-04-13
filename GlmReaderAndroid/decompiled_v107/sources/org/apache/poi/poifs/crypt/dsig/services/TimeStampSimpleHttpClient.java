package org.apache.poi.poifs.crypt.dsig.services;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.poifs.crypt.dsig.SignatureConfig;
import org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RandomSingleton;

/* loaded from: classes10.dex */
public class TimeStampSimpleHttpClient implements TimeStampHttpClient {
    protected static final String BASIC_AUTH = "Authorization";
    protected static final String CONTENT_TYPE = "Content-Type";
    private static final int DEFAULT_TIMESTAMP_RESPONSE_SIZE = 10000000;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) TimeStampSimpleHttpClient.class);
    private static int MAX_TIMESTAMP_RESPONSE_SIZE = 10000000;
    protected static final String REDIRECT_LOCATION = "Location";
    protected static final String USER_AGENT = "User-Agent";
    protected SignatureConfig config;
    protected Proxy proxy = Proxy.NO_PROXY;
    protected final Map<String, String> header = new HashMap();
    protected String contentTypeOut = null;
    protected boolean ignoreHttpsCertificates = false;
    protected boolean followRedirects = false;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public interface MethodHandler {
        void handle(HttpURLConnection httpURLConnection) throws IOException;
    }

    public static void setMaxTimestampResponseSize(int maxTimestampResponseSize) {
        MAX_TIMESTAMP_RESPONSE_SIZE = maxTimestampResponseSize;
    }

    public static int getMaxTimestampResponseSize() {
        int ioMaxSize = IOUtils.getByteArrayMaxOverride();
        int i = MAX_TIMESTAMP_RESPONSE_SIZE;
        return ioMaxSize < 0 ? i : Math.min(i, ioMaxSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class TimeStampSimpleHttpClientResponse implements TimeStampHttpClient.TimeStampHttpClientResponse {
        private final byte[] responseBytes;
        private final int responseCode;

        public TimeStampSimpleHttpClientResponse(int responseCode, byte[] responseBytes) {
            this.responseCode = responseCode;
            this.responseBytes = responseBytes;
        }

        @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient.TimeStampHttpClientResponse
        public int getResponseCode() {
            return this.responseCode;
        }

        @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient.TimeStampHttpClientResponse
        public byte[] getResponseBytes() {
            return this.responseBytes;
        }
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public void init(SignatureConfig config) {
        this.config = config;
        this.header.clear();
        this.header.put(USER_AGENT, config.getUserAgent());
        this.contentTypeOut = null;
        setProxy(config.getProxyUrl());
        setBasicAuthentication(config.getTspUser(), config.getTspPass());
    }

    public void setProxy(String proxyUrl) {
        if (proxyUrl == null || proxyUrl.isEmpty()) {
            this.proxy = Proxy.NO_PROXY;
            return;
        }
        try {
            URL pUrl = new URI(proxyUrl).toURL();
            String host = pUrl.getHost();
            int port = pUrl.getPort();
            this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByName(host), port == -1 ? 80 : port));
        } catch (IOException e) {
        } catch (URISyntaxException e2) {
        }
    }

    public Proxy getProxy() {
        return this.proxy;
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public void setContentTypeIn(String contentType) {
        this.header.put(CONTENT_TYPE, contentType);
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public void setContentTypeOut(String contentType) {
        this.contentTypeOut = contentType;
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public void setBasicAuthentication(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            this.header.remove(BASIC_AUTH);
            return;
        }
        String userPassword = username + ":" + password;
        String encoding = Base64.getEncoder().encodeToString(userPassword.getBytes(StandardCharsets.ISO_8859_1));
        this.header.put(BASIC_AUTH, "Basic " + encoding);
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public boolean isIgnoreHttpsCertificates() {
        return this.ignoreHttpsCertificates;
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public void setIgnoreHttpsCertificates(boolean ignoreHttpsCertificates) {
        this.ignoreHttpsCertificates = ignoreHttpsCertificates;
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public boolean isFollowRedirects() {
        return this.followRedirects;
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public void setFollowRedirects(boolean followRedirects) {
        this.followRedirects = followRedirects;
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public TimeStampHttpClient.TimeStampHttpClientResponse post(String url, final byte[] payload) throws IOException {
        MethodHandler handler = new MethodHandler() { // from class: org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$$ExternalSyntheticLambda2
            @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient.MethodHandler
            public final void handle(HttpURLConnection httpURLConnection) {
                TimeStampSimpleHttpClient.lambda$post$0(payload, httpURLConnection);
            }
        };
        return handleRedirect(url, handler, isFollowRedirects());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$post$0(byte[] payload, HttpURLConnection huc) throws IOException {
        huc.setRequestMethod("POST");
        huc.setDoOutput(true);
        OutputStream hucOut = huc.getOutputStream();
        try {
            hucOut.write(payload);
            if (hucOut != null) {
                hucOut.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (hucOut != null) {
                    try {
                        hucOut.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$get$1(HttpURLConnection huc) throws IOException {
    }

    @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampHttpClient
    public TimeStampHttpClient.TimeStampHttpClientResponse get(String url) throws IOException {
        return handleRedirect(url, new MethodHandler() { // from class: org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$$ExternalSyntheticLambda3
            @Override // org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient.MethodHandler
            public final void handle(HttpURLConnection httpURLConnection) {
                TimeStampSimpleHttpClient.lambda$get$1(httpURLConnection);
            }
        }, isFollowRedirects());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0038. Please report as an issue. */
    protected TimeStampHttpClient.TimeStampHttpClientResponse handleRedirect(String url, MethodHandler handler, boolean followRedirect) throws IOException {
        byte[] responseBytes;
        try {
            final HttpURLConnection huc = (HttpURLConnection) new URI(url).toURL().openConnection(this.proxy);
            if (this.ignoreHttpsCertificates) {
                recklessConnection(huc);
            }
            huc.setConnectTimeout(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
            huc.setReadTimeout(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
            Map<String, String> map = this.header;
            huc.getClass();
            map.forEach(new BiConsumer() { // from class: org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    huc.setRequestProperty((String) obj, (String) obj2);
                }
            });
            try {
                handler.handle(huc);
                huc.connect();
                int responseCode = huc.getResponseCode();
                switch (responseCode) {
                    case 200:
                        String contentType = huc.getHeaderField(CONTENT_TYPE);
                        if (this.contentTypeOut != null && !this.contentTypeOut.equals(contentType)) {
                            throw new IOException("Content-Type mismatch - expected `" + this.contentTypeOut + "', received '" + contentType + "'");
                        }
                        InputStream is = huc.getInputStream();
                        try {
                            byte[] responseBytes2 = IOUtils.toByteArrayWithMaxLength(is, getMaxTimestampResponseSize());
                            if (is != null) {
                                is.close();
                            }
                            responseBytes = responseBytes2;
                            return new TimeStampSimpleHttpClientResponse(responseCode, responseBytes);
                        } finally {
                        }
                    case 301:
                    case 302:
                    case 303:
                        String newUrl = huc.getHeaderField(REDIRECT_LOCATION);
                        if (newUrl != null && followRedirect) {
                            LOG.atWarn().log("Received redirect: {} -> {}", url, newUrl);
                            return handleRedirect(newUrl, handler, false);
                        }
                        LOG.atWarn().log("Redirect ignored - giving up: {} -> {}", url, newUrl);
                        responseBytes = null;
                        return new TimeStampSimpleHttpClientResponse(responseCode, responseBytes);
                    default:
                        String message = "Error contacting TSP server " + url + ", had status code " + responseCode + PackagingURIHelper.FORWARD_SLASH_STRING + huc.getResponseMessage();
                        LOG.atError().log(message);
                        throw new IOException(message);
                }
            } finally {
                huc.disconnect();
            }
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

    protected void recklessConnection(HttpURLConnection conn) throws IOException {
        if (!(conn instanceof HttpsURLConnection)) {
            return;
        }
        HttpsURLConnection conns = (HttpsURLConnection) conn;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new UnsafeTrustManager()}, RandomSingleton.getInstance());
            conns.setSSLSocketFactory(sc.getSocketFactory());
            conns.setHostnameVerifier(new HostnameVerifier() { // from class: org.apache.poi.poifs.crypt.dsig.services.TimeStampSimpleHttpClient$$ExternalSyntheticLambda0
                @Override // javax.net.ssl.HostnameVerifier
                public final boolean verify(String str, SSLSession sSLSession) {
                    return TimeStampSimpleHttpClient.lambda$recklessConnection$2(str, sSLSession);
                }
            });
        } catch (GeneralSecurityException e) {
            throw new IOException("Unable to reckless wrap connection.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$recklessConnection$2(String hostname, SSLSession session) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class UnsafeTrustManager implements X509TrustManager {
        private UnsafeTrustManager() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }
    }
}
