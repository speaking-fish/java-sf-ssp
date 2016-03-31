package com.speakingfish.protocol.ssp;

import java.util.List;
import java.util.Map.Entry;

import com.speakingfish.common.function.Getter;
import com.speakingfish.common.type.Named;

public interface AnyArray extends Any<AnyArray> {
    int          size           ();
    int    insert (int    index, Any<?> value);
    int    add    (              Any<?> value);
    Any<?> update (int    index, Any<?> value);
    Any<?> remove (int    index              );
  //String nameOf (int    index              );
    Any<?> item   (int    index              );
    
    List<              Any<?> > values ();
    
    @Deprecated byte[]           asUTF8String   ();
    @Deprecated String           asString       ();
    @Deprecated long             asInt          ();
    @Deprecated byte             asInt8         ();
    @Deprecated short            asInt16        ();
    @Deprecated int              asInt32        ();
    @Deprecated long             asInt64        ();
    @Deprecated double           asFloat        ();
    @Deprecated float            asFloat32      ();
    @Deprecated double           asFloat64      ();
    @Deprecated double           asDecimal      ();
    @Deprecated String           asDecimalString();
    @Deprecated byte[]           asByteArray    ();
    @Deprecated Getter<AnyArray> asHolder       ();

    @Deprecated int    add    (String  name, Any<?> value);
    @Deprecated Any<?> update (String  name, Any<?> value);
    @Deprecated Any<?> remove (String  name              );
    @Deprecated int    indexOf(String  name              );
    @Deprecated String nameOf (int    index              );
    @Deprecated Any<?> item   (String  name              );
    
    @Deprecated <T> int    add    (Named<T> name, Any<T> value);
    @Deprecated <T> Any<T> update (Named<T> name, Any<T> value);
    @Deprecated <T> Any<T> remove (Named<T> name              );
    @Deprecated <T> Any<T> item   (Named<T> name              );
    @Deprecated <T> T      value  (Named<T> name              );
    
    @Deprecated Entry<String, Any<?>> entry(int    index);
    @Deprecated Entry<String, Any<?>> entry(String name );
    
    @Deprecated List<Entry<String, Any<?>>> entries();
}