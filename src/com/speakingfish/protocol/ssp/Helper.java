/**
 *
 */
package com.speakingfish.protocol.ssp;

import java.io.EOFException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.speakingfish.common.exception.wrapped.java.io.WrappedEOFException;
import com.speakingfish.common.function.Creator;
import com.speakingfish.common.function.Getter;
import com.speakingfish.common.function.Mapper;
import com.speakingfish.common.type.Named;

import static com.speakingfish.common.value.util.Values.*;

/**
 * @author borka
 *
 */
public class Helper {

    //SIMPLE SERIALIZATION PROTOCOL
    //
    public static final byte SSP_TYPE_HOLDER     =-1; // unserializable
    public static final byte SSP_TYPE_OBJECT     = 0; // objectType    intType COUNT (stringType FIELD_NAME valueType FIELD_VALUE)*
    public static final byte SSP_TYPE_ARRAY      = 1; // arrayType     intType COUNT (                      valueType FIELD_VALUE)*
    public static final byte SSP_TYPE_STRING     = 2; // stringType    intType COUNT (                                 BYTE_VALUE)*
    public static final byte SSP_TYPE_DECIMAL    = 4; // decimalType   intType COUNT (                                 BYTE_VALUE)*
    public static final byte SSP_TYPE_INT_8      =10; // int8Type      BYTE_VALUE
    public static final byte SSP_TYPE_INT_16     =11; // int16Type     BYTE_VALUE0 BYTE_VALUE1
    public static final byte SSP_TYPE_INT_32     =12; // int32Type     BYTE_VALUE0 BYTE_VALUE1 BYTE_VALUE2 BYTE_VALUE3
    public static final byte SSP_TYPE_INT_64     =13; // int64Type     BYTE_VALUE0 BYTE_VALUE1 BYTE_VALUE2 BYTE_VALUE3 BYTE_VALUE4 BYTE_VALUE5 BYTE_VALUE6 BYTE_VALUE7
    public static final byte SSP_TYPE_FLOAT_32   =15; // float32Type   BYTE_VALUE0 BYTE_VALUE1 BYTE_VALUE2 BYTE_VALUE3
    public static final byte SSP_TYPE_FLOAT_64   =16; // float64Type   BYTE_VALUE0 BYTE_VALUE1 BYTE_VALUE2 BYTE_VALUE3 BYTE_VALUE4 BYTE_VALUE5 BYTE_VALUE6 BYTE_VALUE7
    public static final byte SSP_TYPE_BYTE_ARRAY =20; // byteArrayType intType COUNT (                                 BYTE_VALUE)*
    
  //public static final byte SSP_TYPE_MASK_META = (byte) 0x80;
    
    public static final Creator<DateFormat, String> CREATOR_SimpleDateFormat = new Creator<DateFormat, String>() {
        public DateFormat apply(String params) {
            return new SimpleDateFormat(params);
        }};
    
    public static final Getter<DateFormat> FORMAT_DEFAULT_TIMESTAMP = threadLocalCloseableSingleton(CREATOR_SimpleDateFormat, "yyyy-MM-dd'T'HH:mm:ss.SSS");
    public static final Getter<DateFormat> FORMAT_QUIRKS_TIMESTAMP  = threadLocalCloseableSingleton(CREATOR_SimpleDateFormat, "yyyy-MM-dd'T'HH:mm:ss:SSS");
    public static final Getter<DateFormat> FORMAT_DISPLAY_TIMESTAMP = threadLocalCloseableSingleton(CREATOR_SimpleDateFormat, "yyyy-MM-dd' 'HH:mm:ss.SSS");
    public static final Getter<DateFormat> FORMAT_SQLITE_TIMESTAMP  = threadLocalCloseableSingleton(CREATOR_SimpleDateFormat, "yyyy-MM-dd' 'HH:mm:ss.SSS");

    public static class DateParseMapper implements Mapper<Date, String> {
        
        protected final Getter<DateFormat> _format;
        
        public DateParseMapper(Getter<DateFormat> format) {
            super();
            _format = format;
        }

        public Date apply(String value) {
            try {
                return _format.get().parse(value);
            } catch(ParseException e) {
                throw new RuntimeException(e);
            }
        }
        
    }
    
    public static DateParseMapper dateParseMapper(Getter<DateFormat> format) { return new DateParseMapper(format); }
    
    public static final Mapper<Date, String> MAPPER_PARSE_DEFAULT_TIMESTAMP = dateParseMapper(FORMAT_DEFAULT_TIMESTAMP);
    public static final Mapper<Date, String> MAPPER_PARSE_QUIRKS_TIMESTAMP  = dateParseMapper(FORMAT_QUIRKS_TIMESTAMP );
    public static final Mapper<Date, String> MAPPER_PARSE_DISPLAY_TIMESTAMP = dateParseMapper(FORMAT_DISPLAY_TIMESTAMP);
    public static final Mapper<Date, String> MAPPER_PARSE_SQLITE_TIMESTAMP  = dateParseMapper(FORMAT_SQLITE_TIMESTAMP );
    
    public static byte getTypeOf(long value) {
            if(value <  Integer.MIN_VALUE) return SSP_TYPE_INT_64;
       else if(value <  Short  .MIN_VALUE) return SSP_TYPE_INT_32;
       else if(value <  Byte   .MIN_VALUE) return SSP_TYPE_INT_16;
       else if(value <= Byte   .MAX_VALUE) return SSP_TYPE_INT_8 ;
       else if(value <= Short  .MAX_VALUE) return SSP_TYPE_INT_16;
       else if(value <= Integer.MAX_VALUE) return SSP_TYPE_INT_32;
       else                                return SSP_TYPE_INT_64;
    }
    
