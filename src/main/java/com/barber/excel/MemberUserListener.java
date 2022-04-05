package com.barber.excel;

import com.barber.dao.MemberUser;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author will
 */
@EqualsAndHashCode(callSuper = true)
public class MemberUserListener extends BaseImportListener<MemberUserImport>{
    @Override
    protected MemberUserImport convertEntity(CellDataDTO cellData) {
        MemberUserImport memberUserImport = MemberUserImport.builder()
                .memberUserName(cellData.getStr(Head.NAME))
                .phoneNum(cellData.getStr(Head.PHONE_NUM))
                .build();
        return memberUserImport;
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
        PHONE_NUM("手机号");

        private final String fieldName;

        @Override
        public Integer getIndex() {
            return ordinal();
        }
    }
}
