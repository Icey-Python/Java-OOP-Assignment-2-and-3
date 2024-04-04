import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class Patient {
    private String name;
    private int id;

    public Patient(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

class Hospital {
    private ArrayList<Patient> admittedPatients;

    public Hospital() {
        admittedPatients = new ArrayList<>();
    }

    public void admitPatient(Patient patient) {
        admittedPatients.add(patient);
        System.out.println("Patient " + patient.getName() + " admitted successfully.");
    }

    public void dischargePatient(int patientId) {
        for (Patient patient : admittedPatients) {
            if (patient.getId() == patientId) {
                admittedPatients.remove(patient);
                System.out.println("Patient " + patient.getName() + " discharged successfully.");
                return;
            }
        }
        System.out.println("Patient with ID " + patientId + " not found.");
    }

    public void displayAdmittedPatients() {
        System.out.println("Admitted Patients:");
        for (Patient patient : admittedPatients) {
            System.out.println("ID: " + patient.getId() + ", Name: " + patient.getName());
        }
    }
}

public class HospitalManagementApp extends JApplet implements ActionListener {
    private Hospital hospital;
    private JTextField nameField, idField;
    private JLabel statusLabel;
    private JButton admitButton, dischargeButton;

    public void init() {
        hospital = new Hospital();

        // Create UI components
        JLabel titleLabel = new JLabel("Hospital Management System");
        nameField = new JTextField(20);
        idField = new JTextField(20);
        admitButton = new JButton("Admit Patient");
        dischargeButton = new JButton("Discharge Patient");
        statusLabel = new JLabel();

        // Set up event handlers
        admitButton.addActionListener(this);
        dischargeButton.addActionListener(this);

        // Create layout
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Patient Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Patient ID:"));
        panel.add(idField);
        panel.add(admitButton);
        panel.add(dischargeButton);
        panel.add(new JLabel());
        panel.add(statusLabel);

        // Add components to the applet
        add(titleLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == admitButton) {
            admitPatient();
        } else if (e.getSource() == dischargeButton) {
            dischargePatient();
        }
    }

    private void admitPatient() {
        String name = nameField.getText();
        int id = Integer.parseInt(idField.getText());
        Patient patient = new Patient(name, id);
        hospital.admitPatient(patient);
        statusLabel.setText("Patient " + name + " admitted successfully.");
    }

    private void dischargePatient() {
        int id = Integer.parseInt(idField.getText());
        hospital.dischargePatient(id);
        statusLabel.setText("Patient discharged.");
    }
}
