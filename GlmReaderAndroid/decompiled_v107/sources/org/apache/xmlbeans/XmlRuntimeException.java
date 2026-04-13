package org.apache.xmlbeans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class XmlRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private List<XmlError> _errors;

    public XmlRuntimeException(String m) {
        super(m);
    }

    public XmlRuntimeException(String m, Throwable t) {
        super(m, t);
    }

    public XmlRuntimeException(Throwable t) {
        super(t);
    }

    public XmlRuntimeException(String m, Throwable t, Collection<XmlError> errors) {
        super(m, t);
        if (errors != null) {
            this._errors = Collections.unmodifiableList(new ArrayList(errors));
        }
    }

    public XmlRuntimeException(XmlError error) {
        this(error.toString(), (Throwable) null, error);
    }

    public XmlRuntimeException(String m, Throwable t, XmlError error) {
        this(m, t, Collections.singletonList(error));
    }

    public XmlRuntimeException(XmlException xmlException) {
        super(xmlException.getMessage(), xmlException.getCause());
        Collection<XmlError> errors = xmlException.getErrors();
        if (errors != null) {
            this._errors = Collections.unmodifiableList(new ArrayList(errors));
        }
    }

    public XmlError getError() {
        if (this._errors == null || this._errors.isEmpty()) {
            return null;
        }
        return this._errors.get(0);
    }

    public Collection<XmlError> getErrors() {
        return this._errors;
    }
}
