/*
 * Stacks for real:  Submit Chapter14.java with some Exercise solutions:
 * Work some Exercises in the BJP text and adhere to only using Stack Operations.
 * Create a Class called Chapter14.java with public static methods that provide solutions to the
 * following Exercises in the text.with the following significant modifications, so please read carefully.  
 * Replace <Integer> with <CalendarDate> as provided here--> CalendarDate.java
 * Please notice this is similar to the Chapter 10 CalendarDate, but I have now upgraded 
 * to implement Comparable, Comparator, added a year field, and a longDate() output.
 * Where the text exercises say "may use" a queue, change that to MUST use a queue as auxiliary storage. 
 * All these Exercises require you use only the basic Stack methods such as push, peek, and pop.  
 * So you cannot simply sort it, nor access internal elements like a List, you must write a bunch of 
 * pop, push, peek, etc... loops.  To enforce this requirement, I will use the Iverson version of the 
 * Stack Class
 * So DO NOT import java.util.Stack;  Other Classes in java.util are OK, like you need those for the Queue's.  
 * I will use <CalendarDate> in each case for testing purposes, so use compareTo as appropriate.
 *
 * Stacks can be empty, so don't let your code blow up in those cases.  I see no need to intentionally 
 * throw Exceptions in these exercises.  For example, on #2, if the Stack is empty, just return an empty Stack.
 * If we examine two Stack objects (#5), be able to handle the case that they are the same object.  
 * And two empty Stack objects are equal, yes.
 *
 * 2. stutter, modify to return the stuttered Stack, and leave the original Stack unchanged.
 * 5. equals, use the compareTo method for comparisons, leave the original Stacks unchanged.
 * 15. isSorted, use compareTo to evaluate if sorted, and leave the original Stack unchanged.
 * 19. removeMin, use compareTo for evaluating the minimum, if the original Stack is empty you can return null.
 *
 * Only the last method here will modify the Stack passed in as a parameter.
 * Do NOT use Vector operations (that's what Oracle Stack does).  Do use my Stack.java
 * But I do encourage whatever Queue you desire for auxiliary storage, a LinkedList, Deque, or other...
 * Here's some test code for those who like that Post.java
 * Created by David Johnson, <Date>
 * for CS211 course, Bellevue College
 */
package chapter14;

import java.util.LinkedList;
import java.util.Queue;

public class Chapter14 {

    // Exercise 2
    // Replaces every value in the stack with two occurrences of that value
    // Preserves original relative order
    // Does not modify original stack
    // Uses a single queue as auxiliary storage
    public static Stack<CalendarDate> stutter(Stack<CalendarDate> dates) {
        // Auxiliary storage
        Queue<CalendarDate> temp = new LinkedList<>();
        // To return to client
        Stack<CalendarDate> result = new Stack<>();
        
        // Pull numbers out of the stack and double them in a temporary queue
        while (!dates.empty()) {
            temp.add(dates.peek());
            temp.add(dates.peek());
            // Storing originals here so we can rebuild the stack that was passed in
            result.push(dates.pop());
        }
        
        // Now put the numbers back into the stack
        while (!result.empty()) {
            dates.push(result.pop());
        }
        
        // And now build the result stack to pass back
        while (!temp.isEmpty()) {
            result.push(temp.remove());
        }
        
        // Stack became reversed through all of this, so we need to reverse it again
        // to get it to match how it was passed in.
        return reverseStack(result);
    }

