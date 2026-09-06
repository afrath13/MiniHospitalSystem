import java.util.Scanner;

public class HospitalSystem {

    private static Scanner scanner = new Scanner(System.in);

    private static PatientBST patientBST = new PatientBST();

    private static EmergencyQueue emergencyQueue =
            new EmergencyQueue(20);

    private static TreatmentStack treatmentStack =
            new TreatmentStack(20);

    public static void main(String[] args) {

        int choice;

        do {

            displayMainMenu();

            choice = getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    deletePatient();
                    break;

                case 4:
                    patientBST.displayInOrder();
                    break;

                case 5:
                    addEmergencyPatient();
                    break;

                case 6:
                    treatNextPatient();
                    break;

                case 7:
                    emergencyQueue.displayQueue();
                    break;

                case 8:
                    completeTreatment();
                    break;

                case 9:
                    treatmentStack.pop();
                    break;

                case 10:
                    treatmentStack.displayStack();
                    break;

                case 11:
                    manageVisitHistory();
                    break;

                case 0:
                    System.out.println(
                            "Thank you for using the Hospital System."
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);

        scanner.close();
    }

    // ================================
    // MAIN MENU
    // ================================

    private static void displayMainMenu() {

        System.out.println("\n======================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT");
        System.out.println("======================================");

        System.out.println("1. Register New Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display Patients (BST In-order)");

        System.out.println("\n--- Emergency Queue ---");

        System.out.println("5. Add Patient to Emergency Queue");
        System.out.println("6. Treat Next Emergency Patient");
        System.out.println("7. Display Emergency Queue");

        System.out.println("\n--- Treatment Stack ---");

        System.out.println("8. Complete Treatment");
        System.out.println("9. Remove Latest Treatment Record");
        System.out.println("10. Display Treatment History");

        System.out.println("\n--- Visit History ---");

        System.out.println("11. Manage Patient Visit History");

        System.out.println("\n0. Exit");

        System.out.println("======================================");
    }

    // ================================
    // PATIENT REGISTRATION
    // ================================

    private static void registerPatient() {

        System.out.println("\n===== REGISTER PATIENT =====");

        int id = getInt("Enter Patient ID: ");

        if (patientBST.search(id) != null) {

            System.out.println(
                    "Patient ID already exists."
            );

            return;
        }

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        int age = getInt("Enter Age: ");

        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(
                id,
                name,
                age,
                contact,
                condition
        );

        patientBST.insert(patient);
    }

    // ================================
    // SEARCH PATIENT
    // ================================

    private static void searchPatient() {

        int id = getInt("Enter Patient ID to search: ");

        Patient patient = patientBST.search(id);

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

        } else {

            System.out.println(
                    "Patient found:"
            );

            patient.displayPatient();
        }
    }

    // ================================
    // DELETE PATIENT
    // ================================

    private static void deletePatient() {

        int id = getInt("Enter Patient ID to delete: ");

        patientBST.delete(id);
    }

    // ================================
    // EMERGENCY QUEUE
    // ================================

    private static void addEmergencyPatient() {

        int id = getInt(
                "Enter Patient ID to add to emergency queue: "
        );

        Patient patient = patientBST.search(id);

        if (patient == null) {

            System.out.println(
                    "Patient does not exist."
            );

            return;
        }

        emergencyQueue.enqueue(patient);
    }

    private static void treatNextPatient() {

        Patient patient = emergencyQueue.dequeue();

        if (patient != null) {

            System.out.println(
                    "\nPatient selected for treatment:"
            );

            patient.displayPatient();
        }
    }

    // ================================
    // TREATMENT
    // ================================

    private static void completeTreatment() {

        int patientId = getInt(
                "Enter Patient ID: "
        );

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        System.out.print(
                "Enter Treatment Date: "
        );

        String date = scanner.nextLine();

        System.out.print(
                "Enter Doctor Name: "
        );

        String doctor = scanner.nextLine();

        System.out.print(
                "Enter Treatment Description: "
        );

        String description = scanner.nextLine();

        int treatmentId = getInt(
                "Enter Treatment ID: "
        );

        TreatmentRecord record =
                new TreatmentRecord(
                        treatmentId,
                        patient,
                        date,
                        doctor,
                        description
                );

        treatmentStack.push(record);
    }

    // ================================
    // VISIT HISTORY
    // ================================

    private static void manageVisitHistory() {

        int patientId = getInt(
                "Enter Patient ID: "
        );

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println(
                    "Patient not found."
            );

            return;
        }

        int choice;

        do {

            System.out.println(
                    "\n===== VISIT HISTORY ====="
            );

            System.out.println("1. Add Visit");
            System.out.println("2. Remove Visit");
            System.out.println("3. Search Visit");
            System.out.println("4. Display Visits");
            System.out.println("0. Back");

            choice = getInt("Enter choice: ");

            switch (choice) {

                case 1:
                    addVisit(patient);
                    break;

                case 2:
                    removeVisit(patient);
                    break;

                case 3:
                    searchVisit(patient);
                    break;

                case 4:
                    patient.getVisitHistory()
                           .displayVisits();
                    break;

                case 0:
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }

        } while (choice != 0);
    }

    private static void addVisit(Patient patient) {

        int visitId = getInt(
                "Enter Visit ID: "
        );

        System.out.print(
                "Enter Visit Date: "
        );

        String date = scanner.nextLine();

        System.out.print(
                "Enter Doctor Name: "
        );

        String doctor = scanner.nextLine();

        System.out.print(
                "Enter Diagnosis: "
        );

        String diagnosis = scanner.nextLine();

        System.out.print(
                "Enter Treatment: "
        );

        String treatment = scanner.nextLine();

        Visit visit = new Visit(
                visitId,
                date,
                doctor,
                diagnosis,
                treatment
        );

        patient.getVisitHistory()
               .addVisit(visit);
    }

    private static void removeVisit(Patient patient) {

        int visitId = getInt(
                "Enter Visit ID to remove: "
        );

        patient.getVisitHistory()
               .removeVisit(visitId);
    }

    private static void searchVisit(Patient patient) {

        int visitId = getInt(
                "Enter Visit ID to search: "
        );

        patient.getVisitHistory()
               .searchVisit(visitId);
    }

    // ================================
    // INPUT METHOD
    // ================================

    private static int getInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                int value = Integer.parseInt(
                        scanner.nextLine()
                );

                return value;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }
}