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
public class FloatImpl<CONTEXT> extends AnyDefaultPrimitiveImpl<CONTEXT, Double> {
    
    protected final byte   _type ;
    protected final double _value;
    protected final Double _boxed;
    
    public FloatImpl(byte type, double value) {
        super();
        _type  = type;
        _value = value;
        _boxed = value;
    }

    public FloatImpl(double value) { this(Helper.getTypeOf(value), value); }
    
    @Override public Double get            () { return _boxed; };
    @Override public short  type           () { return _type; };
    @Override public String toString       () { return asString(); }
    @Override public String asString       () { return Double.toString(_value); };
    @Override public double asFloat        () { return         _value; };
    @Override public float  asFloat32      () { return (float) _value; };
    @Override public double asFloat64      () { return         _value; }

    @Override public void writeTo(OutputStream dest) { writeFloat(dest, _value); }
    
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
        case Helper.SSP_TYPE_FLOAT_32: ;
        case Helper.SSP_TYPE_FLOAT_64: break;
        default: return false;
        }
        
        return _value == other.asFloat();
    };
    
    @Override public LocalAny<CONTEXT, Double> clone() {
        return new FloatImpl<CONTEXT>(_value);
    }

    /*
    @Override public void visit(TypeVisitor visitor) {
        switch(type()) {
        case Helper.SSP_TYPE_FLOAT_32: visitor.visitFloat32(this); break;
        case Helper.SSP_TYPE_FLOAT_64: visitor.visitFloat64(this); break;
        default: throw new IllegalStateException("Invalid float type: " + type());
        }
    };
    */
    /*
    @Override public <DEST> DEST visit(ValueVisitor<DEST, Any<Double>> visitor) {
        switch(type()) {
        case Helper.SSP_TYPE_FLOAT_32: return visitor.visitFloat32(this, get());
        case Helper.SSP_TYPE_FLOAT_64: return visitor.visitFloat64(this, get());
        default: throw new IllegalStateException("Invalid float type: " + type());
        }
    };
    */

    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, Any<?> value) {
        switch(type()) {
        case Helper.SSP_TYPE_FLOAT_32: proceed.visitor().visitFloat32(nestedEmpty(proceed), value); break;
        case Helper.SSP_TYPE_FLOAT_64: proceed.visitor().visitFloat64(nestedEmpty(proceed), value); break;
        default: throw new IllegalStateException("Invalid float type: " + type());
        }
    };
    */
    
}