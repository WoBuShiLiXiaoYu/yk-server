package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.work.ykserver.ykapps.constant.CharConstants;
import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.query.CustomerQuery;
import com.work.ykserver.ykapps.service.CustomerService;
import com.work.ykserver.ykapps.vo.CustomerExcel;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/conversionCustomer")
    public Result conversionCustomer(@RequestBody CustomerQuery customerQuery,
                                     @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) {
        customerQuery.setToken(token);
        return customerService.conversionCustomer(customerQuery);
    }

    @GetMapping("/getCustomerList")
    public Result getCustomerList(@RequestParam("currentPage") Integer currentPage) {
        if (ObjectUtil.isEmpty(currentPage)) {
            currentPage = 1;
        }
        return customerService.getCustomerListByPage(currentPage);
    }

    @GetMapping("/batchExportExcel")
    public void batchExportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(CharConstants.CUSTOMER_EXCEL_FILENAME + System.currentTimeMillis(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        /*response.setHeader("Content-disposition", "attachment;filename=客户信息表.xlsx");*/
        List<CustomerExcel> customerExcelList = customerService.getCustomerListByExcel();
        EasyExcel.write(response.getOutputStream(), CustomerExcel.class).sheet().doWrite(customerExcelList);
    }

    @GetMapping("/chooseExportExcel")
    public void chooseExportExcel(HttpServletResponse response, @RequestParam(value = "ids") String[] ids) throws IOException {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(CharConstants.CUSTOMER_EXCEL_FILENAME + System.currentTimeMillis(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        List<CustomerExcel> customerExcelList = customerService.getCustomerListByIds(ids);
        EasyExcel.write(response.getOutputStream(), CustomerExcel.class).sheet().doWrite(customerExcelList);
    }
}
