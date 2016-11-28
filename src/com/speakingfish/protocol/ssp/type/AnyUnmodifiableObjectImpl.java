package com.speakingfish.protocol.ssp.type;

import com.speakingfish.protocol.ssp.AnyObject;
import com.speakingfish.protocol.ssp.LocalAny;
import com.speakingfish.protocol.ssp.LocalAnyObject;

public class AnyUnmodifiableObjectImpl<CONTEXT> extends AnyUnmodifiableImpl<CONTEXT, AnyObject> implements LocalAnyObject<CONTEXT> {
    public AnyUnmodifiableObjectImpl(LocalAny<CONTEXT, AnyObject> origin, boolean isUnmodifiableClone) {
        super(origin, isUnmodifiableClone);
    }
}