package com.speakingfish.protocol.ssp;

/*
import com.speakingfish.common.traverse.Proceed;
*/

public interface ValueVisitor/*<PROCEED extends Proceed>*/ {
//public interface ValueVisitor<NESTED, PROCEED extends Proceed<NESTED>> {
/*
    void visitObject   (Any<AnyObject > src);
    void visitArray    (Any<AnyArray  > src);
    
    void visitString   (Any<String    > src);
    void visitDecimal  (Any<BigDecimal> src);
    void visitInt8     (Any<Long      > src);
    void visitInt16    (Any<Long      > src);
    void visitInt32    (Any<Long      > src);
    void visitInt64    (Any<Long      > src);
    void visitFloat32  (Any<Double    > src);
    void visitFloat64  (Any<Double    > src);
    void visitByteArray(Any<byte[]    > src);

    void visitHolder   (Any<?         > src);
*/
    /*
    DEST visitObject   (SOURCE src, AnyObject  value);
    DEST visitArray    (SOURCE src, AnyArray   value);
    DEST visitString   (SOURCE src, String     value);
    DEST visitDecimal  (SOURCE src, BigDecimal value);
    DEST visitInt8     (SOURCE src, Long       value);
    DEST visitInt16    (SOURCE src, Long       value);
    DEST visitInt32    (SOURCE src, Long       value);
    DEST visitInt64    (SOURCE src, Long       value);
    DEST visitFloat32  (SOURCE src, Double     value);
    DEST visitFloat64  (SOURCE src, Double     value);
    DEST visitByteArray(SOURCE src, byte[]     value);
    DEST visitHolder   (SOURCE src, Object     value);
    */

    /*
    void visitObject     (PROCEED proceed, Any<?> value, NESTED nested);
    void visitArray      (PROCEED proceed, Any<?> value, NESTED nested);
    
    void visitObjectField(PROCEED proceed, int    index, String name , Any<?> value, NESTED nested);
    void visitArrayItem  (PROCEED proceed, int    index, Any<?> value, NESTED nested);
    
    void visitString     (PROCEED proceed, Any<?> value, NESTED nested);
    void visitDecimal    (PROCEED proceed, Any<?> value, NESTED nested);
    void visitInt8       (PROCEED proceed, Any<?> value, NESTED nested);
    void visitInt16      (PROCEED proceed, Any<?> value, NESTED nested);
    void visitInt32      (PROCEED proceed, Any<?> value, NESTED nested);
    void visitInt64      (PROCEED proceed, Any<?> value, NESTED nested);
    void visitFloat32    (PROCEED proceed, Any<?> value, NESTED nested);
    void visitFloat64    (PROCEED proceed, Any<?> value, NESTED nested);
    void visitByteArray  (PROCEED proceed, Any<?> value, NESTED nested);
    void visitHolder     (PROCEED proceed, Any<?> value, NESTED nested);
    */
    
/*    
    void visitObject     (PROCEED proceed, Any<?> value);
    void visitArray      (PROCEED proceed, Any<?> value);
    
    void visitObjectField(PROCEED proceed, int    index, String name , Any<?> value);
    void visitArrayItem  (PROCEED proceed, int    index, Any<?> value);
    
    void visitString     (PROCEED proceed, Any<?> value);
    void visitDecimal    (PROCEED proceed, Any<?> value);
    void visitInt8       (PROCEED proceed, Any<?> value);
    void visitInt16      (PROCEED proceed, Any<?> value);
    void visitInt32      (PROCEED proceed, Any<?> value);
    void visitInt64      (PROCEED proceed, Any<?> value);
    void visitFloat32    (PROCEED proceed, Any<?> value);
    void visitFloat64    (PROCEED proceed, Any<?> value);
    void visitByteArray  (PROCEED proceed, Any<?> value);
    void visitHolder     (PROCEED proceed, Any<?> value);
*/
}
