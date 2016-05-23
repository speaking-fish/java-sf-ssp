package com.speakingfish.protocol.ssp;

import java.util.Map.Entry;

import com.speakingfish.common.value.Mutable;
import com.speakingfish.protocol.ssp.path.AnyByIndexImpl;
import com.speakingfish.protocol.ssp.path.AnyByNameImpl;
import com.speakingfish.protocol.ssp.path.AnyEndOfPathImpl;

import static com.speakingfish.common.value.Mutables.*;

public class Paths {

    public static <T, T_Any extends Any<T>> AnyEndOfPath<T, T_Any> path() { return new AnyEndOfPathImpl<T, T_Any>((T_Any) null); }
    
    public static <
        R, R_Any  extends Any<R>,
        T, T_Any  extends Any<T>,
        N, N_Any  extends Any<N>,
        T_SubPath extends AnyPath<
            R, R_Any,
            N, N_Any
        >
    > AnyByIndex<
        R, R_Any,
        T, T_Any,
        N, N_Any,
        T_SubPath
    > path(int index, T_SubPath next) {
        return (AnyByIndex) new AnyByIndexImpl<
            R, R_Any,
            T, T_Any,
            N, N_Any,
            AnyPathValue<
                R, R_Any,
                N, N_Any
            >
        >((T_Any) null, index, (AnyPathValue) next);
    }
    
    public static <
        R, R_Any  extends Any<R>,
        T, T_Any  extends Any<T>,
        N, N_Any  extends Any<N>,
        T_SubPath extends AnyPath<
            R, R_Any,
            N, N_Any
        >
    > AnyByName<
        R, R_Any,
        T, T_Any,
        N, N_Any,
        T_SubPath
    > path(String name, T_SubPath next) {
        return (AnyByName) new AnyByNameImpl<
            R, R_Any,
            T, T_Any,
            N, N_Any,
            AnyPathValue<
                R, R_Any,
                N, N_Any
            >
        >((T_Any) null, name, (AnyPathValue) next);
    }

    public static <T, T_Any extends Any<T>> AnyEndOfPathValue<T, T_Any> pathValue(T_Any value) { return new AnyEndOfPathImpl<T, T_Any>(value); }
    
    public static <
        R, R_Any  extends Any<R>,
        T, T_Any  extends Any<T>,
        N, N_Any  extends Any<N>,
        T_SubPath extends AnyPathValue<
            R, R_Any,
            N, N_Any
        >
    > AnyByIndexValue<
        R, R_Any,
        T, T_Any,
        N, N_Any,
        T_SubPath
    > pathValue(T_Any value, int index, T_SubPath next) {
        return new AnyByIndexImpl<
            R, R_Any,
            T, T_Any,
            N, N_Any,
            T_SubPath
        >(value, index, next);
    }
    
    public static <
        R, R_Any  extends Any<R>,
        T, T_Any  extends Any<T>,
        N, N_Any  extends Any<N>,
        T_SubPath extends AnyPathValue<
            R, R_Any,
            N, N_Any
        >
    > AnyByNameValue<
        R, R_Any,
        T, T_Any,
        N, N_Any,
        T_SubPath
    > pathValue(T_Any value, String name, T_SubPath next) {
        return new AnyByNameImpl<
            R, R_Any,
            T, T_Any,
            N, N_Any,
            T_SubPath
        >(value, name, next);
    }
    
    public static <
        R, R_Any  extends Any<R>,
        T, T_Any  extends Any<T>
    > Any<?> forwardPath(
        final T_Any src,
        final int index,
        final AnyPath<R, R_Any, T, T_Any> path
    ) {
        final Mutable<Any<?>> result = mutable();
        final PathValueVisitor<R, R_Any> visitor = new PathValueVisitor<R, R_Any>() {
            int _level = 0;

            public <
                T, T_Any extends Any<T>,
                T_SubPath extends AnyPath<
                    R, R_Any,
                    T, T_Any
                >
            > void visitIndex(
                AnyPath<R, R_Any, T, T_Any> path,
                T_Any value,
                int index,
                T_SubPath next
            ) {
                visitValue(path, value);
            }

            public <
                T, T_Any extends Any<T>,
                T_SubPath extends AnyPath<
                    R, R_Any,
                    T, T_Any
                >
            > void visitField(
                AnyPath<R, R_Any, T, T_Any> path,
                T_Any value,
                String name,
                T_SubPath next
            ) {
                visitValue(path, value);
            }

            public <
                T, T_Any extends Any<T>
            > void visitValue(
                AnyPath<R, R_Any, T, T_Any> path,
                T_Any value
            ) {
                if(_level == index) {
                    result.set((Any<?>) value);
                }
                ++_level;
            }
        };
        path.visitForward(src, visitor);
        return result.get();
    }

