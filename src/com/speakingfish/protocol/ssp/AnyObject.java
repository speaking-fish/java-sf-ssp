package com.speakingfish.protocol.ssp;

import java.io.OutputStream;
import java.util.List;
import java.util.Map.Entry;

import com.speakingfish.common.function.Getter;
import com.speakingfish.common.type.Named;

public interface AnyObject extends AnyComplex<AnyObject> { // Any<AnyObject> {
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

    
    @Deprecated byte[]            asUTF8String   ();
    @Deprecated String            asString       ();
    @Deprecated long              asInt          ();
    @Deprecated byte              asInt8         ();
    @Deprecated short             asInt16        ();
    @Deprecated int               asInt32        ();
    @Deprecated long              asInt64        ();
    @Deprecated double            asFloat        ();
    @Deprecated float             asFloat32      ();
    @Deprecated double            asFloat64      ();
    @Deprecated double            asDecimal      ();
    @Deprecated String            asDecimalString();
    @Deprecated byte[]            asByteArray    ();
    @Deprecated Getter<AnyObject> asHolder       ();

    @Deprecated int    add    (              Any<?> value);
    
    Entry<String, Any<?>> entry(int    index);
    Entry<String, Any<?>> entry(String name );
    
    List<              Any<?> > values ();
    List<Entry<String, Any<?>>> entries();
}