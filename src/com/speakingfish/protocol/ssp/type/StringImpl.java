package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/*
import com.speakingfish.common.traverse.Proceed;
import com.speakingfish.common.traverse.VisitorHolder;
*/
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.Helper;
import com.speakingfish.protocol.ssp.LocalAny;
//import com.speakingfish.protocol.ssp.ValueVisitor;

/*
import static com.speakingfish.common.traverse.Traverses.*;
*/
import static com.speakingfish.protocol.ssp.bin.Helper.*;


@SuppressWarnings("serial")
public class StringImpl<CONTEXT> extends AnyDefaultPrimitiveImpl<CONTEXT, String> {
    
    protected final String _value;
    
    public StringImpl(String value) {
        super();
        assert null != value;
        _value = value;
    }

    @Override public String get            () { return _value; };
    @Override public short  type           () { return Helper.SSP_TYPE_STRING; };
    @Override public int    size           () { return _value.length(); };
    @Override public String toString       () { return '"' + _value + '"'; }
    @Override public String asString       () { return _value; };
    @Override public byte[] asUTF8String   () {
        try {
            return _value.getBytes("UTF-8");
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public void writeTo(OutputStream dest) { writeString(dest, _value); }
    
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
        
        return _value.equals(other.asString());
    }

    @Override public LocalAny<CONTEXT, String> clone() {
        return new StringImpl<CONTEXT>(_value);
    }

    /*
    @Override public void visit(TypeVisitor visitor) {
        visitor.visitString(this);
    };
    */
    
    /*
    @Override public <DEST> DEST visit(ValueVisitor<DEST, Any<String>> visitor) {
        return visitor.visitString(this, get());
    }
    */
    
    //@Override public <DEST, SOURCE> DEST visit(SOURCE src, ValueVisitor<DEST, SOURCE> visitor) {
    //    return visitor.visitString(src, get());
    //}

    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, Any<?> value) {
        proceed.visitor().visitString(nestedEmpty(proceed), value);
    }
    */

}