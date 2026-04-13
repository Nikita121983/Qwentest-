package org.apache.poi.ooxml.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes10.dex */
public class IdentifierManager {
    public static final long MAX_ID = 9223372036854775806L;
    public static final long MIN_ID = 0;
    private final long lowerbound;
    private LinkedList<Segment> segments;
    private final long upperbound;

    public IdentifierManager(long lowerbound, long upperbound) {
        if (lowerbound > upperbound) {
            throw new IllegalArgumentException("lowerbound must not be greater than upperbound, had " + lowerbound + " and " + upperbound);
        }
        if (lowerbound < 0) {
            throw new IllegalArgumentException("lowerbound must be greater than or equal to 0");
        }
        if (upperbound > MAX_ID) {
            throw new IllegalArgumentException("upperbound must be less than or equal to 9223372036854775806 but had " + upperbound);
        }
        this.lowerbound = lowerbound;
        this.upperbound = upperbound;
        this.segments = new LinkedList<>();
        this.segments.add(new Segment(lowerbound, upperbound));
    }

    public long reserve(long id) {
        if (id < this.lowerbound || id > this.upperbound) {
            throw new IllegalArgumentException("Value for parameter 'id' was out of bounds, had " + id + ", but should be within [" + this.lowerbound + ":" + this.upperbound + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        verifyIdentifiersLeft();
        if (id == this.upperbound) {
            Segment lastSegment = this.segments.getLast();
            if (lastSegment.end != this.upperbound) {
                return reserveNew();
            }
            lastSegment.end = this.upperbound - 1;
            if (lastSegment.start > lastSegment.end) {
                this.segments.removeLast();
            }
            return id;
        }
        if (id == this.lowerbound) {
            Segment firstSegment = this.segments.getFirst();
            if (firstSegment.start != this.lowerbound) {
                return reserveNew();
            }
            firstSegment.start = this.lowerbound + 1;
            if (firstSegment.end < firstSegment.start) {
                this.segments.removeFirst();
            }
            return id;
        }
        ListIterator<Segment> iter = this.segments.listIterator();
        while (true) {
            if (!iter.hasNext()) {
                break;
            }
            Segment segment = iter.next();
            if (segment.end >= id) {
                if (segment.start <= id) {
                    if (segment.start == id) {
                        segment.start = 1 + id;
                        if (segment.end < segment.start) {
                            iter.remove();
                        }
                        return id;
                    }
                    if (segment.end != id) {
                        iter.add(new Segment(id + 1, segment.end));
                        segment.end = id - 1;
                        return id;
                    }
                    segment.end = id - 1;
                    if (segment.start > segment.end) {
                        iter.remove();
                    }
                    return id;
                }
            }
        }
        return reserveNew();
    }

    public long reserveNew() {
        verifyIdentifiersLeft();
        Segment segment = this.segments.getFirst();
        long result = segment.start;
        segment.start++;
        if (segment.start > segment.end) {
            this.segments.removeFirst();
        }
        return result;
    }

    public boolean release(long id) {
        if (id < this.lowerbound || id > this.upperbound) {
            throw new IllegalArgumentException("Value for parameter 'id' was out of bounds, had " + id + ", but should be within [" + this.lowerbound + ":" + this.upperbound + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        if (id == this.upperbound) {
            Segment lastSegment = this.segments.getLast();
            if (lastSegment.end == this.upperbound - 1) {
                lastSegment.end = this.upperbound;
                return true;
            }
            if (lastSegment.end == this.upperbound) {
                return false;
            }
            this.segments.add(new Segment(this.upperbound, this.upperbound));
            return true;
        }
        if (this.segments.isEmpty()) {
            this.segments.add(new Segment(id, id));
            return true;
        }
        if (id == this.lowerbound) {
            Segment firstSegment = this.segments.getFirst();
            if (firstSegment.start == this.lowerbound + 1) {
                firstSegment.start = this.lowerbound;
                return true;
            }
            if (firstSegment.start == this.lowerbound) {
                return false;
            }
            this.segments.addFirst(new Segment(this.lowerbound, this.lowerbound));
            return true;
        }
        long higher = id + 1;
        long lower = id - 1;
        ListIterator<Segment> iter = this.segments.listIterator();
        while (true) {
            if (!iter.hasNext()) {
                break;
            }
            Segment segment = iter.next();
            if (segment.end >= lower) {
                if (segment.start > higher) {
                    iter.previous();
                    iter.add(new Segment(id, id));
                    return true;
                }
                if (segment.start == higher) {
                    segment.start = id;
                    return true;
                }
                if (segment.end == lower) {
                    segment.end = id;
                    if (iter.hasNext()) {
                        Segment next = iter.next();
                        if (next.start == segment.end + 1) {
                            segment.end = next.end;
                            iter.remove();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public long getRemainingIdentifiers() {
        long result = 0;
        Iterator<Segment> it = this.segments.iterator();
        while (it.hasNext()) {
            Segment segment = it.next();
            result = segment.end + (result - segment.start) + 1;
        }
        return result;
    }

    private void verifyIdentifiersLeft() {
        if (this.segments.isEmpty()) {
            throw new IllegalStateException("No identifiers left for range [" + this.lowerbound + CollectionUtils.COMMA + this.upperbound + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Segment {
        private long end;
        private long start;

        public Segment(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return CollectionUtils.DEFAULT_TOSTRING_PREFIX + this.start + VectorFormat.DEFAULT_SEPARATOR + this.end + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }
}
