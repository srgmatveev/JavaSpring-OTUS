package users.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "departments")
@Data
@NoArgsConstructor
public class DtoDepartmentList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<DtoDepartment> departments = new ArrayList<>();
}
