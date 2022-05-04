package com.barber.excel;

import com.barber.dao.Haircut;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author will
 */
@EqualsAndHashCode(callSuper = true)
public class HaircutListener extends BaseImportListener<HaircutImport> {

    @Override
    protected HaircutImport convertEntity(CellDataDTO cellData) {
        HaircutImport haircutImport = HaircutImport.builder().
                memberUserName(cellData.getStr(Head.NAME))
                .phoneNum(cellData.getStr(Head.PHONE_NUM))
                .haircutRechargeAmount(cellData.getDouble(Head.haircutRechargeAmount))
                .totalTime(cellData.getInt(Head.totalTime))
                .remainingTime(cellData.getInt(Head.remainingTime)).build();
        return haircutImport;
    }

    @Override
    protected Enum<? extends HeadEnum>[] getHead() {
        return Head.values();
    }

    @AllArgsConstructor
    @Getter
    private enum Head implements HeadEnum {
        /**
         * 会员姓名
         */
        NAME("姓名"),
        /**
         * 手机号
         */
        PHONE_NUM("手机号"),
        haircutRechargeAmount("充值金额(元)"),
        totalTime("总次数"),
        remainingTime("剩余次数");

        private final String fieldName;

        @Override
        public Integer getIndex() {
            return ordinal();
        }
    }
}
