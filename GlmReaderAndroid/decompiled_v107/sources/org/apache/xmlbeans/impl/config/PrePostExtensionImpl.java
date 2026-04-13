package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.PrimitiveType;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

/* loaded from: classes11.dex */
public class PrePostExtensionImpl implements PrePostExtension {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String[] PARAMTYPES_STRING = {XmlErrorCodes.INT, "org.apache.xmlbeans.XmlObject", "javax.xml.namespace.QName", "boolean", XmlErrorCodes.INT};
    private static final String SIGNATURE = "(" + String.join(", ", PARAMTYPES_STRING) + ")";
    private ClassOrInterfaceDeclaration _delegateToClass;
    private String _delegateToClassName;
    private MethodDeclaration _postSet;
    private MethodDeclaration _preSet;
    private NameSet _xbeanSet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PrePostExtensionImpl newInstance(Parser loader, NameSet xbeanSet, Extensionconfig.PrePostSet prePostXO) {
        if (prePostXO == null) {
            return null;
        }
        PrePostExtensionImpl result = new PrePostExtensionImpl();
        result._xbeanSet = xbeanSet;
        result._delegateToClassName = prePostXO.getStaticHandler();
        result._delegateToClass = InterfaceExtensionImpl.validateClass(loader, result._delegateToClassName, prePostXO);
        if (result._delegateToClass == null) {
            BindingConfigImpl.warning("Handler class '" + prePostXO.getStaticHandler() + "' not found on classpath, skip validation.", prePostXO);
            return result;
        }
        if (!result.lookAfterPreAndPost(loader, prePostXO)) {
            return null;
        }
        return result;
    }

    private boolean lookAfterPreAndPost(Parser loader, XmlObject loc) {
        if (this._delegateToClass == null) {
            throw new AssertionError("Delegate to class handler expected.");
        }
        this._preSet = InterfaceExtensionImpl.getMethod(this._delegateToClass, "preSet", PARAMTYPES_STRING);
        if (this._preSet != null && !this._preSet.getType().equals(PrimitiveType.booleanType())) {
            BindingConfigImpl.warning("Method '" + this._delegateToClass.getNameAsString() + ".preSet" + SIGNATURE + "' should return boolean to be considered for a preSet handler.", loc);
            this._preSet = null;
        }
        this._postSet = InterfaceExtensionImpl.getMethod(this._delegateToClass, "postSet", PARAMTYPES_STRING);
        if (this._preSet != null || this._postSet != null) {
            return true;
        }
        BindingConfigImpl.error("prePostSet handler specified '" + this._delegateToClass.getNameAsString() + "' but no preSet" + SIGNATURE + " or postSet" + SIGNATURE + " methods found.", loc);
        return false;
    }

    public NameSet getNameSet() {
        return this._xbeanSet;
    }

    public boolean contains(String fullJavaName) {
        return this._xbeanSet.contains(fullJavaName);
    }

    @Override // org.apache.xmlbeans.PrePostExtension
    public boolean hasPreCall() {
        return this._preSet != null;
    }

    @Override // org.apache.xmlbeans.PrePostExtension
    public boolean hasPostCall() {
        return this._postSet != null;
    }

    @Override // org.apache.xmlbeans.PrePostExtension
    public String getStaticHandler() {
        return this._delegateToClassName;
    }

    public String getHandlerNameForJavaSource() {
        if (this._delegateToClass == null) {
            return null;
        }
        return this._delegateToClass.getNameAsString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasNameSetIntersection(PrePostExtensionImpl ext) {
        return !NameSet.EMPTY.equals(this._xbeanSet.intersect(ext._xbeanSet));
    }
}
