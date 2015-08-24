package com.speakingfish.protocol.ssp;

public interface SimpleValueVisitor<PROCEED, VALUE> {
    
    void visitObject     (PROCEED proceed, VALUE value);
    void visitArray      (PROCEED proceed, VALUE value);
    
    void visitObjectField(PROCEED proceed, int   index, String name , VALUE value);
    void visitArrayItem  (PROCEED proceed, int   index, VALUE  value);
    
    void visitString     (PROCEED proceed, VALUE value);
    void visitDecimal    (PROCEED proceed, VALUE value);
    void visitInt        (PROCEED proceed, VALUE value);
    void visitFloat      (PROCEED proceed, VALUE value);
    void visitByteArray  (PROCEED proceed, VALUE value);
    void visitHolder     (PROCEED proceed, VALUE value);
}
