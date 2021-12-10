import java.util.concurrent.atomic.AtomicReference;

public class AtomicString {
    private final AtomicReference<String> stRef;

    public AtomicString() {
        this.stRef = new AtomicReference<>("");
    }

    public AtomicReference<String> getStRef() {
        return stRef;
    }

    public void append(String string) {
        this.stRef.getAndUpdate(ref -> ref.concat(string));
    }
}
