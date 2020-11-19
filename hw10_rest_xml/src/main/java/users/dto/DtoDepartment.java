package users.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import users.domain.Department;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

/**
 * Department resource placeholder for json/xml representation
 *
 * @author sergio
 */
@SuppressWarnings("restriction")
@XmlRootElement(name = "Department")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@Slf4j
public class DtoDepartment implements Serializable {
    private static final long serialVersionUID = -1288619177798333712L;
    /**
     * id of the department
     */
    @XmlAttribute(name = "id")
    private String id;

    /**
     * name of the department
     */
    @XmlElement(name = "name")
    private String name;

    public DtoDepartment(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public DtoDepartment(Department department) {
        try {
            BeanUtils.copyProperties(department, this);
        } catch (BeansException e) {
            log.error(e.toString());
        }
    }

    public static DtoDepartment toDto(Department department) {
        return new DtoDepartment(department.getId(), department.getName());
    }
}
