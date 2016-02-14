package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;
import java.util.List;
import java.util.Map.Entry;

import com.speakingfish.common.function.Getter;
/*
import com.speakingfish.common.traverse.Proceed;
import com.speakingfish.common.traverse.VisitorHolder;
*/
import com.speakingfish.common.type.LocalNamed;
import com.speakingfish.common.type.Named;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.LocalAny;
//import com.speakingfish.protocol.ssp.ValueVisitor;

public abstract class AnyAbstractImpl<CONTEXT, TYPE> implements LocalAny<CONTEXT, TYPE> {

              public abstract short        type           ();
              public abstract int          size           ();
  //@Override public abstract String       toString       ();
              public abstract byte[]       asUTF8String   ();
              public abstract String       asString       ();
              public abstract long         asInt          ();
              public abstract byte         asInt8         ();
              public abstract short        asInt16        ();
              public abstract int          asInt32        ();
              public abstract long         asInt64        ();
              public abstract double       asFloat        ();
              public abstract float        asFloat32      ();
              public abstract double       asFloat64      ();
              public abstract double       asDecimal      ();
              public abstract String       asDecimalString();
              public abstract byte[]       asByteArray    ();
              public abstract Getter<TYPE> asHolder       ();
              public abstract TYPE         get            ();
             
              public abstract int    insert (int    index, Any<?> value);
              public abstract int    add    (              Any<?> value);
              public abstract int    add    (String  name, Any<?> value);
              public abstract Any<?> update (int    index, Any<?> value);
              public abstract Any<?> update (String  name, Any<?> value);
              public abstract Any<?> remove (int    index              );
              public abstract Any<?> remove (String  name              );
              public abstract int    indexOf(String  name              );
              public abstract String nameOf (int    index              );
              public abstract Any<?> item   (int    index              );
              public abstract Any<?> item   (String  name              );
    /*
    public abstract void writeExternal(ObjectOutput out) throws IOException;
    public abstract void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;
    */
    public abstract void writeTo(OutputStream dest);
    //public final void writeTo(OutputStream dest);
    
    public abstract Any<TYPE> asUnmodifiable();
    
    public abstract <T> int    add    (Named<T> name, Any<T> value);
    public abstract <T> Any<T> update (Named<T> name, Any<T> value);
    public abstract <T> Any<T> remove (Named<T> name              );
    public abstract <T> Any<T> item   (Named<T> name              );
    public abstract <T> T      value  (Named<T> name              );
    /*
    public abstract <SUBCONTEXT, T> int                     add    (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value);
    public abstract <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value);
    public abstract <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    public abstract <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    public abstract <SUBCONTEXT, T> T                       value  (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    */
    /*
    @SuppressWarnings("unchecked") public <SUBCONTEXT, T> int                     add    (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value) { return                           this.<T>add    ((Named<T>) name, value); };
    @SuppressWarnings("unchecked") public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value) { return (LocalAny<SUBCONTEXT, T>) this.<T>update ((Named<T>) name, value); };
    @SuppressWarnings("unchecked") public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              ) { return (LocalAny<SUBCONTEXT, T>) this.<T>remove ((Named<T>) name       ); };
    @SuppressWarnings("unchecked") public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              ) { return (LocalAny<SUBCONTEXT, T>) this.<T>item   ((Named<T>) name       ); };
    @SuppressWarnings("unchecked") public <SUBCONTEXT, T> T                       value  (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              ) { return                           this.<T>value  ((Named<T>) name       ); };
    */
                                   public <            T> int                     add    (LocalNamed<CONTEXT, T> name, Any<T> value) { return                           this.<T>add    ((Named<T>) name, value); };
    @SuppressWarnings("unchecked") public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, T> name, Any<T> value) { return (LocalAny<SUBCONTEXT, T>) this.<T>update ((Named<T>) name, value); };
    @SuppressWarnings("unchecked") public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, T> name              ) { return (LocalAny<SUBCONTEXT, T>) this.<T>remove ((Named<T>) name       ); };
    @SuppressWarnings("unchecked") public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, T> name              ) { return (LocalAny<SUBCONTEXT, T>) this.<T>item   ((Named<T>) name       ); };
                                   public <            T> T                       value  (LocalNamed<CONTEXT, T> name              ) { return                           this.<T>value  ((Named<T>) name       ); };
    
    @Override public abstract LocalAny<CONTEXT, TYPE> clone();
    
    //@Override public abstract void visit(TypeVisitor visitor);
    //@Override public abstract <DEST> DEST visit(ValueVisitor<DEST, Any<TYPE>> visitor);
    //@Override public abstract <DEST, SOURCE> DEST visit(SOURCE src, ValueVisitor<DEST, SOURCE> visitor);
    /*
    @Override public final <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void visit(PROCEED proceed) {
        _visit(proceed, this);
    }
    
    protected abstract <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, Any<?> value);
    */
    
    public abstract Entry<String, Any<?>> entry(int    index);
    public abstract Entry<String, Any<?>> entry(String name );
    
    public abstract List<              Any<?> > values ();
    public abstract List<Entry<String, Any<?>>> entries();
}