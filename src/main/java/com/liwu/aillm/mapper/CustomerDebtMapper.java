package com.liwu.aillm.mapper;

import com.liwu.aillm.entity.CustomerDebt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: liwu
 * @Description:
 * @Date: Create in 17:06 2026/7/10
 */
public interface CustomerDebtMapper {

    @Select("SELECT * FROM t_customer_debt WHERE customer_no = #{customerNo}")
    CustomerDebt selectByCustomerNo(@Param("customerNo") String customerNo);

}
