package com.speakingfish.protocol.ssp.path;

import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyByIndexValue;
import com.speakingfish.protocol.ssp.AnyPath;
import com.speakingfish.protocol.ssp.AnyPathValue;
import com.speakingfish.protocol.ssp.PathValueVisitor;
import com.speakingfish.protocol.ssp.PathVisitor;

public class AnyByIndexImpl<
    R, R_Any  extends Any<R>,
    T, T_Any  extends Any<T>,
    N, N_Any  extends Any<N>,
    T_SubPath extends AnyPathValue<
        R, R_Any,
        N, N_Any
    >
> extends AnyPathContinuationImpl<
    R, R_Any ,
    T, T_Any ,
    N, N_Any ,
    T_SubPath
> implements AnyByIndexValue<
    R, R_Any ,
    T, T_Any ,
    N, N_Any ,
    T_SubPath
> {

    protected final int _index;
    
    public AnyByIndexImpl(
        T_Any     value,
        int       index,
        T_SubPath next
    ) {
        super(value, next);
        _index = index;
    }
    
    public int index() { return _index; }
    
    @SuppressWarnings("unchecked")
    @Override public N_Any next(T_Any current) { return (null == current) ? null : (N_Any) current.item(index()); }
    
    @Override public void visit(PathVisitor<R, R_Any> visitor) {
        visitor.visitIndex(this, index(), next());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void visitForward(T_Any src, PathValueVisitor<R, R_Any> visitor) {
        visitor.visitIndex(this, src, index(), (AnyPath) next());
        if(null != src) {
            next().visitForward(next(src), visitor);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void visitBackward(T_Any src, PathValueVisitor<R, R_Any> visitor) {
        if(null != src) {
            next().visitBackward(next(src), visitor);
        }
        visitor.visitIndex(this, src, index(), (AnyPath) next());
    }

    @Override public String toString() {
        return "%" + index() + next();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void visitForward(PathValueVisitor<R, R_Any> visitor) {
        visitor.visitIndex(this, get(), index(), (AnyPath) next());
        ((AnyPathValue) next()).visitForward(visitor);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void visitBackward(PathValueVisitor<R, R_Any> visitor) {
        ((AnyPathValue) next()).visitBackward(visitor);
        visitor.visitIndex(this, get(), index(), (AnyPath) next());
    }
    
}