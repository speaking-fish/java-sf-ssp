package com.speakingfish.protocol.ssp.type;

import com.speakingfish.protocol.ssp.Any;


public abstract class AnyDefaultPrimitiveImpl<CONTEXT, TYPE> extends AnyDefaultImpl<CONTEXT, TYPE> {
    
    @Override protected Any<TYPE> makeUnmodifiable() {
        return new AnyUnmodifiableImpl<CONTEXT, TYPE>(this);
    };
    
}