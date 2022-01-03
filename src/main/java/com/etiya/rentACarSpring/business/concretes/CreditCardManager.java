package com.etiya.rentACarSpring.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.business.abstracts.LanguageWordService;
import com.etiya.rentACarSpring.business.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.business.abstracts.CreditCardService;
import com.etiya.rentACarSpring.business.abstracts.UserService;
import com.etiya.rentACarSpring.business.dtos.CreditCardSearchListDto;
import com.etiya.rentACarSpring.business.requests.creditCard.CreateCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.creditCard.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.business.requests.creditCard.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.business.BusinessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.mapping.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.Result;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessDataResult;
import com.etiya.rentACarSpring.core.utilities.mapping.results.SuccessResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CreditCardInformationDao;
import com.etiya.rentACarSpring.entities.CreditCardInformation;
import com.etiya.rentACarSpring.entities.User;

@Service
public class CreditCardManager implements CreditCardService {

    private CreditCardInformationDao creditCardInformationDao;
    private ModelMapperService modelMapperService;
    private UserService userService;
    private LanguageWordService languageWordService;

    @Autowired
    public CreditCardManager(CreditCardInformationDao creditCardInformationDao, ModelMapperService modelMapperService,
                             @Lazy UserService userService, LanguageWordService languageWordService) {
        this.creditCardInformationDao = creditCardInformationDao;
        this.modelMapperService = modelMapperService;
        this.userService = userService;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<CreditCardSearchListDto>> getAll() {
        List<CreditCardInformation> result = this.creditCardInformationDao.findAll();
        List<CreditCardSearchListDto> response = result.stream().map(CreditCardInformation -> modelMapperService
                .forDto().map(CreditCardInformation, CreditCardSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CreditCardSearchListDto>>(response, this.languageWordService.getValueByKey(Messages.CREDITCARDLIST).getData());
    }

    @Override
    public DataResult<CreditCardInformation> getById(int cardInformationId) {

        return new SuccessDataResult<CreditCardInformation>(this.creditCardInformationDao.getById(cardInformationId), this.languageWordService.getValueByKey(Messages.CREDITCARDLIST).getData());
    }

    @Override
    public Result add(CreateCreditCardRequest createCreditCardRequest) {
        Result result = BusinessRules.run(checkCardFormat(createCreditCardRequest.getCardNumber(), createCreditCardRequest.getCvv(), createCreditCardRequest.getExpirationDate()),
                checkCreditCardCounter(createCreditCardRequest.getUserId()), userService.existsByUserId(createCreditCardRequest.getUserId()));

        if (result != null) {
            return result;
        }

        User user = this.userService.getByUser(createCreditCardRequest.getUserId()).getData();

        CreditCardInformation creditCardInformation = modelMapperService.forRequest().map(createCreditCardRequest,
                CreditCardInformation.class);

        creditCardInformation.setUser(user);

        this.creditCardInformationDao.save(creditCardInformation);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CREDITCARDADD).getData());
    }

    @Override
    public Result update(UpdateCreditCardRequest updateCreditCardRequest) {

        var result = BusinessRules.run(checkCardFormat(updateCreditCardRequest.getCardNumber(), updateCreditCardRequest.getCvv(), updateCreditCardRequest.getExpirationDate()),
                existsByCreditCardId(updateCreditCardRequest.getCardInformationId()), userService.existsByUserId(updateCreditCardRequest.getUserId()));

        if (result != null) {
            return result;
        }

        CreditCardInformation creditCardInformation = modelMapperService.forRequest().map(updateCreditCardRequest, CreditCardInformation.class);

        this.creditCardInformationDao.save(creditCardInformation);

        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CREDITCARDUPDATE).getData());
    }

    @Override
    public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
        Result result = BusinessRules.run(existsByCreditCardId(deleteCreditCardRequest.getCardInformationId()));

        if (result != null) {
            return result;
        }
        CreditCardInformation creditCardInformation = this.creditCardInformationDao
                .getById(deleteCreditCardRequest.getCardInformationId());
        this.creditCardInformationDao.delete(creditCardInformation);
        return new SuccessResult(this.languageWordService.getValueByKey(Messages.CREDITCARDDELETE).getData());
    }

    private Result checkCardFormat(String cardNumber, String cvv, String exprationDate) {
        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" + "(?<mastercard>5[1-5][0-9]{14})|"
                + "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" + "(?<amex>3[47][0-9]{13})|"
                + "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" + "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardNumber);
        if (!matcher.find()) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CREDITCARDNUMBERERROR).getData());
        }
        String regexcvv = "^[0-9]{3,4}$";
        Pattern patterncvv = Pattern.compile(regexcvv);
        Matcher matchercvv = patterncvv.matcher(cvv);
        if (!matchercvv.find()) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CREDITCARDCVCERROR).getData());
        }
        String regexexpdate = "(?:0[1-9]|1[0-2])/[0-9]{2}";
        Pattern patternexpdate = Pattern.compile(regexexpdate);
        Matcher matcherexpdate = patternexpdate.matcher(exprationDate);
        if (!matcherexpdate.find()) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CREDITCARDDATEERROR).getData());
        }


        return new SuccessResult();
    }


    private Result checkCreditCardCounter(int userId) {
        int counter = this.creditCardInformationDao.countCreditCardInformationByUser_Id(userId);
        if (counter >= 4) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CREDITCARDLIMIT).getData());
        }
        return new SuccessResult();
    }

    private Result existsByCreditCardId(int creditCardId) {
        boolean result = this.creditCardInformationDao.existsById(creditCardId);
        if (result == false) {
            return new ErrorResult(this.languageWordService.getValueByKey(Messages.CREDITCARDNOTSAVE).getData());
        }
        return new SuccessResult();
    }

}
