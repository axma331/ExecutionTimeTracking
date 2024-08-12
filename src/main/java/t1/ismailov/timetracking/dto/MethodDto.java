package t1.ismailov.timetracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Data Transfer Object (DTO) to represent method information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MethodDto {

    /**
     * Method name.
     */
    private String name;

    /**
     * The name of the class to which the method belongs.
     */
    private String declaredType;

    /**
     * The name of the package in which the method class resides.
     */
    private String group;
}
