package com.speakingfish.protocol.ssp;

public interface Visitor<DEST, SOURCE> {

    DEST visitObject   (SOURCE src);
    DEST visitArray    (SOURCE src);

    DEST visitString   (SOURCE src);
    DEST visitDecimal  (SOURCE src);
    DEST visitInt8     (SOURCE src);
    DEST visitInt16    (SOURCE src);
    DEST visitInt32    (SOURCE src);
    DEST visitInt64    (SOURCE src);
    DEST visitFloat32  (SOURCE src);
    DEST visitFloat64  (SOURCE src);
    DEST visitByteArray(SOURCE src);

    DEST visitHolder   (SOURCE src);
}
