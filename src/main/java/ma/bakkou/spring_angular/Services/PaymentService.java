package ma.bakkou.spring_angular.Services;


import jakarta.transaction.Transactional;
import ma.bakkou.spring_angular.Entities.Payment;
import ma.bakkou.spring_angular.Entities.PaymentStatus;
import ma.bakkou.spring_angular.Entities.PaymentType;
import ma.bakkou.spring_angular.Entities.Student;
import ma.bakkou.spring_angular.Repository.PaymentRepository;
import ma.bakkou.spring_angular.Repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }


    public Payment SavePayment(MultipartFile file , LocalDate date ,
                               double amount , PaymentType type , String studendtCode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"bakkou-data","payments");
        if (!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"bakkou-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student = studentRepository.findByCode(studendtCode);
        Payment payment = Payment.builder()
                .date(date).type(type).student(student)
                .amount(amount)
                .status(PaymentStatus.CREATED)
                .file(filePath.toUri().toString())
                .build();
        return paymentRepository.save(payment);
    }

    public Payment updatePaymentStatus( PaymentStatus status,  Long id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
    public byte[] getPaymentFile( Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));

    }
}
