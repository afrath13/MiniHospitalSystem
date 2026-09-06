public class TreatmentRecord {

    private int treatmentId;
    private Patient patient;
    private String treatmentDate;
    private String doctorName;
    private String treatmentDescription;

    public TreatmentRecord(int treatmentId,
                           Patient patient,
                           String treatmentDate,
                           String doctorName,
                           String treatmentDescription) {

        this.treatmentId = treatmentId;
        this.patient = patient;
        this.treatmentDate = treatmentDate;
        this.doctorName = doctorName;
        this.treatmentDescription = treatmentDescription;
    }

    public void displayRecord() {

        System.out.println("--------------------------------");
        System.out.println("Treatment ID : " + treatmentId);
        System.out.println(
                "Patient ID   : " + patient.getPatientId()
        );
        System.out.println(
                "Patient Name : " + patient.getPatientName()
        );
        System.out.println(
                "Date         : " + treatmentDate
        );
        System.out.println(
                "Doctor       : " + doctorName
        );
        System.out.println(
                "Treatment    : " + treatmentDescription
        );
        System.out.println("--------------------------------");
    }
}