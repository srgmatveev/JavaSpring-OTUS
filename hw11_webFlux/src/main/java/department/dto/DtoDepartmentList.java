package department.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="departments")
@NoArgsConstructor
@Data
public class DtoDepartmentList implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<DtoDepartment> departments = new ArrayList<>();

    @XmlElement(name="department")
    public List<DtoDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DtoDepartment> departments) {
        this.departments = departments;
    }



}