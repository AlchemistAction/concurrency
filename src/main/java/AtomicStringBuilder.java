import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicStringBuilder {
    public final AtomicInteger counter = new AtomicInteger();
    private final AtomicReference<String> sbRef;

    public AtomicStringBuilder() {
        this.sbRef = new AtomicReference<>("");
    }

    public AtomicReference<String> getSb() {
        return sbRef;
    }

    public void append(String string) {
        this.sbRef.getAndUpdate(ref -> {
            counter.getAndIncrement();
            if (ref.length() < 128) {
                return ref + string;
            } else {
                String s = ref + string;
               return s.substring(s.length() - 128);
            }
        });
    }
}
