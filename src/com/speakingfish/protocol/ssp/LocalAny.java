package com.speakingfish.protocol.ssp;

import com.speakingfish.common.type.LocalNamed;

public interface LocalAny<CONTEXT, TYPE> extends Any<TYPE> {
/*
    <SUBCONTEXT, T> int                     add    (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value);
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value);
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    <SUBCONTEXT, T> T                       value  (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
*/
    <            T> int                     add    (LocalNamed<CONTEXT, T> name, Any<T> value);
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, T> name, Any<T> value);
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, T> name              );
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, T> name              );
    <            T> T                       value  (LocalNamed<CONTEXT, T> name              );

    LocalAny<CONTEXT, TYPE> clone();
}
