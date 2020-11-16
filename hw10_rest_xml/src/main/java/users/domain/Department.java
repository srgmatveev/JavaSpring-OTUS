package users.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Department")
@NoArgsConstructor
@Data
public class Department {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    @NonNull
    @Indexed(name="authors_name_index")
    private String name;

}
