package com.speakingfish.protocol.ssp.path;

import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyByNameValue;
import com.speakingfish.protocol.ssp.AnyPath;
import com.speakingfish.protocol.ssp.AnyPathValue;
import com.speakingfish.protocol.ssp.PathValueVisitor;
import com.speakingfish.protocol.ssp.PathVisitor;

public class AnyByNameImpl<
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
> implements AnyByNameValue<
    R, R_Any ,
    T, T_Any ,
    N, N_Any ,
    T_SubPath
> {
    
    protected final String _name;

    public AnyByNameImpl(
        T_Any     value,
        String    name,
        T_SubPath next
    ) {
        super(value, next);
        _name = name;
    }
    
    public String name() { return _name; }

    @SuppressWarnings("unchecked")
    @Override public N_Any next(T_Any current) { return (null == current) ? null : (N_Any) current.item(name()); }

    @Override public void visit(PathVisitor<R, R_Any> visitor) {
        visitor.visitField(this, name(), next());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void visitForward(T_Any src, PathValueVisitor<R, R_Any> visitor) {
        visitor.visitField(this, src, name(), (AnyPath) next());
        if(null != src) {
            next().visitForward(next(src), visitor);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void visitBackward(T_Any src, PathValueVisitor<R, R_Any> visitor) {
        if(null != src) {
            next().visitBackward(next(src), visitor);
        }
        visitor.visitField(this, src, name(), (AnyPath) next());
    }

    @Override public String toString() {
        return "." + name() + next();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void visitForward(PathValueVisitor<R, R_Any> visitor) {
        visitor.visitField(this, get(), name(), (AnyPath) next());
        ((AnyPathValue) next()).visitForward(visitor);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void visitBackward(PathValueVisitor<R, R_Any> visitor) {
        ((AnyPathValue) next()).visitBackward(visitor);
        visitor.visitField(this, get(), name(), (AnyPath) next());
    }
    
}
