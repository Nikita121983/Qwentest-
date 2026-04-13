package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.parser.CLArray;
import androidx.constraintlayout.core.parser.CLContainer;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.state.Transition;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class TransitionParser {
    @Deprecated
    public static void parse(CLObject json, Transition transition, CorePixelDp dpToPixel) throws CLParsingException {
        parse(json, transition);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void parse(CLObject json, Transition transition) throws CLParsingException {
        char c;
        transition.resetProperties();
        String pathMotionArc = json.getStringOrNull(TypedValues.TransitionType.S_PATH_MOTION_ARC);
        TypedBundle bundle = new TypedBundle();
        boolean setBundle = false;
        if (pathMotionArc != null) {
            setBundle = true;
            switch (pathMotionArc.hashCode()) {
                case -1857024520:
                    if (pathMotionArc.equals("startVertical")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1007052250:
                    if (pathMotionArc.equals("startHorizontal")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 3145837:
                    if (pathMotionArc.equals("flip")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 3387192:
                    if (pathMotionArc.equals("none")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 92611485:
                    if (pathMotionArc.equals("above")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 93621297:
                    if (pathMotionArc.equals("below")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    bundle.add(509, 0);
                    break;
                case 1:
                    bundle.add(509, 1);
                    break;
                case 2:
                    bundle.add(509, 2);
                    break;
                case 3:
                    bundle.add(509, 3);
                    break;
                case 4:
                    bundle.add(509, 4);
                    break;
                case 5:
                    bundle.add(509, 5);
                    break;
            }
        }
        String interpolator = json.getStringOrNull("interpolator");
        if (interpolator != null) {
            setBundle = true;
            bundle.add(TypedValues.TransitionType.TYPE_INTERPOLATOR, interpolator);
        }
        float staggered = json.getFloatOrNaN(TypedValues.TransitionType.S_STAGGERED);
        if (!Float.isNaN(staggered)) {
            setBundle = true;
            bundle.add(TypedValues.TransitionType.TYPE_STAGGERED, staggered);
        }
        if (setBundle) {
            transition.setTransitionProperties(bundle);
        }
        CLContainer onSwipe = json.getObjectOrNull("onSwipe");
        if (onSwipe != null) {
            parseOnSwipe(onSwipe, transition);
        }
        parseKeyFrames(json, transition);
    }

    private static void parseOnSwipe(CLContainer onSwipe, Transition transition) {
        String anchor = onSwipe.getStringOrNull("anchor");
        int side = map(onSwipe.getStringOrNull("side"), Transition.OnSwipe.SIDES);
        int direction = map(onSwipe.getStringOrNull("direction"), Transition.OnSwipe.DIRECTIONS);
        float scale = onSwipe.getFloatOrNaN("scale");
        float threshold = onSwipe.getFloatOrNaN("threshold");
        float maxVelocity = onSwipe.getFloatOrNaN("maxVelocity");
        float maxAccel = onSwipe.getFloatOrNaN("maxAccel");
        String limitBounds = onSwipe.getStringOrNull("limitBounds");
        int autoCompleteMode = map(onSwipe.getStringOrNull("mode"), Transition.OnSwipe.MODE);
        int touchUp = map(onSwipe.getStringOrNull("touchUp"), Transition.OnSwipe.TOUCH_UP);
        float springMass = onSwipe.getFloatOrNaN("springMass");
        float springStiffness = onSwipe.getFloatOrNaN("springStiffness");
        float springDamping = onSwipe.getFloatOrNaN("springDamping");
        float stopThreshold = onSwipe.getFloatOrNaN("stopThreshold");
        int springBoundary = map(onSwipe.getStringOrNull("springBoundary"), Transition.OnSwipe.BOUNDARY);
        String around = onSwipe.getStringOrNull("around");
        Transition.OnSwipe swipe = transition.createOnSwipe();
        swipe.setAnchorId(anchor);
        swipe.setAnchorSide(side);
        swipe.setDragDirection(direction);
        swipe.setDragScale(scale);
        swipe.setDragThreshold(threshold);
        swipe.setMaxVelocity(maxVelocity);
        swipe.setMaxAcceleration(maxAccel);
        swipe.setLimitBoundsTo(limitBounds);
        swipe.setAutoCompleteMode(autoCompleteMode);
        swipe.setOnTouchUp(touchUp);
        swipe.setSpringMass(springMass);
        swipe.setSpringStiffness(springStiffness);
        swipe.setSpringDamping(springDamping);
        swipe.setSpringStopThreshold(stopThreshold);
        swipe.setSpringBoundary(springBoundary);
        swipe.setRotationCenterId(around);
    }

    private static int map(String val, String... types) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(val)) {
                return i;
            }
        }
        return 0;
    }

    private static void map(TypedBundle bundle, int type, String val, String... types) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(val)) {
                bundle.add(type, i);
            }
        }
    }

    public static void parseKeyFrames(CLObject transitionCLObject, Transition transition) throws CLParsingException {
        CLContainer keyframes = transitionCLObject.getObjectOrNull("KeyFrames");
        if (keyframes == null) {
            return;
        }
        CLArray keyPositions = keyframes.getArrayOrNull("KeyPositions");
        if (keyPositions != null) {
            for (int i = 0; i < keyPositions.size(); i++) {
                CLElement keyPosition = keyPositions.get(i);
                if (keyPosition instanceof CLObject) {
                    parseKeyPosition((CLObject) keyPosition, transition);
                }
            }
        }
        CLArray keyAttributes = keyframes.getArrayOrNull(TypedValues.AttributesType.NAME);
        if (keyAttributes != null) {
            for (int i2 = 0; i2 < keyAttributes.size(); i2++) {
                CLElement keyAttribute = keyAttributes.get(i2);
                if (keyAttribute instanceof CLObject) {
                    parseKeyAttribute((CLObject) keyAttribute, transition);
                }
            }
        }
        CLArray keyCycles = keyframes.getArrayOrNull("KeyCycles");
        if (keyCycles != null) {
            for (int i3 = 0; i3 < keyCycles.size(); i3++) {
                CLElement keyCycle = keyCycles.get(i3);
                if (keyCycle instanceof CLObject) {
                    parseKeyCycle((CLObject) keyCycle, transition);
                }
            }
        }
    }

    private static void parseKeyPosition(CLObject keyPosition, Transition transition) throws CLParsingException {
        TypedBundle bundle = new TypedBundle();
        CLArray targets = keyPosition.getArray(TypedValues.AttributesType.S_TARGET);
        CLArray frames = keyPosition.getArray("frames");
        CLArray percentX = keyPosition.getArrayOrNull("percentX");
        CLArray percentY = keyPosition.getArrayOrNull("percentY");
        CLArray percentWidth = keyPosition.getArrayOrNull("percentWidth");
        CLArray percentHeight = keyPosition.getArrayOrNull("percentHeight");
        String pathMotionArc = keyPosition.getStringOrNull(TypedValues.TransitionType.S_PATH_MOTION_ARC);
        String transitionEasing = keyPosition.getStringOrNull("transitionEasing");
        String curveFit = keyPosition.getStringOrNull("curveFit");
        String type = keyPosition.getStringOrNull(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
        if (type == null) {
            type = "parentRelative";
        }
        if (percentX != null && frames.size() != percentX.size()) {
            return;
        }
        if (percentY != null && frames.size() != percentY.size()) {
            return;
        }
        int i = 0;
        while (i < targets.size()) {
            String target = targets.getString(i);
            int pos_type = map(type, "deltaRelative", "pathRelative", "parentRelative");
            bundle.clear();
            bundle.add(TypedValues.PositionType.TYPE_POSITION_TYPE, pos_type);
            if (curveFit != null) {
                map(bundle, 508, curveFit, "spline", "linear");
            }
            bundle.addIfNotNull(TypedValues.PositionType.TYPE_TRANSITION_EASING, transitionEasing);
            if (pathMotionArc != null) {
                map(bundle, 509, pathMotionArc, "none", "startVertical", "startHorizontal", "flip", "below", "above");
            }
            int j = 0;
            while (j < frames.size()) {
                int frame = frames.getInt(j);
                bundle.add(100, frame);
                set(bundle, TypedValues.PositionType.TYPE_PERCENT_X, percentX, j);
                set(bundle, TypedValues.PositionType.TYPE_PERCENT_Y, percentY, j);
                set(bundle, TypedValues.PositionType.TYPE_PERCENT_WIDTH, percentWidth, j);
                set(bundle, 504, percentHeight, j);
                transition.addKeyPosition(target, bundle);
                j++;
                targets = targets;
            }
            i++;
            targets = targets;
        }
    }

    private static void set(TypedBundle bundle, int type, CLArray array, int index) throws CLParsingException {
        if (array != null) {
            bundle.add(type, array.getFloat(index));
        }
    }

    private static void parseKeyAttribute(CLObject cLObject, Transition transition) throws CLParsingException {
        CLArray arrayOrNull;
        String[] strArr;
        int[] iArr;
        CLObject cLObject2;
        int i;
        CLObject cLObject3;
        boolean[] zArr;
        int i2;
        CLArray arrayOrNull2 = cLObject.getArrayOrNull(TypedValues.AttributesType.S_TARGET);
        if (arrayOrNull2 != null && (arrayOrNull = cLObject.getArrayOrNull("frames")) != null) {
            String stringOrNull = cLObject.getStringOrNull("transitionEasing");
            int i3 = 0;
            boolean z = true;
            String[] strArr2 = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha"};
            int[] iArr2 = {311, 312, 304, 305, 306, 308, 309, 310, 303};
            boolean[] zArr2 = {false, false, true, true, true, false, false, false, false};
            TypedBundle[] typedBundleArr = new TypedBundle[arrayOrNull.size()];
            CustomVariable[][] customVariableArr = null;
            for (int i4 = 0; i4 < arrayOrNull.size(); i4++) {
                typedBundleArr[i4] = new TypedBundle();
            }
            int i5 = 0;
            while (i5 < strArr2.length) {
                String str = strArr2[i5];
                int i6 = iArr2[i5];
                boolean z2 = zArr2[i5];
                boolean z3 = z;
                CLArray arrayOrNull3 = cLObject.getArrayOrNull(str);
                if (arrayOrNull3 != null) {
                    i2 = i3;
                    if (arrayOrNull3.size() != typedBundleArr.length) {
                        throw new CLParsingException("incorrect size for " + str + " array, not matching targets array!", cLObject);
                    }
                    zArr = zArr2;
                } else {
                    zArr = zArr2;
                    i2 = i3;
                }
                if (arrayOrNull3 != null) {
                    for (int i7 = 0; i7 < typedBundleArr.length; i7++) {
                        float f = arrayOrNull3.getFloat(i7);
                        if (z2) {
                            f = transition.mToPixel.toPixels(f);
                        }
                        typedBundleArr[i7].add(i6, f);
                    }
                } else {
                    float floatOrNaN = cLObject.getFloatOrNaN(str);
                    if (!Float.isNaN(floatOrNaN)) {
                        if (z2) {
                            floatOrNaN = transition.mToPixel.toPixels(floatOrNaN);
                        }
                        for (TypedBundle typedBundle : typedBundleArr) {
                            typedBundle.add(i6, floatOrNaN);
                        }
                    }
                }
                i5++;
                z = z3;
                i3 = i2;
                zArr2 = zArr;
            }
            int i8 = i3;
            boolean z4 = z;
            CLElement orNull = cLObject.getOrNull("custom");
            if (orNull != null && (orNull instanceof CLObject)) {
                CLObject cLObject4 = (CLObject) orNull;
                int size = cLObject4.size();
                int size2 = arrayOrNull.size();
                int[] iArr3 = new int[2];
                iArr3[z4 ? 1 : 0] = size;
                iArr3[i8] = size2;
                customVariableArr = (CustomVariable[][]) Array.newInstance((Class<?>) CustomVariable.class, iArr3);
                int i9 = 0;
                while (i9 < size) {
                    CLKey cLKey = (CLKey) cLObject4.get(i9);
                    String content = cLKey.content();
                    CLElement cLElement = orNull;
                    if (cLKey.getValue() instanceof CLArray) {
                        CLArray cLArray = (CLArray) cLKey.getValue();
                        int size3 = cLArray.size();
                        strArr = strArr2;
                        if (size3 != typedBundleArr.length || size3 <= 0) {
                            iArr = iArr2;
                            cLObject2 = cLObject4;
                        } else if (cLArray.get(i8) instanceof CLNumber) {
                            int i10 = 0;
                            while (i10 < typedBundleArr.length) {
                                customVariableArr[i10][i9] = new CustomVariable(content, TypedValues.Custom.TYPE_FLOAT, cLArray.get(i10).getFloat());
                                i10++;
                                iArr2 = iArr2;
                            }
                            iArr = iArr2;
                            cLObject2 = cLObject4;
                        } else {
                            iArr = iArr2;
                            int i11 = 0;
                            while (i11 < typedBundleArr.length) {
                                long parseColorString = ConstraintSetParser.parseColorString(cLArray.get(i11).content());
                                if (parseColorString == -1) {
                                    i = i11;
                                    cLObject3 = cLObject4;
                                } else {
                                    i = i11;
                                    cLObject3 = cLObject4;
                                    customVariableArr[i11][i9] = new CustomVariable(content, TypedValues.Custom.TYPE_COLOR, (int) parseColorString);
                                }
                                i11 = i + 1;
                                cLObject4 = cLObject3;
                            }
                            cLObject2 = cLObject4;
                        }
                    } else {
                        strArr = strArr2;
                        iArr = iArr2;
                        cLObject2 = cLObject4;
                        CLElement value = cLKey.getValue();
                        if (value instanceof CLNumber) {
                            float f2 = value.getFloat();
                            int i12 = 0;
                            while (i12 < typedBundleArr.length) {
                                customVariableArr[i12][i9] = new CustomVariable(content, TypedValues.Custom.TYPE_FLOAT, f2);
                                i12++;
                                value = value;
                            }
                        } else {
                            long parseColorString2 = ConstraintSetParser.parseColorString(value.content());
                            if (parseColorString2 != -1) {
                                int i13 = 0;
                                while (i13 < typedBundleArr.length) {
                                    customVariableArr[i13][i9] = new CustomVariable(content, TypedValues.Custom.TYPE_COLOR, (int) parseColorString2);
                                    i13++;
                                    parseColorString2 = parseColorString2;
                                }
                            }
                        }
                    }
                    i9++;
                    orNull = cLElement;
                    strArr2 = strArr;
                    iArr2 = iArr;
                    cLObject4 = cLObject2;
                    i8 = 0;
                }
            }
            String stringOrNull2 = cLObject.getStringOrNull("curveFit");
            for (int i14 = 0; i14 < arrayOrNull2.size(); i14++) {
                for (int i15 = 0; i15 < typedBundleArr.length; i15++) {
                    String string = arrayOrNull2.getString(i14);
                    TypedBundle typedBundle2 = typedBundleArr[i15];
                    if (stringOrNull2 != null) {
                        String[] strArr3 = new String[2];
                        strArr3[0] = "spline";
                        strArr3[z4 ? 1 : 0] = "linear";
                        typedBundle2.add(508, map(stringOrNull2, strArr3));
                    }
                    typedBundle2.addIfNotNull(TypedValues.PositionType.TYPE_TRANSITION_EASING, stringOrNull);
                    typedBundle2.add(100, arrayOrNull.getInt(i15));
                    transition.addKeyAttribute(string, typedBundle2, customVariableArr != null ? customVariableArr[i15] : null);
                }
            }
        }
    }

    private static void parseKeyCycle(CLObject keyCycleData, Transition transition) throws CLParsingException {
        CLArray targets;
        char c;
        int[] scaleTypes;
        String[] attrNames;
        CLArray targets2 = keyCycleData.getArray(TypedValues.AttributesType.S_TARGET);
        CLArray frames = keyCycleData.getArray("frames");
        String transitionEasing = keyCycleData.getStringOrNull("transitionEasing");
        String[] attrNames2 = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha", TypedValues.CycleType.S_WAVE_PERIOD, TypedValues.CycleType.S_WAVE_OFFSET, TypedValues.CycleType.S_WAVE_PHASE};
        int[] attrIds = {311, 312, 304, 305, 306, 308, 309, 310, TypedValues.CycleType.TYPE_ALPHA, TypedValues.CycleType.TYPE_WAVE_PERIOD, TypedValues.CycleType.TYPE_WAVE_OFFSET, TypedValues.CycleType.TYPE_WAVE_PHASE};
        int[] scaleTypes2 = {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 2, 0};
        TypedBundle[] bundles = new TypedBundle[frames.size()];
        for (int i = 0; i < bundles.length; i++) {
            bundles[i] = new TypedBundle();
        }
        int i2 = 0;
        for (int k = 0; k < attrNames2.length; k++) {
            if (keyCycleData.has(attrNames2[k]) && scaleTypes2[k] == 1) {
                i2 = 1;
            }
        }
        int k2 = 0;
        while (k2 < attrNames2.length) {
            String attrName = attrNames2[k2];
            int attrId = attrIds[k2];
            int scale = scaleTypes2[k2];
            CLArray arrayValues = keyCycleData.getArrayOrNull(attrName);
            if (arrayValues != null) {
                scaleTypes = scaleTypes2;
                if (arrayValues.size() != bundles.length) {
                    throw new CLParsingException("incorrect size for $attrName array, not matching targets array!", keyCycleData);
                }
            } else {
                scaleTypes = scaleTypes2;
            }
            if (arrayValues != null) {
                int i3 = 0;
                while (i3 < bundles.length) {
                    float value = arrayValues.getFloat(i3);
                    int i4 = i3;
                    if (scale == 1) {
                        value = transition.mToPixel.toPixels(value);
                    } else if (scale == 2 && i2 != 0) {
                        value = transition.mToPixel.toPixels(value);
                    }
                    bundles[i4].add(attrId, value);
                    i3 = i4 + 1;
                }
                attrNames = attrNames2;
            } else {
                float value2 = keyCycleData.getFloatOrNaN(attrName);
                if (Float.isNaN(value2)) {
                    attrNames = attrNames2;
                } else {
                    if (scale == 1) {
                        value2 = transition.mToPixel.toPixels(value2);
                    } else if (scale == 2 && i2 != 0) {
                        value2 = transition.mToPixel.toPixels(value2);
                    }
                    int i5 = 0;
                    while (true) {
                        attrNames = attrNames2;
                        if (i5 < bundles.length) {
                            bundles[i5].add(attrId, value2);
                            i5++;
                            attrNames2 = attrNames;
                        }
                    }
                }
            }
            k2++;
            scaleTypes2 = scaleTypes;
            attrNames2 = attrNames;
        }
        String curveFit = keyCycleData.getStringOrNull("curveFit");
        String easing = keyCycleData.getStringOrNull("easing");
        String waveShape = keyCycleData.getStringOrNull("waveShape");
        String customWave = keyCycleData.getStringOrNull(TypedValues.CycleType.S_CUSTOM_WAVE_SHAPE);
        for (int i6 = 0; i6 < targets2.size(); i6++) {
            int j = 0;
            while (j < bundles.length) {
                String target = targets2.getString(i6);
                TypedBundle bundle = bundles[j];
                if (curveFit == null) {
                    targets = targets2;
                } else {
                    switch (curveFit.hashCode()) {
                        case -1102672091:
                            if (curveFit.equals("linear")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -895858735:
                            if (curveFit.equals("spline")) {
                                c = 0;
                                break;
                            }
                            break;
                    }
                    c = 65535;
                    switch (c) {
                        case 0:
                            targets = targets2;
                            bundle.add(TypedValues.CycleType.TYPE_CURVE_FIT, 0);
                            break;
                        case 1:
                            targets = targets2;
                            bundle.add(TypedValues.CycleType.TYPE_CURVE_FIT, 1);
                            break;
                        default:
                            targets = targets2;
                            break;
                    }
                }
                bundle.addIfNotNull(TypedValues.PositionType.TYPE_TRANSITION_EASING, transitionEasing);
                if (easing != null) {
                    bundle.add(420, easing);
                }
                if (waveShape != null) {
                    bundle.add(TypedValues.CycleType.TYPE_WAVE_SHAPE, waveShape);
                }
                if (customWave != null) {
                    bundle.add(TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, customWave);
                }
                int frame = frames.getInt(j);
                bundle.add(100, frame);
                transition.addKeyCycle(target, bundle);
                j++;
                targets2 = targets;
            }
        }
    }
}
