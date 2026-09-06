public class EmergencyQueue {

    private Patient[] queue;

    private int front;
    private int rear;
    private int size;
    private int capacity;

    public EmergencyQueue(int capacity) {

        this.capacity = capacity;

        queue = new Patient[capacity];

        front = 0;
        rear = -1;
        size = 0;
    }

    // Enqueue
    public void enqueue(Patient patient) {

        if (isFull()) {
            System.out.println("Emergency queue is full.");
            return;
        }

        rear = (rear + 1) % capacity;

        queue[rear] = patient;

        size++;

        System.out.println(
                "Patient added to emergency queue."
        );
    }

    // Dequeue
    public Patient dequeue() {

        if (isEmpty()) {

            System.out.println(
                    "Emergency queue is empty."
            );

            return null;
        }

        Patient patient = queue[front];

        queue[front] = null;

        front = (front + 1) % capacity;

        size--;

        System.out.println(
                "Patient removed from emergency queue."
        );

        return patient;
    }

    // Display queue
    public void displayQueue() {

        if (isEmpty()) {

            System.out.println(
                    "No patients are waiting."
            );

            return;
        }

        System.out.println(
                "\n===== EMERGENCY WAITING QUEUE ====="
        );

        int index = front;

        for (int i = 0; i < size; i++) {

            queue[index].displayPatient();

            index = (index + 1) % capacity;
        }
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public boolean isFull() {

        return size == capacity;
    }
}