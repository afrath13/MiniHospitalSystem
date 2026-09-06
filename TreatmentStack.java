public class TreatmentStack {

    private TreatmentRecord[] stack;

    private int top;
    private int capacity;

    public TreatmentStack(int capacity) {

        this.capacity = capacity;

        stack = new TreatmentRecord[capacity];

        top = -1;
    }

    // Push
    public void push(TreatmentRecord record) {

        if (isFull()) {

            System.out.println(
                    "Treatment stack is full."
            );

            return;
        }

        top++;

        stack[top] = record;

        System.out.println(
                "Treatment record added."
        );
    }

    // Pop
    public TreatmentRecord pop() {

        if (isEmpty()) {

            System.out.println(
                    "Treatment stack is empty."
            );

            return null;
        }

        TreatmentRecord record = stack[top];

        stack[top] = null;

        top--;

        System.out.println(
                "Most recent treatment record removed."
        );

        return record;
    }

    // Display
    public void displayStack() {

        if (isEmpty()) {

            System.out.println(
                    "No treatment records available."
            );

            return;
        }

        System.out.println(
                "\n===== TREATMENT HISTORY ====="
        );

        for (int i = top; i >= 0; i--) {

            stack[i].displayRecord();
        }
    }

    public boolean isEmpty() {

        return top == -1;
    }

    public boolean isFull() {

        return top == capacity - 1;
    }
}