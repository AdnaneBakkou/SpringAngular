package ma.bakkou.spring_angular.Dto;

import jakarta.persistence.*;
import lombok.*;
import ma.bakkou.spring_angular.Entities.PaymentStatus;
import ma.bakkou.spring_angular.Entities.PaymentType;
import ma.bakkou.spring_angular.Entities.Student;

import java.time.LocalDate;


@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDTO {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type ;
    private PaymentStatus status;
}
