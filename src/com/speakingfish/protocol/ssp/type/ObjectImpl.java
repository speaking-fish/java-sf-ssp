package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.speakingfish.common.Maps;
//import com.speakingfish.common.function.Invoker;
/*
import com.speakingfish.common.traverse.Proceed;
import com.speakingfish.common.traverse.VisitorHolder;
*/
import com.speakingfish.common.type.LocalNamed;
import com.speakingfish.common.type.Named;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.AnyObject;
import com.speakingfish.protocol.ssp.Helper;
import com.speakingfish.protocol.ssp.LocalAny;
import com.speakingfish.protocol.ssp.LocalAnyObject;
import com.speakingfish.protocol.ssp.Types;
//import com.speakingfish.protocol.ssp.ValueVisitor;



/*
import static com.speakingfish.common.traverse.Traverses.*;
*/
import static com.speakingfish.protocol.ssp.bin.Helper.*;


@SuppressWarnings("serial")
public class ObjectImpl<CONTEXT> extends AnyDefaultMutableImpl<CONTEXT, AnyObject> implements LocalAnyObject<CONTEXT> {
    
    protected final List<String> _names;
    protected final List<Any<?>> _value;
    
    protected AbstractList<              Any<?> > _valuesList ;
    protected AbstractList<Entry<String, Any<?>>> _entriesList; 
            
    protected ObjectImpl(
        List<String> names,
        List<Any<?>> value
    ) {
        super();
        _names = names;
        _value = value;
        assert _names.size() == _value.size();
    }
    
    public ObjectImpl() {
        this(
            new ArrayList<String>(),
            new ArrayList<Any<?>>()
            );
    }

    public void addStringEntries(Iterable<Entry<String, ? extends Any<?>>> items) {
        for(final Entry<String, ? extends Any<?>> item : items) {
            add(item.getKey(), item.getValue());
        }
    }
    
    public void addNamedEntries(Iterable<? extends Entry<? extends Named<?>, ? extends Any<?>>> items) {
        for(final Entry<? extends Named<?>, ? extends Any<?>> item : items) {
            add(item.getKey().id(), item.getValue());
        }
    }
    
    public void addLocalEntries(Iterable<Entry<? extends LocalNamed<CONTEXT, ?>, ? extends LocalAny<?, ?>>> items) {
        for(final Entry<? extends Named<?>, ? extends Any<?>> item : items) {
            add(item.getKey().id(), item.getValue());
        }
    }
    
    //@Override public AnyObject get         () { return this; };
    @Override public LocalAnyObject<CONTEXT> get         () { return this; };
    @Override public short  type           () { return Helper.SSP_TYPE_OBJECT; };
    @Override public int    size           () { return _value.size(); };
    @Override public String toString       () {
        final StringBuilder result = new StringBuilder("{");
        if(0 < size()) {
            result.append("\"").append(nameOf(0)).append("\": ").append(item(0).toString());
            for(int i = 1; i < size(); ++i) {
                result.append(", \"").append(nameOf(i)).append("\": ").append(item(i).toString());
            }
        }
        result.append("}");
        return result.toString();
    }
    
    @Override public int    add    (String  name, Any<?> value) {
        int index = indexOf(name);
        if(0 <= index) {
            _value.set(index,  value);
            return index;
        } else {
            _names.add(name);
            _value.add(value);
            return _value.size();
        }
    };
    
    @Override public Any<?> update (int    index, Any<?> value) { return _value.set(index,  value); };
    @Override public Any<?> update (String  name, Any<?> value) { return update(indexOf(name), value); };
    
    @Override public Any<?> remove (int    index              ) { 
        _names.remove(index);
        return _value.remove(index);
    };
    
    @Override public Any<?> remove (String  name           ) { return remove(indexOf(name)); };
    @Override public int    indexOf(String  name           ) { return _names.indexOf(name); };
    @Override public String nameOf (int    index           ) { return _names.get(index); };
    @Override public Any<?> item   (int    index           ) { return _value.get(index); };
    
    @Override public Any<?> item   (String  name           ) {
        final int index = indexOf(name);
        return (index < 0) ? null : item(index);
    };

