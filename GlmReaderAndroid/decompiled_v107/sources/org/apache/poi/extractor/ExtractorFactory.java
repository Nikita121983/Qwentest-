package org.apache.poi.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EmptyFileException;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.ThreadLocalUtil;

/* loaded from: classes10.dex */
public final class ExtractorFactory {
    public static final String OOXML_PACKAGE = "Package";
    private static Boolean allPreferEventExtractors;
    private final List<ExtractorProvider> provider;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) ExtractorFactory.class);
    private static final ThreadLocal<Boolean> threadPreferEventExtractors = ThreadLocal.withInitial(new Supplier() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda9
        @Override // java.util.function.Supplier
        public final Object get() {
            Boolean bool;
            bool = Boolean.FALSE;
            return bool;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface ProviderMethod {
        POITextExtractor create(ExtractorProvider extractorProvider) throws IOException;
    }

    static {
        final ThreadLocal<Boolean> threadLocal = threadPreferEventExtractors;
        threadLocal.getClass();
        ThreadLocalUtil.registerCleaner(new Runnable() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                threadLocal.remove();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Singleton {
        private static final ExtractorFactory INSTANCE = new ExtractorFactory();

        private Singleton() {
        }
    }

    private ExtractorFactory() {
        this.provider = new ArrayList();
        ClassLoader cl = ExtractorFactory.class.getClassLoader();
        ServiceLoader load = ServiceLoader.load(ExtractorProvider.class, cl);
        final List<ExtractorProvider> list = this.provider;
        list.getClass();
        load.forEach(new Consumer() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((ExtractorProvider) obj);
            }
        });
        this.provider.sort(new Comparator() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda6
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ExtractorFactory.lambda$new$1((ExtractorProvider) obj, (ExtractorProvider) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$1(ExtractorProvider o1, ExtractorProvider o2) {
        if (o1.getClass() != o2.getClass()) {
            if (o1.getClass().getSimpleName().equals("OLE2ScratchpadExtractorFactory")) {
                return -1;
            }
            if (o2.getClass().getSimpleName().equals("OLE2ScratchpadExtractorFactory")) {
                return 1;
            }
        }
        return o1.getClass().getName().compareTo(o2.getClass().getName());
    }

    public static boolean getThreadPrefersEventExtractors() {
        return threadPreferEventExtractors.get().booleanValue();
    }

    public static Boolean getAllThreadsPreferEventExtractors() {
        return allPreferEventExtractors;
    }

    public static void setThreadPrefersEventExtractors(boolean preferEventExtractors) {
        threadPreferEventExtractors.set(Boolean.valueOf(preferEventExtractors));
    }

    public static void removeThreadPrefersEventExtractorsSetting() {
        threadPreferEventExtractors.remove();
    }

    public static void setAllThreadsPreferEventExtractors(Boolean preferEventExtractors) {
        allPreferEventExtractors = preferEventExtractors;
    }

    public static boolean getPreferEventExtractor() {
        return (allPreferEventExtractors != null ? allPreferEventExtractors : threadPreferEventExtractors.get()).booleanValue();
    }

    public static POITextExtractor createExtractor(POIFSFileSystem fs) throws IOException {
        return createExtractor(fs, Biff8EncryptionKey.getCurrentUserPassword());
    }

    public static POITextExtractor createExtractor(POIFSFileSystem fs, String password) throws IOException {
        return createExtractor(fs.getRoot(), password);
    }

    public static POITextExtractor createExtractor(InputStream input) throws IOException {
        return createExtractor(input, Biff8EncryptionKey.getCurrentUserPassword());
    }

    public static POITextExtractor createExtractor(InputStream input, final String password) throws IOException {
        final InputStream is = FileMagic.prepareToCheckMagic(input);
        boolean isOOXML = true;
        byte[] emptyFileCheck = new byte[1];
        is.mark(emptyFileCheck.length);
        if (is.read(emptyFileCheck) < emptyFileCheck.length) {
            throw new EmptyFileException();
        }
        is.reset();
        FileMagic fm = FileMagic.valueOf(is);
        if (FileMagic.OOXML == fm) {
            return wp(fm, new ProviderMethod() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda0
                @Override // org.apache.poi.extractor.ExtractorFactory.ProviderMethod
                public final POITextExtractor create(ExtractorProvider extractorProvider) {
                    POITextExtractor create;
                    create = extractorProvider.create(is, password);
                    return create;
                }
            });
        }
        if (FileMagic.OLE2 != fm) {
            throw new IOException("Can't create extractor - unsupported file type: " + fm);
        }
        POIFSFileSystem poifs = new POIFSFileSystem(is);
        final DirectoryNode root = poifs.getRoot();
        if (!root.hasEntryCaseInsensitive(Decryptor.DEFAULT_POIFS_ENTRY) && !root.hasEntryCaseInsensitive(OOXML_PACKAGE)) {
            isOOXML = false;
        }
        return wp(isOOXML ? FileMagic.OOXML : fm, new ProviderMethod() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda4
            @Override // org.apache.poi.extractor.ExtractorFactory.ProviderMethod
            public final POITextExtractor create(ExtractorProvider extractorProvider) {
                POITextExtractor create;
                create = extractorProvider.create(DirectoryNode.this, password);
                return create;
            }
        });
    }

    public static POITextExtractor createExtractor(File file) throws IOException {
        return createExtractor(file, Biff8EncryptionKey.getCurrentUserPassword());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0040, code lost:
    
        r4 = org.apache.poi.poifs.filesystem.FileMagic.OOXML;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.poi.extractor.POITextExtractor createExtractor(final java.io.File r6, final java.lang.String r7) throws java.io.IOException {
        /*
            long r0 = r6.length()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L6e
            org.apache.poi.poifs.filesystem.FileMagic r0 = org.apache.poi.poifs.filesystem.FileMagic.valueOf(r6)
            org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OOXML
            if (r1 != r0) goto L1c
            org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda12 r1 = new org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda12
            r1.<init>()
            org.apache.poi.extractor.POITextExtractor r1 = wp(r0, r1)
            return r1
        L1c:
            org.apache.poi.poifs.filesystem.FileMagic r1 = org.apache.poi.poifs.filesystem.FileMagic.OLE2
            if (r1 != r0) goto L55
            r1 = 0
            org.apache.poi.poifs.filesystem.POIFSFileSystem r2 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            r3 = 1
            r2.<init>(r6, r3)     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            r1 = r2
            org.apache.poi.poifs.filesystem.DirectoryNode r2 = r1.getRoot()     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            java.lang.String r4 = "EncryptedPackage"
            boolean r4 = r2.hasEntryCaseInsensitive(r4)     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            if (r4 != 0) goto L3e
            java.lang.String r4 = "Package"
            boolean r4 = r2.hasEntryCaseInsensitive(r4)     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            if (r4 == 0) goto L3d
            goto L3e
        L3d:
            r3 = 0
        L3e:
            if (r3 == 0) goto L43
            org.apache.poi.poifs.filesystem.FileMagic r4 = org.apache.poi.poifs.filesystem.FileMagic.OOXML     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            goto L44
        L43:
            r4 = r0
        L44:
            org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda1 r5 = new org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda1     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            r5.<init>()     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            org.apache.poi.extractor.POITextExtractor r4 = wp(r4, r5)     // Catch: java.lang.RuntimeException -> L4e java.io.IOException -> L50
            return r4
        L4e:
            r2 = move-exception
            goto L51
        L50:
            r2 = move-exception
        L51:
            org.apache.poi.util.IOUtils.closeQuietly(r1)
            throw r2
        L55:
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Can't create extractor - unsupported file type: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L6e:
            org.apache.poi.EmptyFileException r0 = new org.apache.poi.EmptyFileException
            r0.<init>(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.extractor.ExtractorFactory.createExtractor(java.io.File, java.lang.String):org.apache.poi.extractor.POITextExtractor");
    }

    public static POITextExtractor createExtractor(DirectoryNode root) throws IOException {
        return createExtractor(root, Biff8EncryptionKey.getCurrentUserPassword());
    }

    public static POITextExtractor createExtractor(final DirectoryNode root, final String password) throws IOException {
        if (root.hasEntryCaseInsensitive(Decryptor.DEFAULT_POIFS_ENTRY) || root.hasEntryCaseInsensitive(OOXML_PACKAGE)) {
            return wp(FileMagic.OOXML, new ProviderMethod() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda7
                @Override // org.apache.poi.extractor.ExtractorFactory.ProviderMethod
                public final POITextExtractor create(ExtractorProvider extractorProvider) {
                    POITextExtractor create;
                    create = extractorProvider.create(DirectoryNode.this, password);
                    return create;
                }
            });
        }
        return wp(FileMagic.OLE2, new ProviderMethod() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda8
            @Override // org.apache.poi.extractor.ExtractorFactory.ProviderMethod
            public final POITextExtractor create(ExtractorProvider extractorProvider) {
                POITextExtractor create;
                create = extractorProvider.create(DirectoryNode.this, password);
                return create;
            }
        });
    }

    public static POITextExtractor[] getEmbeddedDocsTextExtractors(POIOLE2TextExtractor ext) throws IOException {
        if (ext == null) {
            throw new IllegalStateException("extractor must be given");
        }
        final List<Entry> dirs = new ArrayList<>();
        List<InputStream> nonPOIFS = new ArrayList<>();
        DirectoryEntry root = ext.getRoot();
        if (root == null) {
            throw new IllegalStateException("The extractor didn't know which POIFS it came from!");
        }
        if (!(ext instanceof ExcelExtractor)) {
            Iterator<ExtractorProvider> it = Singleton.INSTANCE.provider.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ExtractorProvider prov = it.next();
                if (prov.accepts(FileMagic.OLE2)) {
                    prov.identifyEmbeddedResources(ext, dirs, nonPOIFS);
                    break;
                }
            }
        } else {
            Stream filter = StreamSupport.stream(root.spliterator(), false).filter(new Predicate() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean startsWith;
                    startsWith = ((Entry) obj).getName().startsWith("MBD");
                    return startsWith;
                }
            });
            dirs.getClass();
            filter.forEach(new Consumer() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    dirs.add((Entry) obj);
                }
            });
        }
        if (dirs.isEmpty() && nonPOIFS.isEmpty()) {
            return new POITextExtractor[0];
        }
        ArrayList<POITextExtractor> textExtractors = new ArrayList<>();
        for (Entry dir : dirs) {
            if (dir instanceof DirectoryNode) {
                textExtractors.add(createExtractor((DirectoryNode) dir));
            }
        }
        for (InputStream stream : nonPOIFS) {
            try {
                textExtractors.add(createExtractor(stream));
            } catch (IOException e) {
                LOGGER.atInfo().log("Format not supported yet ({})", e.getLocalizedMessage());
            }
        }
        return (POITextExtractor[]) textExtractors.toArray(new POITextExtractor[0]);
    }

    private static POITextExtractor wp(FileMagic fm, ProviderMethod fun) throws IOException {
        POITextExtractor ext;
        for (ExtractorProvider prov : Singleton.INSTANCE.provider) {
            if (prov.accepts(fm) && (ext = fun.create(prov)) != null) {
                return ext;
            }
        }
        throw new IOException("Your InputStream was neither an OLE2 stream, nor an OOXML stream or you haven't provide the poi-ooxml*.jar and/or poi-scratchpad*.jar in the classpath/modulepath - FileMagic: " + fm + ", providers: " + Singleton.INSTANCE.provider);
    }

    public static void addProvider(ExtractorProvider provider) {
        Singleton.INSTANCE.provider.add(provider);
    }

    public static void removeProvider(final Class<? extends ExtractorProvider> provider) {
        Singleton.INSTANCE.provider.removeIf(new Predicate() { // from class: org.apache.poi.extractor.ExtractorFactory$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isAssignableFrom;
                isAssignableFrom = ((ExtractorProvider) obj).getClass().isAssignableFrom(provider);
                return isAssignableFrom;
            }
        });
    }
}
