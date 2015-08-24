package com.speakingfish.protocol.ssp;

public interface AnyArray extends Any<AnyArray> {
    int          size           ();
    int    insert (int    index, Any<?> value);
    int    add    (              Any<?> value);
    Any<?> update (int    index, Any<?> value);
    Any<?> remove (int    index              );
  //String nameOf (int    index              );
    Any<?> item   (int    index              );
}