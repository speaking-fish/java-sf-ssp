package com.speakingfish.protocol.ssp;

public interface AnyPathContinuationValue<
    R, R_Any  extends Any<R>,
    T, T_Any  extends Any<T>,
    N, N_Any  extends Any<N>,
    T_SubPath extends AnyPathValue<
        R, R_Any,
        N, N_Any
    >
> extends AnyPathValue <
    R, R_Any,
    T, T_Any
>, AnyPathContinuation<
    R, R_Any ,
    T, T_Any ,
    N, N_Any ,
    T_SubPath
>{
    
    T_SubPath next();
    N_Any     next(T_Any current);

}
