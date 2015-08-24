package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;


import com.speakingfish.common.function.Getter;
/*
import com.speakingfish.common.traverse.Proceed;
import com.speakingfish.common.traverse.VisitorHolder;
*/
import com.speakingfish.common.type.Typecasts;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.LocalAny;
//import com.speakingfish.protocol.ssp.ValueVisitor;


/*
import static com.speakingfish.common.traverse.Traverses.*;
*/
import static com.speakingfish.common.Hashes.*;
import static com.speakingfish.protocol.ssp.Helper.*;

/**
 * 
 * @author borka
 * 
 * TODO: + immutable holder
 *
 */
@SuppressWarnings("serial")
public class HolderImpl<CONTEXT, TYPE> extends AnyDefaultPrimitiveImpl<CONTEXT, TYPE> {
    
    protected final Getter<TYPE> _value;
    
    public HolderImpl(Getter<TYPE> value) {
        super();
        _value = value;
    }

    @Override public short        type    () { return SSP_TYPE_HOLDER; }
    @Override public TYPE         get     () { return null == _value ? null : _value.get(); };
    @Override public Getter<TYPE> asHolder() { return _value; };
    @Override public String       toString() { return String.valueOf(_value); }
    @Override public int          hashCode() { return hash(_value); }

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
        
        return Typecasts.equals(_value, other.asHolder(), Typecasts.classTyped(Getter.class));
    }
    
    @Override public void writeTo(OutputStream dest) { throw new UnsupportedOperationException(); }

    @Override public LocalAny<CONTEXT, TYPE> clone() {
        return new HolderImpl<CONTEXT, TYPE>(_value);
    }

    /*
    @Override public void visit(TypeVisitor visitor) {
        visitor.visitHolder(this);
    }
    */
    
    /*
    @Override public <DEST> DEST visit(ValueVisitor<DEST, Any<TYPE>> visitor) {
        return visitor.visitHolder(this, get());
    }
    */
    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, Any<?> value) {
        proceed.visitor().visitHolder(nestedEmpty(proceed), value);
    }
    */

}