import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testStringBuffer() {
        MyStringBuffer myStringBuffer1 = new MyStringBuffer();
        Random random = new Random();
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> random.nextInt(10));
        infiniteStream.parallel().limit(1000000).forEach(integer -> myStringBuffer1.getString().append(integer));
        System.out.println(myStringBuffer1.getString());
        assertEquals(1000000, myStringBuffer1.getString().length());

        MyStringBuffer myStringBuffer2 = new MyStringBuffer();
        String[] strings = {"1", "2", "3", "4", "5"};
        Arrays.stream(strings).parallel().forEach(st -> myStringBuffer2.getString().append(st));
        System.out.println(myStringBuffer2.getString());
        assertEquals(5, myStringBuffer2.getString().length());
    }

    @Test
    public void testStringBuilderFail() {
        MyStringBuffer myStringBuffer1 = new MyStringBuffer(new StringBuilder(""));
        Random random = new Random();
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> random.nextInt(10));
        infiniteStream.parallel().limit(1000000).forEach(integer -> myStringBuffer1.getSb().append(integer));
        System.out.println(myStringBuffer1.getSb());
        assertNotEquals(1000000, myStringBuffer1.getSb().length());
    }

    @Test
    public void testAtomicStringRef() {
        AtomicString atomicString1 = new AtomicString();
        Random random = new Random();
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> random.nextInt(10));
        infiniteStream.parallel().limit(100000).forEach(integer -> atomicString1.append(String.valueOf(integer)));
        System.out.println(atomicString1.getStRef().get());
        assertEquals(100000, atomicString1.getStRef().get().length());

        AtomicString atomicString2 = new AtomicString();
        String[] strings = {"1", "2", "3", "4", "5"};
        Arrays.stream(strings).parallel().forEach(integer -> atomicString2.append(String.valueOf(integer)));
        System.out.println(atomicString2.getStRef().get());
        assertEquals(5, atomicString2.getStRef().get().length());
    }

    @Test
    public void testAtomicStringBuilderRef() {
        AtomicStringBuilder atomicStringBuilder = new AtomicStringBuilder();
        Random random = new Random();
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> random.nextInt(10));
        infiniteStream.parallel().limit(100000).forEach(integer -> atomicStringBuilder.append(String.valueOf(integer)));

        System.out.println(atomicStringBuilder.getSb().get());
        System.out.println(atomicStringBuilder.counter.get());
        assertEquals(128, atomicStringBuilder.getSb().get().length());
    }
}