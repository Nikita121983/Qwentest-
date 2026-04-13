package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherSerializationListener;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.ss.util.IEEEDouble;
import org.apache.poi.util.GenericRecordXmlWriter;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Removal;

/* loaded from: classes10.dex */
public final class EscherAggregate extends AbstractEscherHolderRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = DEFAULT_MAX_RECORD_LENGTH;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACCENTBORDERCALLOUT1 = 50;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACCENTBORDERCALLOUT2 = 51;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACCENTBORDERCALLOUT3 = 52;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACCENTBORDERCALLOUT90 = 181;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACCENTCALLOUT1 = 44;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACCENTCALLOUT2 = 45;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACCENTCALLOUT3 = 46;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACCENTCALLOUT90 = 179;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONBACKPREVIOUS = 194;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONBEGINNING = 196;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONBLANK = 189;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONDOCUMENT = 198;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONEND = 195;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONFORWARDNEXT = 193;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONHELP = 191;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONHOME = 190;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONINFORMATION = 192;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONMOVIE = 200;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONRETURN = 197;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ACTIONBUTTONSOUND = 199;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ARC = 19;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ARROW = 13;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BALLOON = 17;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BENTARROW = 91;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BENTCONNECTOR2 = 33;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BENTCONNECTOR3 = 34;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BENTCONNECTOR4 = 35;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BENTCONNECTOR5 = 36;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BENTUPARROW = 90;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BEVEL = 84;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BLOCKARC = 95;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BORDERCALLOUT1 = 47;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BORDERCALLOUT2 = 48;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BORDERCALLOUT3 = 49;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BORDERCALLOUT90 = 180;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BRACEPAIR = 186;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_BRACKETPAIR = 185;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CALLOUT1 = 41;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CALLOUT2 = 42;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CALLOUT3 = 43;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CALLOUT90 = 178;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CAN = 22;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CHEVRON = 55;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CIRCULARARROW = 99;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CLOUDCALLOUT = 106;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CUBE = 16;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CURVEDCONNECTOR2 = 37;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CURVEDCONNECTOR3 = 38;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CURVEDCONNECTOR4 = 39;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CURVEDCONNECTOR5 = 40;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CURVEDDOWNARROW = 105;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CURVEDLEFTARROW = 103;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CURVEDRIGHTARROW = 102;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_CURVEDUPARROW = 104;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_DIAMOND = 4;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_DONUT = 23;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_DOUBLEWAVE = 188;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_DOWNARROW = 67;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_DOWNARROWCALLOUT = 80;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ELLIPSE = 3;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ELLIPSERIBBON = 107;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ELLIPSERIBBON2 = 108;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTALTERNATEPROCESS = 176;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTCOLLATE = 125;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTCONNECTOR = 120;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTDECISION = 110;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTDELAY = 135;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTDISPLAY = 134;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTDOCUMENT = 114;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTEXTRACT = 127;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTINPUTOUTPUT = 111;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTINTERNALSTORAGE = 113;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTMAGNETICDISK = 132;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTMAGNETICDRUM = 133;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTMAGNETICTAPE = 131;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTMANUALINPUT = 118;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTMANUALOPERATION = 119;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTMERGE = 128;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTMULTIDOCUMENT = 115;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTOFFLINESTORAGE = 129;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTOFFPAGECONNECTOR = 177;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTONLINESTORAGE = 130;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTOR = 124;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTPREDEFINEDPROCESS = 112;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTPREPARATION = 117;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTPROCESS = 109;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTPUNCHEDCARD = 121;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTPUNCHEDTAPE = 122;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTSORT = 126;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTSUMMINGJUNCTION = 123;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FLOWCHARTTERMINATOR = 116;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_FOLDEDCORNER = 65;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_HEART = 74;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_HEXAGON = 9;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_HOMEPLATE = 15;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_HORIZONTALSCROLL = 98;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_HOSTCONTROL = 201;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_IRREGULARSEAL1 = 71;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_IRREGULARSEAL2 = 72;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ISOCELESTRIANGLE = 5;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LEFTARROW = 66;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LEFTARROWCALLOUT = 77;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LEFTBRACE = 87;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LEFTBRACKET = 85;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LEFTRIGHTARROW = 69;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LEFTRIGHTARROWCALLOUT = 81;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LEFTRIGHTUPARROW = 182;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LEFTUPARROW = 89;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LIGHTNINGBOLT = 73;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_LINE = 20;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_MIN = 0;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_MOON = 184;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_NIL = 4095;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_NOSMOKING = 57;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_NOTCHEDCIRCULARARROW = 100;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_NOTCHEDRIGHTARROW = 94;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_NOT_PRIMATIVE = 0;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_OCTAGON = 10;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_PARALLELOGRAM = 7;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_PENTAGON = 56;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_PICTUREFRAME = 75;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_PLAQUE = 21;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_PLUS = 11;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_QUADARROW = 76;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_QUADARROWCALLOUT = 83;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_RECTANGLE = 1;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_RIBBON = 53;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_RIBBON2 = 54;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_RIGHTARROWCALLOUT = 78;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_RIGHTBRACE = 88;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_RIGHTBRACKET = 86;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_RIGHTTRIANGLE = 6;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_ROUNDRECTANGLE = 2;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_SEAL = 18;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_SEAL16 = 59;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_SEAL24 = 92;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_SEAL32 = 60;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_SEAL4 = 187;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_SEAL8 = 58;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_SMILEYFACE = 96;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_STAR = 12;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_STRAIGHTCONNECTOR1 = 32;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_STRIPEDRIGHTARROW = 93;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_SUN = 183;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTARCHDOWNCURVE = 145;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTARCHDOWNPOUR = 149;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTARCHUPCURVE = 144;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTARCHUPPOUR = 148;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTBOX = 202;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTBUTTONCURVE = 147;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTBUTTONPOUR = 151;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCANDOWN = 175;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCANUP = 174;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCASCADEDOWN = 155;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCASCADEUP = 154;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCHEVRON = 140;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCHEVRONINVERTED = 141;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCIRCLECURVE = 146;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCIRCLEPOUR = 150;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCURVE = 27;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCURVEDOWN = 153;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTCURVEUP = 152;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTDEFLATE = 161;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTDEFLATEBOTTOM = 163;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTDEFLATEINFLATE = 166;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTDEFLATEINFLATEDEFLATE = 167;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTDEFLATETOP = 165;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTFADEDOWN = 171;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTFADELEFT = 169;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTFADERIGHT = 168;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTFADEUP = 170;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTHEXAGON = 26;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTINFLATE = 160;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTINFLATEBOTTOM = 162;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTINFLATETOP = 164;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTOCTAGON = 25;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTONCURVE = 30;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTONRING = 31;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTPLAINTEXT = 136;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTRING = 29;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTRINGINSIDE = 142;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTRINGOUTSIDE = 143;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTSIMPLE = 24;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTSLANTDOWN = 173;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTSLANTUP = 172;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTSTOP = 137;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTTRIANGLE = 138;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTTRIANGLEINVERTED = 139;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTWAVE = 28;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTWAVE1 = 156;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTWAVE2 = 157;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTWAVE3 = 158;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TEXTWAVE4 = 159;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_THICKARROW = 14;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_TRAPEZOID = 8;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_UPARROW = 68;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_UPARROWCALLOUT = 79;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_UPDOWNARROW = 70;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_UPDOWNARROWCALLOUT = 82;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_UTURNARROW = 101;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_VERTICALSCROLL = 97;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_WAVE = 64;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_WEDGEELLIPSECALLOUT = 63;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_WEDGERECTCALLOUT = 61;

    @Removal(version = "5.3")
    @Deprecated
    public static final short ST_WEDGERRECTCALLOUT = 62;
    public static final short sid = 9876;
    private final Map<EscherRecord, Record> shapeToObj;
    private final Map<Integer, NoteRecord> tailRec;

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherAggregate(boolean createDefaultTree) {
        this.shapeToObj = new HashMap();
        this.tailRec = new LinkedHashMap();
        if (createDefaultTree) {
            buildBaseTree();
        }
    }

    public EscherAggregate(EscherAggregate other) {
        super(other);
        this.shapeToObj = new HashMap();
        this.tailRec = new LinkedHashMap();
        this.shapeToObj.putAll(other.shapeToObj);
        this.tailRec.putAll(other.tailRec);
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.Record
    public short getSid() {
        return sid;
    }

    public String toXml(String tab) {
        return GenericRecordXmlWriter.marshal(this);
    }

    public static EscherAggregate createAggregate(List<RecordBase> records, int locFirstDrawingRecord) {
        final EscherAggregate agg = new EscherAggregate(false);
        ShapeCollector recordFactory = new ShapeCollector();
        List<Record> objectRecords = new ArrayList<>();
        int nextIdx = locFirstDrawingRecord;
        Iterator<RecordBase> it = records.subList(locFirstDrawingRecord, records.size()).iterator();
        while (true) {
            if (it.hasNext()) {
                RecordBase rb = it.next();
                nextIdx++;
                switch (sid(rb)) {
                    case 28:
                        NoteRecord r = (NoteRecord) rb;
                        agg.tailRec.put(Integer.valueOf(r.getShapeId()), r);
                        break;
                    case 60:
                        recordFactory.addBytes(((ContinueRecord) rb).getData());
                        break;
                    case 93:
                    case 438:
                        objectRecords.add((Record) rb);
                        break;
                    case 236:
                        recordFactory.addBytes(((DrawingRecord) rb).getRecordData());
                        break;
                    default:
                        nextIdx--;
                        break;
                }
            }
        }
        records.set(locFirstDrawingRecord, agg);
        if (locFirstDrawingRecord + 1 <= nextIdx) {
            records.subList(locFirstDrawingRecord + 1, nextIdx).clear();
        }
        final Iterator<EscherRecord> shapeIter = recordFactory.parse(agg).iterator();
        objectRecords.forEach(new Consumer() { // from class: org.apache.poi.hssf.record.EscherAggregate$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                EscherAggregate.lambda$createAggregate$0(EscherAggregate.this, shapeIter, (Record) obj);
            }
        });
        return agg;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void lambda$createAggregate$0(EscherAggregate agg, Iterator shapeIter, Record or) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class ShapeCollector extends DefaultEscherRecordFactory {
        final UnsynchronizedByteArrayOutputStream buffer;
        final List<EscherRecord> objShapes;

        private ShapeCollector() {
            this.objShapes = new ArrayList();
            this.buffer = UnsynchronizedByteArrayOutputStream.builder().get();
        }

        void addBytes(byte[] data) {
            this.buffer.write(data);
        }

        @Override // org.apache.poi.ddf.DefaultEscherRecordFactory, org.apache.poi.ddf.EscherRecordFactory
        public EscherRecord createRecord(byte[] data, int offset) {
            EscherRecord r = super.createRecord(data, offset);
            short rid = r.getRecordId();
            if (rid == EscherClientDataRecord.RECORD_ID || rid == EscherTextboxRecord.RECORD_ID) {
                this.objShapes.add(r);
            }
            return r;
        }

        List<EscherRecord> parse(EscherAggregate agg) {
            byte[] buf = this.buffer.toByteArray();
            int pos = 0;
            while (pos < buf.length) {
                EscherRecord r = createRecord(buf, pos);
                pos += r.fillFields(buf, pos, this);
                agg.addEscherRecord(r);
            }
            return this.objShapes;
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:8:0x004e */
    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.RecordBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int serialize(int r17, byte[] r18) {
        /*
            r16 = this;
            r0 = r16
            java.util.List r6 = r0.getEscherRecords()
            int r7 = r0.getEscherRecordSize(r6)
            byte[] r8 = new byte[r7]
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r9 = r1
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r10 = r1
            r1 = 0
            java.util.Iterator r2 = r6.iterator()
        L1d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L34
            java.lang.Object r3 = r2.next()
            org.apache.poi.ddf.EscherRecord r3 = (org.apache.poi.ddf.EscherRecord) r3
            org.apache.poi.hssf.record.EscherAggregate$1 r4 = new org.apache.poi.hssf.record.EscherAggregate$1
            r4.<init>()
            int r4 = r3.serialize(r1, r8, r4)
            int r1 = r1 + r4
            goto L1d
        L34:
            r2 = 0
            r3 = 0
            r10.add(r3, r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r9.add(r3, r2)
            r1 = r17
            r2 = 0
            r3 = 1
            r4 = 0
            r5 = 1
            r11 = r4
            r12 = r5
            r5 = r3
            r3 = r1
        L4a:
            int r1 = r10.size()
            if (r12 >= r1) goto L7d
            r13 = r11
            java.lang.Object r1 = r9.get(r12)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r11 = r1.intValue()
            byte[] r1 = java.util.Arrays.copyOfRange(r8, r13, r11)
            r4 = r18
            int r14 = r0.writeDataIntoDrawingRecord(r1, r2, r3, r4, r5)
            int r3 = r3 + r14
            int r14 = r1.length
            int r2 = r2 + r14
            java.util.Map<org.apache.poi.ddf.EscherRecord, org.apache.poi.hssf.record.Record> r14 = r0.shapeToObj
            java.lang.Object r15 = r10.get(r12)
            java.lang.Object r14 = r14.get(r15)
            org.apache.poi.hssf.record.Record r14 = (org.apache.poi.hssf.record.Record) r14
            int r15 = r14.serialize(r3, r4)
            int r3 = r3 + r15
            r5 = 0
            int r12 = r12 + 1
            goto L4a
        L7d:
            r4 = r18
            int r1 = r8.length
            int r1 = r1 + (-1)
            if (r11 >= r1) goto L8e
            int r1 = r8.length
            byte[] r1 = java.util.Arrays.copyOfRange(r8, r11, r1)
            int r12 = r0.writeDataIntoDrawingRecord(r1, r2, r3, r4, r5)
            int r3 = r3 + r12
        L8e:
            java.util.Map<java.lang.Integer, org.apache.poi.hssf.record.NoteRecord> r1 = r0.tailRec
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
        L98:
            boolean r12 = r1.hasNext()
            if (r12 == 0) goto Laa
            java.lang.Object r12 = r1.next()
            org.apache.poi.hssf.record.NoteRecord r12 = (org.apache.poi.hssf.record.NoteRecord) r12
            int r13 = r12.serialize(r3, r4)
            int r3 = r3 + r13
            goto L98
        Laa:
            int r1 = r3 - r17
            int r12 = r0.getRecordSize()
            if (r1 != r12) goto Lb3
            return r1
        Lb3:
            org.apache.poi.util.RecordFormatException r12 = new org.apache.poi.util.RecordFormatException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.StringBuilder r13 = r13.append(r1)
            java.lang.String r14 = " bytes written but getRecordSize() reports "
            java.lang.StringBuilder r13 = r13.append(r14)
            int r14 = r0.getRecordSize()
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.EscherAggregate.serialize(int, byte[]):int");
    }

    private int writeDataIntoDrawingRecord(byte[] drawingData, int writtenEscherBytes, int pos, byte[] data, boolean isFirst) {
        int temp = 0;
        boolean useDrawingRecord = isFirst || drawingData.length + writtenEscherBytes <= 8224;
        for (int j = 0; j < drawingData.length; j += 8224) {
            byte[] buf = Arrays.copyOfRange(drawingData, j, Math.min(j + 8224, drawingData.length));
            Record drawing = useDrawingRecord ? new DrawingRecord(buf) : new ContinueRecord(buf);
            temp += drawing.serialize(pos + temp, data);
            useDrawingRecord = false;
        }
        return temp;
    }

    private int getEscherRecordSize(List<EscherRecord> records) {
        int size = 0;
        for (EscherRecord record : records) {
            size += record.getRecordSize();
        }
        return size;
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.RecordBase
    public int getRecordSize() {
        int continueRecordsHeadersSize = 0;
        List<EscherRecord> records = getEscherRecords();
        int rawEscherSize = getEscherRecordSize(records);
        byte[] buffer = IOUtils.safelyAllocate(rawEscherSize, MAX_RECORD_LENGTH);
        final List<Integer> spEndingOffsets = new ArrayList<>();
        int pos = 0;
        for (EscherRecord e : records) {
            pos += e.serialize(pos, buffer, new EscherSerializationListener() { // from class: org.apache.poi.hssf.record.EscherAggregate.2
                @Override // org.apache.poi.ddf.EscherSerializationListener
                public void beforeRecordSerialize(int offset, short recordId, EscherRecord record) {
                }

                @Override // org.apache.poi.ddf.EscherSerializationListener
                public void afterRecordSerialize(int offset, short recordId, int size, EscherRecord record) {
                    if (recordId == EscherClientDataRecord.RECORD_ID || recordId == EscherTextboxRecord.RECORD_ID) {
                        spEndingOffsets.add(Integer.valueOf(offset));
                    }
                }
            });
        }
        spEndingOffsets.add(0, 0);
        for (int i = 1; i < spEndingOffsets.size(); i++) {
            if (i == spEndingOffsets.size() - 1 && spEndingOffsets.get(i).intValue() < pos) {
                continueRecordsHeadersSize += 4;
            }
            if (spEndingOffsets.get(i).intValue() - spEndingOffsets.get(i - 1).intValue() > 8224) {
                continueRecordsHeadersSize += ((spEndingOffsets.get(i).intValue() - spEndingOffsets.get(i - 1).intValue()) / 8224) * 4;
            }
        }
        int drawingRecordSize = (this.shapeToObj.size() * 4) + rawEscherSize;
        if (rawEscherSize != 0 && spEndingOffsets.size() == 1) {
            continueRecordsHeadersSize += 4;
        }
        int objRecordSize = 0;
        for (Record r : this.shapeToObj.values()) {
            objRecordSize += r.getRecordSize();
        }
        int tailRecordSize = 0;
        for (NoteRecord noteRecord : this.tailRec.values()) {
            tailRecordSize += noteRecord.getRecordSize();
        }
        return drawingRecordSize + objRecordSize + tailRecordSize + continueRecordsHeadersSize;
    }

    public void associateShapeToObjRecord(EscherRecord r, Record objRecord) {
        this.shapeToObj.put(r, objRecord);
    }

    public void removeShapeToObjRecord(EscherRecord rec) {
        this.shapeToObj.remove(rec);
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord
    protected String getRecordName() {
        return "ESCHERAGGREGATE";
    }

    private void buildBaseTree() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        EscherContainerRecord escherContainerRecord2 = new EscherContainerRecord();
        EscherContainerRecord spContainer1 = new EscherContainerRecord();
        EscherSpgrRecord spgr = new EscherSpgrRecord();
        EscherSpRecord sp1 = new EscherSpRecord();
        escherContainerRecord.setRecordId(EscherContainerRecord.DG_CONTAINER);
        escherContainerRecord.setOptions((short) 15);
        EscherDgRecord dg = new EscherDgRecord();
        dg.setRecordId(EscherDgRecord.RECORD_ID);
        dg.setOptions((short) (1 << 4));
        dg.setNumShapes(0);
        dg.setLastMSOSPID(1024);
        escherContainerRecord2.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        escherContainerRecord2.setOptions((short) 15);
        spContainer1.setRecordId(EscherContainerRecord.SP_CONTAINER);
        spContainer1.setOptions((short) 15);
        spgr.setRecordId(EscherSpgrRecord.RECORD_ID);
        spgr.setOptions((short) 1);
        spgr.setRectX1(0);
        spgr.setRectY1(0);
        spgr.setRectX2(IEEEDouble.EXPONENT_BIAS);
        spgr.setRectY2(255);
        sp1.setRecordId(EscherSpRecord.RECORD_ID);
        sp1.setOptions((short) 2);
        sp1.setVersion((short) 2);
        sp1.setShapeId(-1);
        sp1.setFlags(5);
        escherContainerRecord.addChildRecord(dg);
        escherContainerRecord.addChildRecord(escherContainerRecord2);
        escherContainerRecord2.addChildRecord(spContainer1);
        spContainer1.addChildRecord(spgr);
        spContainer1.addChildRecord(sp1);
        addEscherRecord(escherContainerRecord);
    }

    public void setDgId(short dgId) {
        EscherContainerRecord dgContainer = getEscherContainer();
        EscherDgRecord dg = (EscherDgRecord) dgContainer.getChildById(EscherDgRecord.RECORD_ID);
        if (dg != null) {
            dg.setOptions((short) (dgId << 4));
        }
    }

    public void setMainSpRecordId(int shapeId) {
        EscherContainerRecord dgContainer = getEscherContainer();
        EscherContainerRecord spgrContainer = (EscherContainerRecord) dgContainer.getChildById(EscherContainerRecord.SPGR_CONTAINER);
        if (spgrContainer != null) {
            EscherContainerRecord spContainer = (EscherContainerRecord) spgrContainer.getChild(0);
            EscherSpRecord sp = (EscherSpRecord) spContainer.getChildById(EscherSpRecord.RECORD_ID);
            if (sp != null) {
                sp.setShapeId(shapeId);
            }
        }
    }

    private static short sid(RecordBase record) {
        if (record instanceof Record) {
            return ((Record) record).getSid();
        }
        return (short) -1;
    }

    public Map<EscherRecord, Record> getShapeToObjMapping() {
        return Collections.unmodifiableMap(this.shapeToObj);
    }

    public Map<Integer, NoteRecord> getTailRecords() {
        return Collections.unmodifiableMap(this.tailRec);
    }

    public NoteRecord getNoteRecordByObj(ObjRecord obj) {
        CommonObjectDataSubRecord cod = (CommonObjectDataSubRecord) obj.getSubRecords().get(0);
        return this.tailRec.get(Integer.valueOf(cod.getObjectId()));
    }

    public void addTailRecord(NoteRecord note) {
        this.tailRec.put(Integer.valueOf(note.getShapeId()), note);
    }

    public void removeTailRecord(NoteRecord note) {
        this.tailRec.remove(Integer.valueOf(note.getShapeId()));
    }

    @Override // org.apache.poi.hssf.record.AbstractEscherHolderRecord, org.apache.poi.hssf.record.Record, org.apache.poi.common.Duplicatable
    public EscherAggregate copy() {
        return new EscherAggregate(this);
    }

    @Override // org.apache.poi.hssf.record.Record, org.apache.poi.common.usermodel.GenericRecord
    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.ESCHER_AGGREGATE;
    }

    @Override // org.apache.poi.common.usermodel.GenericRecord
    public Map<String, Supplier<?>> getGenericProperties() {
        return null;
    }
}
