package com.speakingfish.protocol.ssp;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import static java.util.Arrays.asList;

import java.util.Map.Entry;

import com.speakingfish.common.annotation.Compatibility.*;
import com.speakingfish.common.function.Acceptor;
import com.speakingfish.common.function.Getter;
import com.speakingfish.common.function.Mapper;
import com.speakingfish.common.type.DefaultValue;
import com.speakingfish.common.type.LocalNamed;
import com.speakingfish.common.type.Named;
import com.speakingfish.common.type.Typed;
import com.speakingfish.protocol.ssp.type.ArrayImpl;
import com.speakingfish.protocol.ssp.type.ByteArrayImpl;
import com.speakingfish.protocol.ssp.type.DecimalImpl;
import com.speakingfish.protocol.ssp.type.FloatImpl;
import com.speakingfish.protocol.ssp.type.HolderImpl;
import com.speakingfish.protocol.ssp.type.IntImpl;
import com.speakingfish.protocol.ssp.type.ObjectImpl;
import com.speakingfish.protocol.ssp.type.StringImpl;

import static com.speakingfish.common.Maps.*;
import static com.speakingfish.protocol.ssp.Helper.*;

public class Types {
    
    protected Types() {}
    
    @SuppressWarnings("unchecked")
    public static <T> Any<T> castAnyTo(Any<?> src, Typed<T> castTo) {
        return (Any<T>) src;
    }
    
    public static Any<String    > any(String     value) { return new StringImpl   <Object>(       value) ; }
    public static Any<Long      > any(long       value) { return new IntImpl      <Object>(       value) ; }
    public static Any<Double    > any(double     value) { return new FloatImpl    <Object>(       value) ; }
    public static Any<BigDecimal> any(BigDecimal value) { return new DecimalImpl  <Object>(       value) ; }
    public static Any<byte[]    > any(byte[]     value) { return new ByteArrayImpl<Object>(       value) ; }
    
    public static Mapper<Any<?>, String    > MAPPER_ANY_STRING    = new Mapper<Any<?>, String    >() { public Any<String    > apply(String     v) { return any(v); } };
    public static Mapper<Any<?>, Long      > MAPPER_ANY_INT       = new Mapper<Any<?>, Long      >() { public Any<Long      > apply(Long       v) { return any(v); } };
    public static Mapper<Any<?>, Double    > MAPPER_ANY_FLOAT     = new Mapper<Any<?>, Double    >() { public Any<Double    > apply(Double     v) { return any(v); } };
    public static Mapper<Any<?>, BigDecimal> MAPPER_ANY_DECIMAL   = new Mapper<Any<?>, BigDecimal>() { public Any<BigDecimal> apply(BigDecimal v) { return any(v); } };
    public static Mapper<Any<?>, byte[]    > MAPPER_ANY_BYTE_ARRAY= new Mapper<Any<?>, byte[]    >() { public Any<byte[]    > apply(byte[]     v) { return any(v); } };
    
    public static Any<AnyArray> anyArray() {
        return new ArrayImpl<Object>();
    }
    
    @SafeVarargs
    public static Any<AnyArray> anyArray(Any<?>... value) {
        final ArrayImpl<?> result = new ArrayImpl<Object>();
        if(0 < value.length) {
            result.addAll(asList(value));
        }
        return result;
    }
    
    public static Any<AnyArray> anyArray(Collection<Any<?>> value) {
        final ArrayImpl<?> result = new ArrayImpl<Object>();
        result.addAll(value);
        return result;
    }
    
    public static Any<AnyArray> anyArray(Iterator<Any<?>> value) {
        final ArrayImpl<?> result = new ArrayImpl<Object>();
        result.addAll(value);
        return result;
    }
    
    public static Any<AnyObject> anyObject() {
        return new ObjectImpl<Object>();
    }
    
    @SafeVarargs
    public static Any<AnyObject> anyObject(Entry<String, ? extends Any<?>>... value) {
        final ObjectImpl<?> result = new ObjectImpl<Object>();
        result.addStringEntries(asList(value));
        return result;
    }
    
    public static Any<AnyObject> anyObject(Iterable<Entry<String, ? extends Any<?>>> value) {
        final ObjectImpl<?> result = new ObjectImpl<Object>();
        result.addStringEntries(value);
        return result;
    }

