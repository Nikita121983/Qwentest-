package org.apache.poi.util;

/* loaded from: classes10.dex */
public interface DelayableLittleEndianOutput extends LittleEndianOutput {
    LittleEndianOutput createDelayedOutput(int i);
}
