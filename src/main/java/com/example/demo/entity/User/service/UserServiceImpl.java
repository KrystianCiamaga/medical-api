package com.example.demo.entity.User.service;

import com.example.demo.email.MailService;
import com.example.demo.entity.Doctor.Doctor;
import com.example.demo.entity.Patient.Patient;
import com.example.demo.entity.User.User;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailService mailService;


   private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MailService mailService, PatientRepository patientRepository,
                           DoctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(User user) throws Exception {

        if(user.getRole() == null || user.getRole().isEmpty()) {
            throw new Exception("User must have at least a role set!");
        }

        if(userRepository.findByLogin(user.getLogin()) !=null){
            throw new Exception("User with this login already exists");
        }

        switch (user.getRole()){
            case "ROLE_PATIENT":{

             /*   Patient patient = new Patient();
                patient.setLogin(user.getLogin()+"dupa");
                patient.setPassword(passwordEncoder.encode(user.getPassword()));
                patient.setRole(user.getRole());
                patient.setActive(user.isActive());

                patientRepository.save(patient);*/
             userRepository.save(user);

                break;

            }
            case "ROLE_DOCTOR":{

                Doctor doctor = new Doctor();
                doctor.setLogin(user.getLogin());
                doctor.setPassword(passwordEncoder.encode(user.getPassword()));
                doctor.setRole(user.getRole());
                doctor.setActive(false);
                doctor.setEmail(user.getEmail());

                doctorRepository.save(doctor);
                mailService.sendNewAccountToken(doctor);

                break;
            }
        }



    }
}
