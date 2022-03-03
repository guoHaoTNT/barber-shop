package dao;

import lombok.Data;

/**
 *
 * @author DELL
 */
@Data
public class Haircut {

    private Long id;

    private String name;

    private Integer phoneNum;

    /**
     *  总次数
     */
    private Integer totalTime;

    private Integer remainingTime;

}
