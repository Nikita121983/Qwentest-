package org.apache.poi.sl.usermodel;

import java.io.IOException;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public interface MetroShapeProvider {
    Shape<?, ?> parseShape(byte[] bArr) throws IOException;
}
