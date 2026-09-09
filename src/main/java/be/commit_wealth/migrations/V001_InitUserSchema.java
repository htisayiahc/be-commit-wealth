package be.commit_wealth.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.schema.JsonSchemaProperty;
import org.springframework.data.mongodb.core.schema.MongoJsonSchema;
import org.springframework.data.mongodb.core.validation.Validator;

@ChangeUnit(id= "add-user-table", order = "001", author = "FHX")
public class V001_InitUserSchema {
    @Execution
    public void execution(MongoTemplate mongoTemplate) {
        try {
            MongoJsonSchema schema = MongoJsonSchema.builder()
                    .required("username", "password", "salary", "birthdayDate")
                    .properties(
                            JsonSchemaProperty.string("username"),
                            JsonSchemaProperty.string("password"),
                            JsonSchemaProperty.decimal128("salary"),
                            JsonSchemaProperty.string("email"),
                            JsonSchemaProperty.date("birthdayDate"),
                            JsonSchemaProperty.date("createdAt"),
                            JsonSchemaProperty.date("updatedAt"),
                            JsonSchemaProperty.string("createdBy"),
                            JsonSchemaProperty.string("updatedBy")
                    )
                    .build();

            CollectionOptions options = CollectionOptions.empty()
                    .validator(Validator.schema(schema));

            // สร้าง Collection พร้อม Schema Validation
            if (!mongoTemplate.collectionExists("users")) {
                mongoTemplate.createCollection("users", options);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {
        if (mongoTemplate.collectionExists("users")) {
            mongoTemplate.dropCollection("users");
        }
    }
}

