package com.speakingfish.protocol.ssp.bin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.speakingfish.common.exception.wrapped.java.io.WrappedEOFException;
import com.speakingfish.common.function.Mapper;
import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.InvalidDataException;
import com.speakingfish.protocol.ssp.type.ArrayImpl;
import com.speakingfish.protocol.ssp.type.ObjectImpl;

import static com.speakingfish.pipe.Helper.*;
import static com.speakingfish.protocol.ssp.Helper.*;

public class Helper {

    public static final Mapper<Any<?>, byte[]> MAPPER_BYTES_TO_ANY = new Mapper<Any<?>, byte[]>() {
        public Any<?> apply(byte[] value) {
            try {
                return readAny(value);
            } catch(EOFException e) {
                throw new WrappedEOFException(e);
            }
        }
    };
    
    public static final Mapper<byte[], Any<?>> MAPPER_ANY_TO_BYTES = new Mapper<byte[], Any<?>>() {
        public byte[] apply(Any<?> value) {
            return toBytes(value);
        }
    };
    
    public static void writeType(OutputStream dest, byte value) {
       internalWrite(dest, value);
    }
    
    public static void writeByteArray(OutputStream dest, int offset, int size, byte[] buffer) {
       writeType(dest, SSP_TYPE_BYTE_ARRAY);
       writeInt(dest, size);
       internalWrite(dest, offset, size, buffer);
    }
    
    public static void writeByteArray(OutputStream dest, byte[] buffer) {
       writeByteArray(dest, 0, buffer.length, buffer);
    }
    
    public static void writeObject(OutputStream dest, int fieldCount) {
       writeType(dest, SSP_TYPE_OBJECT);
       writeInt(dest, fieldCount);
    }
    
    public static void writeField(OutputStream dest, String fieldName) {
       writeString(dest, fieldName);
    }
    
    public static void writeArray(OutputStream dest, int itemCount) {
       writeType(dest, SSP_TYPE_ARRAY);
       writeInt(dest, itemCount);
    }
    
    public static void writeUTF8String(OutputStream dest, byte[] value) {
       writeType(dest, SSP_TYPE_STRING);
       writeInt(dest, value.length);
       internalWrite(dest, value);
    }
    
    public static void writeString(OutputStream dest, String value) {
       try {
           writeUTF8String(dest, value.getBytes("UTF-8"));
       } catch(UnsupportedEncodingException e) {
           throw new RuntimeException(e);
       }
    }
    
    public static void writeInt(OutputStream dest, long value) {
       final byte type = getTypeOf(value);
       
       switch(type) {
           case SSP_TYPE_INT_8 : writeInt8 (dest, (byte ) value); break;
           case SSP_TYPE_INT_16: writeInt16(dest, (short) value); break;
           case SSP_TYPE_INT_32: writeInt32(dest, (int  ) value); break;
           default             : writeInt64(dest, (long ) value); break;
       }
    }
    
    public static void writeInt8         (OutputStream dest, byte   value) {
       writeType(dest, SSP_TYPE_INT_8 );
       internalWrite(dest, value);
    }
    
    public static void writeInt32        (OutputStream dest, int    value) {
       writeType(dest, SSP_TYPE_INT_32);
       writeInt32Data(dest, value);
    }
    
    public static void writeInt64        (OutputStream dest, long   value) {
       writeType(dest, SSP_TYPE_INT_64);
       writeInt64Data(dest, value);
    }
    
    public static void writeInt16        (OutputStream dest, short  value) {
       writeType(dest, SSP_TYPE_INT_16);
       internalWrite(dest, new byte[] {(byte) (value >>> (8 * 1)), 
                                       (byte) (value >>> (8 * 0))});
    }
    
    protected static void writeInt32Data    (OutputStream dest, int    value) {
       internalWrite(dest, new byte[] {(byte) (value >>> (8 * 3)),
                                       (byte) (value >>> (8 * 2)),
                                       (byte) (value >>> (8 * 1)),
                                       (byte) (value >>> (8 * 0))});
    }
    
    protected static void writeInt64Data    (OutputStream dest, long   value) {
       internalWrite(dest, new byte[] {(byte) (value >>> (8 * 7)),
                                       (byte) (value >>> (8 * 6)),
                                       (byte) (value >>> (8 * 5)),
                                       (byte) (value >>> (8 * 4)),
                                       (byte) (value >>> (8 * 3)),
                                       (byte) (value >>> (8 * 2)),
                                       (byte) (value >>> (8 * 1)),
                                       (byte) (value >>> (8 * 0))});
    }
    
    public static void writeFloat        (OutputStream dest, double value) {
       final byte type = getTypeOf(value);
       if(SSP_TYPE_FLOAT_32 == type) {
           writeFloat32(dest, (float) value);
       } else {
           writeFloat64(dest,         value);
       }
    }
    
