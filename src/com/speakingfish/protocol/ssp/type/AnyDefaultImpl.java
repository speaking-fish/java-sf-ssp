package com.speakingfish.protocol.ssp.type;

import java.io.ByteArrayOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;

import com.speakingfish.common.function.Getter;
import com.speakingfish.common.type.Named;
import com.speakingfish.protocol.ssp.Any;

public abstract class AnyDefaultImpl<CONTEXT, TYPE> extends AnyAbstractImpl<CONTEXT, TYPE> implements Serializable, Cloneable {
    
  //@Override public short        type           () { throw new UnsupportedOperationException(); };
    @Override public int          size           () { throw new UnsupportedOperationException(); };
  //@Override public String       toString       () { throw new UnsupportedOperationException(); };
    @Override public byte[]       asUTF8String   () { throw new UnsupportedOperationException(); };
    @Override public String       asString       () { throw new UnsupportedOperationException(); };
    @Override public long         asInt          () { throw new UnsupportedOperationException(); };
    @Override public byte         asInt8         () { throw new UnsupportedOperationException(); };
    @Override public short        asInt16        () { throw new UnsupportedOperationException(); };
    @Override public int          asInt32        () { throw new UnsupportedOperationException(); };
    @Override public long         asInt64        () { throw new UnsupportedOperationException(); };
    @Override public double       asFloat        () { throw new UnsupportedOperationException(); };
    @Override public float        asFloat32      () { throw new UnsupportedOperationException(); };
    @Override public double       asFloat64      () { throw new UnsupportedOperationException(); };
    @Override public double       asDecimal      () { throw new UnsupportedOperationException(); };
    @Override public String       asDecimalString() { throw new UnsupportedOperationException(); };
    @Override public byte[]       asByteArray    () { throw new UnsupportedOperationException(); };
    @Override public Getter<TYPE> asHolder       () { throw new UnsupportedOperationException(); };

    @Override public TYPE get() { throw new UnsupportedOperationException(); };
    
    @Override public int    insert (int    index, Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public int    add    (              Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public int    add    (String  name, Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public Any<?> update (int    index, Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public Any<?> update (String  name, Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public Any<?> remove (int    index              ) { throw new UnsupportedOperationException(); };
    @Override public Any<?> remove (String  name              ) { throw new UnsupportedOperationException(); };
    @Override public int    indexOf(String  name              ) { throw new UnsupportedOperationException(); };
    @Override public String nameOf (int    index              ) { throw new UnsupportedOperationException(); };
    @Override public Any<?> item   (int    index              ) { throw new UnsupportedOperationException(); };
    @Override public Any<?> item   (String  name              ) { throw new UnsupportedOperationException(); };
    
    @Override public <T> int    add    (Named<T> name, Any<T> value) { throw new UnsupportedOperationException(); };
    @Override public <T> Any<T> update (Named<T> name, Any<T> value) { throw new UnsupportedOperationException(); };
    @Override public <T> Any<T> remove (Named<T> name              ) { throw new UnsupportedOperationException(); };
    @Override public <T> Any<T> item   (Named<T> name              ) { throw new UnsupportedOperationException(); };
    @Override public <T> T      value  (Named<T> name              ) { throw new UnsupportedOperationException(); };

    @Override public Entry<String, Any<?>> entry(int    index) { throw new UnsupportedOperationException(); };
    @Override public Entry<String, Any<?>> entry(String name ) { throw new UnsupportedOperationException(); };
    
    @Override public List<              Any<?> > values () { throw new UnsupportedOperationException(); };
    @Override public List<Entry<String, Any<?>>> entries() { throw new UnsupportedOperationException(); };
    
    protected Object writeReplace() throws ObjectStreamException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeTo(out);
        return new SerializableHolder(out.toByteArray());
    }
}