package ma.bakkou.spring_angular.Web;


import ma.bakkou.spring_angular.Entities.Payment;
import ma.bakkou.spring_angular.Entities.PaymentStatus;
import ma.bakkou.spring_angular.Entities.PaymentType;
import ma.bakkou.spring_angular.Entities.Student;
import ma.bakkou.spring_angular.Repository.PaymentRepository;
import ma.bakkou.spring_angular.Repository.StudentRepository;
// import org.springframework.security.core.parameters.P;
import ma.bakkou.spring_angular.Services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    private PaymentService paymentService;

    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public List<Payment> allPayment(){
        return paymentRepository.findAll();
    }
    @GetMapping("/payments/byType")
    public List<Payment> paymentType(@RequestParam PaymentType paymentType){
        return paymentRepository.findByType(paymentType);
    }
    @GetMapping("/students/{code}/payment")
    public List<Payment> paymentByStudent( @PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping("/payments/byStatus")
    public List<Payment> paymentByStatus(@RequestParam PaymentStatus paymentStatus){
        return paymentRepository.findByStatus(paymentStatus);
    }

    @GetMapping("payments/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }
    @GetMapping("/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/students/{code}")
    public Student getStudentsByCode(@PathVariable String code){
        return studentRepository.findByCode(code);
    }

    @GetMapping("/studentsByProgramId/")
    public List<Student> getStudentsByProgramId( @RequestParam String programId)
    {
        return studentRepository.findByProgramId(programId);
    }

    @PutMapping("/payment/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus status, @PathVariable Long id){
       return paymentService.updatePaymentStatus(status,id);
    }


    @PostMapping(path = "/payments" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam MultipartFile file , LocalDate date ,
                               double amount , PaymentType type , String studendtCode) throws IOException {
       return this.paymentService.savePayment(file,date,amount,type,studendtCode);
    }

    @GetMapping(value = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
            return paymentService.getPaymentFile(paymentId);

    }

}
