package com.speakingfish.protocol.ssp;

public interface PathVisitor<R, R_Any extends Any<R>> {
    
    <
        T, T_Any extends Any<T>,
        N, N_Any extends Any<N>
    > void visitIndex(
        AnyPath<R, R_Any, T, T_Any> path ,
        int                         index,
        AnyPath<R, R_Any, N, N_Any> next
    );
    
    <
    T, T_Any extends Any<T>,
    N, N_Any extends Any<N>
    > void visitField(
        AnyPath<R, R_Any, T, T_Any> path,
        String                      name,
        AnyPath<R, R_Any, N, N_Any> next
    );
    
    void visitValue(
        AnyPath<R, R_Any, R, R_Any> path
    );

}
