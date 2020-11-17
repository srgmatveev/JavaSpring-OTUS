package users.domain;


import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import users.dto.DtoDepartment;

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
