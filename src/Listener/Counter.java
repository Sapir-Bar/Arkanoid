// 211540562 Sapir Bar
package Listener;
/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 28.05.2023
 * the class is in charge of representing the current value of a countable object. */
public class Counter {
    private int count;
    /**
     * Constructor - creating a new instance of Counter given a current count.
     * @param count integer
     */
    public Counter(int count) {
        this.count = count;
    }
    /**
     * add number to current count.
     * @param number integer
     */
    public void increase(int number) {
        this.count += number;
    }
    /**
     *  subtract number from current count.
     * @param number integer
     */
    public void decrease(int number) {
        this.count -= number;
    }
    /**
     * getter of current count.
     * @return current count
     */
    public int getValue() {
        return this.count;
    }
    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }
}