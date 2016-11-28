package com.speakingfish.protocol.ssp.type;

import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.LocalAny;


public abstract class AnyDefaultPrimitiveImpl<CONTEXT, TYPE> extends AnyDefaultImpl<CONTEXT, TYPE> {

    @Override public Any<TYPE> asUnmodifiable() { return this; }

    @Override public LocalAny<CONTEXT, TYPE> clone() { return this; }

    @Override public LocalAny<CONTEXT, TYPE> cloneUnmodifiable() { return this; }

}