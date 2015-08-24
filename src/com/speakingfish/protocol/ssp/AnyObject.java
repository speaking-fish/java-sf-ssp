package com.speakingfish.protocol.ssp;

import com.speakingfish.common.type.Named;

public interface AnyObject extends Any<AnyObject> {
    int          size           ();

    String nameOf (int    index              );
    
    int    add    (String  name, Any<?> value);
    Any<?> update (String  name, Any<?> value);
    Any<?> remove (String  name              );
    int    indexOf(String  name              );
    Any<?> item   (String  name              );
    
    <T> int    add    (Named<T> name, Any<T> value);
    <T> Any<T> update (Named<T> name, Any<T> value);
    <T> Any<T> remove (Named<T> name              );
    <T> Any<T> item   (Named<T> name              );
    <T> T      value  (Named<T> name              );
    
}