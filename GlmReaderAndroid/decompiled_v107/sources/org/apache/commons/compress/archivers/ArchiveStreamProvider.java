package org.apache.commons.compress.archivers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

/* loaded from: classes9.dex */
public interface ArchiveStreamProvider {
    <I extends ArchiveInputStream<? extends ArchiveEntry>> I createArchiveInputStream(String str, InputStream inputStream, String str2) throws ArchiveException;

    <O extends ArchiveOutputStream<? extends ArchiveEntry>> O createArchiveOutputStream(String str, OutputStream outputStream, String str2) throws ArchiveException;

    Set<String> getInputStreamArchiveNames();

    Set<String> getOutputStreamArchiveNames();
}
