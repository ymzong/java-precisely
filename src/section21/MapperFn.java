package section21;

/**
 * This interface encapsulates a function that maps type T to U, and it can be used for funciton-as-value as in
 * functional programming.
 *
 * Java 8 now includes Function<T,U> under java.util.function package.
 */
public interface MapperFn<T,U> {

    U call(T arg);

}
