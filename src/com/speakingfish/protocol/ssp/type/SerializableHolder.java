package com.speakingfish.protocol.ssp.type;

import java.io.ByteArrayInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import com.speakingfish.common.exception.wrapped.java.io.WrappedEOFException;

public class SerializableHolder implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4143300115107698452L;
    
    final byte[] _value;
    
    public SerializableHolder(byte[] value) {
        _value = value;
    }

    protected Object readResolve() throws ObjectStreamException {
        final ByteArrayInputStream in = new ByteArrayInputStream(_value);
        try {
            return com.speakingfish.protocol.ssp.bin.Helper.readAny(in);
        } catch(WrappedEOFException e) {
            throw new StreamCorruptedException("Unexpected EOF.");
        }
    };
}