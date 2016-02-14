package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;


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
import static com.speakingfish.common.Hashes.*;
import static com.speakingfish.protocol.ssp.bin.Helper.*;


@SuppressWarnings("serial")
public class IntImpl<CONTEXT> extends AnyDefaultPrimitiveImpl<CONTEXT, Long> {
    
    protected final byte _type ;
    protected final long _value;
    protected final Long _boxed;
    
    public IntImpl(byte type, long value) {
        super();
        _type  = type ;
        _value = value;
        _boxed = value;
    }

    public IntImpl(long value) { this(Helper.getTypeOf(value), value); }
    
    @Override public Long   get            () { return _boxed; };
    @Override public short  type           () { return _type; };
    @Override public String toString       () { return asString(); }
    @Override public String asString       () { return Long.toString(_value); };
    @Override public long   asInt          () { return         _value; };
    @Override public byte   asInt8         () { return (byte ) _value; };
    @Override public short  asInt16        () { return (short) _value; };
    @Override public int    asInt32        () { return (int  ) _value; };
    @Override public long   asInt64        () { return         _value; };

    @Override public void writeTo(OutputStream dest) { writeInt(dest, _value); }
    
    @Override public int hashCode() { return hash(_value); }
    
    @Override public boolean equals(Object obj) {
        if(this == obj)
            return true;
        
        if(obj == null)
            return false;
        
        if(!(obj instanceof Any))
            return false;
        
        final Any<?> other = (Any<?>) obj;
        
        switch(other.type()) {
        case Helper.SSP_TYPE_INT_8 : ;
        case Helper.SSP_TYPE_INT_16: ;
        case Helper.SSP_TYPE_INT_32: ;
        case Helper.SSP_TYPE_INT_64: break;
        default: return false;
        }
        
        return _value == other.asInt();
    }
/*
    @Override public LocalAny<CONTEXT, Long> clone() { return this; }

    @Override public Any<Long> asUnmodifiable() { return this; }
*/
    

    /*
    @Override public void visit(TypeVisitor visitor) {
        switch(type()) {
        case Helper.SSP_TYPE_INT_8 : visitor.visitInt8 (this); break;
        case Helper.SSP_TYPE_INT_16: visitor.visitInt16(this); break;
        case Helper.SSP_TYPE_INT_32: visitor.visitInt32(this); break;
        case Helper.SSP_TYPE_INT_64: visitor.visitInt64(this); break;
        default: throw new IllegalStateException("Invalid int type: " + type());
        }
    };
    */

    /*
    @Override public <DEST> DEST visit(ValueVisitor<DEST, Any<Long>> visitor) {
        switch(type()) {
        case Helper.SSP_TYPE_INT_8 : return visitor.visitInt8 (this, get());
        case Helper.SSP_TYPE_INT_16: return visitor.visitInt16(this, get());
        case Helper.SSP_TYPE_INT_32: return visitor.visitInt32(this, get());
        case Helper.SSP_TYPE_INT_64: return visitor.visitInt64(this, get());
        default: throw new IllegalStateException("Invalid int type: " + type());
        }
    };
    */
    
    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, Any<?> value) {
        switch(type()) {
        case Helper.SSP_TYPE_INT_8 : proceed.visitor().visitInt8 (nestedEmpty(proceed), value); break;
        case Helper.SSP_TYPE_INT_16: proceed.visitor().visitInt16(nestedEmpty(proceed), value); break;
        case Helper.SSP_TYPE_INT_32: proceed.visitor().visitInt32(nestedEmpty(proceed), value); break;
        case Helper.SSP_TYPE_INT_64: proceed.visitor().visitInt64(nestedEmpty(proceed), value); break;
        default: throw new IllegalStateException("Invalid int type: " + type());
        }
    };
*/
}