    public static byte getTypeOf(double value) {
       final float tempSingle = (float) value;
       if(Double.isNaN(tempSingle) || (tempSingle == value))
           return SSP_TYPE_FLOAT_32; else
           return SSP_TYPE_FLOAT_64;
    }

    
    public static Date parseTimestamp(String timestamp) throws ParseException {
        try {
            return FORMAT_DEFAULT_TIMESTAMP.get().parse(timestamp);
        } catch(ParseException e) {
            return FORMAT_QUIRKS_TIMESTAMP.get().parse(timestamp);
        }
    }
    
    public static String formatTimestamp(Date timestamp) throws ParseException {
        return FORMAT_DEFAULT_TIMESTAMP.get().format(timestamp);
    }
    
    public static final Mapper<Date, String> MAPPER_PARSE_TIMESTAMP = new Mapper<Date, String>() {
        public Date apply(String value) {
            try {
                return parseTimestamp(value);
            } catch(ParseException e) {
                throw new RuntimeException(e);
            }
        }
    };
    
    public static Mapper<Date, String> parseMapper(final DateFormat format) {
        return new Mapper<Date, String>() {
            public Date apply(String value) {
                try {
                    return format.parse(value);
                } catch(ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public static Mapper<String, Date> formatMapper(final DateFormat format) {
        return new Mapper<String, Date>() {
            public String apply(Date value) {
                return format.format(value);
            }
        };
    }

    public static <SRC> Mapper<String, SRC> reformatMapper(final Mapper<Date, SRC> parseMapper, final DateFormat format) {
        return new Mapper<String, SRC>() {
            public String apply(SRC value) {
                return format.format(parseMapper.apply(value));
            }
        };
    }
    
    public static <SRC> Mapper<String, SRC> reformatTimestampMapper(final Mapper<String, SRC> srcMapper, final DateFormat format) {
        return new Mapper<String, SRC>() {
            public String apply(SRC value) {
                try {
                    return format.format(parseTimestamp(srcMapper.apply(value)));
                } catch(ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            
        };
    }
    

    public static <DEST, SOURCE> DEST visitType(SOURCE src, byte type, Visitor<DEST, SOURCE> visitor) {
        switch(type) {
        case SSP_TYPE_HOLDER    : return visitor.visitHolder   (src);
        case SSP_TYPE_OBJECT    : return visitor.visitObject   (src);
        case SSP_TYPE_ARRAY     : return visitor.visitArray    (src);
        case SSP_TYPE_STRING    : return visitor.visitString   (src);
        case SSP_TYPE_BYTE_ARRAY: return visitor.visitByteArray(src);
        case SSP_TYPE_DECIMAL   : return visitor.visitDecimal  (src);
        case SSP_TYPE_INT_8     : return visitor.visitInt8     (src);
        case SSP_TYPE_INT_16    : return visitor.visitInt16    (src);
        case SSP_TYPE_INT_32    : return visitor.visitInt32    (src);
        case SSP_TYPE_INT_64    : return visitor.visitInt64    (src);
        case SSP_TYPE_FLOAT_32  : return visitor.visitFloat32  (src);
        case SSP_TYPE_FLOAT_64  : return visitor.visitFloat64  (src);
        default                 : throw new InvalidDataException("Illegal type: " + type + ".");
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T_ANY extends Any<T>, T> T_ANY asUnmodifiable(T_ANY src) {
        return (T_ANY) ((null == src) ? null : src.asUnmodifiable());
    }
    
    public static final Mapper<Any<?>, Any<?>> MAPPER_AS_UNMODIFIABLE = new Mapper<Any<?>, Any<?>>() {
        public Any<?> apply(Any<?> value) {
            return asUnmodifiable(value);
        }};
    
    @SuppressWarnings("unchecked")
    public static <T extends Any<?>> Mapper<T, T> asUnmodifiableMapper() { return (Mapper<T, T>) MAPPER_AS_UNMODIFIABLE; } 

    public static <T> Mapper<T, Any<?>> extractValueMapper(final Named<T> name) {
        return new Mapper<T, Any<?>>() {
            public T apply(Any<?> value) {
                return value.value(name);
            }
        };
    }

    // compatibility
    
    public static final Mapper<Any<?>, byte[]> MAPPER_BYTES_TO_ANY = com.speakingfish.protocol.ssp.bin.Helper.MAPPER_BYTES_TO_ANY;
    public static final Mapper<byte[], Any<?>> MAPPER_ANY_TO_BYTES = com.speakingfish.protocol.ssp.bin.Helper.MAPPER_ANY_TO_BYTES;
    
    public static Any<?> readAny(InputStream src) throws WrappedEOFException {
        return com.speakingfish.protocol.ssp.bin.Helper.readAny(src);
    }
        
    public static Any<?> readAny(byte[] src) throws EOFException {
        return com.speakingfish.protocol.ssp.bin.Helper.readAny(src);
    }
    
    public static byte[] toBytes(Any<?> src) {
        return com.speakingfish.protocol.ssp.bin.Helper.toBytes(src);
    }
    
    protected Helper() {}
    
}
