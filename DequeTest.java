public class DequeTest {
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();

        System.out.println("Initial empty deque:");
        deque.printDeque();
        assert deque.isEmpty();
        assert deque.size() == 0;

        System.out.println("\nAdding 10 to front:");
        deque.addFirst(10);
        deque.printDeque();
        assert deque.size() == 1;

        System.out.println("\nAdding 20 to back:");
        deque.addLast(20);
        deque.printDeque();
        assert deque.size() == 2;

        System.out.println("\nAdding 5 to front:");
        deque.addFirst(5);
        deque.printDeque();
        assert deque.size() == 3;

        System.out.println("\nRemoving first (expect 5):");
        int removedFirst = deque.removeFirst();
        assert removedFirst == 5;
        deque.printDeque();

        System.out.println("\nRemoving last (expect 20):");
        int removedLast = deque.removeLast();
        assert removedLast == 20;
        deque.printDeque();

        System.out.println("\nRemoving last (expect 10):");
        removedLast = deque.removeLast();
        assert removedLast == 10;
        deque.printDeque();
        assert deque.isEmpty();

        System.out.println("\nRe-adding values to test resizing and reuse:");
        for (int i = 1; i <= 10; i++) {
            deque.addLast(i);
        }
        deque.printDeque();
        assert deque.size() == 10;

        System.out.println("\nRemoving all elements:");
        while (!deque.isEmpty()) {
            System.out.print(deque.removeFirst() + " ");
        }
        System.out.println();
        deque.printDeque();
        assert deque.isEmpty();

        System.out.println("\nAll tests passed.");
    }
}
