package com.speakingfish.protocol.ssp;

import java.util.List;

public interface AnyComplex<TYPE extends AnyComplex> extends Any<TYPE> {
    int          size           ();
    List<              Any<?> > values ();

}
