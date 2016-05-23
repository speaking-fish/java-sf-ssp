package com.speakingfish.protocol.ssp.path;

import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyEndOfPath;
import com.speakingfish.protocol.ssp.AnyEndOfPathValue;
import com.speakingfish.protocol.ssp.PathValueVisitor;
import com.speakingfish.protocol.ssp.PathVisitor;

public class AnyEndOfPathImpl <
    T, T_Any extends Any<T>
>   extends AnyPathImpl<
    T, T_Any,
    T, T_Any
> implements AnyEndOfPathValue<T, T_Any> {
    
    public AnyEndOfPathImpl(T_Any value) {
        super(value);
    }

    @Override public void visit(PathVisitor<T, T_Any> visitor) {
        visitor.visitValue(this);
    }

    public T_Any value(T_Any src) {
        return src;
    }

    public void visitForward(T_Any src, PathValueVisitor<T, T_Any> visitor) {
        visitor.visitValue(this, src);
    }

    public void visitBackward(T_Any src, PathValueVisitor<T, T_Any> visitor) {
        visitor.visitValue(this, src);
    }

    public void visitForward(PathVisitor<T, T_Any> visitor) {
        visitor.visitValue(this);
    }

    public void visitBackward(PathVisitor<T, T_Any> visitor) {
        visitor.visitValue(this);
    }

    @Override public String toString() {
        return ".";
    }

    public void visitForward(PathValueVisitor<T, T_Any> visitor) {
        visitor.visitValue(this, get());
    }

    public void visitBackward(PathValueVisitor<T, T_Any> visitor) {
        visitor.visitValue(this, get());
    }
    
    

}
