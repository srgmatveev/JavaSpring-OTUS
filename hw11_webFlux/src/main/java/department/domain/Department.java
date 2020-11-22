package department.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper=false)
@Document(collection = "department")
public class Department extends BaseEntity{

    @TextIndexed
    private String name;

}
