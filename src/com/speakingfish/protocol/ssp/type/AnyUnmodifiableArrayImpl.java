package com.speakingfish.protocol.ssp.type;

import com.speakingfish.protocol.ssp.AnyArray;
import com.speakingfish.protocol.ssp.LocalAny;
import com.speakingfish.protocol.ssp.LocalAnyArray;


public class AnyUnmodifiableArrayImpl<CONTEXT> extends AnyUnmodifiableImpl<CONTEXT, AnyArray> implements LocalAnyArray<CONTEXT>  {
    public AnyUnmodifiableArrayImpl(LocalAny<CONTEXT, AnyArray> origin) {
        super(origin);
    }
}