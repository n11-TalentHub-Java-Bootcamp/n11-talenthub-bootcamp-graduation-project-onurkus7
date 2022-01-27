package com.onurkus.graduationproject.credit.controller;

import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.credit.entity.Credit;
import com.onurkus.graduationproject.credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("app/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/credit-save/{identityId}")
    public CreditDto getCreditLimitByIdentityId(@PathVariable Long identityId) {
        return creditService.getCreditLimitByIdentityId(identityId);
    }

    @GetMapping("/credit-search")
    public BigDecimal getCreditLimitByIdentityIdAndBirthdayDate(
            @RequestParam Long identityId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdayDate) {
        return creditService.getCreditLimitByIdentityIdAndBirthdayDate(identityId, birthdayDate);
    }

}
