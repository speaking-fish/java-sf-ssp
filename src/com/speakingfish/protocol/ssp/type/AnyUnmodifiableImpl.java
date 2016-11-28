package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.speakingfish.common.Maps;
import com.speakingfish.common.function.Getter;
import com.speakingfish.common.mapper.Mappers;
/*
import com.speakingfish.common.traverse.Proceed;
import com.speakingfish.common.traverse.VisitorHolder;
*/
import com.speakingfish.common.type.Named;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.Helper;
import com.speakingfish.protocol.ssp.LocalAny;
//import com.speakingfish.protocol.ssp.ValueVisitor;



import static java.util.Collections.*;
import static com.speakingfish.common.collection.CollectionHelper.*;
import static com.speakingfish.common.iterator.Iterators.*;
import static com.speakingfish.protocol.ssp.Helper.*;
import static com.speakingfish.protocol.ssp.Types.*;

public class AnyUnmodifiableImpl<CONTEXT, TYPE> extends AnyAbstractImpl<CONTEXT, TYPE> {
    
    protected final LocalAny<CONTEXT, TYPE> _origin;
    
    protected LocalAny<CONTEXT, TYPE> _unmodifiableClone;
    
    public AnyUnmodifiableImpl(LocalAny<CONTEXT, TYPE> origin, boolean isUnmodifiableClone) {
        super();
        _origin            = origin           ;
        _unmodifiableClone = isUnmodifiableClone ? this : null;
    }
    
    @Override public short        type           () { return _origin.type           (); };
    @Override public int          size           () { return _origin.size           (); };
    @Override public String       toString       () { return _origin.toString       (); };
    @Override public byte[]       asUTF8String   () { return _origin.asUTF8String   (); };
    @Override public String       asString       () { return _origin.asString       (); };
    @Override public long         asInt          () { return _origin.asInt          (); };
    @Override public byte         asInt8         () { return _origin.asInt8         (); };
    @Override public short        asInt16        () { return _origin.asInt16        (); };
    @Override public int          asInt32        () { return _origin.asInt32        (); };
    @Override public long         asInt64        () { return _origin.asInt64        (); };
    @Override public double       asFloat        () { return _origin.asFloat        (); };
    @Override public float        asFloat32      () { return _origin.asFloat32      (); };
    @Override public double       asFloat64      () { return _origin.asFloat64      (); };
    @Override public double       asDecimal      () { return _origin.asDecimal      (); };
    @Override public String       asDecimalString() { return _origin.asDecimalString(); };
    @Override public byte[]       asByteArray    () { return _origin.asByteArray    (); };
    @Override public Getter<TYPE> asHolder       () { return _origin.asHolder       (); };
    @Override public TYPE         get            () { return _origin.get            (); };
    
    @Override public int    insert (int    index, Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public int    add    (              Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public int    add    (String  name, Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public Any<?> update (int    index, Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public Any<?> update (String  name, Any<?> value) { throw new UnsupportedOperationException(); };
    @Override public Any<?> remove (int    index              ) { throw new UnsupportedOperationException(); };
    @Override public Any<?> remove (String  name              ) { throw new UnsupportedOperationException(); };
    @Override public int    indexOf(String  name              ) { return                       _origin.indexOf(name ) ; };
    @Override public String nameOf (int    index              ) { return                       _origin.nameOf (index) ; };
    @Override public Any<?> item   (int    index              ) { return Helper.asUnmodifiable(_origin.item   (index)); };
    @Override public Any<?> item   (String  name              ) { return Helper.asUnmodifiable(_origin.item   (name )); };
    
    @Override public <T> int    add    (Named<T> name, Any<T> value) { throw new UnsupportedOperationException(); };
    @Override public <T> Any<T> update (Named<T> name, Any<T> value) { throw new UnsupportedOperationException(); };
    @Override public <T> Any<T> remove (Named<T> name              ) { throw new UnsupportedOperationException(); };
    @Override public <T> Any<T> item   (Named<T> name              ) { return         Helper.asUnmodifiable(_origin.item(name)) ; };
    @Override public <T> T      value  (Named<T> name              ) { return valueOf(Helper.asUnmodifiable(_origin.item(name))); };
    @Deprecated
    @Override public void writeTo(OutputStream dest) { _origin.writeTo(dest); }
    
    @Override public Any<TYPE> asUnmodifiable() { return this; }

    @Override public int hashCode() { return _origin.hashCode(); }

    @Override public boolean equals(Object obj) {
        return _origin.equals(obj);
    }

    @Override public LocalAny<CONTEXT, TYPE> clone() {
        return _origin.clone();
    }
    
    @Override public LocalAny<CONTEXT, TYPE> cloneUnmodifiable() {
        
        if(null == _unmodifiableClone) {
            return _origin.cloneUnmodifiable();
        } else {
            return _unmodifiableClone;
        }
        /*
        return _origin.clone();
        */
    }
    
    /*
    @Override public void visit(TypeVisitor visitor) {
        _origin.visit(visitor);
    }
    */

    //@Override public <DEST> DEST visit(ValueVisitor<DEST, Any<TYPE>> visitor) {
    //    return _origin.visit(visitor);
    //}
    
    //@Override public <DEST, SOURCE> DEST visit(SOURCE src, ValueVisitor<DEST, SOURCE> visitor) {
    //    return _origin.visit(src, visitor);
    //}

    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, Any<?> value) {
        ((AnyAbstractImpl<CONTEXT, TYPE>) _origin)._visit(proceed, value);
    }
    */
    
    @Override public Entry<String, Any<?>> entry(int    index) { return Maps.<String, Any<?>>keyValue(nameOf(index), item(index)); };
    @Override public Entry<String, Any<?>> entry(String name ) { return Maps.<String, Any<?>>keyValue(       name  , item(name )); };
    
    @Override public List<              Any<?> > values () { return unmodifiableList(collect(new ArrayList<              Any<?> >(_origin.values ().size()), mapIterator(_origin.values ().iterator(),                                                  asUnmodifiableMapper() ))); };
    @Override public List<Entry<String, Any<?>>> entries() { return unmodifiableList(collect(new ArrayList<Entry<String, Any<?>>>(_origin.entries().size()), mapIterator(_origin.entries().iterator(), Mappers.<String, Any<?>, Any<?>>mapperEntryValue(asUnmodifiableMapper())))); };
    //TODO proxy list
    //@Override public List<              Any<?> > values () { return unmodifiableList(_origin.values()); };
    //@Override public List<Entry<String, Any<?>>> entries() { return unmodifiableList(_origin.envalues()); };
}