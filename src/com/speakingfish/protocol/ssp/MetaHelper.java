package com.speakingfish.protocol.ssp;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import com.speakingfish.common.function.Mapper;

import static java.util.regex.Pattern.*;
import static java.util.EnumSet.*;
import static java.util.Collections.*;
import static com.speakingfish.common.Maps.*;
import static com.speakingfish.common.iterator.Iterators.*;

public class MetaHelper {

    public static enum Type {
        INVALID   (""         ) { @Override public <P, V> void visit(SimpleValueVisitor<P, V> t, P p, V v) { throw new UnknownError("Unknown meta"); } },
        OBJECT    ("object"   ) { @Override public <P, V> void visit(SimpleValueVisitor<P, V> t, P p, V v) { t.visitObject   (p, v); } },
        ARRAY     ("array"    ) { @Override public <P, V> void visit(SimpleValueVisitor<P, V> t, P p, V v) { t.visitArray    (p, v); } },
        STRING    ("string"   ) { @Override public <P, V> void visit(SimpleValueVisitor<P, V> t, P p, V v) { t.visitString   (p, v); } },
        INT       ("int"      ) { @Override public <P, V> void visit(SimpleValueVisitor<P, V> t, P p, V v) { t.visitInt      (p, v); } },
        FLOAT     ("float"    ) { @Override public <P, V> void visit(SimpleValueVisitor<P, V> t, P p, V v) { t.visitFloat    (p, v); } },
        DECIMAL   ("decimal"  ) { @Override public <P, V> void visit(SimpleValueVisitor<P, V> t, P p, V v) { t.visitDecimal  (p, v); } },
        BYTE_ARRAY("bytearray") { @Override public <P, V> void visit(SimpleValueVisitor<P, V> t, P p, V v) { t.visitByteArray(p, v); } };
        
        public static final Set<Type> FULL_SET = unmodifiableSet(allOf(Type.class));
        
        public static final Mapper<String, Type> MAPPER_TAG = new Mapper<String, Type>() {
            public String apply(Type src) { return src.name();}
            };
            
        public static final Map<String, Type> MAP_BY_TAG = unmodifiableSortedMap(collectMap(
            new TreeMap<String, Type>(String.CASE_INSENSITIVE_ORDER), 
            mapIterator(FULL_SET.iterator(), makeEntryKeyMapper(MAPPER_TAG))
            ));
        
        protected final String _tag;
        
        private Type(String tag) {
            _tag = tag;
        }
        
        public String tag() { return _tag; }
        
        public abstract <PROCEED, VALUE> void visit(SimpleValueVisitor<PROCEED, VALUE> visitor, PROCEED proceed, VALUE value);
    }
    
    public static final char CHAR_META_START = '$';
    
    public static final Pattern REGEXP_META_TYPE_NAME = compile("([A-Z][a-z]_)([A-Z][a-z]_[0-9])*");

    public static boolean isStartMetaAt(char meta, int offset, String key) {
        if(key.length() <= offset) return false;
        if(meta != key.charAt(offset)) return false;
        ++offset;
        if(key.length() == offset) return true;
        if(meta == key.charAt(offset)) return false;
        return true;
    }

    public static boolean isStartMetaAt(int offset, String key) {
        return isStartMetaAt(CHAR_META_START, offset, key);
    }
    
    public static Entry<String, String> splitKeyMeta(char meta, int offset, String srcKey) {
        int pos = srcKey.indexOf(meta, offset);
        if(pos < 0) {
            return keyValue(srcKey, "");
        }
        String destKey = srcKey.substring(offset, pos);
        int anchor = -1;
        while(true) {
            pos = pos + 1;
            if(srcKey.length() <= pos) {
                throw new IllegalArgumentException("Meta delimiter without meta.");
            }
            if(meta == srcKey.charAt(pos)) {
                anchor = pos;
                pos = srcKey.indexOf(meta, pos + 1);
                if(pos < 0) {
                    return keyValue(destKey + srcKey.substring(anchor), "");
                }
                destKey = destKey + srcKey.substring(anchor, pos);
            } else {
                return keyValue(destKey, srcKey.substring(pos));
            }
        }
    }

    public static Entry<String, String> splitKeyMeta(char meta,             String srcKey) { return splitKeyMeta(meta           , 0     , srcKey); }
    public static Entry<String, String> splitKeyMeta(           int offset, String srcKey) { return splitKeyMeta(CHAR_META_START, offset, srcKey); }
    public static Entry<String, String> splitKeyMeta(                       String srcKey) { return splitKeyMeta(CHAR_META_START, 0     , srcKey); }
    
    public static String extractMetaType(Entry<String, String> keyMeta) {
        return extractMetaType(keyMeta.getValue());
    }

    public static String extractMetaType(String meta) {
        final int endOfType = REGEXP_META_TYPE_NAME.matcher(meta).end();
        if(0 < endOfType) {
            return meta.substring(0, endOfType);
        }
        return "";
    }
    
}
