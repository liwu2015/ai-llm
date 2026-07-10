package com.liwu.aillm.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: liwu
 * @Description:
 * @Date: Create in 17:05 2026/7/10
 */
@Data
public class CustomerDebt {

    private Long id;
    private String customerNo;
    private String customerName;
    private String phone;
    private String idCard;
    private BigDecimal totalAmount;
    private BigDecimal remainAmount;
    private Integer overdueDay;
    private String productName;
}
