package com.example.demo;

import com.example.demo.entity.Address;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Medicine;
import com.example.demo.entity.Patient;

import com.example.demo.entity.Visit;
import com.example.demo.enums.DoctorSpecialization;
import com.example.demo.enums.Gender;
import com.example.demo.enums.Role;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;

@Service
public class Demo implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    VisitRepository visitRepository;

    @Autowired
    AddressRepository addressRepository;


    @Override
    public void run(String... args) throws Exception {

        Medicine medicine=new Medicine();
        medicine.setDose(10);
        medicine.setName("Amotax");

        Medicine medicine2=new Medicine();
        medicine2.setDose(50);
        medicine2.setName("Zinnat");

        Medicine medicine3=new Medicine();
        medicine3.setDose(10);
        medicine3.setName("Ibuprom");

        Medicine medicine4=new Medicine();
        medicine4.setDose(1);
        medicine4.setName("Apap");

        ////////////////////////////////////////////////////////////////////////////


        Doctor doctor=new Doctor();
        doctor.setFirstName("Jan");
        doctor.setLastName("Ogłaza");
        doctor.setSpecialization(DoctorSpecialization.ANESTESIOLOGITS);
        doctor.setGender(Gender.Male);
        doctor.setLogin("doktor");
        doctor.setPassword(passwordEncoder.encode("doktor"));
        doctor.setActive(true);
        doctor.setRole(Role.DOCTOR.getValue());

        Doctor doctor2=new Doctor();
        doctor2.setFirstName("Anna");
        doctor2.setLastName("Jarosławska");
        doctor2.setSpecialization(DoctorSpecialization.ALLERGIST);
        doctor2.setGender(Gender.Female);
        doctor2.setLogin("doktor2");
        doctor2.setPassword(passwordEncoder.encode("doktor2"));
        doctor2.setActive(true);
        doctor2.setRole(Role.DOCTOR.getValue());

        Doctor doctor3=new Doctor();
        doctor3.setFirstName("Adam");
        doctor3.setLastName("Kwaśniak");
        doctor3.setSpecialization(DoctorSpecialization.ALLERGIST);
        doctor3.setGender(Gender.Male);
        doctor3.setLogin("doktor3");
        doctor3.setPassword(passwordEncoder.encode("doktor3"));
        doctor3.setActive(true);
        doctor3.setRole(Role.DOCTOR.getValue());


        ////////////////////////////////////////////////////////////////////////////




        Patient patient=new Patient();
        patient.setFirstName("Jan");
        patient.setLastName("Kowalczyk");
        patient.setLogin("user");
        patient.setPassword(passwordEncoder.encode("user"));
        patient.setRole(Role.PATIENT.getValue());
        patient.setActive(true);

        //patient.setDoctor(doctor);

       // patient.setMedicineList(Collections.singletonList(medicine));



        Patient patient2=new Patient();
        patient2.setFirstName("Zbyszek");
        patient2.setLastName("Kowalski");
        patient2.setLogin("user2");
        patient2.setPassword(passwordEncoder.encode("user2"));
        patient2.setRole(Role.PATIENT.getValue());
        patient2.setDoctor(doctor);
        patient2.setActive(true);
        patient2.setEmail("jagoda@o2.pl");

        patient2.setMedicineList(List.of(medicine));

        patient2.setDoctor(doctor2);

        medicine.setPatients(Collections.singletonList(patient2));


        ////////////////////////////////////////////////////////////////////////////

        //doctor.setPatientList(List.of(patient));

        doctor2.setPatientList(List.of(patient2));

        ////////////////////////////////////////////////////////////////////////////


        Visit visit = new Visit();
        visit.setDoctorsOfficeNumber(10);
        visit.setVisitDate(LocalDateTime.of(2020, Month.MAY, 22, 1, 1));

        Visit visit2 = new Visit();
        visit2.setDoctorsOfficeNumber(10);
        visit2.setVisitDate(LocalDateTime.of(2020, Month.MAY, 22, 1, 1));


        visit2.setPatient(patient2);

        visit.setPatient(patient2);

        patient2.setVisits(List.of(visit2,visit));
        //visit2.setPatient(patient);

        patient2.setVisits(List.of(visit2,visit));

        //doctorRepository.save(doctor);
        //patientRepository.save(patient);


        Address address = new Address();
        address.setCity("Iwaniska");
        address.setZippCode("113636");
        address.setHouseNumber("numer domu");




        doctor2.setAddress(address);
        addressRepository.save(address);

        doctorRepository.save(doctor2);

        medicineRepository.save(medicine4);


        patientRepository.save(patient);
        patientRepository.save(patient2);


    }
}
