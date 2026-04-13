package org.apache.poi.sl.usermodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.apache.poi.EmptyFileException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/* loaded from: classes10.dex */
public final class SlideShowFactory {
    private final List<SlideShowProvider<?, ?>> provider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public interface ProviderMethod {
        SlideShow<?, ?> create(SlideShowProvider<?, ?> slideShowProvider) throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Singleton {
        private static final SlideShowFactory INSTANCE = new SlideShowFactory();

        private Singleton() {
        }
    }

    private SlideShowFactory() {
        this.provider = new ArrayList();
        ClassLoader cl = SlideShowFactory.class.getClassLoader();
        ServiceLoader load = ServiceLoader.load(SlideShowProvider.class, cl);
        final List<SlideShowProvider<?, ?>> list = this.provider;
        list.getClass();
        load.forEach(new Consumer() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                list.add((SlideShowProvider) obj);
            }
        });
    }

    public static SlideShow<?, ?> create(boolean XSLF) throws IOException {
        return wp(XSLF ? FileMagic.OOXML : FileMagic.OLE2, new ProviderMethod() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda5
            @Override // org.apache.poi.sl.usermodel.SlideShowFactory.ProviderMethod
            public final SlideShow create(SlideShowProvider slideShowProvider) {
                return slideShowProvider.create();
            }
        });
    }

    public static SlideShow<?, ?> create(POIFSFileSystem fs) throws IOException {
        return create(fs, (String) null);
    }

    private static SlideShow<?, ?> create(POIFSFileSystem fs, String password) throws IOException {
        return create(fs.getRoot(), password);
    }

    public static SlideShow<?, ?> create(DirectoryNode root) throws IOException {
        return create(root, (String) null);
    }

    public static SlideShow<?, ?> create(final DirectoryNode root, final String password) throws IOException {
        if (root.hasEntryCaseInsensitive(Decryptor.DEFAULT_POIFS_ENTRY) || root.hasEntryCaseInsensitive(ExtractorFactory.OOXML_PACKAGE)) {
            return wp(FileMagic.OOXML, new ProviderMethod() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda1
                @Override // org.apache.poi.sl.usermodel.SlideShowFactory.ProviderMethod
                public final SlideShow create(SlideShowProvider slideShowProvider) {
                    SlideShow create;
                    create = slideShowProvider.create(DirectoryNode.this, password);
                    return create;
                }
            });
        }
        return wp(FileMagic.OLE2, new ProviderMethod() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda2
            @Override // org.apache.poi.sl.usermodel.SlideShowFactory.ProviderMethod
            public final SlideShow create(SlideShowProvider slideShowProvider) {
                SlideShow create;
                create = slideShowProvider.create(DirectoryNode.this, password);
                return create;
            }
        });
    }

    public static SlideShow<?, ?> create(InputStream inp) throws IOException, EncryptedDocumentException {
        return create(inp, (String) null);
    }

    public static SlideShow<?, ?> create(InputStream inp, final String password) throws IOException, EncryptedDocumentException {
        final InputStream is = FileMagic.prepareToCheckMagic(inp);
        boolean isOOXML = true;
        byte[] emptyFileCheck = new byte[1];
        is.mark(emptyFileCheck.length);
        if (is.read(emptyFileCheck) < emptyFileCheck.length) {
            throw new EmptyFileException();
        }
        is.reset();
        FileMagic fm = FileMagic.valueOf(is);
        if (FileMagic.OOXML == fm) {
            return wp(fm, new ProviderMethod() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda7
                @Override // org.apache.poi.sl.usermodel.SlideShowFactory.ProviderMethod
                public final SlideShow create(SlideShowProvider slideShowProvider) {
                    SlideShow create;
                    create = slideShowProvider.create(is);
                    return create;
                }
            });
        }
        if (FileMagic.OLE2 != fm) {
            throw new IOException("Can't open slideshow - unsupported file type: " + fm);
        }
        final POIFSFileSystem poifs = new POIFSFileSystem(is);
        DirectoryNode root = poifs.getRoot();
        if (!root.hasEntryCaseInsensitive(Decryptor.DEFAULT_POIFS_ENTRY) && !root.hasEntryCaseInsensitive(ExtractorFactory.OOXML_PACKAGE)) {
            isOOXML = false;
        }
        return wp(isOOXML ? FileMagic.OOXML : fm, new ProviderMethod() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda8
            @Override // org.apache.poi.sl.usermodel.SlideShowFactory.ProviderMethod
            public final SlideShow create(SlideShowProvider slideShowProvider) {
                SlideShow create;
                create = slideShowProvider.create(POIFSFileSystem.this.getRoot(), password);
                return create;
            }
        });
    }

    public static SlideShow<?, ?> create(File file) throws IOException, EncryptedDocumentException {
        return create(file, (String) null);
    }

    public static SlideShow<?, ?> create(File file, String password) throws IOException, EncryptedDocumentException {
        return create(file, password, false);
    }

    public static SlideShow<?, ?> create(final File file, final String password, final boolean readOnly) throws IOException, EncryptedDocumentException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.toString());
        }
        if (file.length() == 0) {
            throw new EmptyFileException(file);
        }
        FileMagic fm = FileMagic.valueOf(file);
        if (fm == FileMagic.OOXML) {
            return wp(fm, new ProviderMethod() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda3
                @Override // org.apache.poi.sl.usermodel.SlideShowFactory.ProviderMethod
                public final SlideShow create(SlideShowProvider slideShowProvider) {
                    SlideShow create;
                    create = slideShowProvider.create(file, password, readOnly);
                    return create;
                }
            });
        }
        if (fm == FileMagic.OLE2) {
            boolean ooxmlEnc = true;
            POIFSFileSystem fs = new POIFSFileSystem(file, true);
            try {
                DirectoryNode root = fs.getRoot();
                if (!root.hasEntryCaseInsensitive(Decryptor.DEFAULT_POIFS_ENTRY)) {
                    if (!root.hasEntryCaseInsensitive(ExtractorFactory.OOXML_PACKAGE)) {
                        ooxmlEnc = false;
                    }
                }
                fs.close();
                return wp(ooxmlEnc ? FileMagic.OOXML : fm, new ProviderMethod() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda4
                    @Override // org.apache.poi.sl.usermodel.SlideShowFactory.ProviderMethod
                    public final SlideShow create(SlideShowProvider slideShowProvider) {
                        SlideShow create;
                        create = slideShowProvider.create(file, password, readOnly);
                        return create;
                    }
                });
            } finally {
            }
        } else {
            throw new IOException("Can't open slideshow - unsupported file type: " + fm);
        }
    }

    private static SlideShow<?, ?> wp(FileMagic fm, ProviderMethod fun) throws IOException {
        for (SlideShowProvider<?, ?> prov : Singleton.INSTANCE.provider) {
            if (prov.accepts(fm)) {
                return fun.create(prov);
            }
        }
        throw new IOException("Your InputStream was neither an OLE2 stream, nor an OOXML stream or you haven't provide the poi-ooxml*.jar in the classpath/modulepath - FileMagic: " + fm);
    }

    public static void addProvider(SlideShowProvider<?, ?> provider) {
        Singleton.INSTANCE.provider.add(provider);
    }

    public static void removeProvider(final Class<? extends SlideShowProvider<?, ?>> provider) {
        Singleton.INSTANCE.provider.removeIf(new Predicate() { // from class: org.apache.poi.sl.usermodel.SlideShowFactory$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isAssignableFrom;
                isAssignableFrom = ((SlideShowProvider) obj).getClass().isAssignableFrom(provider);
                return isAssignableFrom;
            }
        });
    }
}
