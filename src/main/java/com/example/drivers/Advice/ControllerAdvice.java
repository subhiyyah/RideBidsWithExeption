package com.example.drivers.Advice;

import com.example.drivers.ApiResponse.ApiException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    public ResponseEntity<?> ApiException(ApiException apiException){

        String message=apiException.getMessage();
        return ResponseEntity.status(400).body(message);

    }
    // اذا كان في مشكلة بالفاليديشن
    @ExceptionHandler(value= ConstraintViolationException.class)
    public ResponseEntity<?>ConstraintViolationException(ConstraintViolationException constraintViolationException){
        String message=constraintViolationException.getMessage();

        return ResponseEntity.status(400).body(message);
    }
    //لما ارسل قيم خاطئة بالباث او البودي بالبوست مان
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?>HttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException){
        String message= httpMessageNotReadableException.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    //
    @ExceptionHandler(value= HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?>HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException){
        String message=httpRequestMethodNotSupportedException.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    //اذا اخترت نوع ميثود خاطئ بالبوست مان
    @ExceptionHandler(value= MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?>MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        String message=methodArgumentTypeMismatchException.getMessage();
        return ResponseEntity.status(400).body(message);
    }


    //خطأ في اللينك الخاص بالبوست مان
    @ExceptionHandler(value= NoResourceFoundException.class)
    public ResponseEntity<?>NoResourceFoundException(NoResourceFoundException noResourceFoundException){
        String message=noResourceFoundException.getMessage();
        return ResponseEntity.status(400).body(message);
    }
    //دخلت ال id الي هو اصلا يتولد تلقائيا
    @ExceptionHandler(value= ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<?>ObjectOptimisticLockingFailureException(ObjectOptimisticLockingFailureException objectOptimisticLockingFailureException){
        String message=objectOptimisticLockingFailureException.getMessage();
        return ResponseEntity.status(400).body(message);
    }



    //حذفت خانت بيانات لازم تتدخل في الجوسون
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?>MethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        String message=methodArgumentNotValidException.getMessage();
        return ResponseEntity.status(400).body(message);
    }
// اسم المتغير في الانوتشن في الكنترول يختلف عن اسم المتغير الي استقبله بالباث فاريبول
    @ExceptionHandler(value= MissingPathVariableException.class)
    public ResponseEntity<?>MissingPathVariableException(MissingPathVariableException missingPathVariableException){
        String message=missingPathVariableException.getMessage();
        return ResponseEntity.status(400).body(message);
    }
}