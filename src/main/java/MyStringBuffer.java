public class MyStringBuffer {
    private StringBuffer string;
    private StringBuilder sb;

    public MyStringBuffer() {
        this.string = new StringBuffer("");
    }

    public MyStringBuffer(StringBuilder sb) {
        this.sb = sb;
    }

    public StringBuilder getSb() {
        return sb;
    }

    public StringBuffer getString() {
        return string;
    }
}
