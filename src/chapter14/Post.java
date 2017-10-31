package chapter14;

/* February 2017, CS211, W.P. Iverson, instructor and author
 * this is a start at testing code for Chapter 14 assignment
 * other tests can and will happen....
 */
public class Post {
    public static void main(String[] args) {
	// store1 some dates so they can be reused
        CalendarDate[] store1 = {new CalendarDate(1,2,10), new CalendarDate(1,1,10), new CalendarDate(12,30,10)};
        CalendarDate[] store2 = {new CalendarDate(1,2,10), new CalendarDate(1,3,10), new CalendarDate(12,30,10)};
        Stack<CalendarDate> testAll = new Stack<CalendarDate>();
        Stack<CalendarDate> testAll2 = new Stack<CalendarDate>();
        Stack<CalendarDate> testEmpty = new Stack<>();

        for (CalendarDate i: store1) testAll.push(i); // build a Stack
        for (CalendarDate i: store2) testAll2.push(i); // build a second Stack

        System.out.println("Original: " + testAll);
        System.out.println("Original 2: " + testAll2);
        System.out.println("Stuttered: " + Chapter14.stutter(testAll)); // 6 dates
        System.out.println("Original: " + testAll);
        System.out.println("Empty stutter: " + Chapter14.stutter(testEmpty));
	System.out.println("Equals itself? " + Chapter14.equals(testAll,testAll)); // true
        System.out.println("Original stack: " + testAll);
	System.out.println("Equals non-matching? " + Chapter14.equals(testAll,testAll2));
        System.out.println("Original stack1: " + testAll);
        System.out.println("Original stack2: " + testAll2);
	System.out.println("Sorted? " + Chapter14.isSorted(testAll2)); // false
        System.out.println("Original: " + testAll2);
        System.out.println("Empty sort: " + Chapter14.isSorted(testEmpty));
	for (int i=1;i<=9;i++) testAll.push(new CalendarDate(1,1,10));
	Chapter14.removeMin(testAll);
	while (!testAll.empty())
           System.out.println(testAll.pop().longDate()); // only 2 remain
        System.out.println(Chapter14.removeMin(testEmpty));
    }
}
