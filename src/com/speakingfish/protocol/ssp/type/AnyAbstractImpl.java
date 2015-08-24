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

    @Override public abstract short        type           ();
    @Override public abstract int          size           ();
  //@Override public abstract String       toString       ();
    @Override public abstract byte[]       asUTF8String   ();
    @Override public abstract String       asString       ();
    @Override public abstract long         asInt          ();
    @Override public abstract byte         asInt8         ();
    @Override public abstract short        asInt16        ();
    @Override public abstract int          asInt32        ();
    @Override public abstract long         asInt64        ();
    @Override public abstract double       asFloat        ();
    @Override public abstract float        asFloat32      ();
    @Override public abstract double       asFloat64      ();
    @Override public abstract double       asDecimal      ();
    @Override public abstract String       asDecimalString();
    @Override public abstract byte[]       asByteArray    ();
    @Override public abstract Getter<TYPE> asHolder       ();
    @Override public abstract TYPE         get            ();

    @Override public abstract int    insert (int    index, Any<?> value);
    @Override public abstract int    add    (              Any<?> value);
    @Override public abstract int    add    (String  name, Any<?> value);
    @Override public abstract Any<?> update (int    index, Any<?> value);
    @Override public abstract Any<?> update (String  name, Any<?> value);
    @Override public abstract Any<?> remove (int    index              );
    @Override public abstract Any<?> remove (String  name              );
    @Override public abstract int    indexOf(String  name              );
    @Override public abstract String nameOf (int    index              );
    @Override public abstract Any<?> item   (int    index              );
    @Override public abstract Any<?> item   (String  name              );
    /*
    @Override public abstract void writeExternal(ObjectOutput out) throws IOException;
    @Override public abstract void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;
    */
    @Override public abstract void writeTo(OutputStream dest);
    //@Override public final void writeTo(OutputStream dest);
    
    @Override public abstract Any<TYPE> asUnmodifiable();
    
    @Override public abstract <T> int    add    (Named<T> name, Any<T> value);
    @Override public abstract <T> Any<T> update (Named<T> name, Any<T> value);
    @Override public abstract <T> Any<T> remove (Named<T> name              );
    @Override public abstract <T> Any<T> item   (Named<T> name              );
    @Override public abstract <T> T      value  (Named<T> name              );
    /*
    @Override public abstract <SUBCONTEXT, T> int                     add    (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value);
    @Override public abstract <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value);
    @Override public abstract <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    @Override public abstract <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    @Override public abstract <SUBCONTEXT, T> T                       value  (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              );
    */
    /*
    @SuppressWarnings("unchecked") @Override public <SUBCONTEXT, T> int                     add    (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value) { return                           this.<T>add    ((Named<T>) name, value); };
    @SuppressWarnings("unchecked") @Override public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name, Any<T> value) { return (LocalAny<SUBCONTEXT, T>) this.<T>update ((Named<T>) name, value); };
    @SuppressWarnings("unchecked") @Override public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              ) { return (LocalAny<SUBCONTEXT, T>) this.<T>remove ((Named<T>) name       ); };
    @SuppressWarnings("unchecked") @Override public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              ) { return (LocalAny<SUBCONTEXT, T>) this.<T>item   ((Named<T>) name       ); };
    @SuppressWarnings("unchecked") @Override public <SUBCONTEXT, T> T                       value  (LocalNamed<CONTEXT, LocalNamed<SUBCONTEXT, T>> name              ) { return                           this.<T>value  ((Named<T>) name       ); };
    */
                                   @Override public <            T> int                     add    (LocalNamed<CONTEXT, T> name, Any<T> value) { return                           this.<T>add    ((Named<T>) name, value); };
    @SuppressWarnings("unchecked") @Override public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> update (LocalNamed<CONTEXT, T> name, Any<T> value) { return (LocalAny<SUBCONTEXT, T>) this.<T>update ((Named<T>) name, value); };
    @SuppressWarnings("unchecked") @Override public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> remove (LocalNamed<CONTEXT, T> name              ) { return (LocalAny<SUBCONTEXT, T>) this.<T>remove ((Named<T>) name       ); };
    @SuppressWarnings("unchecked") @Override public <SUBCONTEXT, T> LocalAny<SUBCONTEXT, T> item   (LocalNamed<CONTEXT, T> name              ) { return (LocalAny<SUBCONTEXT, T>) this.<T>item   ((Named<T>) name       ); };
                                   @Override public <            T> T                       value  (LocalNamed<CONTEXT, T> name              ) { return                           this.<T>value  ((Named<T>) name       ); };
    
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
    
    @Override public abstract Entry<String, Any<?>> entry(int    index);
    @Override public abstract Entry<String, Any<?>> entry(String name );
    
    @Override public abstract List<              Any<?> > values ();
    @Override public abstract List<Entry<String, Any<?>>> entries();
}