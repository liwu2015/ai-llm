package com.liwu.aillm.tool;

import com.liwu.aillm.entity.CustomerDebt;
import com.liwu.aillm.mapper.CustomerDebtMapper;
import dev.langchain4j.agent.tool.Tool;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @Author: liwu
 * @Description:
 * @Date: Create in 17:08 2026/7/10
 */
@Component
public class CollectionQueryTool {

    @Resource
    private CustomerDebtMapper customerDebtMapper;

    /**
     * 工具1：查询客户欠款信息，自动脱敏手机号、身份证
     */
    @Tool("输入客户编号，查询客户欠款、逾期、产品信息，手机号和身份证必须脱敏返回，禁止明文")
    public String queryDebtInfo(String customerNo) {
        CustomerDebt debt = customerDebtMapper.selectByCustomerNo(customerNo);
        if (debt == null) {
            return "未查询到该客户欠款数据，请核对客户编号";
        }
        // 手机号脱敏
        String safePhone = debt.getPhone().substring(0,3) + "****" + debt.getPhone().substring(7);
        // 身份证脱敏
        String safeIdCard = debt.getIdCard().substring(0,6) + "********" + debt.getIdCard().substring(14);

        return String.format("""
                客户编号：%s
                客户姓名：%s
                联系电话：%s
                身份证：%s
                借款产品：%s
                总借款金额：%s元
                剩余待还金额：%s元
                当前逾期天数：%d天
                """,
                debt.getCustomerNo(),
                debt.getCustomerName(),
                safePhone,
                safeIdCard,
                debt.getProductName(),
                debt.getTotalAmount(),
                debt.getRemainAmount(),
                debt.getOverdueDay()
        );
    }

    /**
     * 工具2：模拟SLS催收通话日志查询
     */
    @Tool("输入客户编号，查询该客户历史外呼、短信、系统催收日志记录")
    public String queryCollectionLog(String customerNo) {
        return """
                【客户%s催收日志】
                1. 2026-07-05 人工外呼，客户承诺7日内还款，通话时长180秒
                2. 2026-07-07 短信催收推送，客户已读未回复
                3. 2026-07-09 系统自动逾期提醒，接口调用耗时162ms
                """.formatted(customerNo);
    }

    /**
     * 工具3：查询分期还款方案
     */
    @Tool("输入客户编号，查询该客户可选择的分期还款方案与罚息规则")
    public String queryRepayPlan(String customerNo) {
        return """
                客户%s可选还款方案：
                方案1：一次性结清，减免5%逾期罚息
                方案2：分3期偿还，每期手续费0.8%%
                方案3：分6期偿还，每期手续费1.2%%
                """.formatted(customerNo);
    }
}
