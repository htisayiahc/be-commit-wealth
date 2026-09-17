package be.commit_wealth.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

@ChangeUnit(id= "add-wallet-table", order = "002", author = "FHX")
public class V002_InitWalletSchema {
    @Execution
    public void execution(MongoTemplate mongoTemplate) {
        try {
            MongoJsonSchema schema = MongoJsonSchema.builder()
                    .required(
                            "userId",
                            "walletName",
                            "walletType",
                            "startBalance"
                    )
                    .properties(
                            JsonSchemaProperty.string("userId"),
                            JsonSchemaProperty.string("walletName"),
                            JsonSchemaProperty.string("walletType"),
                            JsonSchemaProperty.decimal128("incomeTotal"),
                            JsonSchemaProperty.decimal128("expendTotal"),
                            JsonSchemaProperty.decimal128("startBalance"),
                            JsonSchemaProperty.decimal128("summary"),
                            JsonSchemaProperty.date("createdAt"),
                            JsonSchemaProperty.date("updatedAt"),
                            JsonSchemaProperty.string("createdBy"),
                            JsonSchemaProperty.string("updatedBy")
                    )
                    .build();

            CollectionOptions options = CollectionOptions.empty()
                    .validator(Validator.schema(schema));

            // สร้าง Collection พร้อม Schema Validation
            if (!mongoTemplate.collectionExists("wallets")) {
                mongoTemplate.createCollection("wallets", options);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        if (mongoTemplate.collectionExists("wallets")) {
            mongoTemplate.dropCollection("wallets");
        }
    }
}
