package com.speakingfish.protocol.ssp.bin;

import java.io.InputStream;

import com.speakingfish.protocol.ssp.Any;
import com.speakingfish.protocol.ssp.InvalidDataException;
import com.speakingfish.protocol.ssp.Visitor;
import com.speakingfish.protocol.ssp.type.ByteArrayImpl;
import com.speakingfish.protocol.ssp.type.DecimalImpl;
import com.speakingfish.protocol.ssp.type.FloatImpl;
import com.speakingfish.protocol.ssp.type.IntImpl;
import com.speakingfish.protocol.ssp.type.StringImpl;

import static com.speakingfish.protocol.ssp.Helper.*;
import static com.speakingfish.protocol.ssp.bin.Helper.*;

public class BinaryReaderVisitor implements Visitor<Any<?>, InputStream> {

    public Any<?> visitObject   (InputStream src) { return                                              readObjectData       (src, SSP_TYPE_OBJECT    ) ;}
    public Any<?> visitArray    (InputStream src) { return                                              readArrayData        (src, SSP_TYPE_ARRAY     ) ;}
    public Any<?> visitString   (InputStream src) { return new StringImpl   <Object>(                   readStringData       (src, SSP_TYPE_STRING    ));}
    public Any<?> visitDecimal  (InputStream src) { return new DecimalImpl  <Object>(                   readDecimalStringData(src, SSP_TYPE_DECIMAL   ));}
    public Any<?> visitInt8     (InputStream src) { return new IntImpl      <Object>(SSP_TYPE_INT_8   , readInt8Data         (src, SSP_TYPE_INT_8     ));}
    public Any<?> visitInt16    (InputStream src) { return new IntImpl      <Object>(SSP_TYPE_INT_16  , readInt16Data        (src, SSP_TYPE_INT_16    ));}
    public Any<?> visitInt32    (InputStream src) { return new IntImpl      <Object>(SSP_TYPE_INT_32  , readInt32Data        (src, SSP_TYPE_INT_32    ));}
    public Any<?> visitInt64    (InputStream src) { return new IntImpl      <Object>(SSP_TYPE_INT_64  , readInt64Data        (src, SSP_TYPE_INT_64    ));}
    public Any<?> visitFloat32  (InputStream src) { return new FloatImpl    <Object>(SSP_TYPE_FLOAT_32, readFloat32Data      (src, SSP_TYPE_FLOAT_32  ));}
    public Any<?> visitFloat64  (InputStream src) { return new FloatImpl    <Object>(SSP_TYPE_FLOAT_64, readFloat64Data      (src, SSP_TYPE_FLOAT_64  ));}
    public Any<?> visitByteArray(InputStream src) { return new ByteArrayImpl<Object>(                   readByteArrayData    (src, SSP_TYPE_BYTE_ARRAY));}
    public Any<?> visitHolder   (InputStream src) { throw new InvalidDataException("Illegal type: " + SSP_TYPE_HOLDER + "."); }
    
    public static final BinaryReaderVisitor INSTANCE = new BinaryReaderVisitor();

}