    @Override public <T> int    add    (Named<T> name, Any<T> value) { return           add   (name.id(), value)       ; };
    @Override public <T> Any<T> update (Named<T> name, Any<T> value) { return Types.castAnyTo(update(name.id(), value), name); };
    @Override public <T> Any<T> remove (Named<T> name              ) { return Types.castAnyTo(remove(name.id()       ), name); };
    @Override public <T> Any<T> item   (Named<T> name              ) { return Types.castAnyTo(item  (name.id()       ), name); };
    @Override public <T> T      value  (Named<T> name              ) {
        final Any<T> result = item(name);
        return (null == result) ? null : result.get();
    };
    
    @Override public void writeTo(OutputStream dest) {
        writeObject(dest, Types.serializableSize(this));
        for(int i = 0; i < size(); ++i) {
            if(Types.isSerializable(item(i))) {
                writeField(dest, nameOf(i));
                item(i).writeTo(dest);
            }
        }
    }
    
    @Override public int hashCode() {
        int result = 0;
        for(final Any<?> value : _value) {
            result+= value.hashCode();
        }
        return result;
    }

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
            if(!item(i).equals(other.item(nameOf(i))))
                return false;
        }
        return true;
    }

    @Override protected Any<AnyObject> makeUnmodifiable() {
        return new AnyUnmodifiableObjectImpl<CONTEXT>(this);
    }

    @Override public LocalAny<CONTEXT, AnyObject> clone() {
        return new ObjectImpl<CONTEXT>(
            new ArrayList<String>(_names),
            new ArrayList<Any<?>>(_value)
            );
    }

    /*
    @Override public void visit(TypeVisitor visitor) {
        visitor.visitObject(this);
    }
    */
 
    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, final Any<?> objectValue) {
        proceed.visitor().visitObject(
            nestedInvoker(proceed, new Invoker<PROCEED>() {
                @Override public void invoke(PROCEED proceed) {
                    for(int i = 0; i < objectValue.size(); ++i) {
                        final Any<?> objectItem = objectValue.item(i);
                        proceed.visitor().visitObjectField(
                            nestedInvoker(proceed, new Invoker<PROCEED>() {
                                @Override public void invoke(PROCEED proceed) {
                                    objectItem.visit(nestedUnknown(proceed));
                                }}
                                ),
                            i,
                            objectItem.nameOf(i),
                            objectItem
                            );
                    }
                }}
                ),
            objectValue
            );
    }
    */
    
    @Override public Entry<String, Any<?>> entry(int    index) { return Maps.<String, Any<?>>keyValue(nameOf(index), item(index)); };
    @Override public Entry<String, Any<?>> entry(String name ) { return Maps.<String, Any<?>>keyValue(       name  , item(name )); };
    
    @Override public List<Any<?> > values () { 
        if(null == _valuesList) {
            _valuesList = new AbstractList<Any<?>>() {
                @Override public int    size  (                         ) { return ObjectImpl.this.size  (              ); }
                @Override public Any<?> get   (int index                ) { return ObjectImpl.this.item  (index         ); }
                @Override public Any<?> remove(int index                ) { return ObjectImpl.this.remove(index         ); }
                @Override public Any<?> set   (int index, Any<?> element) { return ObjectImpl.this.update(index, element); }
            };
        }
        return _valuesList;
    };
    
    @Override public List<Entry<String, Any<?>>> entries() {
        if(null == _entriesList) {
            _entriesList = new AbstractList<Entry<String, Any<?>>>() {
                @Override public int                   size  (                                        ) { return ObjectImpl.this.size  (              ); }
                @Override public Entry<String, Any<?>> get   (int index                               ) { return ObjectImpl.this.entry (index         ); }
                @Override public Entry<String, Any<?>> remove(int index                               ) {
                    final Entry<String, Any<?>> result = this.get(index);
                    ObjectImpl.this.remove(index);
                    return result;
                }
                @Override public boolean               add   (           Entry<String, Any<?>> element) {
                    ObjectImpl.this.add(element.getKey(), element.getValue());
                    return true;
                }
                @Override public Entry<String, Any<?>> set   (int index, Entry<String, Any<?>> element) {
                    if(!element.getKey().equals(ObjectImpl.this.nameOf(index))) {
                        throw new UnsupportedOperationException("Entry key does not match index.");
                    }
                    final Entry<String, Any<?>> result = this.get(index);
                    ObjectImpl.this.update(index, element.getValue());
                    return result;
                }
            };
        }
        return _entriesList;
    }
    
}
