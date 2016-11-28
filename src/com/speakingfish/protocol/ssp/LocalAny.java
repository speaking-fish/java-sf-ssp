package com.speakingfish.protocol.ssp;

import com.speakingfish.common.type.LocalNamed;
import com.speakingfish.common.type.Named;

public interface LocalAny<CONTEXT, TYPE> extends Any<TYPE> {
/*
    <SUBCONTEXT, T> int                     add    (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value);
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value);
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    <SUBCONTEXT, T> T                       value  (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
*/
    @Deprecated <T> int    add    (Named<T> name, Any<T> value);
    @Deprecated <T> Any<T> update (Named<T> name, Any<T> value);
    @Deprecated <T> Any<T> remove (Named<T> name              );
    @Deprecated <T> Any<T> item   (Named<T> name              );
    @Deprecated <T> T      value  (Named<T> name              );
    
    <            T> int                     add    (LocalNamed<CONTEXT, T> name, Any<T> value);
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, T> name, Any<T> value);
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, T> name              );
    <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, T> name              );
    <            T> T                       value  (LocalNamed<CONTEXT, T> name              );

    LocalAny<CONTEXT, TYPE> clone();
    LocalAny<CONTEXT, TYPE> cloneUnmodifiable();
    
}
