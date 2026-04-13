package org.apache.poi.xslf.util;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.Internal;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public class WMFHandler extends EMFHandler {
    @Override // org.apache.poi.xslf.util.EMFHandler
    protected String getContentType() {
        return PictureData.PictureType.WMF.contentType;
    }
}
