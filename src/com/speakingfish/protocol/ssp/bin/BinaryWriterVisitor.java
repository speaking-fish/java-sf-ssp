package com.speakingfish.protocol.ssp.bin;

//import java.io.OutputStream;
/*
import com.speakingfish.common.traverse.ContextHolder;
import com.speakingfish.common.traverse.Proceed;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.InvalidDataException;
import com.speakingfish.protocol.ssp.ValueVisitor;

import static com.speakingfish.protocol.ssp.Types.*;
import static com.speakingfish.protocol.ssp.Helper.*;
import static com.speakingfish.protocol.ssp.bin.Helper.*;
*/
public class BinaryWriterVisitor/*<PROCEED extends Proceed & ContextHolder<OutputStream>> implements ValueVisitor<PROCEED>*/ {
    /*
    @Override public Void visitObject   (OutputStream dest, AnyObject  value) { writeObject       (dest, value); return null; }
    @Override public Void visitArray    (OutputStream dest, AnyArray   value) { writeArray        (dest, value); return null; }
    @Override public Void visitString   (OutputStream dest, String     value) { writeString       (dest, value); return null; }
    @Override public Void visitDecimal  (OutputStream dest, BigDecimal value) { writeDecimalString(dest, value.toString   ()); return null; }
    @Override public Void visitInt8     (OutputStream dest, Long       value) { writeInt8         (dest, value.byteValue  ()); return null; }
    @Override public Void visitInt16    (OutputStream dest, Long       value) { writeInt16        (dest, value.shortValue ()); return null; }
    @Override public Void visitInt32    (OutputStream dest, Long       value) { writeInt32        (dest, value.intValue   ()); return null; }
    @Override public Void visitInt64    (OutputStream dest, Long       value) { writeInt64        (dest, value.longValue  ()); return null; }
    @Override public Void visitFloat32  (OutputStream dest, Double     value) { writeFloat32      (dest, value.floatValue ()); return null; }
    @Override public Void visitFloat64  (OutputStream dest, Double     value) { writeFloat64      (dest, value.doubleValue()); return null; }
    @Override public Void visitByteArray(OutputStream dest, byte[]     value) { writeByteArray    (dest, value); return null; }
    @Override public Void visitHolder   (OutputStream dest, Object     value) { throw new InvalidDataException("Illegal type: " + SSP_TYPE_HOLDER + "."); }
    */
    /*
    @Override public void visitObject     (PROCEED proceed, Any<?> value, NESTED nested) {
        writeObject(proceed.get(), serializableSize(value));
        proceed.invoke();
    }
    
    @Override public void visitObjectField(PROCEED proceed, int    index, String name , Any<?> value, NESTED nested) {
        if(isSerializable(value)) {
            writeField(proceed.get(), name);
            proceed.invoke();
        }
    }
    
    @Override public void visitArray      (PROCEED proceed, Any<?> value, NESTED nested)  {
        writeArray(proceed.get(), serializableSize(value));
        proceed.invoke(null);
    }
    
    @Override public void visitArrayItem  (PROCEED proceed, int    index, Any<?> value, NESTED nested) {
        if(isSerializable(value)) {
            proceed.invoke(null);
        }
    }
    
    @Override public void visitString     (PROCEED proceed, Any<?> value, NESTED nested) { writeString       (proceed.get(), value.asString       ()); }
    @Override public void visitDecimal    (PROCEED proceed, Any<?> value, NESTED nested) { writeDecimalString(proceed.get(), value.asDecimalString()); }
    @Override public void visitInt8       (PROCEED proceed, Any<?> value, NESTED nested) { writeInt8         (proceed.get(), value.asInt8         ()); }
    @Override public void visitInt16      (PROCEED proceed, Any<?> value, NESTED nested) { writeInt16        (proceed.get(), value.asInt16        ()); }
    @Override public void visitInt32      (PROCEED proceed, Any<?> value, NESTED nested) { writeInt32        (proceed.get(), value.asInt32        ()); }
    @Override public void visitInt64      (PROCEED proceed, Any<?> value, NESTED nested) { writeInt64        (proceed.get(), value.asInt64        ()); }
    @Override public void visitFloat32    (PROCEED proceed, Any<?> value, NESTED nested) { writeFloat32      (proceed.get(), value.asFloat32      ()); }
    @Override public void visitFloat64    (PROCEED proceed, Any<?> value, NESTED nested) { writeFloat64      (proceed.get(), value.asFloat64      ()); }
    @Override public void visitByteArray  (PROCEED proceed, Any<?> value, NESTED nested) { writeByteArray    (proceed.get(), value.asByteArray    ()); }
    @Override public void visitHolder     (PROCEED proceed, Any<?> value, NESTED nested) { throw new InvalidDataException("Illegal type: " + SSP_TYPE_HOLDER + "."); }
    */
/*
    @Override public void visitObject     (PROCEED proceed, Any<?> value) {
        writeObject(proceed.context(), serializableSize(value));
        proceed.invoke();
    }
    
    @Override public void visitObjectField(PROCEED proceed, int    index, String name , Any<?> value) {
        if(isSerializable(value)) {
            writeField(proceed.context(), name);
            proceed.invoke();
        }
    }
    
    @Override public void visitArray      (PROCEED proceed, Any<?> value)  {
        writeArray(proceed.context(), serializableSize(value));
        proceed.invoke();
    }
    
    @Override public void visitArrayItem  (PROCEED proceed, int    index, Any<?> value) {
        if(isSerializable(value)) {
            proceed.invoke();
        }
    }
    
    @Override public void visitString     (PROCEED proceed, Any<?> value) { writeString       (proceed.context(), value.asString       ()); }
    @Override public void visitDecimal    (PROCEED proceed, Any<?> value) { writeDecimalString(proceed.context(), value.asDecimalString()); }
    @Override public void visitInt8       (PROCEED proceed, Any<?> value) { writeInt8         (proceed.context(), value.asInt8         ()); }
    @Override public void visitInt16      (PROCEED proceed, Any<?> value) { writeInt16        (proceed.context(), value.asInt16        ()); }
    @Override public void visitInt32      (PROCEED proceed, Any<?> value) { writeInt32        (proceed.context(), value.asInt32        ()); }
    @Override public void visitInt64      (PROCEED proceed, Any<?> value) { writeInt64        (proceed.context(), value.asInt64        ()); }
    @Override public void visitFloat32    (PROCEED proceed, Any<?> value) { writeFloat32      (proceed.context(), value.asFloat32      ()); }
    @Override public void visitFloat64    (PROCEED proceed, Any<?> value) { writeFloat64      (proceed.context(), value.asFloat64      ()); }
    @Override public void visitByteArray  (PROCEED proceed, Any<?> value) { writeByteArray    (proceed.context(), value.asByteArray    ()); }
    @Override public void visitHolder     (PROCEED proceed, Any<?> value) { throw new InvalidDataException("Illegal type: " + SSP_TYPE_HOLDER + "."); }
    
//    public static final BinaryWriterVisitor INSTANCE = new BinaryWriterVisitor();
*/
}
