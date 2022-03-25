package com.barber.excel;

import com.barber.dao.Haircut;
import lombok.EqualsAndHashCode;

/**
 * @author will
 */
@EqualsAndHashCode(callSuper = true)
public class HaircutListener extends BaseImportListener<Haircut>{

    @Override
    protected Haircut convertEntity(CellDataDTO cellData) {
        return null;
    }

    @Override
    protected Enum<? extends HeadEnum>[] getHead() {
        return new Enum[0];
    }
}
