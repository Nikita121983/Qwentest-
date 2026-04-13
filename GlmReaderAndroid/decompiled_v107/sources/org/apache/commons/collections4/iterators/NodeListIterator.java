package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes9.dex */
public class NodeListIterator implements Iterator<Node> {
    private int index;
    private final NodeList nodeList;

    public NodeListIterator(Node node) {
        Objects.requireNonNull(node, "node");
        this.nodeList = node.getChildNodes();
    }

    public NodeListIterator(NodeList nodeList) {
        this.nodeList = (NodeList) Objects.requireNonNull(nodeList, "nodeList");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.nodeList != null && this.index < this.nodeList.getLength();
    }

    @Override // java.util.Iterator
    public Node next() {
        if (this.nodeList != null && this.index < this.nodeList.getLength()) {
            NodeList nodeList = this.nodeList;
            int i = this.index;
            this.index = i + 1;
            return nodeList.item(i);
        }
        throw new NoSuchElementException("underlying nodeList has no more elements");
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove() method not supported for a NodeListIterator.");
    }
}