    @SafeVarargs
    //public static Any<AnyObject> namedAnyObject(Entry<? extends Named<?>, ? extends Any<?>>... value) {
    public static <E extends Entry<? extends Named<?>, ? extends Any<?>>> Any<AnyObject> namedAnyObject(E... value) {
        final ObjectImpl<?> result = new ObjectImpl<Object>();
        result.addNamedEntries(asList(value));
        return result;
    }
    
    public static Any<AnyObject> namedAnyObject(Iterable<? extends Entry<? extends Named<?>, ? extends Any<?>>> value) {
        final ObjectImpl<?> result = new ObjectImpl<Object>();
        result.addNamedEntries(value);
        return result;
    }
    
    public static <CONTEXT> LocalAny<CONTEXT, String    > localAny(String     value) { return new StringImpl   <CONTEXT>(       value) ; }
    public static <CONTEXT> LocalAny<CONTEXT, Long      > localAny(long       value) { return new IntImpl      <CONTEXT>(       value) ; }
    public static <CONTEXT> LocalAny<CONTEXT, Double    > localAny(double     value) { return new FloatImpl    <CONTEXT>(       value) ; }
    public static <CONTEXT> LocalAny<CONTEXT, BigDecimal> localAny(BigDecimal value) { return new DecimalImpl  <CONTEXT>(       value) ; }
    public static <CONTEXT> LocalAny<CONTEXT, byte[]    > localAny(byte[]     value) { return new ByteArrayImpl<CONTEXT>(       value) ; }
//    /*
//    @SuppressWarnings("unchecked")
//    public static <CONTEXT/*, RESULT extends LocalAnyArray<CONTEXT>*/> /*RESULT*/ LocalAny<CONTEXT, AnyArray> localAny(
//        LocalAny<?, ?>...  value
//    ) {
//        final ArrayImpl<CONTEXT> result = new ArrayImpl<CONTEXT>();
//        result.addAll((Collection<Any<?>>) (Collection<?>) asList(value));
//        return /*(RESULT)*/ result;
//    }
//    @SuppressWarnings("unchecked")
//    @SafeVarargs
//    public static <CONTEXT, RESULT extends LocalAnyObject<CONTEXT>> RESULT localAny(
//        Entry<? extends LocalNamed<CONTEXT, ?>, ? extends LocalAny<?, ?>>... value
//    ) {
//        //(Collection<Entry<String, ? extends Any<?>>>) (Collection<?>) asList(value));
//        final ObjectImpl<CONTEXT> result = new ObjectImpl<CONTEXT>();
//        result.addLocalEntries(asList(value));
//        return (RESULT) result;
//    }
//    */
    
    public static <         T> Any     <         T>      anyHolder(Getter<T> value) { return new HolderImpl<Object , T>(value) ; }
    public static <CONTEXT, T> LocalAny<CONTEXT, T> localAnyHolder(Getter<T> value) { return new HolderImpl<CONTEXT, T>(value) ; }
    
    public static <
        T,
        ANY_T extends Any<T>
    > Entry<String, ANY_T> entry(
        String name ,
        ANY_T  value
    ) {
        return keyValue(name, value);
    }
    
    public static <
        T,
        NAMED_T extends Named<T>,
        ANY_T   extends Any  <T>
    > Entry<NAMED_T, ANY_T> namedEntry(
        NAMED_T name ,
        ANY_T   value
    ) {
        return keyValue(name, value);
    }

    public static <
        T,
        CONTEXT,
        LOCAL_NAMED_CONTEXT_T extends LocalNamed<CONTEXT, T>,
        ANY_T                 extends Any       <T         >
    > Entry<LOCAL_NAMED_CONTEXT_T, ANY_T> namedEntry(
        LOCAL_NAMED_CONTEXT_T name ,
        ANY_T                 value
    ) {
        return keyValue(name, value);
    }
    
    public static <
        T, 
        CONTEXT,
        SUBCONTEXT,
        LOCAL_NAMED_CONTEXT_T  extends LocalNamed<CONTEXT   , T>,
        LOCAL_ANY_SUBCONTEXT_T extends LocalAny  <SUBCONTEXT, T>
    > Entry<LOCAL_NAMED_CONTEXT_T, LOCAL_ANY_SUBCONTEXT_T> localEntry(
        LOCAL_NAMED_CONTEXT_T  name ,
        LOCAL_ANY_SUBCONTEXT_T value
    ) {
        return keyValue(name, value);
    }
    