    public static void writeFloat32      (OutputStream dest, float  value) {
       writeType(dest, SSP_TYPE_FLOAT_32);
       writeInt32Data(dest, Float .floatToIntBits  (value));
    }
    
    public static void writeFloat64      (OutputStream dest, double value) {
       writeType(dest, SSP_TYPE_FLOAT_64);
       writeInt64Data(dest, Double.doubleToLongBits(value));
    }
    
    public static void writeDecimalString(OutputStream dest, String value) {
       writeType(dest, SSP_TYPE_DECIMAL);
       writeInt(dest, value.length());
       internalWrite(dest, value.getBytes(/*"UTF-8"*/));
    }
    
    public static byte checkType(byte actual, byte required) {
       if(actual != required) {
           throw new InvalidDataException("Illegal type: " + actual + ". Required type: " + required + '.');
       }
       return actual;
    }
    
    public static byte readType(InputStream src) throws WrappedEOFException {
       return readInt8Data(src, SSP_TYPE_INT_8);
    }
    
    public static byte[] readByteArrayData(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_BYTE_ARRAY);
       byte[] result = new byte[(int) readInt(src)];
       if(0 < result.length) {
           internalRead(src, 0, result.length, result);
       }
       return result;
    }
    
    public static byte[] readUTF8StringData(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_STRING);
       byte[] result = new byte[(int) readInt(src)];
       if(0 < result.length) {
           internalRead(src, 0, result.length, result);
       }
       return result;
    }
    
    public static String readStringData(InputStream src, byte type) throws WrappedEOFException {
       try {
           return new String(readUTF8StringData(src, type), "UTF-8");
       } catch(UnsupportedEncodingException e) {
           throw new RuntimeException(e);
       }
    }
    
    public static long readIntData(InputStream src, byte type) throws WrappedEOFException {
       switch(type) {
       case SSP_TYPE_INT_8 : return readInt8Data (src, type);
       case SSP_TYPE_INT_16: return readInt16Data(src, type);
       case SSP_TYPE_INT_32: return readInt32Data(src, type);
       case SSP_TYPE_INT_64: return readInt64Data(src, type);
       default             : throw new InvalidDataException(
           "Illegal type: " + type + ". Required types: ("
           + SSP_TYPE_INT_8  + ','
           + SSP_TYPE_INT_16 + ','
           + SSP_TYPE_INT_32 + ','
           + SSP_TYPE_INT_64 + ")."
           );
       }
    }
    
    public static byte readInt8Data(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_INT_8 ); return internalRead(src);
    }
    
    public static short readInt16Data(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_INT_16);
       final byte[] buffer = new byte[2];
       internalRead(src, buffer);
       return  (short) ((((0
           | (buffer[0] & (short) 0xFF)) << 8)
           | (buffer[1] & (short) 0xFF))     )
           ;
    }
    
    public static int readInt32Data(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_INT_32);
       final byte[] buffer = new byte[4];
       internalRead(src, buffer);
       return  (int) ((((((((0
           | (buffer[0] & (int) 0xFF)) << 8)
           | (buffer[1] & (int) 0xFF)) << 8)
           | (buffer[2] & (int) 0xFF)) << 8)
           | (buffer[3] & (int) 0xFF))     )
           ;
    }
    
    public static long readInt64Data(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_INT_64);
       final byte[] buffer = new byte[8];
       internalRead(src, buffer);
       return  (long) ((((((((((((((((0
           | (buffer[0] & (long) 0xFFL)) << 8)
           | (buffer[1] & (long) 0xFFL)) << 8)
           | (buffer[2] & (long) 0xFFL)) << 8)
           | (buffer[3] & (long) 0xFFL)) << 8)
           | (buffer[4] & (long) 0xFFL)) << 8)
           | (buffer[5] & (long) 0xFFL)) << 8)
           | (buffer[6] & (long) 0xFFL)) << 8)
           | (buffer[7] & (long) 0xFFL))     )
           ;
    }
    
    public static double readFloatData(InputStream src, byte type) throws WrappedEOFException {
       switch(type) {
       case SSP_TYPE_FLOAT_32: return readFloat32Data(src, type);
       case SSP_TYPE_FLOAT_64: return readFloat64Data(src, type);
       default               : throw new InvalidDataException(
           "Illegal type: " + type + ". Required types: ("
           + SSP_TYPE_FLOAT_32 + ','
           + SSP_TYPE_FLOAT_64 + "')."
           );
       }
    }
    
    public static float readFloat32Data(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_FLOAT_32);
       return Float.intBitsToFloat(readInt32Data(src, SSP_TYPE_INT_32));
    }
    
    public static double readFloat64Data(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_FLOAT_64);
       return Double.longBitsToDouble(readInt64Data(src, SSP_TYPE_INT_64));
    }
    
    public static String readDecimalStringData(InputStream src, byte type) throws WrappedEOFException {
       checkType(type, SSP_TYPE_DECIMAL);
       //try {
           return new String(readUTF8StringData(src, SSP_TYPE_STRING)/*, "UTF-8"*/);
       //} catch(UnsupportedEncodingException e) {
       //    throw new RuntimeException(e);
       //}
    }
    
    /*
    public static Any<?> readAnyData(InputStream src, byte type) throws WrappedEOFException {
        switch(type) {
        case SSP_TYPE_OBJECT    : return                                 readObjectData       (src, type) ;
        case SSP_TYPE_ARRAY     : return                                 readArrayData        (src, type) ;
        case SSP_TYPE_STRING    : return new StringImpl   <Object>(      readStringData       (src, type));
        case SSP_TYPE_BYTE_ARRAY: return new ByteArrayImpl<Object>(      readByteArrayData    (src, type));
        case SSP_TYPE_DECIMAL   : return new DecimalImpl  <Object>(      readDecimalStringData(src, type));
        case SSP_TYPE_INT_8     : return new IntImpl      <Object>(type, readInt8Data         (src, type));
        case SSP_TYPE_INT_16    : return new IntImpl      <Object>(type, readInt16Data        (src, type));
        case SSP_TYPE_INT_32    : return new IntImpl      <Object>(type, readInt32Data        (src, type));
        case SSP_TYPE_INT_64    : return new IntImpl      <Object>(type, readInt64Data        (src, type));
        case SSP_TYPE_FLOAT_32  : return new FloatImpl    <Object>(type, readFloat32Data      (src, type));
        case SSP_TYPE_FLOAT_64  : return new FloatImpl    <Object>(type, readFloat64Data      (src, type));
        default                 : throw new InvalidDataException("Illegal type: " + type + ".");
        }
    }
    */
    public static Any<?> readAnyData(InputStream src, byte type) throws WrappedEOFException {
        return visitType(src, type, BinaryReaderVisitor.INSTANCE);
    }

    
    public static Any<?> readObjectData(InputStream src, byte type) throws WrappedEOFException {
        checkType(type, SSP_TYPE_OBJECT);
        final int size = (int) readInt(src);
        final Any<?> result = new ObjectImpl<Object>();
        for(int i = 0; i < size; ++i) {
            final String name = readString(src);
            result.add(name, readAny(src));
        }
        return result;
    }

    static abstract class ArrayImplAccess extends ArrayImpl<Object> {
    }
    
    public static Any<?> readArrayData(InputStream src, byte type) throws WrappedEOFException {
        checkType(type, SSP_TYPE_ARRAY);
        final int size = (int) readInt(src);
        final List<Any<?>> list = new ArrayList<Any<?>>(size);
        for(int i = 0; i < size; ++i) {
            list.add(readAny(src));
        }
        //return new ArrayImpl<Object>(list);
        return ArrayImpl.createOwnList(list);
    }

    public static byte[] readByteArray    (InputStream src) throws WrappedEOFException { return readByteArrayData    (src, readType(src)); }
    public static byte[] readUTF8String   (InputStream src) throws WrappedEOFException { return readUTF8StringData   (src, readType(src)); }
    public static String readString       (InputStream src) throws WrappedEOFException { return readStringData       (src, readType(src)); }
    public static long   readInt          (InputStream src) throws WrappedEOFException { return readIntData          (src, readType(src)); }
    public static byte   readInt8         (InputStream src) throws WrappedEOFException { return readInt8Data         (src, readType(src)); }
    public static short  readInt16        (InputStream src) throws WrappedEOFException { return readInt16Data        (src, readType(src)); }
    public static int    readInt32        (InputStream src) throws WrappedEOFException { return readInt32Data        (src, readType(src)); }
    public static long   readInt64        (InputStream src) throws WrappedEOFException { return readInt64Data        (src, readType(src)); }
    public static double readFloat        (InputStream src) throws WrappedEOFException { return readFloatData        (src, readType(src)); }
    public static float  readSingle       (InputStream src) throws WrappedEOFException { return readFloat32Data      (src, readType(src)); }
    public static double readDouble       (InputStream src) throws WrappedEOFException { return readFloat64Data      (src, readType(src)); }
    public static String readDecimalString(InputStream src) throws WrappedEOFException { return readDecimalStringData(src, readType(src)); }
    public static Any<?> readAny          (InputStream src) throws WrappedEOFException { return readAnyData          (src, readType(src)); }
    public static Any<?> readObject       (InputStream src) throws WrappedEOFException { return readObjectData       (src, readType(src)); }
    public static Any<?> readArray        (InputStream src) throws WrappedEOFException { return readArrayData        (src, readType(src)); }
    
    public static Any<?> readAny(byte[] src) throws EOFException {
        final ByteArrayInputStream temp = new ByteArrayInputStream(src);
        return readAny(temp);
    }
    
    public static byte[] toBytes(Any<?> src) {
        final ByteArrayOutputStream temp = new ByteArrayOutputStream();
        src.writeTo(temp);
        return temp.toByteArray();
    }
    
    
    protected Helper() {}
    
}
