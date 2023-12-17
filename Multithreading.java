import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Hospital 
{

    private List<Doctor> doctors;
    private List<Patient> patients;

    public Hospital(int numDoctors, int numPatients) 
    {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();

        for (int i = 1; i <= numDoctors; i++) 
        {
            doctors.add(new Doctor("Doctor " + i));
        }

        for (int i = 1; i <= numPatients; i++) 
        {
            patients.add(new Patient("Patient " + i));
        }
    }

    public void startTreatment() 
    {
        for (Doctor doctor : doctors) 
        {
            new Thread(doctor).start();
        }

        for (Patient patient : patients) 
        {
            new Thread(patient).start();
        }
    }
}

class Doctor implements Runnable 
{
    private String name;

    public Doctor(String name) 
    {
        this.name = name;
    }

    @Override
    public void run() 
    {
        while (true) 
        {
            treatPatient();
        }
    }

    private void treatPatient() 
    {
        try 
        {
            System.out.println(name + " is treating a patient.");
            Thread.sleep(new Random().nextInt(5000)); // Simulating treatment time
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}

class Patient implements Runnable
{
    private final String name;

    public Patient(String name) 
    {
        this.name = name;
    }

    @Override
    public void run() 
    {
        while (true) 
        {
            visitDoctor();
        }
    }

    private void visitDoctor() 
    {
        try 
        {
            System.out.println(name + " is visiting a doctor.");
            Thread.sleep(new Random().nextInt(3000)); // Simulating waiting time before treatment
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}

public class Multithreading 
{
    public static void main(String[] args) 
    {
        int numDoctors = 3;
        int numPatients = 5;

        Hospital hospital = new Hospital(numDoctors, numPatients);
        hospital.startTreatment();
    }
}