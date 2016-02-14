package com.speakingfish.protocol.ssp.type;

import com.speakingfish.protocol.ssp.Any;

public abstract class AnyDefaultMutableImpl<CONTEXT, TYPE> extends AnyDefaultImpl<CONTEXT, TYPE> {

    protected transient Any<TYPE> _unmodifiable;

    @Override public Any<TYPE> asUnmodifiable() {
        if(null == _unmodifiable) {
            _unmodifiable = makeUnmodifiable();
        }
        return _unmodifiable;
    };
    
    abstract protected Any<TYPE> makeUnmodifiable();
    
}
