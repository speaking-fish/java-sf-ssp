package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

//import com.speakingfish.common.function.Invoker;
/*
import com.speakingfish.common.traverse.Proceed;
import com.speakingfish.common.traverse.VisitorHolder;
*/
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyArray;
import com.speakingfish.protocol.ssp.Helper;
import com.speakingfish.protocol.ssp.LocalAny;
import com.speakingfish.protocol.ssp.LocalAnyArray;
import com.speakingfish.protocol.ssp.Types;
//import com.speakingfish.protocol.ssp.ValueVisitor;

import static com.speakingfish.common.iterator.Iterators.*;
/*
import static com.speakingfish.common.traverse.Traverses.*;
*/
import static com.speakingfish.protocol.ssp.bin.Helper.*;

public class ArrayImpl<CONTEXT> extends AnyDefaultImpl<CONTEXT, AnyArray> implements LocalAnyArray<CONTEXT> {
    
    public static ArrayImpl<Object> createOwnList(List<Any<?>> value) {
        return new ArrayImpl<Object>(value);
    }
    
    protected final List<Any<?>> _value;
    
    protected ArrayImpl(List<Any<?>> value) {
        super();
        _value = value;
    }

    public ArrayImpl() { this(new ArrayList<Any<?>>()); }

    public void addAll(Collection<Any<?>> values) {
        _value.addAll(values);
    }
    
    public void addAll(Iterator<Any<?>> values) {
        for(Any<?> value : iterableOf(values)) {
            _value.add(value);
        }
    }
    
  //@Override public AnyArray get          () { return this; };
    @Override public LocalAnyArray<CONTEXT> get          () { return this; };
    @Override public short  type           () { return Helper.SSP_TYPE_ARRAY; };
    @Override public int    size           () { return _value.size    (); };
    @Override public String toString       () { return _value.toString(); }
    
    @Override public int    add    (              Any<?> value) {
        _value.add(value);
        return _value.size() - 1;
    };
    
    @Override public int    insert (int    index, Any<?> value) {
        _value.add(index, value);
        return index;
    };
    
    @Override public Any<?> update (int    index, Any<?> value) { return _value.set   (index,  value); };
    @Override public Any<?> remove (int    index              ) { return _value.remove(index); };
    @Override public Any<?> item   (int    index              ) { return _value.get   (index); };
    
    @Override public void writeTo(OutputStream dest) {
        writeArray(dest, Types.serializableSize(this));
        for(int i = 0; i < size(); ++i) {
            if(Types.isSerializable(item(i))) {
                item(i).writeTo(dest);
            }
        }
    }
    
    @Override public int hashCode() { return _value.hashCode(); }

    @Override public boolean equals(Object obj) {
        if(this == obj)
            return true;
        
        if(obj == null)
            return false;
        
        if(!(obj instanceof Any))
            return false;
        
        final Any<?> other = (Any<?>) obj;
        
        if(type() != other.type())
            return false;

        if(size() != other.size())
            return false;

        for(int i = 0; i < size(); ++i) {
            if(!item(i).equals(other.item(i)))
                return false;
        }
        return true;
    }

    @Override protected Any<AnyArray> makeUnmodifiable() {
        return new AnyUnmodifiableArrayImpl<CONTEXT>(this);
    }

    @Override public LocalAny<CONTEXT, AnyArray> clone() {
        return new ArrayImpl<CONTEXT>(new ArrayList<Any<?>>(_value));
    }

    /*
    @Override public void visit(TypeVisitor visitor) {
        visitor.visitArray(this);
    }
    */
    /*
    @Override public <DEST> DEST visit(ValueVisitor<DEST, Any<AnyArray>> visitor) {
        return visitor.visitArray(this, get());
    }
    */
  
    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, final Any<?> arrayValue) {
        proceed.visitor().visitArray(
            nestedInvoker(proceed, new Invoker<PROCEED>() {
                @Override public void invoke(PROCEED proceed) {
                    for(int i = 0; i < arrayValue.size(); ++i) {
                        final Any<?> arrayItem = arrayValue.item(i);
                        proceed.visitor().visitArrayItem(
                            nestedInvoker(proceed, new Invoker<PROCEED>() {
                                @Override public void invoke(PROCEED proceed) {
                                    arrayItem.visit(nestedUnknown(proceed));
                                }}
                                ),
                            i,
                            arrayItem
                            );
                    }
                }}
                ),
            arrayValue
            );
    }
    */

    @Override public List<Any<?> > values () { 
        return new AbstractList<Any<?>>() {
            @Override public int    size  (                         ) { return ArrayImpl.this.size  (              ); }
            @Override public Any<?> get   (int index                ) { return ArrayImpl.this.item  (index         ); }
            @Override public Any<?> remove(int index                ) { return ArrayImpl.this.remove(index         ); }
            @Override public void   add   (int index, Any<?> element) {        ArrayImpl.this.insert(index, element); }
            @Override public Any<?> set   (int index, Any<?> element) { return ArrayImpl.this.update(index, element); }
        };
    };

}