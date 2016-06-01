package com.speakingfish.protocol.ssp.path;

import com.speakingfish.common.function.Mapper;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyPathValue;
import com.speakingfish.protocol.ssp.PathValueVisitor;
import com.speakingfish.protocol.ssp.PathVisitor;

public class AnyPathExprImpl<
    R, R_Any  extends Any<R>,
    T, T_Any  extends Any<T>,
    N, N_Any  extends Any<N>,
    T_SubPath extends AnyPathValue<
        R, R_Any,
        N, N_Any>
> extends AnyPathContinuationImpl<
    R, R_Any,
    T, T_Any,
    N, N_Any,
    T_SubPath
> {
    
    protected final Mapper<N_Any, T_Any> _selectNext;

    public AnyPathExprImpl(T_Any value, Mapper<N_Any, T_Any> selectNext, T_SubPath next) {
        super(value, next);
        _selectNext = selectNext;
    }

    @Override public N_Any next(T_Any current) {
        return _selectNext.apply(current);
    }

    public void visitForward(PathValueVisitor<R, R_Any> visitor) {
        throw new UnsupportedOperationException();
    }

    public void visitBackward(PathValueVisitor<R, R_Any> visitor) {
        throw new UnsupportedOperationException();
    }


    public void visitForward(T_Any src, PathValueVisitor<R, R_Any> visitor) {
        final N_Any result = next(src);
        if(null != result) {
            next().visitForward(result, visitor);
        }
    }

    public void visitBackward(T_Any src, PathValueVisitor<R, R_Any> visitor) {
        throw new UnsupportedOperationException();
    }

    @Override public void visit(PathVisitor<R, R_Any> visitor) {
        throw new UnsupportedOperationException();
    }

}
