package com.speakingfish.protocol.ssp;

public interface PathValueVisitor<R, R_Any extends Any<R>> {
    
    <
        T, T_Any extends Any<T>,
        T_SubPath extends AnyPath<
            R, R_Any,
            T, T_Any
        >
    > void visitIndex(
        AnyPath<R, R_Any, T, T_Any> path,
        T_Any     value,
        int       index,
        T_SubPath next
    );
    
    <
        T, T_Any extends Any<T>,
        T_SubPath extends AnyPath<
            R, R_Any,
            T, T_Any
        >
    > void visitField(
        AnyPath<R, R_Any, T, T_Any> path,
        T_Any     value,
        String    name,
        T_SubPath next
    );
    
    <
        T, T_Any extends Any<T>
    > void visitValue(
        AnyPath<R, R_Any, T, T_Any> path,
        T_Any     value
    );

}