    public static     Entry<String, Any<String    >> named(String name, String     value) { return entry(name, any(value)); }
    public static     Entry<String, Any<Long      >> named(String name, long       value) { return entry(name, any(value)); }
    public static     Entry<String, Any<Double    >> named(String name, double     value) { return entry(name, any(value)); }
    public static     Entry<String, Any<BigDecimal>> named(String name, BigDecimal value) { return entry(name, any(value)); }
    public static     Entry<String, Any<byte[]    >> named(String name, byte[]     value) { return entry(name, any(value)); }
    public static <T> Entry<String, Any<T         >> named(String name, Any<T>     value) { return entry(name, value); }
    public static     Entry<String, Any<AnyArray  >> namedArray(String name,          Any<?>... value) {
                                                                                            return entry(name, anyArray(value)); }
    public static     Entry<String, Any<AnyArray  >> namedArray(String name, Iterable<Any<?>>   value) {
                                                                                            return entry(name, anyArray(value.iterator())); }
    @SafeVarargs  
    public static     Entry<String, Any<AnyObject >> namedObject(String name,          Entry<String, ? extends Any<?>>... value) {
                                                                                            return entry(name, anyObject(value)); }
    public static     Entry<String, Any<AnyObject >> namedObject(String name, Iterable<Entry<String, ? extends Any<?>>>   value) {
                                                                                            return entry(name, anyObject(value)); }
    public static <T> Entry<String, Any<T>>namedHolder(String name, Getter<T>  value)     { return entry(name, anyHolder(value)); }
    
    public static     Entry<Named<String    >, Any<String    >> named(Named<String    > name, String     value) { return namedEntry(name, any(value)); }
    public static     Entry<Named<Long      >, Any<Long      >> named(Named<Long      > name, long       value) { return namedEntry(name, any(value)); }
    public static     Entry<Named<Double    >, Any<Double    >> named(Named<Double    > name, double     value) { return namedEntry(name, any(value)); }
    public static     Entry<Named<BigDecimal>, Any<BigDecimal>> named(Named<BigDecimal> name, BigDecimal value) { return namedEntry(name, any(value)); }
    public static     Entry<Named<byte[]    >, Any<byte[]    >> named(Named<byte[]    > name, byte[]     value) { return namedEntry(name, any(value)); }
    public static <T> Entry<Named<T         >, Any<T         >> named(Named<T         > name, Any<T>     value) { return namedEntry(name,     value ); }
    public static     Entry<Named<AnyArray  >, Any<AnyArray  >> namedArray(Named<AnyArray  > name,          Any<?>... value) {
                                                                                                                  return namedEntry(name, anyArray(value)); }
    public static     Entry<Named<AnyArray  >, Any<AnyArray  >> namedArray(Named<AnyArray  > name, Iterable<Any<?>>   value) {
                                                                                                                  return namedEntry(name, anyArray(value.iterator())); }
    @SafeVarargs                             
    public static     Entry<Named<AnyObject >, Any<AnyObject >> namedObject(Named<AnyObject> name,          Entry<? extends Named<?>, ? extends Any<?>>... value) {
                                                                                                                  return namedEntry(name, namedAnyObject(value)); }
    public static     Entry<Named<AnyObject >, Any<AnyObject >> namedObject(Named<AnyObject> name, Iterable<? extends Entry<? extends Named<?>, ? extends Any<?>>>   value) {
                                                                                                                  return namedEntry(name, namedAnyObject(value)); }
    public static <T> Entry<Named<T     >, Any<T       >> namedHolder(Named<T         > name, Getter<T>  value) { return namedEntry(name, anyHolder(value)); }

