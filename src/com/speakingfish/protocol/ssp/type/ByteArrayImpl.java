package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;
import java.util.Arrays;

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

/**
 * 
 * @author borka
 * 
 * TODO: + immutable holder
 *
 */
@SuppressWarnings("serial")
public class ByteArrayImpl<CONTEXT> extends AnyDefaultPrimitiveImpl<CONTEXT, byte[]> {
    
    protected final byte[] _value;
    
    public ByteArrayImpl(byte[] value) {
        super();
        assert null != value;
        _value = value;
    }

    protected static final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    
    @Override public byte[] get            () { return _value; };
    @Override public short  type           () { return Helper.SSP_TYPE_BYTE_ARRAY; };
    @Override public int    size           () { return _value.length; };
    @Override public byte[] asByteArray    () { return _value; };
    @Override public String toString       () {
        final char[] result = new char[1 + _value.length * 2 + 1];
        result[0] = '\"';
        for(int dest = 0, src = 0, count = _value.length; 0 < count; --count) {
            final byte v = _value[src++];
            result[++dest] = hexArray[(v >>> 4) & 0x0F];
            result[++dest] = hexArray[ v        & 0x0F];
        }
        result[result.length - 1] = '\"';
        return new String(result);
    }
    
    @Override public void writeTo(OutputStream dest) { writeByteArray(dest, 0, _value.length, _value); }
    
    @Override public int hashCode() { return Arrays.hashCode(_value); }

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
        
        return Arrays.equals(_value, other.asByteArray());
    }
    
    @Override public LocalAny<CONTEXT, byte[]> clone() {
        return new ByteArrayImpl<CONTEXT>(_value);
    }
    
    /*
    @Override public void visit(TypeVisitor visitor) {
        visitor.visitByteArray(this);
    }
    */
    /*
    @Override public <DEST> DEST visit(ValueVisitor<DEST, Any<byte[]>> visitor) {
        return visitor.visitByteArray(this, get());
    }
    */

    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, Any<?> value) {
        proceed.visitor().visitByteArray(nestedEmpty(proceed), value);
    }
    */
    
}