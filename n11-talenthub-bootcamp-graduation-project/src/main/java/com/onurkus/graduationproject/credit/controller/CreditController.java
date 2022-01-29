package com.onurkus.graduationproject.credit.controller;

import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.credit.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("app/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/{identityId}")
    public CreditDto transactionCreditLimitByIdentityId(@PathVariable Long identityId) {
        return creditService.transactionCreditLimitByIdentityId(identityId);
    }

    @GetMapping("/{identityId}/{birthdayDate}")
    public CreditDto getCreditLimitByIdentityIdAndBirthdayDate(
            @PathVariable Long identityId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthdayDate) {
        return creditService.getCreditLimitByIdentityIdAndBirthdayDate(identityId, birthdayDate);
    }

}