    public static <CONTEXT, SUBCONTEXT> Entry<LocalNamed<CONTEXT, String    >, LocalAny<SUBCONTEXT, String    >> local(LocalNamed<CONTEXT, String    > name, String     value) { return localEntry(name, Types.<SUBCONTEXT>localAny(value)); }
    public static <CONTEXT, SUBCONTEXT> Entry<LocalNamed<CONTEXT, Long      >, LocalAny<SUBCONTEXT, Long      >> local(LocalNamed<CONTEXT, Long      > name, long       value) { return localEntry(name, Types.<SUBCONTEXT>localAny(value)); }
    public static <CONTEXT, SUBCONTEXT> Entry<LocalNamed<CONTEXT, Double    >, LocalAny<SUBCONTEXT, Double    >> local(LocalNamed<CONTEXT, Double    > name, double     value) { return localEntry(name, Types.<SUBCONTEXT>localAny(value)); }
    public static <CONTEXT, SUBCONTEXT> Entry<LocalNamed<CONTEXT, BigDecimal>, LocalAny<SUBCONTEXT, BigDecimal>> local(LocalNamed<CONTEXT, BigDecimal> name, BigDecimal value) { return localEntry(name, Types.<SUBCONTEXT>localAny(value)); }
    public static <CONTEXT, SUBCONTEXT> Entry<LocalNamed<CONTEXT, byte[]    >, LocalAny<SUBCONTEXT, byte[]    >> local(LocalNamed<CONTEXT, byte[]    > name, byte[]     value) { return localEntry(name, Types.<SUBCONTEXT>localAny(value)); }
    ///
    /// "T_" = generic name prefix
    /// "__" = generic start = "<"
    /// "_" = generic delimiter = ","
    /// "___" = generic end = ">" (trailings skipped, "," skipped)
    /// for ex: T_MyClass1__MyClass2__Integer_Boolean___String
    /// mean:     MyClass1< MyClass2< Integer,Boolean>  String>
    /// postfix ,:MyClass1< MyClass2< Integer,Boolean>, String>
//    @SafeVarargs
//    public static <
//        T_Context,
//        //T_Subcontext,
//        T_LocalAnyArray__T_Subcontext extends LocalAny<T_Context, AnyArray>/*LocalAnyArray<?T_Subcontext>*/,
//        T_LocalNamed__T_Context_T_LocalAnyArray__T_Subcontext extends LocalNamed<T_Context, T_LocalAnyArray__T_Subcontext>
//    > Entry<
//        T_LocalNamed__T_Context_T_LocalAnyArray__T_Subcontext,
//        T_LocalAnyArray__T_Subcontext
//    > localArray(
//        T_LocalNamed__T_Context_T_LocalAnyArray__T_Subcontext name ,
//        LocalAny <?/*T_Subcontext*/, ?                   >... value
//    ) {
//        return keyValue(name, Types.<Object/*T_Subcontext*/, T_LocalAnyArray__T_Subcontext>localAny(value));
//    }
//    
//    @SafeVarargs
//    public static <
//        T_Context,
//        //T_Subcontext,
//        T_LocalAnyObject__T_SubContext extends LocalAny<?, AnyObject>/*LocalAnyObject<T_Subcontext>*/,
//        T_LocalNamed__T_Context_T_LocalAnyObject__T_SubContext extends LocalNamed<T_Context, T_LocalAnyObject__T_SubContext>
//    > Entry<
//        T_LocalNamed__T_Context_T_LocalAnyObject__T_SubContext,
//        T_LocalAnyObject__T_SubContext
//    > local(
//        T_LocalNamed__T_Context_T_LocalAnyObject__T_SubContext name,
//        Entry<LocalNamed<?/*T_Subcontext*/, ?>, ? extends LocalAny<?, ?>>... value
//    ) {
//        return keyValue(name, Types.<Object/*T_Subcontext*/, T_LocalAnyObject__T_SubContext>localAny(value));
//    }
    
    public static <T> T valueOf(Any<T> any, T defaultValue) {
        return (null == any) ? defaultValue : any.get();
    }
    
    public static <T> T valueOf(Any<?> any, Named<T> name, T defaultValue) {
        return (null == any) ? defaultValue : valueOf(any.item(name), defaultValue);
    }

    /*
    public static <T> T valueOf(Any<?> any, Named<T> name) {
        return valueOf(any, name, null);
    }
    */
    
    public static <T> T valueOf(Any<?> any, DefaultValue<Named<T>, T> name) {
        return valueOf(any, name.origin(), name.defaultValue());
    }

    public static <CONTEXT, T> Entry<LocalNamed<CONTEXT, T>, LocalAny<CONTEXT, T>> localHolder(
        LocalNamed<CONTEXT, T> name, Getter<T>value
    ) {
        return localEntry(name, Types.<CONTEXT, T>localAnyHolder(value)); 
    }
    
