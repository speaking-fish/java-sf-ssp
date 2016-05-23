package com.speakingfish.protocol.ssp;

public interface AnyByNameValue<
    R, R_Any  extends Any<R>,
    T, T_Any  extends Any<T>,
    N, N_Any  extends Any<N>,
    T_SubPath extends AnyPathValue<
        R, R_Any,
        N, N_Any
    >
> extends AnyPathContinuationValue<
    R, R_Any,
    T, T_Any,
    N, N_Any,
    T_SubPath
>, AnyByName<
    R, R_Any,
    T, T_Any,
    N, N_Any,
    T_SubPath
> {
    
    String name();
    
}
