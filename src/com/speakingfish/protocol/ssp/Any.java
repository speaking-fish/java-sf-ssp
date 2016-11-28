/**
 * 
 */
package com.speakingfish.protocol.ssp;

import java.io.OutputStream;
import java.util.List;
import java.util.Map.Entry;

import com.speakingfish.common.function.Getter;
/*
import com.speakingfish.common.traverse.Proceed;
import com.speakingfish.common.traverse.VisitorHolder;
*/
import com.speakingfish.common.type.Named;

/**
 * @author borka
 *
 */
public interface Any<TYPE> extends Getter<TYPE> {

    short        type           ();
    int          size           ();
  //String       toString       ();
    byte[]       asUTF8String   ();
    String       asString       ();
    long         asInt          ();
    byte         asInt8         ();
    short        asInt16        ();
    int          asInt32        ();
    long         asInt64        ();
    double       asFloat        ();
    float        asFloat32      ();
    double       asFloat64      ();
    double       asDecimal      ();
    String       asDecimalString();
    byte[]       asByteArray    ();
    Getter<TYPE> asHolder       ();

    int    insert (int    index, Any<?> value);
    int    add    (              Any<?> value);
    int    add    (String  name, Any<?> value);
    Any<?> update (int    index, Any<?> value);
    Any<?> update (String  name, Any<?> value);
    Any<?> remove (int    index              );
    Any<?> remove (String  name              );
    int    indexOf(String  name              );
    String nameOf (int    index              );
    Any<?> item   (int    index              );
    Any<?> item   (String  name              );
    
    void writeTo(OutputStream dest);
    
    Any<TYPE> asUnmodifiable();
    
    <T> int    add    (Named<T> name, Any<T> value);
    <T> Any<T> update (Named<T> name, Any<T> value);
    <T> Any<T> remove (Named<T> name              );
    <T> Any<T> item   (Named<T> name              );
    <T> T      value  (Named<T> name              );
    
    Any<TYPE> clone();
    Any<TYPE> cloneUnmodifiable();
    
    //void visit(TypeVisitor visitor);
    //<SOURCE, NESTED> void visit(SOURCE src, ValueVisitor<SOURCE, NESTED> visitor, NESTED nested);
    /*
    <PROCEED extends Proceed & VisitorHolder<ValueVisitor<PROCEED>>> void visit(PROCEED proceed);
    */
    
    Entry<String, Any<?>> entry(int    index);
    Entry<String, Any<?>> entry(String name );
    
    List<              Any<?> > values ();
    List<Entry<String, Any<?>>> entries();
    
    /*
    Any<?> locate(Iterable<AnyPath> path);
    */
}
