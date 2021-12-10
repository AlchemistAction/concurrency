import java.util.concurrent.atomic.AtomicReference;

public class AtomicStringBuilder {
    private final AtomicReference<StringBuilder> sbRef;

    public AtomicStringBuilder() {
        this.sbRef = new AtomicReference<>(new StringBuilder(""));
    }

    public AtomicReference<StringBuilder> getSb() {
        return sbRef;
    }

    public void append(String string) {
        this.sbRef.getAndUpdate(ref -> {
            if (ref.length() < 128) {
                ref.append(string);
            } else {
                ref.append(string).delete(0, ref.length() - 128);
            }
            return ref;
        });
    }
}
