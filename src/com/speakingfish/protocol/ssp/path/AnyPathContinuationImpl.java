package com.speakingfish.protocol.ssp.path;

import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyPath;
import com.speakingfish.protocol.ssp.AnyPathContinuation;
import com.speakingfish.protocol.ssp.AnyPathContinuationValue;
import com.speakingfish.protocol.ssp.AnyPathValue;
import com.speakingfish.protocol.ssp.PathVisitor;

public abstract class AnyPathContinuationImpl<
    R, R_Any  extends Any<R>,
    T, T_Any  extends Any<T>,
    N, N_Any  extends Any<N>,
    T_SubPath extends AnyPathValue<
        R, R_Any,
        N, N_Any>
> extends AnyPathImpl<
    R, R_Any ,    
    T, T_Any
> implements AnyPathContinuationValue<
    R, R_Any ,
    T, T_Any ,
    N, N_Any ,
    T_SubPath
> {
    
    protected final T_SubPath _next;

    public AnyPathContinuationImpl(T_Any value, T_SubPath next) {
        super(value);
        _next = next;
    }

    public T_SubPath next() { return _next; }
    
    public R_Any value(T_Any src) {
        final N_Any next = next(src);
        return (null == next) ? null : next().value(next);
    }

    public void visitForward(PathVisitor<R, R_Any> visitor) {
        visit(visitor);
        next().visitForward(visitor);
    }

    public void visitBackward(PathVisitor<R, R_Any> visitor) {
        next().visitBackward(visitor);
        visit(visitor);
    }

    public abstract N_Any next(T_Any current);

}
