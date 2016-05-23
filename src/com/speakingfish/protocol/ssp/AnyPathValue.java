package com.speakingfish.protocol.ssp;

import com.speakingfish.common.function.Getter;

public interface AnyPathValue<
    R, R_Any extends Any<R>,
    T, T_Any extends Any<T>
> extends AnyPath<R, R_Any, T, T_Any>, Getter<T_Any> {

    void visitForward (PathValueVisitor<R, R_Any> visitor);
    void visitBackward(PathValueVisitor<R, R_Any> visitor);

}
