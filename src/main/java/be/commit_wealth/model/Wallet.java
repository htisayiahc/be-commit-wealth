package be.commit_wealth.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "wallets")
@Getter
@Setter
@NoArgsConstructor
public class Wallet{
    @Id
    private UUID id = UUID.randomUUID();

    @Indexed(unique = true)
    private UUID userId;

    private String walletName;

    private String walletType;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal incomeTotal = BigDecimal.ZERO;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal expendTotal = BigDecimal.ZERO;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal startBalance = BigDecimal.ZERO;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal summary = BigDecimal.ZERO;

    @CreatedDate // ต้องเปิด @EnableMongoAuditing ที่ Config
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;
}
