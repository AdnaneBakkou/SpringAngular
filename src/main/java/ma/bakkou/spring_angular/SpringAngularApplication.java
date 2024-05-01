package ma.bakkou.spring_angular;

import ma.bakkou.spring_angular.Entities.Payment;
import ma.bakkou.spring_angular.Entities.PaymentStatus;
import ma.bakkou.spring_angular.Entities.PaymentType;
import ma.bakkou.spring_angular.Entities.Student;
import ma.bakkou.spring_angular.Repository.PaymentRepository;
import ma.bakkou.spring_angular.Repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAngularApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository , PaymentRepository paymentRepository){
        return args ->{
        studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                        .firstName("adnane").code("1220").lastName("bakkou").programId("IIR")
                .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("meryem").code("1221").lastName("bouzakri").programId("IIR")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("moe").code("1222").lastName("kachmar").programId("IFA")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstName("bennis").code("1223").lastName("abdo").programId("IFA")
                    .build());

            PaymentType[] paymentTypes = PaymentType.values();
            Random random = new Random();
            studentRepository.findAll().forEach(
                    st->{
                        for (int i = 0 ; i<10;i++){
                            int index = random.nextInt(paymentTypes.length);
                            Payment payment = Payment.builder()
                                    .amount(1000+(int)(Math.random()+2000))
                                    .type(paymentTypes[index])
                                    .status(PaymentStatus.CREATED)
                                    .date(LocalDate.now())
                                    .student(st)
                                    .build();

                            paymentRepository.save(payment);
                        }
                    }
            );


        };
    }
}