    // Exercise 5
    // Returns true if the two stacks store exactly the same sequence of integer values in the same order
    // Uses a single stack as auxiliary storage
    static boolean equals(Stack<CalendarDate> stack1, Stack<CalendarDate> stack2) {
        // Auxiliary storage
        Queue<CalendarDate> temp = new LinkedList<>();
        // Equality flag
        boolean equal = true;
        
        // If the stacks aren't the same size, we don't even need to continue
        if (stack1.size() != stack2.size()) {
            return false;
        }
        
        // If we're comparing the same stack, let's just return true
        // Otherwise, we'll destroy the integrity of the original
        if (stack1 == stack2) {
            return true;
        }
        
        // We only need to test one of the stacks, since we know they're the same size at this point
        while (!stack1.empty()) {
            // Move the stacks to auxiliary storage
            temp.add(stack1.pop());
            temp.add(stack2.pop());
        }
        
        // Now we'll start comparing things
        while (!temp.isEmpty()) {
            // Grab the dates
            CalendarDate d1 = temp.remove();
            CalendarDate d2 = temp.remove();
            // Compare the dates
            if (d1.compareTo(d2) != 0) {
                equal = false;
            }
            // Rebuild the original stacks
            stack1.push(d1);
            stack2.push(d2);
        }
        
        // The stacks are reversed, so we need to fix that
        // Can't simply use the helper method here since the compiler sees that
        // the variables are unused afterword and considers the whole thing a
        // no-op.  So have to force things in here.
        // Definitely not the most O friendly way of doing things, but I'm not
        // sure what else to do about it.
        while (!stack1.empty()) {
            temp.add(stack1.pop());
        }
        while (!temp.isEmpty()) {
            stack1.push(temp.remove());
        }
        while (!stack2.empty()) {
            temp.add(stack2.pop());
        }
        while (!temp.isEmpty()) {
            stack2.push(temp.remove());
        }
        
        return equal;
    }
    
    // Exercise 15
    // Returns true if stack is sorted in ascending order from top to bottom
    // Uses one queue as auxiliary storage
    static boolean isSorted(Stack<CalendarDate> stack) {
        // Auxiliary Storage
        Queue<CalendarDate> temp = new LinkedList<>();
        // Sorted flag
        boolean sorted = true;
        
        while (!stack.empty()) {
            temp.add(stack.pop());
        }
        
        while (!temp.isEmpty()) {
            CalendarDate d = temp.remove();
            // Compare returns > 0 means current is >= next, which means we're still sorted
            // (Because the queue is built in reverse order)
            if (!(temp.peek() == null)) {
                if (d.compareTo(temp.peek()) < 0) {
                    sorted = false;
                }
            }
            // Put the original stack back together
            stack.push(d);
        }
        
        // Stack is reversed, so let's fix that
        while (!stack.empty()) {
            temp.add(stack.pop());
        }
        while (!temp.isEmpty()) {
            stack.push(temp.remove());
        }
        
        return sorted;
    }

    // Exercise 19
    // Removes and returns the smallest value in the stack
    // Uses one queue as auxiliary storage
    static CalendarDate removeMin(Stack<CalendarDate> stack) {
        // Auxiliary storage
        Queue<CalendarDate> temp = new LinkedList<>();
        
        // Just return null if the passed in stack is empty
        if (stack.empty()) {
            return null;
        }
        // Arbitrarily set the smallest value to the first item in the stack
        CalendarDate min = stack.peek();
        
        while (!stack.empty()) {
            CalendarDate d = stack.pop();
            // if current is smaller than min, then set min to current
            if (d.compareTo(min) < 0) {
                min = d;
            }
            temp.add(d);
        }
        
        while (!temp.isEmpty()) {
            CalendarDate d = temp.remove();
            // If this value matches minimun, don't put it back in the stack
            if (!(d.compareTo(min) == 0)) {
                stack.push(d);
            }
        }

        // Stack is reversed, so let's fix that
        while (!stack.empty()) {
            temp.add(stack.pop());
        }
        while (!temp.isEmpty()) {
            stack.push(temp.remove());
        }
        
        return min;
    }
    
    // Helper method
    public static Stack<CalendarDate> reverseStack(Stack<CalendarDate> dates) {
        Stack<CalendarDate> result = new Stack<>();
        while (!dates.empty()) {
            result.push(dates.pop());
        }
        
        return result;
    }
 }
