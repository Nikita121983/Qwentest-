package org.apache.commons.compress.compressors;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

/* loaded from: classes9.dex */
public interface CompressorStreamProvider {
    CompressorInputStream createCompressorInputStream(String str, InputStream inputStream, boolean z) throws CompressorException;

    <T extends OutputStream> CompressorOutputStream<T> createCompressorOutputStream(String str, T t) throws CompressorException;

    Set<String> getInputStreamCompressorNames();

    Set<String> getOutputStreamCompressorNames();
}
