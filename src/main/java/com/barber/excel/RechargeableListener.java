package com.barber.excel;

/**
 * 充值卡监听器
 * @author will
 * @date 2022/5/7
 */
public class RechargeableListener extends BaseImportListener<RechargeableImport>{
    @Override
    protected RechargeableImport convertEntity(CellDataDTO cellData) {
        return null;
    }

    @Override
    protected Enum<? extends HeadEnum>[] getHead() {
        return new Enum[0];
    }
}