    public static <
        R, R_Any  extends Any<R>,
        T, T_Any  extends Any<T>
    > Any<?> backwardPath(
        final T_Any src,
        final int index,
        final AnyPath<R, R_Any, T, T_Any> path
    ) {
        final Mutable<Any<?>> result = mutable();
        final PathValueVisitor<R, R_Any> visitor = new PathValueVisitor<R, R_Any>() {
            int _level = 0;
    
            public <
                T, T_Any extends Any<T>,
                T_SubPath extends AnyPath<
                    R, R_Any,
                    T, T_Any
                >
            > void visitIndex(
                AnyPath<R, R_Any, T, T_Any> path,
                T_Any value,
                int index,
                T_SubPath next
            ) {
                visitValue(path, value);
            }
    
            public <
                T, T_Any extends Any<T>,
                T_SubPath extends AnyPath<
                    R, R_Any,
                    T, T_Any
                >
            > void visitField(
                AnyPath<R, R_Any, T, T_Any> path,
                T_Any value,
                String name,
                T_SubPath next
            ) {
                visitValue(path, value);
            }
    
            public <
                T, T_Any extends Any<T>
            > void visitValue(
                AnyPath<R, R_Any, T, T_Any> path,
                T_Any value
            ) {
                if(_level == index) {
                    result.set((Any<?>) value);
                }
                ++_level;
            }
        };
        path.visitBackward(src, visitor);
        return result.get();
    }

    public static <
        Start_R/* extends AnyComplex<Start_R>*/, Start_R_Any extends Any<Start_R>,
        Sub_R  , Sub_R_Any   extends Any<Sub_R  >,
        Start_T, Start_T_Any extends Any<Start_T>,
        //Sub_T  , Sub_T_Any   extends Any<Sub_T  >,
        T_StartPath extends AnyPath<
            Start_R, Start_R_Any,
            Start_T, Start_T_Any
        >,
        T_SubPath extends AnyPath<
            Sub_R  , Sub_R_Any  ,
            Start_R, Start_R_Any
        >
    > AnyPath<
        Sub_R  , Sub_R_Any  ,
        Start_T, Start_T_Any
    > concat(
        T_StartPath start,
        T_SubPath   sub
    ) {
        final Mutable<AnyPath> current = mutable((AnyPath) sub);
        final PathVisitor<Start_R, Start_R_Any> start_visitor = new PathVisitor<Start_R, Start_R_Any>() {

            public <T, T_Any extends Any<T>, N, N_Any extends Any<N>> void visitIndex(
                AnyPath<Start_R, Start_R_Any, T, T_Any> path,
                int index,
                AnyPath<Start_R, Start_R_Any, N, N_Any> next
            ) {
                current.set(Paths.path(index, current.get()));
            }

            public <T, T_Any extends Any<T>, N, N_Any extends Any<N>> void visitField(
                AnyPath<Start_R, Start_R_Any, T, T_Any> path,
                String name,
                AnyPath<Start_R, Start_R_Any, N, N_Any> next
            ) {
                current.set(Paths.path(name, current.get()));
            }
            
            public void visitValue(
                AnyPath<Start_R, Start_R_Any, Start_R, Start_R_Any> path
            ) {
            }

        };
        start.visitBackward(start_visitor);
        return (AnyPath<Sub_R, Sub_R_Any, Start_T, Start_T_Any>) current.get();
    }
    
    public static <
        Start_R/* extends AnyComplex<Start_R>*/, Start_R_Any extends Any<Start_R>,
        Sub_R  , Sub_R_Any   extends Any<Sub_R  >,
        Start_T, Start_T_Any extends Any<Start_T>,
        //Sub_T  , Sub_T_Any   extends Any<Sub_T  >,
        T_StartPath extends AnyPathValue<
            Start_R, Start_R_Any,
            Start_T, Start_T_Any
        >,
        T_SubPath extends AnyPathValue<
            Sub_R  , Sub_R_Any  ,
            Start_R, Start_R_Any
        >
    > AnyPathValue<
        Sub_R  , Sub_R_Any  ,
        Start_T, Start_T_Any
    > concatValue(
        T_StartPath start,
        T_SubPath   sub
    ) {
        final Mutable<AnyPathValue> current = mutable((AnyPathValue) sub);
        final PathValueVisitor<Start_R, Start_R_Any> start_visitor = new PathValueVisitor<Start_R, Start_R_Any>() {
    
            public <
                T, T_Any extends Any<T>,
                T_SubPath extends AnyPath<
                    Start_R, Start_R_Any,
                    T, T_Any
                >
            > void visitIndex(
                AnyPath<Start_R, Start_R_Any, T, T_Any> path,
                T_Any value,
                int index,
                T_SubPath next
            ) {
                current.set(Paths.pathValue(value, index, current.get()));
            }

            public <
                T, T_Any extends Any<T>,
                T_SubPath extends AnyPath<
                    Start_R, Start_R_Any,
                    T, T_Any
                >
            > void visitField(
                AnyPath<Start_R, Start_R_Any, T, T_Any> path,
                T_Any value,
                String name,
                T_SubPath next
            ) {
                current.set(Paths.pathValue(value, name, current.get()));
            }

            public <
                T, T_Any extends Any<T>
            > void visitValue(
                AnyPath<Start_R, Start_R_Any, T, T_Any> path,
                T_Any value
            ) {
            }

    
        };
        start.visitBackward(start_visitor);
        return
            (AnyPathValue<
                Sub_R  , Sub_R_Any  ,
                Start_T, Start_T_Any
            >) current.get();
    }

}
