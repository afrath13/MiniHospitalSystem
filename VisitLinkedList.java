public class VisitLinkedList {

    private VisitNode head;

    // Node for the linked list
    private class VisitNode {

        Visit visit;
        VisitNode next;

        VisitNode(Visit visit) {
            this.visit = visit;
            this.next = null;
        }
    }

    // Add a new visit
    public void addVisit(Visit visit) {

        VisitNode newNode = new VisitNode(visit);

        if (head == null) {
            head = newNode;
            System.out.println("Visit added successfully.");
            return;
        }

        VisitNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        System.out.println("Visit added successfully.");
    }

    // Remove a visit using Visit ID
    public void removeVisit(int visitId) {

        if (head == null) {
            System.out.println("Visit history is empty.");
            return;
        }

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            System.out.println("Visit removed successfully.");
            return;
        }

        VisitNode current = head;

        while (current.next != null) {

            if (current.next.visit.getVisitId() == visitId) {

                current.next = current.next.next;

                System.out.println("Visit removed successfully.");
                return;
            }

            current = current.next;
        }

        System.out.println("Visit not found.");
    }

    // Search for a visit
    public void searchVisit(int visitId) {

        VisitNode current = head;

        while (current != null) {

            if (current.visit.getVisitId() == visitId) {

                System.out.println("Visit found:");
                current.visit.displayVisit();
                return;
            }

            current = current.next;
        }

        System.out.println("Visit not found.");
    }

    // Display all visits
    public void displayVisits() {

        if (head == null) {
            System.out.println("No visit history available.");
            return;
        }

        VisitNode current = head;

        System.out.println("\n===== PATIENT VISIT HISTORY =====");

        while (current != null) {

            current.visit.displayVisit();

            current = current.next;
        }
    }
}