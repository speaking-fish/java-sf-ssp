package com.speakingfish.protocol.ssp.path;

import java.util.Iterator;

import com.speakingfish.common.function.Mapper;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyPathValue;
import com.speakingfish.protocol.ssp.PathValueVisitor;
import com.speakingfish.protocol.ssp.PathVisitor;

import static com.speakingfish.common.iterator.Iterators.*;

public class AnyPathListExprImpl<
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
    
    protected final Mapper<Iterator<N_Any>, T_Any> _selectNext;

    public AnyPathListExprImpl(T_Any value, Mapper<Iterator<N_Any>, T_Any> selectNext, T_SubPath next) {
        super(value, next);
        _selectNext = selectNext;
    }

    public void visitForward(PathValueVisitor<R, R_Any> visitor) {
        throw new UnsupportedOperationException();
    }

    public void visitBackward(PathValueVisitor<R, R_Any> visitor) {
        throw new UnsupportedOperationException();
    }
    
    public void visitForward(T_Any src, PathValueVisitor<R, R_Any> visitor) {
        final Iterator<N_Any> resultList = following(src);
        if(null != resultList) {
            for(final N_Any result : iterableOf(resultList)) {
                next().visitForward(result, visitor);
            }
        }
    }

    public void visitBackward(T_Any src, PathValueVisitor<R, R_Any> visitor) {
        throw new UnsupportedOperationException();
    }

    @Override public void visit(PathVisitor<R, R_Any> visitor) {
        throw new UnsupportedOperationException();
    }

    @Override public N_Any next(T_Any current) {
        final Iterator<N_Any> resultIterator = following(current);
        if(!resultIterator.hasNext()) {
            return null;
        }
        final N_Any result = resultIterator.next();
        if(resultIterator.hasNext()) {
            throw new UnsupportedOperationException();
        }
        return result;
    }

    @Override public R_Any value(T_Any src) {
        final Iterator<R_Any> resultIterator = values(src);
        if(!resultIterator.hasNext()) {
            return null;
        }
        final R_Any result = resultIterator.next();
        if(resultIterator.hasNext()) {
            throw new UnsupportedOperationException();
        }
        return result;
    }

    @Override public Iterator<N_Any> following(T_Any current) {
        return _selectNext.apply(current);
    }

    @Override public Iterator<R_Any> values(T_Any src) {
        // TODO Auto-generated method stub
        return super.values(src);
    }
    
    
    

}
