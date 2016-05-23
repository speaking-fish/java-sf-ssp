package com.speakingfish.protocol.ssp;

public interface AnyPath<
    R, R_Any  extends Any<R>,
    T, T_Any  extends Any<T>/*,
    N, N_Any  extends Any<N>,
    T_SubPath extends AnyPath<
        R, R_Any,
        N, N_Any,
        ?, ?,
        ?>*/
> {

    R_Any value(T_Any src);

    void visit(PathVisitor<R, R_Any> visitor);
    
    void visitForward (PathVisitor<R, R_Any> visitor);
    void visitBackward(PathVisitor<R, R_Any> visitor);
    
    void visitForward (T_Any src, PathValueVisitor<R, R_Any> visitor);
    void visitBackward(T_Any src, PathValueVisitor<R, R_Any> visitor);
}
