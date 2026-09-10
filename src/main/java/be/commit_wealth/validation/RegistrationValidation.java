package be.commit_wealth.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegistrationValidator.class)
public @interface RegistrationValidation {
    // 1. Message: ข้อความแจ้งเตือน default
    String message() default "Invalid user data";

    // 2. Groups: ใช้สำหรับแบ่งกลุ่มการตรวจ (เช่น ตรวจเฉพาะตอนสมัคร, ไม่ตรวจตอนล็อคอิน)
    Class<?>[] groups() default {};

    // 3. Payload: ใช้สำหรับเก็บข้อมูลเพิ่มเติม (Metadata) เกี่ยวกับความรุนแรงของ Error
    Class<? extends Payload>[] payload() default {};

}
