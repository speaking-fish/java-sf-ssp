package com.speakingfish.protocol.ssp.type;

import java.io.OutputStream;
import java.math.BigDecimal;

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
 * TODO: BigDecimal
 */
@SuppressWarnings("serial")
public class DecimalImpl<CONTEXT> extends AnyDefaultPrimitiveImpl<CONTEXT, BigDecimal> {
    
    protected final String _value;
    
    public DecimalImpl(String value) {
        super();
        _value = value;
    }
    
    public DecimalImpl(BigDecimal value) {
        this(value.toString());
    }

    @Override public BigDecimal get        () { return new BigDecimal(asDecimalString()); };
    @Override public short  type           () { return Helper.SSP_TYPE_DECIMAL; };
    @Override public String toString       () { return asString(); }
    @Override public String asString       () { return _value; };
    @Override public double asDecimal      () { return Double.parseDouble(_value); };
    @Override public String asDecimalString() { return _value; };
    
    @Override public void writeTo(OutputStream dest) { writeDecimalString(dest, _value); }
    
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
    };
    
    @Override public LocalAny<CONTEXT, BigDecimal> clone() {
        return new DecimalImpl<CONTEXT>(_value);
    }

    /*
    @Override public void visit(TypeVisitor visitor) {
        visitor.visitDecimal(this);
    };
    */
    
    /*
    @Override public <DEST> DEST visit(ValueVisitor<DEST, Any<BigDecimal>> visitor) {
        return visitor.visitDecimal(this, get());
    }
    */
  
    /*
    @Override public <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void _visit(PROCEED proceed, Any<?> value) {
        proceed.visitor().visitDecimal(nestedEmpty(proceed), value);
    }
    */

}