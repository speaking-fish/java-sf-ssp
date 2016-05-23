package com.speakingfish.protocol.ssp;

public interface AnyEndOfPathValue<
    T, T_Any extends Any<T>
> extends AnyPathValue<
    T, T_Any, T, T_Any
>, AnyEndOfPath<
    T, T_Any
> {

}
