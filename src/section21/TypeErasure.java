package section21;

/**
 * This example demonstrates the effect of type erasure with a before-after comparison.
 *
 * The resulting bytecode for BeforeErasure<T> and AfterErasure is attached at the bottom (spoiler: they are identical).
 */
public class TypeErasure {

    public static void main(String[] argv) {
        BeforeErasure<Integer> be = new BeforeErasure<Integer>();
        be.set(Integer.valueOf(12));
        Integer beResult = be.get();

        AfterErasure ae = new AfterErasure();
        ae.set(Integer.valueOf(12));
        Integer aeResult = (Integer) ae.get();
    }
}

class BeforeErasure<T> {
    public T elem;

    public void set(T elem) {
        this.elem = elem;
    }

    public T get() {
        return elem;
    }
}

class AfterErasure {
    public Object elem;

    public void set(Object elem) {
        this.elem = elem;
    }

    public Object get() {
        return elem;
    }
}

/**
 * The bytecode below is returned from `javap -c -p {Before,After}Erasure`
 *
 * class section21.BeforeErasure<T> {
 *   public T elem;
 *
 *   section21.BeforeErasure();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public void set(T);
 *     Code:
 *        0: aload_0
 *        1: aload_1
 *        2: putfield      #2                  // Field elem:Ljava/lang/Object;
 *        5: return
 *
 *   public T get();
 *     Code:
 *        0: aload_0
 *        1: getfield      #2                  // Field elem:Ljava/lang/Object;
 *        4: areturn
 * }
 *
 * class section21.AfterErasure {
 *   public java.lang.Object elem;
 *
 *   section21.AfterErasure();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public void set(java.lang.Object);
 *     Code:
 *        0: aload_0
 *        1: aload_1
 *        2: putfield      #2                  // Field elem:Ljava/lang/Object;
 *        5: return
 *
 *   public java.lang.Object get();
 *     Code:
 *        0: aload_0
 *        1: getfield      #2                  // Field elem:Ljava/lang/Object;
 *        4: areturn
 * }
 *
 */