package com.speakingfish.protocol.ssp.path;

import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyPath;
import com.speakingfish.protocol.ssp.AnyPathValue;
import com.speakingfish.protocol.ssp.PathVisitor;

public abstract class AnyPathImpl<
    R, R_Any  extends Any<R>,
    T, T_Any  extends Any<T>
> implements AnyPathValue<
    R, R_Any,
    T, T_Any
> {
    
    protected final T_Any _value;
    
    public AnyPathImpl(T_Any value) {
        _value = value;
    }

    public T_Any get() { return _value; }

    public abstract void visit(PathVisitor<R, R_Any> visitor);
    
}
