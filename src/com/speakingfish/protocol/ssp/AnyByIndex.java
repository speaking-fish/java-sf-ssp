package com.speakingfish.protocol.ssp;

public interface AnyByIndex<
    R, R_Any  extends Any<R>,
    T, T_Any  extends Any<T>,
    N, N_Any  extends Any<N>,
    T_SubPath extends AnyPath<
        R, R_Any,
        N, N_Any
    >
> extends AnyPathContinuation<R, R_Any, T, T_Any, N, N_Any, T_SubPath> {

    int index();

}
