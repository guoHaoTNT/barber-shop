package com.barber.util;

import java.io.Serializable;

/**
 * @author will
 */
public interface IResultCode extends Serializable {
    /**
     * msg
     * @return
     */
    String getMessage();

    /**
     * code
     * @return
     */
    int getCode();
}
