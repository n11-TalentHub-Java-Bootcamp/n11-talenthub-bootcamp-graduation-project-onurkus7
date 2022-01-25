package com.onurkus.graduationproject.credit.controller;

import com.onurkus.graduationproject.credit.dto.CreditDto;
import com.onurkus.graduationproject.credit.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/credits")
@AllArgsConstructor
public class CreditController {

private CreditService creditService;

    @PostMapping("/credit-save/{identityId}")
    public CreditDto getCreditLimitByIdentityId(@PathVariable Long identityId)
    {
        return creditService.getCreditLimitByIdentityId(identityId);
    }

    @GetMapping("/credit-search/{identityId}")
    public CreditDto findCreditLimitByIdentityId(@PathVariable Long identityId)
    {
        return creditService.findCreditLimitByIdentityId(identityId);
    }

}