    public static int serializableSize(Any<?> src) {
        int size = 0;
        for(int i = 0; i < src.size(); ++i) {
            if(isSerializable(src.item(i))) {
                ++size;
            }
        }
        return size;
    }

    public static boolean isSerializable(Any<?> item) {
        return SSP_TYPE_HOLDER != item.type(); 
    }

    public static final Acceptor<Any<?>> ACCEPTOR_SERIALIZABLE = new Acceptor<Any<?>>() {
        public boolean test(Any<?> src) { return isSerializable(src); }
    };
    
    public static boolean matchMask(Any<?> src, Any<?> mask) {
        if(SSP_TYPE_ARRAY == src.type()) { 
            return false;                                        // src=[]
        } else if(SSP_TYPE_OBJECT == src.type()) {               // src={}
            if(SSP_TYPE_ARRAY == mask.type()) {
                return false;                                    // src={} mask=[]
            } else if(SSP_TYPE_OBJECT == mask.type()) {          // src={} mask={}
                outer: for(int i = 0; i < mask.size(); ++i) {
                    final Any<?> srcField = src.item(mask.nameOf(i));
                    if(null != srcField) {
                        final Any<?> maskField = mask.item(i);
                        if(SSP_TYPE_ARRAY == maskField.type()) { // src={field:?} mask={field:[]}
                            for(int m = 0; m < maskField.size(); ++m) {
                                if(srcField.equals(maskField)) {
                                    continue outer;              // src={field:value1} mask={field:[value0,value1,value2]}
                                }
                            }
                            return false;
                        } else if(SSP_TYPE_OBJECT == maskField.type()) {
                            return false;                        // src={field:?} mask={field:{}}
                        } else if(!srcField.equals(maskField)) {
                            return false;                        // src={field:?} mask={field:value}
                        }
                    }
                }
                return true;
            } else {
                return false;                                    // src={} mask=value
            }
        } else {
            return false;                                        // src=value
        }
    }
    
    public static final Mapper<String, Any<?>> MAPPER_ANY_AS_STRING = new Mapper<String, Any<?>>() {
        public String apply(Any<?> value) { return value.asString(); }
    };

    public static final Mapper<Object, Any<?>> MAPPER_ANY_VALUE = new Mapper<Object, Any<?>>() {
        public Object apply(Any<?> value) { return value.get(); }
    };

    public static final Mapper<Entry<String, Object>, Entry<String, Any<?>>> MAPPER_MAKE_ENTRY_ANY_VALUE = makeEntryValueMapper(MAPPER_ANY_VALUE);
    
    /**
     * 
     * @param parent
     * @param name
     * @param value
     * @return previous value or null
     */
    public static final <T> Any<T> addUpdate(Any<?> parent, String name, Any<T> value) {
        @SuppressWarnings("unchecked")
        final Any<T> result = (Any<T>) parent.item(name);
        if(null != result) {
            parent.update(name, value);
            return result;
        } else {
            parent.add(name, value);
            return null;
        }
    }
    
    public static final <T> Any<T> addUpdate(Any<?> parent, Named<T> name, Any<T> value) {
        return addUpdate(parent, name.id(), value);
    }
    
    public static final <CONTEXT, T> Any<T> addUpdate(LocalAny<CONTEXT, ?> parent, LocalNamed<CONTEXT, T> name, Any<T> value) {
        return addUpdate(parent, name.id(), value);
    }
    
    /**
     * 
     * @param parent
     * @param name
     * @param value
     * @return old existing or new added value
     */
    public static final <T> Any<T> addAbsent(Any<?> parent, String name, Any<T> value) {
        @SuppressWarnings("unchecked")
        final Any<T> result = (Any<T>) parent.item(name);
        if(null != result) {
            return result;
        } else {
            parent.add(name, value);
            return value;
        }
    }
    
    public static final <T> Any<T> addAbsent(Any<?> parent, Named<T> name, Any<T> value) {
        return addAbsent(parent, name.id(), value);
    }
    
    public static final <CONTEXT, T> Any<T> addAbsent(LocalAny<CONTEXT, ?> parent, LocalNamed<CONTEXT, T> name, Any<T> value) {
        return addAbsent(parent, name.id(), value);
    }
    
    static { Dummy.dummy(); }

}
