package org.apache.poi.poifs.eventfilesystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.apache.poi.poifs.filesystem.DocumentDescriptor;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

/* loaded from: classes10.dex */
class POIFSReaderRegistry {
    private Set<POIFSReaderListener> omnivorousListeners = new HashSet();
    private Map<POIFSReaderListener, Set<DocumentDescriptor>> selectiveListeners = new HashMap();
    private Map<DocumentDescriptor, Set<POIFSReaderListener>> chosenDocumentDescriptors = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerListener(POIFSReaderListener listener, POIFSDocumentPath path, String documentName) {
        if (!this.omnivorousListeners.contains(listener)) {
            Set<DocumentDescriptor> descriptors = this.selectiveListeners.computeIfAbsent(listener, new Function() { // from class: org.apache.poi.poifs.eventfilesystem.POIFSReaderRegistry$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return POIFSReaderRegistry.lambda$registerListener$0((POIFSReaderListener) obj);
                }
            });
            DocumentDescriptor descriptor = new DocumentDescriptor(path, documentName);
            if (descriptors.add(descriptor)) {
                Set<POIFSReaderListener> listeners = this.chosenDocumentDescriptors.computeIfAbsent(descriptor, new Function() { // from class: org.apache.poi.poifs.eventfilesystem.POIFSReaderRegistry$$ExternalSyntheticLambda1
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return POIFSReaderRegistry.lambda$registerListener$1((DocumentDescriptor) obj);
                    }
                });
                listeners.add(listener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Set lambda$registerListener$0(POIFSReaderListener k) {
        return new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Set lambda$registerListener$1(DocumentDescriptor k) {
        return new HashSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerListener(POIFSReaderListener listener) {
        if (!this.omnivorousListeners.contains(listener)) {
            removeSelectiveListener(listener);
            this.omnivorousListeners.add(listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Iterable<POIFSReaderListener> getListeners(POIFSDocumentPath path, String name) {
        Set<POIFSReaderListener> rval = new HashSet<>(this.omnivorousListeners);
        Set<POIFSReaderListener> selectiveListenersInner = this.chosenDocumentDescriptors.get(new DocumentDescriptor(path, name));
        if (selectiveListenersInner != null) {
            rval.addAll(selectiveListenersInner);
        }
        return rval;
    }

    private void removeSelectiveListener(POIFSReaderListener listener) {
        Set<DocumentDescriptor> selectedDescriptors = this.selectiveListeners.remove(listener);
        if (selectedDescriptors != null) {
            for (DocumentDescriptor selectedDescriptor : selectedDescriptors) {
                dropDocument(listener, selectedDescriptor);
            }
        }
    }

    private void dropDocument(POIFSReaderListener listener, DocumentDescriptor descriptor) {
        Set<POIFSReaderListener> listeners = this.chosenDocumentDescriptors.get(descriptor);
        listeners.remove(listener);
        if (listeners.isEmpty()) {
            this.chosenDocumentDescriptors.remove(descriptor);
        }
    }
}
