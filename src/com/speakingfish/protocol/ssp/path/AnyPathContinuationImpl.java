package com.speakingfish.protocol.ssp.path;

import java.util.Iterator;

import com.speakingfish.common.function.Mapper;
import com.speakingfish.common.iterator.Iterators;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyPathContinuationValue;
import com.speakingfish.protocol.ssp.AnyPathValue;
import com.speakingfish.protocol.ssp.PathVisitor;

import static com.speakingfish.common.iterator.Iterators.*;

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

    public Iterator<N_Any> following(T_Any current) {
        final N_Any result = next(current);
        return ((null == result) ? Iterators.<N_Any>noneIterator() : singleIterator(result));
    }

    public Iterator<R_Any> values(T_Any src) {
        final Iterator<N_Any> result = following(src);
        return (null == result) ? Iterators.<R_Any>noneIterator()
            : iteratorChain(mapIterator(result, new Mapper<Iterator<R_Any>, N_Any>() {
                public Iterator<R_Any> apply(N_Any s) {
                    return next().values(s);
                }}));
    }
    
    

}
